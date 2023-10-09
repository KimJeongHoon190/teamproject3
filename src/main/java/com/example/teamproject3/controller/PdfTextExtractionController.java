import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/pdf")
public class PdfTextExtractionController {

    private final PdfTextExtractionService pdfTextExtractionService;

    public PdfTextExtractionController(PdfTextExtractionService pdfTextExtractionService) {
        this.pdfTextExtractionService = pdfTextExtractionService;
    }

    @PostMapping("/extract")
    public String extractText(@RequestParam("file") MultipartFile file) throws IOException {
        File pdfFile = convertMultipartFileToFile(file);
        return pdfTextExtractionService.extractTextFromPdf(pdfFile);
    }

    // MultipartFile을 File로 변환하는 유틸리티 메서드
    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
    }
}
