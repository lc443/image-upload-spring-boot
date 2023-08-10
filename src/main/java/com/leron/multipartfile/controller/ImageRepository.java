package com.leron.multipartfile.controller;

import com.leron.multipartfile.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageRepository {

    private final ImageService service;

    public ImageRepository(ImageService service) {
        this.service = service;
    }

    @PostMapping()
   private ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadFile(file));
    }

    @GetMapping("/{fileName}")
    private  ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] image = service.downloadFile(fileName);

        return ResponseEntity.ok().contentType(MediaType.valueOf("image/heif"))
                .body(image);
    }

}
