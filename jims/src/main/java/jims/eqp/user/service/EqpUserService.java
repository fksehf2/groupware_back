package jims.eqp.user.service;

import java.util.HashMap;

/**
 * 
 * <pre>
 * 1. 클래스명 : EqpUserService.java
 * 2. 작성일 : 2021. 7. 21.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 사용자 관리 service
 * </pre>
 */
@SuppressWarnings("rawtypes")
public interface EqpUserService {
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regEqpUserRDtl
	 * 2. 작성일 : 2021. 7. 21.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 등록 service
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void regEqpUserRDtl(HashMap<String, String> map)  throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 21.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 수정 service
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void updEqpUserUDtl(HashMap<String, String> map)  throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 21.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 삭제 service
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void delEqpUserUDtl(HashMap<String, String> map)  throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEqpUserBiz
	 * 2. 작성일 : 2021. 7. 22.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 -> 현재 사업명 업데이트
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void updEqpUserBiz(HashMap<String, String> map)  throws Exception;
}