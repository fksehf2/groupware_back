package com.group.groupware.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@SuppressWarnings({"rawtypes","unchecked"})
@Mapper
@Repository
public class EqpMgmtDAO {
    @Autowired
    SqlSession session;

      public void regEqpMgmtRDtl(HashMap<String, Object> map) throws Exception {
          session.insert("eqpMgmtDAO.regEqpMgmtRDtl", map);

        }


        public void updEqpMgmtUDtl(HashMap<String, Object> map) throws Exception {
            session.update("eqpMgmtDAO.updEqpMgmtUDtl", map);

        }

        public void delEqpMgmtUDtl(String eqpSno) throws Exception {
            session.update("eqpMgmtDAO.delEqpMgmtUDtl", eqpSno);

        }

        public List getEqpList(Map<String, Object> params, Integer offset,
                               Integer perPageNum) throws Exception {
//		Map<String, Integer> params = new HashMap<>();
            params.put("offset", offset);
            params.put("perPageNum", perPageNum);
            return session.selectList("eqpMgmtDAO.eqpMgmtMList2", params);
        }

        public List getErpCode(String cdId) throws Exception {
            return session.selectList("eqpMgmtDAO.getErpCode", cdId);

        }

        public int getErpTotCnt(Map<String, Object> params, Integer offset,
                                Integer perPageNum) throws Exception {
            params.put("offset", offset);
            params.put("perPageNum", perPageNum);
            return session.selectOne("eqpMgmtDAO.eqpMgmtMListTotCnt", params);
        }

        public List erpDtl(String eqpSno) throws Exception{
            return session.selectList("eqpMgmtDAO.queryEqpMgmtUDtl", eqpSno);
        }

        public List popList(Map<String, Object>params, Integer offset,
                            Integer perPageNum) throws Exception{
            params.put("offset", offset);
            params.put("perPageNum", perPageNum);
            return session.selectList("eqpMgmtDAO.eqpMgmtMListPop", params);
        }
}
