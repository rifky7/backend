package com.spring.tesbackend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.tesbackend.model.Matkul;

public interface MatkulDao extends JpaRepository<Matkul, Long> {

	@Query("SELECT MT FROM Matkul MT where MT.status = false")
	public List<Matkul> findNotDeleted();
}
