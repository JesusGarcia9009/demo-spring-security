package cl.bci.springtest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.bci.springtest.dto.UserLogIn;
import cl.bci.springtest.utils.HelperUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("[loadUserByUsername]::start of method");
		UserLogIn userDetails = (UserLogIn) new UserLogIn(userService.getUserByEmail(email), HelperUtil.getAuthList());
		log.info("[loadUserByUsername]::end of method");
		return userDetails;
	}
	
}
