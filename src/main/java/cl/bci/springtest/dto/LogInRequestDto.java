package cl.bci.springtest.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequestDto {
	
	@JsonProperty
	@NotEmpty(message = "test de datos vacios")
	private String email;
	
	@JsonProperty
	private String password;
	
}
