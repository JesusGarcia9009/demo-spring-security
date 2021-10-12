package cl.bci.springtest.config;

import static cl.bci.springtest.utils.ConstantUtil.AUTHORIZATION;
import static cl.bci.springtest.utils.ConstantUtil.BEARER;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider tokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader(AUTHORIZATION);

		String jwtToken = null;
		if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER)) {
			jwtToken = requestTokenHeader.substring(7);
			if(tokenHelper.validateToken(jwtToken)) {
				Claims claims = tokenHelper.getAllClaimsFromToken(jwtToken);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						tokenHelper.getUserLogInFromToken(jwtToken), null, tokenHelper.getAuthList(claims));
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
