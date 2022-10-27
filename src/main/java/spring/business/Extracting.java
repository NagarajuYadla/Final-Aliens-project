package spring.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class Extracting {
	@Value("${emailPattern}")
	private String emailPattern;

	@Value("${mobilePattern}")
	private String mobilePattern;

	@Value("${dobPattern}")
	private String dobPattern;

	@Value("${namePattern}")
	private String namePattern;

	public JSONObject fieldExtraction(Object[] content_array) {
		JSONObject jsonFormat_details = new JSONObject();

		try {

			int filecontent_length = content_array.length;
			System.out.println("file content is " + filecontent_length);
			InputStream educationReader = this.getClass().getResourceAsStream("/Json/education.json");
			InputStream skillsReader = this.getClass().getResourceAsStream("/Json/skills.json");
			// System.out.println(educationReader);
			Pattern email_pattern = Pattern.compile(emailPattern);
			Pattern mobile_pattern = Pattern.compile(mobilePattern);
			Pattern dob_pattern = Pattern.compile(dobPattern);
			Pattern name_pattern = Pattern.compile(namePattern);

			ObjectMapper educationMapper = new ObjectMapper();
			ObjectMapper skillMapper = new ObjectMapper();

			LinkedHashMap<String, Object> skillData = skillMapper.readValue(skillsReader,
					new TypeReference<LinkedHashMap<String, Object>>() {
					});
			LinkedHashMap<String, Object> educationData = educationMapper.readValue(educationReader,
					new TypeReference<LinkedHashMap<String, Object>>() {
					});
			//System.out.println(educationData);

			ArrayList<String> skills = (ArrayList<String>) skillData.get("skills");
			ArrayList<String> education = (ArrayList<String>) educationData.get("education");
			ArrayList<String> dontConsider_educationData = (ArrayList<String>) educationData.get("exclude");

			List<String> skill_list = new ArrayList<>();
			List<String> education_list = new ArrayList<>();
			Set<String> education_set = new LinkedHashSet<>();

			List<String> education_list1 = new ArrayList<>();
			Set<String> education_set1 = new LinkedHashSet<>();

			for (Object textData : content_array) {
				String line = (String) textData;
				line = line.trim();
				Matcher email_matcher = email_pattern.matcher(line);
				Matcher mobile_matcher = mobile_pattern.matcher(line);
				Matcher dob_matcher = dob_pattern.matcher(line);

				if (email_matcher.find()) {
					String email = email_matcher.group(0);
					jsonFormat_details.put("EMAIL", email.toUpperCase());
					// details.setEmail(email.toUpperCase());
					Matcher matcher = name_pattern.matcher(email);
					if (matcher.find()) {
						String name = matcher.group(0);
						// System.out.println("Name:"+name);
						if (!jsonFormat_details.containsKey("name")) {
							jsonFormat_details.put("NAME", name.toUpperCase());
						}

					}
					// System.out.println("Email:" + email );
				}
				if (mobile_matcher.find()) {
					String mobile = mobile_matcher.group(0);
					// System.out.println("Mobile:" + mobile);
					if (!jsonFormat_details.containsKey("mobile")) {
						jsonFormat_details.put("MOBILE", mobile.toUpperCase());
					}

				}
				if (dob_matcher.find()) {
					String dob = dob_matcher.group(1);
					// System.out.println("DOB:" + dob);
					if (!jsonFormat_details.containsKey("dob")) {
						jsonFormat_details.put("DOB:", dob.toUpperCase());
					}
				}
				String skill = "";
				String[] words = line.split(" ");
				for (String word : words) {
					if (skills.contains(word)) {
						// System.out.println(words);
						skill = word;
						skills.remove(word);
						skill_list.add(skill);
					}

				}
				List<String> skill_list_upper = skill_list.stream().map(String::toUpperCase)
						.collect(Collectors.toList());
				if (!jsonFormat_details.containsKey("skills")) {
					jsonFormat_details.put("SKILLS", skill_list_upper);
				}

				boolean skip = false;
				for (String education_dontconsider : dontConsider_educationData) {
					Pattern education_pattren_dontmatch = Pattern.compile(".*" + education_dontconsider + ".*");
					Matcher education_matcher_dontmatch = education_pattren_dontmatch.matcher(line);
					if (education_matcher_dontmatch.find()) {
						skip = true;
					}
				}
				if (skip) {
					continue;
				}
				for (String educations : education) {
					Pattern education_patttren = Pattern.compile(".*" + educations + ".*");
					Matcher education_matcher = education_patttren.matcher(line);
					if (education_matcher.find()) {
						String education_details = education_matcher.group(0);
						education_set.add(education_details);
					}

					Pattern education_patttren1 = Pattern.compile(educations);
					Matcher education_matcher1 = education_patttren.matcher(line);
					if (education_matcher1.find()) {
						education_set1.add(educations);
					}

				}
			}
			// System.out.println(skill_list);
			// System.out.println(education_list);
			education_list.addAll(education_set);
			List<String> education_list_upper = education_list.stream().map(String::toUpperCase)
					.collect(Collectors.toList());
			if (!jsonFormat_details.containsKey("EducationDetails")) {
				jsonFormat_details.put("EDUCATIONDETAILS", education_list_upper);
			}

			education_list1.addAll(education_set1);
			List<String> education_list_upper1 = education_list1.stream().map(String::toUpperCase)
					.collect(Collectors.toList());
			if (!jsonFormat_details.containsKey("Education")) {
				jsonFormat_details.put("EDUCATION", education_list_upper1);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return jsonFormat_details;
	}

}
