package com.group.groupware.controller;

import com.group.groupware.dto.FileEntity;
import com.group.groupware.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);



    @GetMapping("/images")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<FileEntity> findAllImages(@RequestParam(defaultValue = "1") int num ) throws Exception{
        log.info("fileController");
        return fileService.findAllImages(num);
    }

    @PostMapping("/images")
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
