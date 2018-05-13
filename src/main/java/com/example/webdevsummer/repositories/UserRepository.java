package com.example.webdevsummer.repositories;

import com.example.webdevsummer.model.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Integer>{

	@Query("SELECT u FROM User u WHERE u.username=:username")
	Iterable<User> findUserByUsername(@Param("username") String u);
	
}
