package jims.eqp.mgmt.service;

import java.util.HashMap;
import java.util.List;

import frame.flyt.login.service.FLytLoginVO;



/**
 * 
 * <pre>
 * 1. 클래스명 : EqpMgmtService.java
 * 2. 작성일 : 2021. 7. 19.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 관리 service
 * </pre>
 */
@SuppressWarnings("rawtypes")
public interface EqpMgmtService {


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
	void regEqpMgmtRDtl(HashMap<String, String> map)  throws Exception;
	
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
	void updEqpMgmtUDtl(HashMap<String, String> map)  throws Exception;
	
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
	void delEqpMgmtUDtl(HashMap<String, String> map)  throws Exception;
	
	List getEqpList (int offset, int limit) throws Exception;

	List getCode(String cdId) throws Exception;
	
	int getErpTotCnt() throws Exception;
	
	List erpDtl(String eqpSno) throws Exception;

	
}

