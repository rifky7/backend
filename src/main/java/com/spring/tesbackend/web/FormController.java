/**
 * 
 */
package com.spring.tesbackend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Awiyanto Ajisasongko
 *
 */
@Controller
public class FormController {
	
	@RequestMapping("/mahasiswa.html")
	public String form() {
		return "mahasiswa";				// lihat dispatcher-servlet line 33, digabung prefix dan suffixnya
	}
	
	@RequestMapping("/jurusan.html")
	public String formJur() {
		return "jurusan";				// lihat dispatcher-servlet line 33, digabung prefix dan suffixnya
	}
	
	@RequestMapping("/login.html")
	public String loginForm() {
		return "login";
	}
	@RequestMapping("/home.html")
	public String home() {
		return "home";
	}
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}
	@RequestMapping(value= {"/403.html","/403"})
	public String error403() {
		return "403";
	}
	
}
