package com.group.groupware.service;

import com.group.groupware.repository.EqpMgmtDAO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EqpMgmtServiceImpl implements EqpMgmtService{
    @Resource(name="eqpMgmtDAO")
    private EqpMgmtDAO eqpMgmtDao;

    /**
     *
     * <pre>
     * 1. 클래스명 : regEqpMgmtRDtl.java
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 관리 등록 impl
     * </pre>
     */
    public void regEqpMgmtRDtl(HashMap<String, Object> map) throws Exception{

        eqpMgmtDao.regEqpMgmtRDtl(map);
    }


    /**
     *
     * <pre>
     * 1. 클래스명 : updEqpMgmtUDtl.java
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 관리 수정 impl
     * </pre>
     */
    public void updEqpMgmtUDtl(HashMap<String, Object> params) throws Exception {
        eqpMgmtDao.updEqpMgmtUDtl(params);

    }

    /**
     *
     * <pre>
     * 1. 클래스명 : delEqpMgmtUDtl.java
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 관리 삭제 impl
     * </pre>
     */
    public void delEqpMgmtUDtl(String eqpSno) throws Exception {
        eqpMgmtDao.delEqpMgmtUDtl(eqpSno);

    }


    @Override
    public List getEqpList(Map<String, Object> params, Integer offset,
                           Integer perPageNum) throws Exception {

        return eqpMgmtDao.getEqpList(params, offset, perPageNum);
    }


    @Override
    public List getCode(String cdId) throws Exception {

        return eqpMgmtDao.getErpCode(cdId);
    }


    @Override
    public int getErpTotCnt(Map<String, Object> params, Integer offset,
                            Integer perPageNum) throws Exception {
        return eqpMgmtDao.getErpTotCnt(params, offset, perPageNum);
    }


    @Override
    public List erpDtl(String eqpSno) throws Exception {
        return eqpMgmtDao.erpDtl(eqpSno);
    }


    @Override
    public int getPopListCnt(Map<String, Object> params, Integer offset, Integer perPageNum) throws Exception {
        return 0;
    }

    @Override
    public List popList(Map<String, Object> params, Integer offset,
                        Integer perPageNum) throws Exception {
        return eqpMgmtDao.popList(params, offset, perPageNum);
    }
}
