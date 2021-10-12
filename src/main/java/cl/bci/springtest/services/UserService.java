package cl.bci.springtest.services;

import cl.bci.springtest.dto.ResponseDto;
import cl.bci.springtest.dto.UserRequestDto;
import cl.bci.springtest.models.UserModel;


/**
 * UsersService - Class that contains the methods of the User entity
 * 
 * @author Jesus Garcia
 * @since 1.0
 * @version jdk-11.0.7
 */
public interface UserService {

	
	/**
	 * get user by field username
	 * 
	 * @param string username
	 * @return user @see {@link UserModel}
	 */
	public UserModel getUserByEmail(String email); 
	
	/**
	 * count users with email
	 * 
	 * @param string email
	 * @return Long - count number of users
	 */
	public Long countUserByEmail(String email) ;
	
	/**
	 * exist users with email
	 * 
	 * @param string email
	 * @return Boolean - exist users
	 */
	public Boolean existUserByEmail(String email) ;
	
	/**
	 * save user an list of phones
	 * 
	 * @param UserRequestDto dto @see {@link UserRequestDto}
	 * @return ResponseDto - @see {@link ResponseDto}
	 */
	public UserModel saveUser(UserRequestDto dto) ;
	
	/**
	 * update user model
	 * 
	 * @param model dto @see {@link UserModel}
	 * @return UserModel - @see {@link UserModel}
	 */
	public UserModel updateUser(UserModel model) ;
	
}
