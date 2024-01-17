package jims.eqp.user.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 
 * <pre>
 * 1. 클래스명 : EqpUserDAO.java
 * 2. 작성일 : 2021. 7. 20.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 사용자 관리 DAO
 * </pre>
 */
@Repository("eqpUserDAO")
@SuppressWarnings({"rawtypes","unchecked"})
public class EqpUserDAO extends EgovComAbstractDAO {
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regEqpUserRDtl
	 * 2. 작성일 : 2021. 7. 21.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 관리 등록
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void regEqpUserRDtl(HashMap<String, String> map) throws Exception {
		insert("eqpUserDAO.regEqpUserRDtl", map);

	}
	

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 21.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 관리 수정
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void updEqpUserUDtl(HashMap<String, String> map) throws Exception {
		update("eqpUserDAO.updEqpUserUDtl", map);

	}
	

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 21.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 관리 삭제
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void delEqpUserUDtl(HashMap<String, String> map) throws Exception {
		update("eqpUserDAO.delEqpUserUDtl", map);

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEqpUserBiz
	 * 2. 작성일 : 2021. 7. 22.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 -> 현재 사업명 업데이트
	 * </pre>
	 * @param map
	 */
	public void updEqpUserBiz(HashMap<String, String> map) {
		update("eqpUserDAO.updEqpUserBiz", map);
	}
	
}