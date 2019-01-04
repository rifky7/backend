package com.spring.tesbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_KOTA")
public class Kota {

	@Id
	@Column(name="KOTA_KODE", length=25, nullable=false)
	private String kdKota;
	
	@Column(name="KOTA_NAMA", length=100, nullable=false)
	private String nmKota;
	
	@Column(name="KOTA_KODEPROV", length=25, nullable=false)
	private String kdProv;
	
	public String getKdKota() {
		return kdKota;
	}
	public void setKdKota(String kdKota) {
		this.kdKota = kdKota;
	}
	public String getNmKota() {
		return nmKota;
	}
	public void setNmKota(String nmKota) {
		this.nmKota = nmKota;
	}
	public String getKdProv() {
		return kdProv;
	}
	public void setKdProv(String kdProv) {
		this.kdProv = kdProv;
	}
	
	
}
