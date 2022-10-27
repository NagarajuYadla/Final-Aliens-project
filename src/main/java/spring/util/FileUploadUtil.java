package spring.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static Path saveFile(String fileName, MultipartFile multipartFile) throws Exception {
      Path customBaseDir = FileSystems.getDefault().getPath("C:\\\\STS Workspace\\\\AliensProject\\\\src\\\\main\\\\resources\\\\File_Upload");
		
		String customFilePrefix = "resume";
		
		String customFileSuffix = ".pdf";
		
		Path tmpFile
		    = Files.createTempFile(customBaseDir, customFilePrefix, customFileSuffix);
		
		//Path uploadDirectory = Paths.get("C:\\STS Workspace\\AliensProject\\src\\main\\resources\\File_Upload");
//		try (InputStream inputStream = multipartFile.getInputStream()) {
//			Path filePath = uploadDirectory.resolve(fileName);
//			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException ioe) {
//			throw new IOException("Error Saving Uploaded File" + fileName, ioe);
//		}
	    //Path filepath = Paths.get(tmpFile, multipartFile.getOriginalFilename());
	    multipartFile.transferTo(tmpFile);
	    tmpFile.toFile().deleteOnExit();
	    return tmpFile;
	}

}
