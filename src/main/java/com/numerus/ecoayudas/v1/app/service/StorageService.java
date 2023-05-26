package com.numerus.ecoayudas.v1.app.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
/**
 * Service interface for managing file storage.
 */
@Service
public interface StorageService {
    /**
     * Initializes the storage service.
     *
     * @throws IOException if an I/O error occurs during initialization.
     */
    void init() throws IOException;

    /**
     * Stores the provided file in the storage system.
     *
     * @param file The file to be stored.
     * @return The filename or identifier assigned to the stored file.
     */
    String store(MultipartFile file);

    /**
     * Loads a stored file as a Resource.
     *
     * @param filename The filename or identifier of the file to be loaded.
     * @return A Resource representing the loaded file.
     */
    Resource loadAsResource(String filename);
}

