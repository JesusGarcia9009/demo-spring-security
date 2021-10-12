package cl.bci.springtest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.bci.springtest.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{
	
	boolean existsByEmail(String email);
	
	Long countByEmail(String email);
	
	Optional<UserModel> findByEmail(String email);

}
