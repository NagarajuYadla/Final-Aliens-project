package spring.service;

import java.util.List;

import org.json.simple.JSONObject;

import spring.exception.UserEmailNotFoundException;
import spring.model.CandidateDetails;
import spring.model.UserLogin;

public interface UserLoginService {
	
	public UserLogin saveUser(UserLogin user);
	public UserLogin validate(String email, String password)  throws UserEmailNotFoundException;
	 
	

}
