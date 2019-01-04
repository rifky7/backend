package com.spring.tesbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="T_JURUSAN", uniqueConstraints=@UniqueConstraint(columnNames= {"JURUSANKD"}, name="UK_KD_JURUSAN"))
public class Jurusan {
	@Id
	@GeneratedValue
	@Column(name="JURUSANID", nullable=false)
	private Long id;
	
	@Column(name="JURUSANKD", length=20, nullable=false)
	private String kdJurusan;
	
	@Column(name="JURUSANNM", length=100, nullable=false)
	private String nmJurusan;
	
	@Column(name="JURUSANSTATUS")
	private Boolean status = false;

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKdJurusan() {
		return kdJurusan;
	}

	public void setKdJurusan(String kdJurusan) {
		this.kdJurusan = kdJurusan;
	}

	public String getNmJurusan() {
		return nmJurusan;
	}

	public void setNmJurusan(String nmJurusan) {
		this.nmJurusan = nmJurusan;
	}
	
	

}
