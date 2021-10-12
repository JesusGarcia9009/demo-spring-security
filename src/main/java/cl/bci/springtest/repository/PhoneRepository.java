package cl.bci.springtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.bci.springtest.models.PhoneModel;

public interface PhoneRepository extends JpaRepository<PhoneModel, Integer>{

}
