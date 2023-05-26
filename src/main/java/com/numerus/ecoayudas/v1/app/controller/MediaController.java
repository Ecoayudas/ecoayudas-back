package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class MediaController {

    private final StorageService storageService;

    public MediaController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        storageService.store(multipartFile);
    }


}


