package spring.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.simple.JSONObject;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Data;
@Entity
@Table(name="applicant_details")
@Data
@TypeDef(name= "jsonb" ,typeClass=JsonBinaryType.class)

public class CandidateDetails {
	
	@Id
	@GeneratedValue(generator="candidate_seq",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="candidate_seq",sequenceName="candidate_sequence",initialValue=1,allocationSize=1)
	int id;
	@Type(type="jsonb")
	@Column(name="candidate_details")
	JSONObject details;
	//LocalDateTime upload_date=java.time.LocalDateTime.now();
	@CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
	
	
	
    
	
}
