package cl.bci.springtest.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * GlobalExceptionHandler - advice controller
 * 
 * @author Jesus Garcia
 * @version 1.0
 * @version jdk-11.0.7
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


	@Nullable
	@ExceptionHandler({ MethodArgumentNotValidException.class, MailFoundException.class, Exception.class})
	public final ResponseEntity<ApiError> handleException(Exception ex, WebRequest request) {
		log.error(ex.getClass().getSimpleName() + " Exceptions: " + ex.getMessage());
		HttpHeaders headers = new HttpHeaders();

		if (ex instanceof MailFoundException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			MailFoundException unfe = (MailFoundException) ex;
			return handleCommonException(unfe, headers, status, request);
		} else if (ex instanceof MethodArgumentNotValidException) {
			BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
			List<FieldError> fieldErrors = result.getFieldErrors();
			List<String> errorMessage = new ArrayList<>();
			fieldErrors.forEach(f -> errorMessage.add(f.getField() + " " + f.getDefaultMessage()));
			HttpStatus status = HttpStatus.NOT_FOUND;
			MethodArgumentNotValidException excp = (MethodArgumentNotValidException) ex;
			return handleCommonException(excp, headers, status, request, errorMessage);

		} else {
			log.warn("Unknown exception type: " + ex.getClass().getName());
			HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
			return handleExceptionInternal(ex, new ApiError(ex.getMessage()), headers, status, request);
		}
	}
	
	protected ResponseEntity<ApiError> handleCommonException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, new ApiError(ex.getMessage()), headers, status, request);
	}
	
	protected ResponseEntity<ApiError> handleCommonException(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request, List<String> errors) {
		return handleExceptionInternal(ex, new ApiError(errors.toString()), headers, status, request);
	}


	protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, @Nullable ApiError body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}
		return new ResponseEntity<>(body, headers, status);
	}
}
