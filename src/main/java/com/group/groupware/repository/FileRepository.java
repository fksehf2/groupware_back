package com.group.groupware.repository;

import com.group.groupware.dto.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long>{

    List<FileEntity> findByFileTyp(int fileTyp);

}
