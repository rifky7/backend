/**
 * 
 */
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

import com.spring.tesbackend.dao.MahasiswaDao;
import com.spring.tesbackend.model.Mahasiswa;

/**
 * @author Awiyanto Ajisasongko
 *
 */
@RestController
@RequestMapping(value= {"/api/mahasiswa"})
public class MahasiswaController {
	@Autowired
	private MahasiswaDao mahasiswaDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	@CrossOrigin
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<Mahasiswa> get(@PathVariable Long id) {
		try {
			Mahasiswa mhs = this.mahasiswaDao.findOne(id);
			return new ResponseEntity<>(mhs, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="search/{name}", method=RequestMethod.GET)
	public ResponseEntity<List<Mahasiswa>> search(@PathVariable String name) {
		try {
			List<Mahasiswa> mhsList = this.mahasiswaDao.findByNameContaining(name);
			return new ResponseEntity<>(mhsList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> del (@RequestBody Mahasiswa mahasiswa) {
		Mahasiswa mhs = this.mahasiswaDao.findOne(mahasiswa.getId());
		Map<String, Object> m = new HashMap<>();
		
		try {
			if (mhs != null) {
				mhs.setStatus(true);
				this.mahasiswaDao.save(mhs);
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
	public ResponseEntity<List<Mahasiswa>> getAll() {
		try {
			List<Mahasiswa> mhs = this.mahasiswaDao.findNotDeleted();
			return new ResponseEntity<>(mhs, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@CrossOrigin
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Mahasiswa> insert(@RequestBody Mahasiswa mhs) {
		try {
			this.mahasiswaDao.save(mhs);
			return new ResponseEntity<>(mhs, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}	

	@CrossOrigin
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public ResponseEntity<Mahasiswa> update(@RequestBody Mahasiswa mhs) {
		try {
			this.mahasiswaDao.save(mhs);
			return new ResponseEntity<>(mhs, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
}
