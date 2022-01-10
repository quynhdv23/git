package com.example.computershop.api;

import com.example.computershop.entities.DatabaseFile;
import com.example.computershop.payload.Response;
import com.example.computershop.repository.DatabaseFileRepository;
import com.example.computershop.service.impl.DatabaseFileService;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InputStreamApi {

    @Autowired
    private DatabaseFileService fileStorageService;

    @PostMapping("/uploadFileInput")
    public Response uploadFileInput(@FormDataParam("file") final InputStream file) throws IOException {
        DatabaseFile fileName = fileStorageService.storeFileInputStream(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getMediaId().toString())
                .toUriString();
        return new Response(fileName.getMediaId(),fileName.getFileName(), fileDownloadUri,
               "",12,fileName.getData());
    }

//    @PostMapping("/uploadMultipleFiles")
//    public List< Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }
}
