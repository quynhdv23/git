package com.example.computershop.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.computershop.entities.DatabaseFile;
import com.example.computershop.payload.Response;
import com.example.computershop.repository.DatabaseFileRepository;
import com.example.computershop.service.impl.DatabaseFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class FileUploadApi {
    @Autowired
    private DatabaseFileRepository dbFileRepository;

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        DatabaseFile fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getMediaId().toString())
                .toUriString();

        return new Response(fileName.getMediaId(),fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize(),fileName.getData());
    }

    @PostMapping("/uploadMultipleFiles")
    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
}