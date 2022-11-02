package spring.controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import spring.exception.UserEmailNotFoundException;
import spring.model.UserLogin;
import spring.repository.UserLoginRepository;
import spring.service.UserLoginService;

@RestController
@CrossOrigin(origins = "*")
public class UserLoginController {
	@Autowired
	private UserLoginService userService;
	@Autowired
	private UserLoginRepository userRepo;
	
	JSONObject json=new JSONObject();

	@PostMapping("/usersave")
	public JSONObject saveStudent(@RequestBody UserLogin user) {
		UserLogin em = new UserLogin();
		BeanUtils.copyProperties(user, em);
		UserLogin em1 = userService.saveUser(em);
		String message = null;
		if (em1 != null) {
			
			message = "User saved sucessfully in Database";
			json.put(em1, message);
		} else {
			message = "User Not saved sucessfully in Database";
			json.put(em1, message);
		}
		return json;

	}

	// UserSave API : http://localhost:8080/app/usersave

	@PostMapping("/login")
	//public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap)
	public JSONObject loginUser(@RequestBody Map<String, Object> userMap) throws UserEmailNotFoundException {
		

		String email = (String) userMap.get("email");
		String password = (String) userMap.get("password");

		UserLogin userLogin = userService.validate(email, password);
		json.put("response", userLogin);
		return json;

		//return new ResponseEntity<>(generateJWTToken(userLogin), HttpStatus.OK);

	}

	private Map<String, String> generateJWTToken(UserLogin userLogin) {

		// TODO Auto-generated method stub
		return null;
	}
	
	// User Get API : http://localhost:8080/app/login
}
