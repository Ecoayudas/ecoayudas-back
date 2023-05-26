package com.numerus.ecoayudas.v1.app.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init() throws IOException;
    String store(MultipartFile file);//Almacena archivo
    Resource loadAsResource(String filename);//Carga archivo

}
