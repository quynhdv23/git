package com.example.computershop.service.impl;

import com.example.computershop.entities.DatabaseFile;
import com.example.computershop.exception.FileStorageException;
import com.example.computershop.repository.DatabaseFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


@Service
public class DatabaseFileService {

    @Autowired
    private DatabaseFileRepository dbFileRepository;

    public DatabaseFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public DatabaseFile storeFileInputStream(InputStream file) throws IOException {
        // Normalize file name
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        try {
//            // Check if the file's name contains invalid characters
//            if (fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }

            DatabaseFile dbFile = new DatabaseFile("", "", StreamUtils.copyToByteArray(file));
            return dbFileRepository.save(dbFile);
//        } catch (IOException ex) {
//            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//        }
    }

    public DatabaseFile getFile(Integer fileId) {
       return dbFileRepository.findById(fileId).orElse(null);
    }
}