package spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.model.UserLogin;
import spring.repository.User_login_Repo;
import spring.service.User_login_service;

@RestController
@CrossOrigin(origins = "*")
public class User_login_controller {
	@Autowired
	private User_login_service userService;
	@Autowired
	private User_login_Repo userRepo;

	@PostMapping("/usersave")
	public String saveStudent(@RequestBody UserLogin user) {
		UserLogin em = new UserLogin();
		BeanUtils.copyProperties(user, em);
		UserLogin em1 = userService.saveUser(em);
		String message = null;
		if (em1 != null) {
			message = "User saved sucessfully in Database";
		} else {
			message = "User Not saved sucessfully in Database";
		}
		return message;

	}

	// UserSave API : http://localhost:8080/app/usersave

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {

		String email = (String) userMap.get("email");
		String password = (String) userMap.get("password");

		UserLogin userLogin = userService.validate(email, password);

		return new ResponseEntity<>(generateJWTToken(userLogin), HttpStatus.OK);

	}

	private Map<String, String> generateJWTToken(UserLogin userLogin) {

		// TODO Auto-generated method stub
		return null;
	}
	
	// User Get API : http://localhost:8080/app/login
}
