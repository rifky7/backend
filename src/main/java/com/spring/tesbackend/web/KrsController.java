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

import com.spring.tesbackend.dao.KrsDao;
import com.spring.tesbackend.model.Krs;

@RestController
@RequestMapping(value="/api/krs")
public class KrsController {
	
	@Autowired
	private KrsDao krsDao;
	
	private Log log = LogFactory.getLog(getClass());

	@CrossOrigin
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Krs> insert (@RequestBody Krs krs) {
		try {
			this.krsDao.save(krs);
			return new ResponseEntity<>(krs, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public ResponseEntity<Krs> update (@RequestBody Krs krs) {
		try {
			this.krsDao.save(krs);
			return new ResponseEntity<>(krs, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> del (@RequestBody Krs krs) {
		Krs kr = this.krsDao.findOne(krs.getId());
		Map<String, Object> m = new HashMap<>();
		try {
			if (kr != null) {
				kr.setStatus(true);
				this.krsDao.save(kr);
				m.put("code", "1");
			} else {
				m.put("code", "0");
			}
			return new ResponseEntity<>(m, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<Krs>> getAll() {
		try {
			List<Krs> krsList = this.krsDao.findNotDeleted();
			return new ResponseEntity<>(krsList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
