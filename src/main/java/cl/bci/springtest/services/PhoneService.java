package cl.bci.springtest.services;

import java.util.List;

import cl.bci.springtest.dto.PhoneRequestDto;
import cl.bci.springtest.models.PhoneModel;
import cl.bci.springtest.models.UserModel;


/**
 * PhoneService - Class that contains the methods of the Phone entity
 * 
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface PhoneService {

	
	/**
	 * save list of phones
	 * 
	 * @param none
	 * @return model @see User
	 */
	public List<PhoneModel> saveAll(List<PhoneRequestDto> list, UserModel user); 
	
}
