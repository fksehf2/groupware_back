package jims.eqp.mgmt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jims.eqp.mgmt.service.EqpDto;
import jims.eqp.mgmt.service.EqpMgmtService;

/**
 * 
 * <pre>
 * 1. 클래스명 : EqpMgmtServiceImpl.java
 * 2. 작성일 : 2021. 7. 19.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 관리 impl
 * </pre>
 */
@Service("eqpMgmtService")
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class EqpMgmtServiceImpl extends EgovAbstractServiceImpl implements	EqpMgmtService{
	
	@Resource(name="eqpMgmtDAO")
	private EqpMgmtDAO eqpMgmtDAO;

	/**
	 * 
	 * <pre>
	 * 1. 클래스명 : regEqpMgmtRDtl.java
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 등록 impl
	 * </pre>
	 */
	public void regEqpMgmtRDtl(HashMap<String, String> map) throws Exception{
		
		eqpMgmtDAO.regEqpMgmtRDtl(map);
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
	public void updEqpMgmtUDtl(HashMap<String, String> map) throws Exception {
		eqpMgmtDAO.updEqpMgmtUDtl(map);
		
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
	public void delEqpMgmtUDtl(HashMap<String, String> map) throws Exception {
		eqpMgmtDAO.delEqpMgmtUDtl(map);
		
	}


	@Override
	public List getEqpList(int offset, int limit) throws Exception {
		
		return eqpMgmtDAO.getEqpList(offset,limit);
	}


	@Override
	public List getCode(String cdId) throws Exception {
		
		return eqpMgmtDAO.getErpCode(cdId);
	}


	@Override
	public int getErpTotCnt() throws Exception {
		return eqpMgmtDAO.getErpTotCnt();
	}


	@Override
	public List erpDtl(String eqpSno) throws Exception {
			return eqpMgmtDAO.erpDtl(eqpSno);
	}
}

