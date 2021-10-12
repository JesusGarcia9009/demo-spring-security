package cl.bci.springtest.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.bci.springtest.dto.UserRequestDto;
import cl.bci.springtest.models.UserModel;
import cl.bci.springtest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * UsersServiceImpl class that implements the service interface
 * 
 * @author Jesus Garcia
 * @version 1.0 Creacion
 * @since Java 11
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserModel getUserByEmail(String email) {
		log.info("[getUserByEmail]::start of method");
		UserModel result = null;
		Optional<UserModel> opt = userRepository.findByEmail(email);
		if(opt.isPresent())
			result = opt.get();
		
		log.info("[getUserByEmail]::end of method");
		return result;
	}

	@Override
	public Long countUserByEmail(String email) {
		log.info("[countUserByUsername]::start/end of method");
		return userRepository.countByEmail(email);
	}

	@Override
	public Boolean existUserByEmail(String email) {
		log.info("[existUserByEmail]::start/end of method");
		return userRepository.existsByEmail(email);
	}

	@Override
	@Transactional
	public UserModel saveUser(UserRequestDto dto) {
		log.info("[saveUser]::start of method");
		Date now = new Date();
		UserModel model = new UserModel();
		model.setName(dto.getName());
		model.setEmail(dto.getEmail());
		model.setPassword(encoder.encode(dto.getPassword()));
		model.setCreated(now);
		model.setLastLogin(now);
		model.setModified(now);
		model.setActive(true);
		model.setLastToken("");
		
		model = userRepository.save(model);
		phoneService.saveAll(dto.getPhones(), model);
		log.info("[saveUser]::end of method");
		return model;
	}

	@Override
	public UserModel updateUser(UserModel model) {
		log.info("[updateUser]::start of method");
		model.setLastLogin(new Date());
		model = userRepository.save(model);
		log.info("[updateUser]::end of method");
		return model;
	}

}
