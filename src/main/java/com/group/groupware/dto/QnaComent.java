package com.group.groupware.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class QnaComent {

    private int PUTUP_SNO;
    private int COMT;
    private String USER_ID;
    private String COMT_CNTS;
    private String USE_YN;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date REG_DT;
    private String REGR_ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date UPD_DT;
    private String UPDR_ID;

    @Override
    public String toString() {
        return super.toString();
    }
}
