package cl.bci.springtest.restcontroller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.springtest.config.JwtProvider;
import cl.bci.springtest.dto.LogInRequestDto;
import cl.bci.springtest.dto.ResponseDto;
import cl.bci.springtest.models.UserModel;
import cl.bci.springtest.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${cl.bci.springboot.example.uri}")
public class LogInControllerImpl implements LogInController {

	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private UserService userService;
	
	@Override
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> authUser(@Valid @RequestBody LogInRequestDto req) throws IOException {
		log.info("[authUser] :: start of method" );
		Authentication sigin = null;
		sigin = jwtProvider.createToken(req.getEmail(), req.getPassword());
		
		ResponseDto autPass = new ResponseDto();
		String token = jwtProvider.generateToken(sigin);
		autPass.setToken(token);
		
		UserModel model = userService.getUserByEmail(req.getEmail());
		autPass.setActive(model.isActive());
		autPass.setCreated(model.getCreated());
		autPass.setId(model.getId().toString());
		autPass.setLastLogin(model.getLastLogin());
		autPass.setModified(model.getModified());
		model.setLastToken(token);
		userService.updateUser(model);
		log.info("[authUser] :: end of method" );
		
		return new ResponseEntity<>(autPass, HttpStatus.OK);
	}
	
}
