package com.discovery.voyager.aplication.service.implementation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import com.discovery.voyager.aplication.config.FileStorageProperties;
import com.discovery.voyager.aplication.exception.InternalApplicationException;
import com.discovery.voyager.aplication.model.entity.ImagesData;
import com.discovery.voyager.aplication.repository.ImagesDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ImageServiceImpl{

    private final Path fileStorageLocation;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ImagesDataRepository imagesDataRepository;

    @Autowired
    public ImageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new InternalApplicationException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public ImagesData createImage(MultipartFile file) throws IOException{
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ImagesData entity =imagesDataRepository.findByName(fileName);

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new InternalApplicationException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            if(entity ==null){
                entity= new ImagesData();
                entity.setCreationDate(new Date());
            }

            entity.setName(fileName);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/file/downloadFile/")
            .path(fileName)
            .toUriString();

            String fileViewUri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/api/file/view/image/")
            .path(fileName)
            .toUriString();

            entity.setFileDownloadUri(fileDownloadUri);
            entity.setFileViewUri(fileViewUri);
            entity.setFileType(file.getContentType());
            entity.setSize(file.getSize());
            entity.setUpdateDate(new Date());
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            imagesDataRepository.save(entity);
        } catch (IOException ex) {
            throw new InternalApplicationException("Could not store file " + fileName + ". Please try again!", ex);
        }
        return entity;
    }

    public Resource loadFileAsResource(String fileName) {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource = resourceLoader.getResource(filePath.toUri().toString());
        if (resource.exists()) {
            return resource;
        } else {
            throw new InternalApplicationException("File not found " + fileName);
        }
    }

    public ImagesData findbyId(long id){
        return imagesDataRepository.findById(id).get();
    }

}