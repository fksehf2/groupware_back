package com.group.groupware.service;

import com.group.groupware.dto.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    List<FileEntity> findAllImages(int num) throws Exception;
    void saveFile(MultipartFile image, FileEntity param) throws Exception;
}
