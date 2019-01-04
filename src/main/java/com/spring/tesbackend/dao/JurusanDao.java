package com.spring.tesbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.tesbackend.model.Jurusan;

public interface JurusanDao extends JpaRepository<Jurusan, Long> {

	@Query("SELECT j FROM Jurusan j where j.nmJurusan like %:name%")
	public List<Jurusan> findByNameContaining(@Param("name") String name);
	
	@Query("SELECT j FROM Jurusan j where j.status=false")
	public List<Jurusan> findNotDeleted();
}
