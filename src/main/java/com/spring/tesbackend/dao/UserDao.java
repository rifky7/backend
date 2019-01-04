package com.spring.tesbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.tesbackend.model.User;

public interface UserDao extends JpaRepository<User, String> {
	
	@Query("SELECT m FROM User m where username=:username")
	public User login(@Param("username") String username);

	@Query("SELECT u FROM User u where u.status=false")
	public List<User> findNotDeleted();
	
}
