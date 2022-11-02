package spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	
	public ErrorMessage throwUserEmailNotFOundException(UserEmailNotFoundException e) {
		ErrorMessage message = new ErrorMessage();
		message.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		message.setErrorMessage(e.getLocalizedMessage());
		
		return message;
	}
}
