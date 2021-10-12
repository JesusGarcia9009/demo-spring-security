package cl.bci.springtest.restcontroller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import cl.bci.springtest.dto.ResponseDto;
import cl.bci.springtest.dto.UserRequestDto;
import cl.bci.springtest.handler.MailFoundException;


/**
 * RegisterController - Register Services Services - Spring Boot
 *
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface RegisterController {


	/**
	 * User Registration - method to register users- Spring Boot
	 *
	 * @param UserRequestDto @see {@link UserRequestDto}
	 * @return ResponseEntity<ResponseDto> see {@link ResponseDto} authentication response object
	 */
	public ResponseEntity<ResponseDto> registerUser(UserRequestDto req) throws IOException, MailFoundException ;
	
	
}
