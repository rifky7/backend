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

import com.spring.tesbackend.dao.UserDao;
import com.spring.tesbackend.model.User;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	private Log log = LogFactory.getLog(getClass());
	
	@CrossOrigin
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user) {
		String result = "";
		try {
			System.out.println("User: "+user.getUsername()+"Pass : "+user.getPassword());
			User us = this.userDao.login(user.getUsername());
			
			if (us != null) {
				if (us.getPassword().equals(user.getPassword())) {
//					result = "Success";
					result= 1+ "";
				}
				else {
					result = "Password wrong";
				}
			}
			else {
				result = "User not found";
			}
			return ResponseEntity.ok(result);
			//	return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	
//	<< PEmbuatan method getAllUser >>>
	@CrossOrigin
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		try {
			List<User> userList = this.userDao.findNotDeleted();
			return new ResponseEntity<>(userList, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<?> insert(@RequestBody User user) {
		String result = "";
		try {
			User us = this.userDao.login(user.getUsername());
			
			if (us == null) {
				this.userDao.save(user);
				return new ResponseEntity<>(user, HttpStatus.CREATED);
			}
			else {
				result = "Username sudah dipakai";
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin
	@RequestMapping(value="/edit", method=RequestMethod.PUT)
	public ResponseEntity<User> update (@RequestBody User user) {
		try {
			this.userDao.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> del (@RequestBody User user) {
		
		User userModel = this.userDao.findOne(user.getUserId());
		Map<String, Object> m = new HashMap<>();
		try {
			if (userModel != null) {
				userModel.setStatus(true);
				this.userDao.save(userModel);
				m.put("code", "1");
			}
			else {
				m.put("code", "0");
			}
			return new ResponseEntity<>(m, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(m, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
