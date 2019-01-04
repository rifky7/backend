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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.tesbackend.dao.MatkulDao;
import com.spring.tesbackend.model.Matkul;

@RestController
@RequestMapping(value="/api/matkul")
public class MatkulController {

	@Autowired
	private MatkulDao matkulDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	@CrossOrigin
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Matkul> create (@RequestBody Matkul matkul) {
		try {
			this.matkulDao.save(matkul);
			return new ResponseEntity<>(matkul, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public ResponseEntity<Matkul> update (@RequestBody Matkul matkul) {
		try {
			this.matkulDao.save(matkul);
			return new ResponseEntity<>(matkul, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Matkul>> getAll() {
		try {
			List<Matkul> matkulList = this.matkulDao.findNotDeleted();
			return new ResponseEntity<>(matkulList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> del (@RequestBody Matkul matkul) {
		Matkul mk = this.matkulDao.findOne(matkul.getId());
		Map<String, Object> m = new HashMap<>();
		
		try {
			if (mk != null) {
				mk.setStatus(true);
				this.matkulDao.save(mk);
				m.put("code", "1");
			}
			else {
				m.put("code", "0");
			}
			return new ResponseEntity<>(m, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
