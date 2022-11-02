
package spring.controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
import spring.service.CandidateDetailsService;
import spring.util.FileUploadUtil;

@RestController
@CrossOrigin(origins = "*")
public class CandidateDetailsController {
	@Autowired
	private CandidateDetailsService candidate_service;
	@PersistenceContext
	EntityManager entityManager;
	

	@PostMapping("/uploadFile")
	// @ResponseStatus(code = HttpStatus.OK, reason = "OK")
	public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Throwable {
		
		//FileUploadResponse fileUploadResponse = new FileUploadResponse();
		//fileUploadResponse.setFileName(fileName);
		//fileUploadResponse.setSize(size);
		 System.out.println(multipartFile.getOriginalFilename()+ ""+multipartFile.getName());
		
		candidate_service.parseAndMatch(multipartFile);
	

		return "Data Sucessfully Inserted In DataBase";
	}

	// UploadFile API : http://localhost:8080/app/uploadFile

	@PostMapping("/search/{pageNo}/{pageSize}")
	public ResponseEntity<Page<CandidateDetails>> searchDetalis(@RequestBody JSONObject query, @PathVariable int pageNo,
			@PathVariable int pageSize) {
		List<CandidateDetails> candidateDetails = candidate_service.searchDetails(query);
		Pageable paging = PageRequest.of(pageNo, pageSize);
		System.out.println(candidateDetails.size());
		int start = (pageNo -1) * pageSize;
		int end = Math.min(candidateDetails.size(), pageNo * pageSize);
		Page<CandidateDetails> page = new PageImpl<CandidateDetails>(candidateDetails.subList(start, end), paging,
				candidateDetails.size());
	
		return ResponseEntity.ok(page);

	}
}

	// Search API : http://localhost:8080/app/search

	// pagination
//	@GetMapping("/candidate/{pageNo}/{pageSize}")
//	public List<CandidateDetails> getPaginated()
//	{
//		return candidate_service.findPaginated(pageNo, pageSize);
//		
//	}


//candidate_service.findPaginated(pageNo, pageSize,candidateDetails);
//List<CandidateDetails> page = new ArrayList<CandidateDetails>();
//page = candidateDetails.subList((pageNo -1) * pageSize, pageNo * pageSize);


//CriteriaQuery<Long> countQuery = criteriaBuilder
//  .createQuery(Long.class);
//countQuery.select(criteriaBuilder
//  .count(countQuery.from(CandidateDetails.class)));
//Long count = entityManager.createQuery(countQuery)
//  .getSingleResult();
//
//CriteriaQuery<CandidateDetails> criteriaQuery = criteriaBuilder
//  .createQuery(CandidateDetails.class);
//Root<CandidateDetails> from = criteriaQuery.from(CandidateDetails.class);
//CriteriaQuery<CandidateDetails> select = criteriaQuery.select(from);
//
//TypedQuery<CandidateDetails> typedQuery = entityManager.createQuery(select);
//while (pageNo < count.intValue()) {
//    typedQuery.setFirstResult(pageNo - 1);
//    typedQuery.setMaxResults(pageSize);
//    System.out.println("Current page: " + typedQuery.getResultList());
//    pageNo += pageSize;
//}
//List<CandidateDetails> result = typedQuery.getResultList();



//CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//String buildQuery = "SELECT c.details FROM public.candidate_details c where c.details ->> 'SKILLS' ilike " + query.get("SKILLS").toString() + " and c.details ->> 'EDUCATION' ilike " + query.get("EDUCATION");                                                            
//Query query1 = entityManager.createQuery(buildQuery);
//
//query1.setFirstResult((pageNo-1) * pageSize); 
//query1.setMaxResults(pageSize);
//List <CandidateDetails> result = query1.getResultList();
//PageRequest pr = PageRequest.of(pageNo,pageSize);
//return repository.findAll(pr);


//pagination
//int start = (int) paging.getOffset();
		// System.out.println(paging.getOffset()+" "+start);
		//int limit = Math.min(start + paging.getPageSize(), candidateDetails.size());
		//int limit = paging.getPageSize();



