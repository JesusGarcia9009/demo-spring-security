package cl.bci.springtest.restcontroller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bci.springtest.dto.LogInRequestDto;
import cl.bci.springtest.dto.ResponseDto;
import cl.bci.springtest.dto.UserRequestDto;
import cl.bci.springtest.handler.MailFoundException;
import cl.bci.springtest.services.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${cl.bci.springboot.example.uri}")
public class RegisterControllerImpl implements RegisterController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LogInController logInController;
	
	@Override
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody(required = true) UserRequestDto req) throws IOException, MailFoundException {
		log.info("[registerUser] :: method start" );
		ResponseEntity<ResponseDto> result = null;
		if(!userService.existUserByEmail(req.getEmail())) {
			userService.saveUser(req);
			result = logInController.authUser(new LogInRequestDto(req.getEmail(), req.getPassword()));
		}else {
			throw new MailFoundException("Mail "+ req.getEmail() +" found in another user.");
		}
		log.info("[registerUser] :: method end" );
		return result;
	}
	
}
