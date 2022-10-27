package spring.service.implementation;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.model.CandidateDetails;
import spring.model.UserLogin;
import spring.repository.User_login_Repo;
import spring.service.User_login_service;

@Service
public class User_login_ServiceImplementation implements User_login_service {
	@Autowired
	private User_login_Repo userRepo;

	@Override
	public UserLogin saveUser(UserLogin user) {
		// TODO Auto-generated method stub
		UserLogin user_login = userRepo.save(user);
		return user_login;
	}

	@Override
	public UserLogin validate(String email, String password) {
		// TODO Auto-generated method stub
		if(email != null) email = email.toLowerCase();
        return userRepo.findByEmailAndPassword(email, password);
	}

	

	
}
