package com.nisanth.billingsoftware.service.impl;

import com.nisanth.billingsoftware.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${file.upload.dir}")
    private String uploadDir;
    
    @Value("${file.base.url}")
    private String baseUrl;

    @Override
    public String uploadFile(MultipartFile file) {
        try {
            // Create upload directory if it doesn't exist
            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }

            // Get the file extension
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File name cannot be empty");
            }
            
            String fileExtension = "";
            int lastDotIndex = originalFilename.lastIndexOf('.');
            if (lastDotIndex > 0) {
                fileExtension = originalFilename.substring(lastDotIndex);
            }

            // Generate unique filename
            String filename = UUID.randomUUID().toString() + fileExtension;
            
            // Create the file path
            Path filePath = Paths.get(uploadDir, filename);
            
            // Copy file to the target location
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Return the accessible URL
            return baseUrl + "/" + filename;
            
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "An error occurred while uploading the file: " + e.getMessage());
        }
    }

    @Override
    public boolean deletFile(String imgUrl) {
        try {
            if (imgUrl == null || imgUrl.isEmpty()) {
                return false;
            }
            
            // Extract filename from URL
            String filename = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
            
            // Create file path
            Path filePath = Paths.get(uploadDir, filename);
            
            // Delete the file if it exists
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return true;
            }
            
            return false;
            
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, 
                "An error occurred while deleting the file: " + e.getMessage());
        }
    }
}
