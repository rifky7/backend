package com.spring.tesbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_KRS")
public class Krs {
	
	@Id
	@GeneratedValue
	@Column(name="KRS_ID")
	private Long id;
	
	@Column(name="KRS_KDMATKUL")
	private String kdMatkul;
	
	@Column(name="KRS_KDJUR")
	private String kdJur;
	
	@Column(name="KRS_NIM")
	private String nim;
	
	@Column(name="KRS_NAMAMHS")
	private String nmMhs;
	
	@Column(name="KRS_SMT")
	private Integer smt;
	
	@Column(name="KRS_STATUS")
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
	public String getNim() {
		return nim;
	}
	public void setNim(String nim) {
		this.nim = nim;
	}
	public String getNmMhs() {
		return nmMhs;
	}
	public void setNmMhs(String nmMhs) {
		this.nmMhs = nmMhs;
	}
	public Integer getSmt() {
		return smt;
	}
	public void setSmt(Integer smt) {
		this.smt = smt;
	}
	public String getKdJur() {
		return kdJur;
	}
	public void setKdJur(String kdJur) {
		this.kdJur = kdJur;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
