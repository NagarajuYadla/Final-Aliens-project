package spring.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import spring.model.CandidateDetails;

public interface Candidate_Details_Service {
	public void parseAndMatch( MultipartFile multipartFile) throws Throwable;
	List<CandidateDetails> searchDetails(JSONObject query);
	
	// pagination
	List<CandidateDetails> findPaginated(int pageNo ,int pageSize,List<CandidateDetails> candidateDetails);
	

}
