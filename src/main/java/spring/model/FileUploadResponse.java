package spring.model;

import lombok.Data;

@Data
public class FileUploadResponse {
	private String fileName;
	private long size;

}
