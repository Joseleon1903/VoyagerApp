package com.discovery.voyager.aplication.service.controller;

import java.io.IOException;
import com.discovery.voyager.aplication.service.implementation.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class ImagesRestController {

    @Autowired
    private ImageServiceImpl imageServiceImpl;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            imageServiceImpl.createImage(file);
        }catch(IOException ex){
          System.out.println("Error: "+ ex.getMessage());
        }
        return "successifully upload!";
    }


    @GetMapping("/downloadFile/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {

        String contentType = "application/octet-stream";
        Resource resource = imageServiceImpl.loadFileAsResource(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping(value = "/view/image/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> viewImage(@PathVariable("fileName") String fileName) throws IOException {
        Resource in = imageServiceImpl.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .contentLength(in.contentLength())
                .contentType(MediaType.parseMediaType( MediaType.IMAGE_JPEG_VALUE))
                .body(new InputStreamResource(in.getInputStream()));
    }

}