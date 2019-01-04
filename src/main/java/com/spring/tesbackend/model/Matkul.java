package com.spring.tesbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_MATKUL")
public class Matkul {
	
	@Id
	@GeneratedValue
	@Column(name="MK_ID")
	private Long id;
	
	@Column(name="MK_KD")
	private String kdMatkul;
	
	@Column(name="MK_NM")
	private String nmMatkul;
	
	@Column(name="MK_STATUS")
	private Boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKdMatkul() {
		return kdMatkul;
	}
	public void setKdMatkul(String kdMatkul) {
		this.kdMatkul = kdMatkul;
	}
	public String getNmMatkul() {
		return nmMatkul;
	}
	public void setNmMatkul(String nmMatkul) {
		this.nmMatkul = nmMatkul;
	}
	public Boolean isStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
