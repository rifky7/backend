package com.spring.tesbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.tesbackend.model.Mahasiswa;

/**
 * @author Awiyanto Ajisasongko
 */
public interface MahasiswaDao extends JpaRepository<Mahasiswa, Long> {
	
	@Query("SELECT m FROM Mahasiswa m where m.nama like %:name%")
	public List<Mahasiswa> findByNameContaining(@Param("name") String name);
	
	@Query("SELECT m FROM Mahasiswa m where m.status = false")
	public List<Mahasiswa> findNotDeleted();
}
