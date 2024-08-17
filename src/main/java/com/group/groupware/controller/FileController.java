package com.group.groupware.controller;

import com.group.groupware.dto.FileEntity;
import com.group.groupware.repository.FileRepository;
import com.group.groupware.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);



    @GetMapping("/image")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<FileEntity> findAllImages(@RequestParam(defaultValue = "1") int num ) throws Exception{
        log.info("fileController");
        return fileService.findAllImages(num);
    }

    @PostMapping("/upload")
    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile image,
                                         @RequestPart("fileData") FileEntity param) {

        try {
             fileService.saveFile(image, param);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

}
