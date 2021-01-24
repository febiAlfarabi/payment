package com.numeralasia.payment.component;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileStorage {
    Logger logger = LoggerFactory.getLogger(FileStorage.class);

    public String storeFile(String folder, @Nullable String subfolder, MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Path targetDir = null ;
            Path targetFile = null ;
            if(subfolder!=null){
                targetDir = Paths.get(folder).resolve(subfolder);
                targetFile = Paths.get(folder).resolve(subfolder).resolve(fileName);
            }else{
                targetDir = Paths.get(folder);
                targetFile = Paths.get(folder).resolve(fileName);
            }
            if(!Files.exists(targetDir)){
                Files.createDirectories(targetDir);
            }
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public String storeFile(String folder, @Nullable String subfolder, String renaming, MultipartFile file) throws Exception {
        // Normalize file name
        String extension = FilenameUtils.getExtension(file.getName());
        if(extension==null || extension.isEmpty()){
            String contents[] =  StringUtils.split(file.getContentType(), "/");
            extension = contents.length>1?contents[1]:"";
        }
        if(extension==null || extension.isEmpty()){
            // Force to hardcode as image/jpeg
            extension = "jpg";
        }

        String fileName = StringUtils.cleanPath(renaming)+"."+extension;
        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
        }
        // Copy file to the target location (Replacing existing file with the same name)
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Path targetDir = null ;
        Path targetFile = null ;
        if(subfolder!=null){
            targetDir = Paths.get(folder).resolve(subfolder);
            targetFile = Paths.get(folder).resolve(subfolder).resolve(fileName);
        }else{
            targetDir = Paths.get(folder);
            targetFile = Paths.get(folder).resolve(fileName);
        }
        if(!Files.exists(targetDir)){
            Files.createDirectories(targetDir);
        }
        Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }


    public String moveFile(String from, String to) throws Exception{
        Path target = Paths.get(to) ;
        if(!Files.exists(target)){
            Files.createDirectories(target);
        }
        Files.move(Paths.get(from), target, StandardCopyOption.REPLACE_EXISTING);
        return to ;
    }

    public Resource loadFileAsResource(String path) {
        try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Path filePath = Paths.get(path).toAbsolutePath().normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + path);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + path, ex);
        }
    }
}
