package com.spring.tesbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_PROVINSI")
public class Provinsi {
	
	@Id
	@Column(name="PROVINSI_KODE", length=25, nullable=false)
	private String kdProv;
	
	@Column(name="PROVINSI_NAMA", length=100, nullable=false)
	private String nmProv;
	
	public String getKdProv() {
		return kdProv;
	}
	public void setKdProv(String kdProv) {
		this.kdProv = kdProv;
	}
	public String getNmProv() {
		return nmProv;
	}
	public void setNmProv(String nmProv) {
		this.nmProv = nmProv;
	}

}
