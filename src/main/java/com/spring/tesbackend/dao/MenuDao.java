package com.spring.tesbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.tesbackend.model.Menu;

public interface MenuDao extends JpaRepository<Menu, Long> {

	@Query("SELECT m FROM Menu m WHERE m.status = false")
	public List<Menu> findNotDeleted();
}
