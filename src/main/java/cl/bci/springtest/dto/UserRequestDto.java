package cl.bci.springtest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
	
	@JsonProperty
	@NotEmpty(message = "not empty")
	private String name;
	
	@Email(message = "not valid")
	@JsonProperty
	@NotEmpty(message = "not empty")
	private String email;
	
	@JsonProperty
	@NotEmpty(message = "not empty")
	@Pattern(regexp = "[A-Z]{1,1}[a-z]{1,}[0-9]{2,2}", message = "not valid")
	private String password;
	
	@Valid
	private List<PhoneRequestDto> phones = new ArrayList<>();
	
}
