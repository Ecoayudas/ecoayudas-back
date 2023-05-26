package com.numerus.ecoayudas.v1.app.controller;

import com.numerus.ecoayudas.v1.app.service.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller class for managing media files.
 */
@RestController
@RequestMapping("${api.version}")
@CrossOrigin(origins = "http://localhost:4200")
public class MediaController {

    private final StorageService storageService;

    /**
     * Constructor for MediaController.
     *
     * @param storageService The service for file storage.
     */
    public MediaController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * Upload a media file.
     *
     * @param multipartFile The multipart file to be uploaded.
     */
    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        storageService.store(multipartFile);
    }


}


