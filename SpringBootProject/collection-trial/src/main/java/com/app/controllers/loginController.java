package com.app.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.MessageDTO;
import com.app.dto.UserDTO;
import com.app.entities.Customer;
import com.app.service.LoginService;

@RestController
@RequestMapping("/gofarm")
@CrossOrigin(origins = "http://localhost:3000")
public class loginController {
	
	@Autowired
	private LoginService loginService;
	
	public loginController() {
		System.out.println("in ctor of "+getClass());
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody UserDTO user, HttpServletRequest request) {
		System.out.println("in userLogin of "+getClass());
		//return new messageDTO(loginService.authenticate(user));
		Object object=loginService.authenticate(user);
		//request.getSession().setAttribute("user", object);
		if(object instanceof String) {
			return new ResponseEntity<MessageDTO>(new MessageDTO((String)object),HttpStatus.NOT_FOUND);
		}else if(object instanceof Customer) {
			Customer cmt= (Customer)object;
			return new ResponseEntity<>(cmt, HttpStatus.OK);
			
		}else
			return new ResponseEntity<>(object, HttpStatus.OK);
	}

}
