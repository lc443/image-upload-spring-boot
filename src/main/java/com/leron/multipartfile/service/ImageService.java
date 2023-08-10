package com.leron.multipartfile.service;

import com.leron.multipartfile.ImageUtils;
import com.leron.multipartfile.models.ImageData;
import com.leron.multipartfile.repository.ImageStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private final ImageStorageRepository repository;


    public ImageService(ImageStorageRepository repository) {
        this.repository = repository;
    }

    public ImageData uploadFile(MultipartFile file) throws IOException {
       return repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressData(file.getBytes()))
                .build());
    }

    public byte[] downloadFile(String fileName) {
        Optional<ImageData> file =  repository.findByName(fileName);
        return ImageUtils.deCompressFile(file.get().getImageData());
    }


}
