package com.spring.tesbackend.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.spring.tesbackend.dao.ProvinsiDao;
import com.spring.tesbackend.model.Provinsi;

@RestController
@RequestMapping(value="/api/provinsi")
public class ProvinsiController {
	
	@Autowired
	private ProvinsiDao provinsiDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	@CrossOrigin
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Provinsi> insert(@RequestBody Provinsi provinsi) {
		try {
			this.provinsiDao.save(provinsi);
			return new ResponseEntity<>(provinsi, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public ResponseEntity<Provinsi> update(@RequestBody Provinsi provinsi) {
		try {
			this.provinsiDao.save(provinsi);
			return new ResponseEntity<>(provinsi, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/delete/{kode}", method=RequestMethod.DELETE)
	public ResponseEntity<Provinsi> delete(@PathVariable String kode) {
		try {
			this.provinsiDao.delete(kode);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Provinsi>> getAll() {
		try {
			List<Provinsi> provlist = this.provinsiDao.findAll();
			return new ResponseEntity<>(provlist, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
