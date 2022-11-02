package spring.service.implementation;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import spring.business.DOCReading;
import spring.business.Extracting;
import spring.business.PDFReading;
import spring.model.CandidateDetails;
import spring.repository.CandidateDetailsRepository;
import spring.service.CandidateDetailsService;
import spring.util.FileUploadUtil;


@Service
public class CandidateDetailsServiceImplementation implements CandidateDetailsService {
	@Autowired
	private CandidateDetailsRepository candidate_repo;
	
	@Autowired
	private Extracting pdr;
	@Autowired
	private PDFReading pdf;
	@Autowired
	private DOCReading doc;
	

	public void parseAndMatch(MultipartFile multipartFile) throws Throwable{
		
		//String filePath = "C:\\STS Workspace\\InHouseProject-ReadingFile_PDF\\src\\main\\resources\\File_Upload\\"+fileName;
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		long size = multipartFile.getSize();
		String fileFormat = fileName.toString().substring(fileName.toString().length()-2);
		String fileFormatFull = null;
		switch(fileFormat) {
		case "df": 
			fileFormatFull = "pdf";
			break;
		case "oc":
			fileFormatFull = "doc";
			break;
		case "cx":
			fileFormatFull = "docx";
			break;
		default:
				break;
		}
		Path filePath=FileUploadUtil.saveFile(fileName, multipartFile, fileName, fileFormatFull);
		
		//System.out.println(filePath);
		List<String> content = new ArrayList<>();
		//String path = filePath.toString();
		if (fileFormat.equals("df")) {
			content = pdf.readPDF(filePath.toString());
		}
		else if (fileFormat.equals("cx") | fileFormat.equals("oc")) {
			try {
				content = doc.readDOC(filePath.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Un Supported File(It will accept only PDF & DOC Files)");
		}
		System.out.println("reading done");
		JSONObject details = pdr.fieldExtraction(content.toArray());
		System.out.println("matching done");
		CandidateDetails jsonDetails = new CandidateDetails();
		jsonDetails.setDetails(details);
		
		System.out.println(jsonDetails);
		candidate_repo.save(jsonDetails);
	}

	public List<CandidateDetails> searchDetails(JSONObject query) {
		// TODO Auto-generated method stub

		List<CandidateDetails> detailsList = candidate_repo.searchDetails(query.get("EDUCATION").toString(),
				query.get("SKILLS").toString());
		
		//System.out.println(detailsList);
		
		return detailsList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// pagination
//	@Override
//	public List<CandidateDetails> findPaginated(int pageNo, int pageSize,List<CandidateDetails> candidateDetails) {
//		// TODO Auto-generated method stub
//		Pageable paging=PageRequest.of(pageNo, pageSize);
//		Page<CandidateDetails> pagedResult=candidate_repo.findAll(paging);
//		return pagedResult.toList();
//	}

//	@Override
//	public Page<CandidateDetails> findAll(int page, int size) {
//		// TODO Auto-generated method stub
//		  PageRequest pr = PageRequest.of(page,size);
//		 
//	        return candidate_repo.findAll(pr);
//	}
//	

}
