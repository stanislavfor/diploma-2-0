package com.example.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${upload.path}") // путь указан в application.properties
    private String uploadDir;

    public boolean deleteFile(String filename) {
        Path filePath = Paths.get(uploadDir, filename);

        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}