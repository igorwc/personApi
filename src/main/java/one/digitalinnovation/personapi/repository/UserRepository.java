package one.digitalinnovation.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinnovation.personapi.entity.ApiUser;

public interface UserRepository extends   JpaRepository<ApiUser, Long> {
	ApiUser findByUsername(String username); 
}
