package frame.fsys.user.service;

import java.util.HashMap;
import frame.flyt.login.service.FLytLoginVO;


/**
 * @Class Name : UserService.java
 * @Description
 * @Modification Information
 *
 * @author 임영택
 * @since 2015.01.13
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@SuppressWarnings("rawtypes")
public interface FsysUserService {


	/**
	 * 내정보를 가져온다.
	 * @param vo - mainVO
	 * @return 내정보
	 * @exception Exception
	 */
	FLytLoginVO myInfoList(String userId) throws Exception;

	
	/**
	 * 비밀번호 오류 횟수 조회
	 * @param vo - LoginVO
	 * @return
	 * @throws Exception
	 */
	int getPwdErrCnt(FLytLoginVO vo) throws Exception;

	
	/**
	 * 비밀번호 오류 횟수 업데이트
	 * @param vo - LoginVO
	 * @throws Exception
	 */
	void updatePwdErrCnt(FLytLoginVO vo) throws Exception;
	
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regFsysUserRDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:17:54
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자관리 > 사용자 등록 처리
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void regFsysUserRDtl(HashMap<String, String> map)  throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysUserUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:17:54
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자관리 > 사용자 삭제 처리
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void delFsysUserUDtl(HashMap<String, String> map)  throws Exception;

	
	/**
	 * <pre>
	 * 1. 메소드명 : userInfoList
	 * 2. 작성일 : 2021. 4. 16. 오후 2:25:37
	 * 3. 작성자 : Eric
	 * 4. 설명 : 메인 사용자 수정에서 , 사용자 정보 조회
	 * </pre>
	 * @param userId
	 * @return
	 */
	
	FsysUserVO userInfoList(String userId);
	
}
