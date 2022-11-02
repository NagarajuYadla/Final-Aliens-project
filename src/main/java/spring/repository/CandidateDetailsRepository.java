package spring.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.model.CandidateDetails;

@Repository
public interface CandidateDetailsRepository extends JpaRepository<CandidateDetails, Integer>,PagingAndSortingRepository<CandidateDetails, Integer>{

	@Query(value = "SELECT * FROM public.candidate_details c where c.details ->> 'SKILLS' ilike (%?2%) and c.details ->> 'EDUCATION' ilike (%?1%) ", nativeQuery = true)
	List<CandidateDetails> searchDetails(@Param("education") String education, @Param("skills") String skills);
	

	
	

	

}
