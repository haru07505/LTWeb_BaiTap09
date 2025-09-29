package haru.kieu.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	@PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Lưu vào thư mục static/images
        Path path = Paths.get("src/main/resources/static/images/" + fileName);
        Files.createDirectories(path.getParent()); // đảm bảo thư mục tồn tại
        Files.write(path, file.getBytes());

        // Trả về URL để frontend lưu vào DB
        return "/images/" + fileName;
    }
}
