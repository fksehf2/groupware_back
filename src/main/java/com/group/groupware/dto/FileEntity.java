package com.group.groupware.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@Getter // getter 메소드 생성
@Setter
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor
//        (access = AccessLevel.PROTECTED)
@Entity(name="ATCH_FILE") // 테이블 명을 작성
@ToString
public class FileEntity {
    @Id // primary key임을 명시
    private String ATCH_FILE_ID;

    @Column(name = "FILE_TYP")
    private int fileTyp;

    @Column(length = 1000)
    private String FILE_NM;

    @Column
    private String USE_YN;

    @Column
    private String REGR_ID;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String REG_DT;

    @Column(name = "Description")
    private String Description;

}
