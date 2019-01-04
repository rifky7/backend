package com.spring.tesbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.tesbackend.model.Krs;

public interface KrsDao extends JpaRepository<Krs, Long> {
	
	@Query("SELECT k FROM Krs k WHERE k.status=false")
	public List<Krs> findNotDeleted();

}
