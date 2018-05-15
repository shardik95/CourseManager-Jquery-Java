package com.example.webdevsummer.repositories;

import com.example.webdevsummer.model.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Integer>{

	/**
	 * Find the user by username using the query JPQL
	 * @param u - user
	 * @return Iterable<User>n that satisfy the query
	 */
	@Query("SELECT u FROM User u WHERE u.username=:username")
	Iterable<User> findUserByUsername(@Param("username") String u);
	
	/**
	 * Find the user by username and password using the query JPQL
	 * @param username - username of user
	 * @param password - password of the user
	 * @return Iterable<User>n that satisfy the query
	 */
	@Query("SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
	Iterable<User> findUserByCredentials(@Param("username") String username, @Param("password") String password);
				
}
