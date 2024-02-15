package jims.eqp.mgmt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import jims.eqp.mgmt.service.EqpDto;

/**
 * 
 * <pre>
 * 1. 클래스명 : EqpMgmtDAO.java
 * 2. 작성일 : 2021. 7. 19.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 관리 DAO
 * </pre>
 */
@Repository("eqpMgmtDAO")
@SuppressWarnings({"rawtypes","unchecked"})
public class EqpMgmtDAO extends EgovComAbstractDAO {

	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regEqpMgmtRDtl
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 등록 DAO
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void regEqpMgmtRDtl(HashMap<String, String> map) throws Exception {
		insert("eqpMgmtDAO.regEqpMgmtRDtl", map);

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEqpMgmtUDtl
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 수정 DAO
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void updEqpMgmtUDtl(HashMap<String, String> map) throws Exception {
		update("eqpMgmtDAO.updEqpMgmtUDtl", map);

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delEqpMgmtUDtl
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 삭제 DAO
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void delEqpMgmtUDtl(HashMap<String, String> map) throws Exception {
		update("eqpMgmtDAO.delEqpMgmtUDtl", map);

	}
	
	public List getEqpList(Map<String, Object> params, Integer offset,
           Integer perPageNum) throws Exception {
//		Map<String, Integer> params = new HashMap<>();
		params.put("offset", offset);
		params.put("perPageNum", perPageNum);
		return selectList("eqpMgmtDAO.eqpMgmtMList2", params);
	}
	
	public List getErpCode(String cdId) throws Exception {
		return selectList("eqpMgmtDAO.getErpCode", cdId);
				
	}
	
	public int getErpTotCnt(Map<String, Object> params, Integer offset,
            Integer perPageNum) throws Exception {
		params.put("offset", offset);
		params.put("perPageNum", perPageNum);
		return selectOne("eqpMgmtDAO.eqpMgmtMListTotCnt", params);
	}
	
	public List erpDtl(String eqpSno) throws Exception{
		return selectList("eqpMgmtDAO.queryEqpMgmtUDtl", eqpSno);
	}
	
}

