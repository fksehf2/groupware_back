package com.group.groupware.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EqpMgmtService {
    /**
     *
     * <pre>
     * 1. 메소드명 : getEqpList
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 조회 service
     * </pre>
     * @param
     * @throws Exception
     */
    List getEqpList (Map<String, Object> params, Integer offset,
                     Integer perPageNum) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : getCode
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 코드 조회 service
     * </pre>
     * @param
     * @throws Exception
     */
    List getCode(String cdId) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : getErpTotCnt
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 목록 카운트 service
     * </pre>
     * @param params
     * @throws Exception
     */
    int getErpTotCnt(Map<String, Object> params, Integer offset,
                     Integer perPageNum) throws Exception;
    /**
     *
     * <pre>
     * 1. 메소드명 : erpDtl
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 상세 조회 service
     * </pre>
     * @param eqpSno
     * @throws Exception
     */
    List erpDtl(String eqpSno) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : popList
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 상세 조회 service
     * </pre>
     * @param params
     * @param offset
     * @param perPageNum
     * @throws Exception
     */
    List popList (Map<String, Object> params, Integer offset,
                  Integer perPageNum) throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : getPopListCnt
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 팝업 목록 카운트 service
     * </pre>
     * @param params
     * @param offset
     * @param perPageNum
     * @throws Exception
     */
    int getPopListCnt(Map<String, Object> params, Integer offset,
                      Integer perPageNum) throws Exception;


    /**
     *
     * <pre>
     * 1. 메소드명 : regEqpMgmtRDtl
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
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
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 수정 service
     * </pre>
     * @param params
     * @throws Exception
     */
    void updEqpMgmtUDtl(HashMap<String, Object> params)  throws Exception;

    /**
     *
     * <pre>
     * 1. 메소드명 : delEqpMgmtUDtl
     * 2. 작성일 : 2024. 4. 23.
     * 3. 작성자 : seran
     * 4. 설명 : 장비 관리 삭제 service
     * </pre>
     * @param eqpSno
     * @throws Exception
     */
    void delEqpMgmtUDtl(String eqpSno)  throws Exception;
}
