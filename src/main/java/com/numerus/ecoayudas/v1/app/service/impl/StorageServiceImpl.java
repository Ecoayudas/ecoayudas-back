package com.numerus.ecoayudas.v1.app.service.impl;

import com.numerus.ecoayudas.v1.app.service.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Service implementation for managing file storage.
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Value("${media.location}")
    private String mediaLocation;
    private Path rootLocation;

    /**
     * Initializes the storage service by creating the root location directory.
     *
     * @throws IOException if an I/O error occurs during initialization.
     */
    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    /**
     * Stores the provided file in the storage system.
     *
     * @param file The file to be stored.
     * @return The filename or identifier assigned to the stored file.
     */
    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("No se ha podido cargar el archivo");
            }
            String filename = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                    .normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        } catch (IOException e) {
            throw new RuntimeException("Fallo al guardar el archivo", e);
        }
    }

    /**
     * Loads a stored file as a Resource.
     *
     * @param filename The filename or identifier of the file to be loaded.
     * @return A Resource representing the loaded file.
     */
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se pudo leer el archivo " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("No se pudo leer el archivo " + filename);
        }
    }
}

