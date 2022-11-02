package spring.service.implementation;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.exception.UserEmailNotFoundException;
import spring.model.CandidateDetails;
import spring.model.UserLogin;
import spring.repository.UserLoginRepository;
import spring.service.UserLoginService;

@Service
public class UserLoginServiceImplementation implements UserLoginService {
	@Autowired
	private UserLoginRepository userRepo;

	@Override
	public UserLogin saveUser(UserLogin user) {
		// TODO Auto-generated method stub
		UserLogin user_login = userRepo.save(user);
		return user_login;
	}

	@Override
	public UserLogin validate(String email, String password) throws UserEmailNotFoundException {
		// TODO Auto-generated method stub
		if(email != null) 
			email = email;
		UserLogin data = userRepo.findByEmailAndPassword(email, password);
		if(data == null) {
			throw new UserEmailNotFoundException("Invalid Credentials, please try again");
		}
			
        return data;
	}

	

	
}
