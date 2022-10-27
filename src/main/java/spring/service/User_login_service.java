package spring.service;

import java.util.List;

import org.json.simple.JSONObject;

import spring.model.CandidateDetails;
import spring.model.UserLogin;

public interface User_login_service {
	
	public UserLogin saveUser(UserLogin user);
	
	
	public UserLogin validate(String email, String password);
	 
	

}
