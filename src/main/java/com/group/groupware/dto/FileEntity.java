package com.group.groupware.dto;

import jakarta.persistence.*;
import lombok.*;


@Getter // getter 메소드 생성
@Builder // 빌더를 사용할 수 있게 함
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="ATCH_FILE") // 테이블 명을 작성
public class FileEntity {
    @Id // primary key임을 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ATCH_FILE_ID;

    @Column
    private int FILE_TYP;

    @Column
    private String FILE_NM;

    @Column
    private String USE_YN;


    @Column
    private String REGR_ID;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String REG_DT;


}
