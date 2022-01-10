package com.example.computershop.controller;

import com.example.computershop.entities.DatabaseFile;
import com.example.computershop.payload.Response;
import com.example.computershop.repository.DatabaseFileRepository;
import com.example.computershop.service.impl.DatabaseFileService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ImageController {

    @Autowired
    DatabaseFileRepository databaseFileRepository;

    @Autowired
    private DatabaseFileService fileStorageService;

    @GetMapping("/file")
    public String homepage() {
        return "upload-file";
    }

    @RequestMapping(value = {"/image"})
    public String listImage(Model model) throws UnsupportedEncodingException {
        List<DatabaseFile> all = databaseFileRepository.findAll();
        DatabaseFile data=all.get(1);
        byte[] imageDate= Base64.encodeBase64(data.getData());
        String base64Encoded = new String(imageDate, "UTF-8");
        model.addAttribute("image",base64Encoded);
        return "listImage";
    }
    @RequestMapping(value = {"/upload"},method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/api/file";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileStorageService.storeFile(file);
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        return "redirect:/api/file";
    }
}
