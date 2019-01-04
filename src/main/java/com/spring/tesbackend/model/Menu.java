package com.spring.tesbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_MENU")
public class Menu {

	@Id
	@GeneratedValue
	@Column(name="MENU_ID")
	private Long id;
	
	@Column(name="MENU_TEKS")
	private String teks;
	
	@Column(name="MENU_ICON")
	private String icon;
	
	@Column(name="MENU_PATH")
	private String path;
	
	@Column(name="MENU_STATUS")
	private Boolean status=false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeks() {
		return teks;
	}

	public void setTeks(String teks) {
		this.teks = teks;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean isStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
