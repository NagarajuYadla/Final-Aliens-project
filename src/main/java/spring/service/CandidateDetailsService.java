package spring.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import spring.model.CandidateDetails;

public interface CandidateDetailsService 
	{
	  public void parseAndMatch( MultipartFile multipartFile) throws Throwable;
	  List<CandidateDetails> searchDetails(JSONObject query);
	}


//pagination
	//List<CandidateDetails> findPaginated(int pageNo ,int pageSize,List<CandidateDetails> candidateDetails);
	//public Page<CandidateDetails> findAll(@RequestParam int page, @RequestParam int size);
	
