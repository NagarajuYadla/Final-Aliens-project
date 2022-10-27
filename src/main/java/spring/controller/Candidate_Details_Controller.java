
package spring.controller;

import java.nio.file.Path;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.model.CandidateDetails;
import spring.model.FileUploadResponse;
import spring.service.Candidate_Details_Service;
import spring.util.FileUploadUtil;

@RestController
@CrossOrigin(origins = "*")
public class Candidate_Details_Controller {
	@Autowired
	private Candidate_Details_Service candidate_service;

	@PostMapping("/uploadFile")
	// @ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Throwable {
		
		//FileUploadResponse fileUploadResponse = new FileUploadResponse();
		//fileUploadResponse.setFileName(fileName);
		//fileUploadResponse.setSize(size);
		 System.out.println(multipartFile.getOriginalFilename()+ ""+multipartFile.getName());
		
		candidate_service.parseAndMatch(multipartFile);

		return "Data Sucessfully Inserted in DataBase";
	}

	// UploadFile API : http://localhost:8080/app/uploadFile

	@PostMapping("/search/{pageNo}/{pageSize}")
	public ResponseEntity<Page<CandidateDetails>> searchDetalis(@RequestBody JSONObject query, @PathVariable int pageNo,
			@PathVariable int pageSize) {
		List<CandidateDetails> candidateDetails = candidate_service.searchDetails(query);
		Pageable paging = PageRequest.of(pageNo, pageSize);
		int start = (int) paging.getOffset();
		// System.out.println(paging.getOffset()+" "+start);
		int end = Math.min(start + paging.getPageSize(), candidateDetails.size());
		System.out.println(candidateDetails.size());
		// System.out.println(paging.getPageSize()+ " "+end);
		Page<CandidateDetails> page = new PageImpl<CandidateDetails>(candidateDetails.subList(0, end), paging,
				candidateDetails.size());
		// candidate_service.findPaginated(pageNo, pageSize,candidateDetails);

		return ResponseEntity.ok(page);

	}

	// Search API : http://localhost:8080/app/search

	// pagination
//	@GetMapping("/candidate/{pageNo}/{pageSize}")
//	public List<CandidateDetails> getPaginated()
//	{
//		return candidate_service.findPaginated(pageNo, pageSize);
//		
//	}

}
