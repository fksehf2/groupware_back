package com.group.groupware.controller;

import com.group.groupware.dto.FileEntity;
import com.group.groupware.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final FileRepository fileRepository;

    private final String dir ="/Users/Rans/Downloads/images";
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/image")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List<FileEntity> findAllImages(@RequestParam(defaultValue = "1") int num ){
        log.info("fileController");

        return fileRepository.findByFileTyp(num); }

    @PostMapping("/upload")
    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestPart("image") MultipartFile image,
                                         @RequestPart("fileData") FileEntity param) {
        // 디렉토리가 존재하지 않으면 생성
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        System.out.println("directory = " + dir);
        try {
            //중복 방지를 위해 random uuid 생성 후 파일명으로 저장
            String uuid = UUID.randomUUID().toString();
            String cutId = uuid.substring(0,8);
            String fileName = cutId + "_" + image.getOriginalFilename();
            Path path = Paths.get(dir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            //게시판별 구분을 위해 fileType활용
            int fileType= param.getFileTyp();

            FileEntity img = new FileEntity();
            img.setFILE_NM(fileName);
            img.setREG_DT(String.valueOf(LocalDateTime.now()));
            img.setDescription(param.getDescription());
            img.setFileTyp(fileType);
            img.setATCH_FILE_ID("img_"+fileType+"_"+cutId);

            System.out.println("FileController.uploadImage" + img) ;
            fileRepository.save(img);

            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

}
