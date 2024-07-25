package com.group.groupware.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class QnaBoard {
    private int PUTUP_SNO;
    private String USER_GB;
    private String TITLE;
    private String CNTS; //내용
    private String ATCH_FILE_ID;
    private int REG_DT; //등록일
    private int SELECT_NUM; //조회수
    private String REGR_ID;
    private String REGR_NM;
    private int TOT_CNT;
}
