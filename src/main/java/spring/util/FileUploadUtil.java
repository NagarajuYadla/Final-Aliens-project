package spring.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadUtil {
	@Value("${filepath}")
	private static String filepath;

	public static Path saveFile(String fileName, MultipartFile multipartFile,String prefix, String suffix) throws Exception {
		Path customBaseDir = FileSystems.getDefault().getPath("C:\\STS Workspace\\AliensProject\\src\\main\\resources\\File_Upload");

		//String customFilePrefix = "resume";

		//String customFileSuffix = ".pdf";

		Path tmpFile = Files.createTempFile(customBaseDir, prefix, suffix);

		multipartFile.transferTo(tmpFile);
		tmpFile.toFile().deleteOnExit();
		return tmpFile;
	}

}









//Path uploadDirectory = Paths.get("C:\\STS Workspace\\AliensProject\\src\\main\\resources\\File_Upload");
//try (InputStream inputStream = multipartFile.getInputStream()) {
//	Path filePath = uploadDirectory.resolve(fileName);
//	Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//} catch (IOException ioe) {
//	throw new IOException("Error Saving Uploaded File" + fileName, ioe);
//}
//Path filepath = Paths.get(tmpFile, multipartFile.getOriginalFilename());
