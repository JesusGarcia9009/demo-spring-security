package cl.bci.springtest.config;

import static cl.bci.springtest.utils.ConstantUtil.AUTHORITIES;
import static cl.bci.springtest.utils.ConstantUtil.USERNAME_MAIL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.bci.springtest.dto.UserLogIn;
import cl.bci.springtest.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtProvider {

	@Autowired
	public AuthenticationManager authenticationManager;

	@Value("${cl.bci.springboot.example.secretSeed}")
	private String secret;

	@Value("${cl.bci.springboot.example.mls}")
	private Integer JWT_TOKEN_VALIDITY;

	public Authentication createToken(String email, String pass) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, pass));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return authentication;
	}

	public String generateToken(Authentication authentication) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		UserLogIn loginUser = (UserLogIn) authentication.getPrincipal();
		Claims claims = Jwts.claims();

		String usernameOrMail = loginUser.getUsername();

		claims.put(AUTHORITIES, mapper.writeValueAsString(authentication.getAuthorities()));
		claims.put(USERNAME_MAIL, usernameOrMail);
		Date expiryDate = new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY);
		return Jwts.builder().setClaims(claims).setSubject(usernameOrMail).setIssuedAt(new Date())
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public String getEmailFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// check if the token has expired
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	
	
	/**
	 * getUserLogInFromToken - JwtProvider - Spring Boot
	 *
	 * @param String - jwt token
	 * @return UserLogIn - object for user log in {@link UserLogIn}
	 */
	public UserLogIn getUserLogInFromToken(String token) throws IOException {
		Claims claims = getAllClaimsFromToken(token);
		
		UserLogIn codifyUser = new UserLogIn(new UserModel(), getAuthList(claims));
		return codifyUser;
	}
	
	/**
	 * getAuthList - JwtProvider - Spring Boot
	 *
	 * @param Claims claims
	 * @return Collection<GrantedAuthority> - {@link GrantedAuthority}
	 */
	public Collection<? extends GrantedAuthority> getAuthList(Claims claims) throws IOException {
		String auth = claims.get(AUTHORITIES, String.class);
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		if(auth != null && !auth.isEmpty()) {
			authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(auth.getBytes(), SimpleGrantedAuthority[].class));
		}
		return authorities;
	}
	
	/**
	 * validateToken - JwtTokenProvider - Spring Boot
	 *
	 * @param token - authentication token
	 * @return boolean - if is valid
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} 
		return false;
	}

}
