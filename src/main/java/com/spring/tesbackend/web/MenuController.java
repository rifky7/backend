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

import com.spring.tesbackend.dao.MenuDao;
import com.spring.tesbackend.model.Menu;

@RestController
@RequestMapping(value="/api/menu")
public class MenuController {

	@Autowired
	private MenuDao menuDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	@CrossOrigin
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Menu> insert (@RequestBody Menu menu) {
		try {
			this.menuDao.save(menu);
			return new ResponseEntity<>(menu, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public ResponseEntity<Menu> update (@RequestBody Menu menu) {
		try {
			this.menuDao.save(menu);
			return new ResponseEntity<>(menu, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> del (@RequestBody Menu menu) {
		Menu menuModel = this.menuDao.findOne(menu.getId());
		Map<String, Object> m = new HashMap<>();
		
		try {
			if (menuModel != null) {
				menuModel.setStatus(true);
				this.menuDao.save(menuModel);
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
	public ResponseEntity<List<Menu>> getAll() {
		try {
			List<Menu> menulist = this.menuDao.findNotDeleted();
			return new ResponseEntity<>(menulist, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
