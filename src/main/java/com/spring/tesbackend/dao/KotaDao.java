package com.spring.tesbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.tesbackend.model.Kota;

public interface KotaDao extends JpaRepository<Kota, String>{
	
}
