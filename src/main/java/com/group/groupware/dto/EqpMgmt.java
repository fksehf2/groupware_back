package com.group.groupware.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EqpMgmt {

    String EQP_SNO;
    String EQP_BUY_DIV;
    String EQP_NM;
    String SR_NO;
    String EQP_TYP;
    String PURC_DT;
    String EXPR_DT;
    String GUAR_TRM;
    String MNFT_CO;
    String MDL_NM;
    String MNFT_NAT;
    int UNIT_AMT;
    int PURC_QTY;
    int SUM_AMT;
    int DSTB_QTY;
    String DEPR_PRID;
    String PAY_DIV;
    String CPU;
    String RAM;
    String HDD_VOL;
    String SSD_VOL;
    String MNTR_SIZE;
    String MNTR_RES;
    String GRAPHICS;
    String HLD_PLC;
    String REMARKS;
    String USE_YN;
    int REG_DT;
    String REGR_ID;
    int UPD_DT;
    String UPDR_ID;

    @Override
    public String toString() {
        return super.toString();
    }
}
