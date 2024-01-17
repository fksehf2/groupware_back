package jims.eqp.user.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jims.eqp.user.service.impl.EqpUserDAO;
import jims.eqp.user.service.EqpUserService;

/**
 * 
 * <pre>
 * 1. 클래스명 : EqpUserServiceImpl.java
 * 2. 작성일 : 2021. 7. 22.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 사용자 관리 impl
 * </pre>
 */
@Service("eqpUserService")
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class EqpUserServiceImpl extends EgovAbstractServiceImpl implements	EqpUserService{

	@Resource(name="eqpUserDAO")
	private EqpUserDAO eqpUserDAO;
	

/**
 * 
 * <pre>
 * 1. 클래스명 : regEqpUserRDtl
 * 2. 작성일 : 2021. 7. 22.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 사용자 관리 등록 impl
 * </pre>
 */
	@Override
	public void regEqpUserRDtl(HashMap<String, String> map) throws Exception {
		eqpUserDAO.regEqpUserRDtl(map);
	}


/**
 * 
 * <pre>
 * 1. 클래스명 : updEqpUserUDtl
 * 2. 작성일 : 2021. 7. 22.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 사용자 관리 수정 impl
 * </pre>
 */
	@Override
	public void updEqpUserUDtl(HashMap<String, String> map) throws Exception {
		eqpUserDAO.updEqpUserUDtl(map);
	}

	/**
	 * 
	 * <pre>
	 * 1. 클래스명 : delEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 22.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 관리 삭제 impl
	 * </pre>
	 */
	@Override
	public void delEqpUserUDtl(HashMap<String, String> map) throws Exception {
		eqpUserDAO.delEqpUserUDtl(map);
	}

	/**
	 * 
	 * <pre>
	 * 1. 클래스명 : updEqpUserBiz
	 * 2. 작성일 : 2021. 7. 22.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 -> 현재 사업명 업데이트
	 * </pre>
	 */
	@Override
	public void updEqpUserBiz(HashMap<String, String> map) throws Exception {
		eqpUserDAO.updEqpUserBiz(map);
	}
	
}