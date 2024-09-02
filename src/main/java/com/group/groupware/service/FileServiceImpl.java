package com.group.groupware.service;

import com.group.groupware.dto.FileEntity;
import com.group.groupware.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl  implements FileService{

    @Autowired
    private FileRepository fileRepository;

    private final String dir ="/Users/Rans/Downloads/images";

    @Override
    public List<FileEntity> findAllImages(int num) throws Exception {
        return fileRepository.findByFileTyp(num);
    }

    @Override
    public void saveFile(MultipartFile image, FileEntity param) throws Exception {
            // 디렉토리가 존재하지 않으면 생성
            File directory = new File(dir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try{
            // 중복 방지를 위해 random uuid 생성 후 파일명으로 저장
            String uuid = UUID.randomUUID().toString();
            String cutId = uuid.substring(0, 8);
            String fileName = cutId + "_" + image.getOriginalFilename();
            Path path = Paths.get(dir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, image.getBytes());

            // 게시판별 구분을 위해 fileType 활용
            int fileType = param.getFileTyp();

            FileEntity img = new FileEntity();
            img.setFILE_NM(fileName);
            img.setREG_DT(String.valueOf(LocalDateTime.now()));
            img.setDescription(param.getDescription());
            img.setFileTyp(fileType);
            img.setATCH_FILE_ID("img_" + fileType + "_" + cutId);

            // 파일 엔티티를 데이터베이스에 저장
            fileRepository.flush(img);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

