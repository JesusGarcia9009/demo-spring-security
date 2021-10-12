package cl.bci.springtest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bci.springtest.dto.PhoneRequestDto;
import cl.bci.springtest.models.PhoneModel;
import cl.bci.springtest.models.UserModel;
import cl.bci.springtest.repository.PhoneRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * PhoneServiceImpl class that implements the service interface
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Slf4j
@Service
public class PhoneServiceImpl implements PhoneService {
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Override
	public List<PhoneModel> saveAll(List<PhoneRequestDto> list, UserModel user) {
		log.info("[saveAll]::start of method");
		List<PhoneModel> phonesList = new ArrayList<PhoneModel>();
		for (PhoneRequestDto item : list) {
			PhoneModel phone = new PhoneModel();
			phone.setCountryCode(item.getContrycode());
			phone.setNumber(item.getNumber());
			phone.setCityCode(item.getCitycode());
			phone.setUserPhone(user);
			phonesList.add(phone);
		}
		phoneRepository.saveAll(phonesList);
		log.info("[saveAll]::end of method");
		return phonesList;
	}
	
	
}
