package com.group.groupware.controller;

import com.group.groupware.dto.FileEntity;
import com.group.groupware.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    private final FileRepository fileRepository;
    private final String path ="/Users/Rans/Downloads/images";
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/image")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<FileEntity> findAllImages() {
        log.info("fileController");

        return fileRepository.findAll(); }


}
