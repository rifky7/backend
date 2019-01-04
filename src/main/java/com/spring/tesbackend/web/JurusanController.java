package com.spring.tesbackend.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.spring.tesbackend.dao.JurusanDao;
import com.spring.tesbackend.model.Jurusan;

@RestController
@RequestMapping(value= {"/api/jurusan"})
public class JurusanController {

	@Autowired
	private JurusanDao jurusanDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Jurusan> get(@PathVariable Long id) {
		try {
			Jurusan jur = this.jurusanDao.findOne(id);
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Jurusan>> search(@PathVariable String name) {
		try {
			List<Jurusan> jurList = this.jurusanDao.findByNameContaining(name);
			return new ResponseEntity<>(jurList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/delete", method=RequestMethod.POST) 
	public ResponseEntity<Map<String, Object>> del (@RequestBody Jurusan jurusan) {
		Jurusan jur = this.jurusanDao.findOne(jurusan.getId());
		Map<String, Object> m = new HashMap<>();
		
		try {
			if (jur != null) {
				jur.setStatus(true);
				this.jurusanDao.save(jur);
				m.put("code", "1");
			}
			else {
				m.put("code", "0");
			}
			return new ResponseEntity<>(m, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(m, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Jurusan>> getAll() {
		try {
			List<Jurusan> jur = this.jurusanDao.findNotDeleted();
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Jurusan> insert(@RequestBody Jurusan jur) {
		try {
			this.jurusanDao.save(jur);
			return new ResponseEntity<>(jur, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public ResponseEntity<Jurusan> update(@RequestBody Jurusan jur) {
		try {
			this.jurusanDao.save(jur);
			return new ResponseEntity<>(jur, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
