package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.simple.JSONObject;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Data;
@Entity
@Table(name="candidate_details")
@Data
@TypeDef(name= "jsonb" ,typeClass=JsonBinaryType.class)

public class CandidateDetails {
	
	@Id
	@GeneratedValue(generator="candidate_seq",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name="candidate_seq",sequenceName="candidate_sequence",initialValue=1,allocationSize=1)
	int id;
	@Type(type="jsonb")
	JSONObject details;

}
