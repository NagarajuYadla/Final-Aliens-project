package spring.business;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;
@Component
public class DOCReading {
	
	public List<String> readDOC(String filePath) throws FileNotFoundException {
		List<String> content = new ArrayList<>();
		FileInputStream file = new FileInputStream(filePath);
		int length = filePath.length();
		String fileFormat = filePath.substring(length - 2);
		if (fileFormat.equals("cx")) {
			try {

				XWPFDocument docFile = new XWPFDocument(file);
				List<XWPFParagraph> paragraphList = docFile.getParagraphs();
				for (XWPFParagraph paragraphs : paragraphList) {
					String paragraph = paragraphs.getText();
					String page = paragraph.toString().toLowerCase();
					String[] content_array = page.split("\n");
					content.addAll(Arrays.stream(content_array).toList());
				}
			} catch (Exception e) {
			}
		} 
		else if (fileFormat.equals("oc")) {
			try {
				HWPFDocument document = new HWPFDocument(file);
				WordExtractor extractor = new WordExtractor(document);
				String str1 = extractor.getText();
				String page = str1.toString().toLowerCase();
				String[] content_array = page.split("\n");
				content.addAll(Arrays.stream(content_array).toList());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return content;
	}

}
