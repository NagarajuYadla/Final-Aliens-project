package spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.model.CandidateDetails;
import spring.model.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

	UserLogin findByEmailAndPassword(String email, String password);

}
