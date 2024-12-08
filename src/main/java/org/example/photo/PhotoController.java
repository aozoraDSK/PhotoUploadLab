package org.example.photo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PhotoController {
    PhotoRepository photoRepository;
    PhotoService photoService;

    public PhotoController(PhotoRepository photoRepository, PhotoService photoService) {
        this.photoRepository = photoRepository;
        this.photoService = photoService;
    }

    @GetMapping({"/", "home"})
    public String home() {
        return "home";
    }

    @GetMapping("/upload")
    public String photos(Model model) {
        model.addAttribute("photos", photoRepository.findAll());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadPhoto(MultipartFile file) throws IOException {
        photoService.uploadPhoto(file);
        return "redirect:/";
    }
}
