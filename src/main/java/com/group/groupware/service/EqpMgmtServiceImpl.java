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
     * 장비 관리 등록 impl
     * </pre>
     */
    public void regEqpMgmtRDtl(HashMap<String, Object> map) throws Exception{

        eqpMgmtDao.regEqpMgmtRDtl(map);
    }


    /**
     * 장비 관리 수정 impl
     */
    public void updEqpMgmtUDtl(HashMap<String, Object> params) throws Exception {
        eqpMgmtDao.updEqpMgmtUDtl(params);

    }

    /**
     * 장비 관리 삭제 impl
     */
    public void delEqpMgmtUDtl(String eqpSno) throws Exception {
        eqpMgmtDao.delEqpMgmtUDtl(eqpSno);

    }

    /**
     * 장비 관리 조회 impl
     */
    @Override
    public List getEqpList(Map<String, Object> params, Integer offset,
                           Integer perPageNum) throws Exception {

        return eqpMgmtDao.getEqpList(params, offset, perPageNum);
    }


    /**
     * 장비 관리 코드 조회 impl
     */
    @Override
    public List getCode(String cdId) throws Exception {

        return eqpMgmtDao.getErpCode(cdId);
    }


    /**
     * 장비 관리 카운트 조회 impl
     */
    @Override
    public int getErpTotCnt(Map<String, Object> params, Integer offset,
                            Integer perPageNum) throws Exception {
        return eqpMgmtDao.getErpTotCnt(params, offset, perPageNum);
    }


    /**
     * 장비 관리 상세 조회 impl
     */
    @Override
    public List erpDtl(String eqpSno) throws Exception {
        return eqpMgmtDao.erpDtl(eqpSno);
    }


    @Override
    public int getPopListCnt(Map<String, Object> params, Integer offset, Integer perPageNum) throws Exception {
        return 0;
    }


    /**
     * 장비 관리 팝업 조회 impl
     */
    @Override
    public List popList(Map<String, Object> params, Integer offset,
                        Integer perPageNum) throws Exception {
        return eqpMgmtDao.popList(params, offset, perPageNum);
    }
}
