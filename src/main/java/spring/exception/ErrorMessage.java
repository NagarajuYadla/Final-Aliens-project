package spring.exception;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor
public class ErrorMessage {
	private int statusCode;
	private String errorMessage;
}
