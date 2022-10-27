package spring.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
@Component
public class PDFReading {
	
	public List<String> readPDF(String filePath) {
        List<String> content = new ArrayList<>();
        try {
            PdfReader reader = new PdfReader(filePath);
            int pages = reader.getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                String pageContent = PdfTextExtractor.getTextFromPage(reader, i);
                pageContent = pageContent.toLowerCase();
                String[] content_array = pageContent.split("\n");
                content.addAll(Arrays.stream(content_array).toList());
            }
        } catch (Exception e) {
        	e.printStackTrace();
       }
        return content;
    }

}
