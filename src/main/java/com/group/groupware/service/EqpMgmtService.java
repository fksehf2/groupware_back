package com.group.groupware.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EqpMgmtService {
    /**
     *
     * <pre>
     * 1. 메소드명 : getEqpList
     * 2. 작성일 : 2024. 2. 29.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 조회 service
     * </pre>
     * @param
     * @throws Exception
     */
    List getEqpList (Map<String, Object> params, Integer offset,
                     Integer perPageNum) throws Exception;

    List getCode(String cdId) throws Exception;

    int getErpTotCnt(Map<String, Object> params, Integer offset,
                     Integer perPageNum) throws Exception;

    List erpDtl(String eqpSno) throws Exception;

    List popList (Map<String, Object> params, Integer offset,
                  Integer perPageNum) throws Exception;

    int getPopListCnt(Map<String, Object> params, Integer offset,
                      Integer perPageNum) throws Exception;



    /**
     *
     * <pre>
     * 1. 메소드명 : regEqpMgmtRDtl
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 관리 등록 service
     * </pre>
     * @param map
     * @throws Exception
     */
    void regEqpMgmtRDtl(HashMap<String, Object> map)  throws Exception;
//
    /**
     *
     * <pre>
     * 1. 메소드명 : updEqpMgmtUDtl
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 관리 수정 service
     * </pre>
     * @param map
     * @throws Exception
     */
    void updEqpMgmtUDtl(HashMap<String, Object> params)  throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : delEqpMgmtUDtl
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 관리 삭제 service
     * </pre>
     * @param map
     * @throws Exception
     */
    void delEqpMgmtUDtl(String eqpSno)  throws Exception;
}
