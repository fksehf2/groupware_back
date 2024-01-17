package frame.flyt.login.service;

import java.util.Map;


/**
 * @Class Name : LoginService.java
 * @Description : Login Business class
 * @Modification Information
 *
 * @author 우성택
 * @since 2014.09.03
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public interface FLytLoginService {

	/**
	 * 아이디 중복 체크
	 * @param vo - mainVO
	 * @return 아이디 중복 체크
	 * @exception Exception
	 */
	
	int checkId(FLytLoginVO paramVO) throws Exception;	

	
	/**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
	
    FLytLoginVO actionLogin(FLytLoginVO vo) throws Exception;
    /*    
	*//**
	 * 관리자 사용자관리 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    FLytLoginVO actionAdminLogin(FLytLoginVO vo) throws Exception;
    
	
	/**
	 * 비밀번호 초기화 세팅을 한다.
	 * @param vo - mainVO
	 * @return
	 * @exception Exception
	 *//*
	void findPw(LoginVO iupvo) throws Exception;
	
	*//**
	 * 아이디 찾기
	 * @param vo - mainVO
	 * @return 아이디
	 * @exception Exception
	 *//*
	String ajaxfindId(Map map) throws Exception;

	*//**
	 * 접속이력을 남긴다.
	 * @param vo - LoginVO
	 * @return
	 * @exception Exception
	 */
	void insertConctHist(Map map) throws Exception;
	
	/**
	 * 비밀번호 변경주기를 체크한다.
	 * @param vo - LoginVO
	 * @return
	 * @throws Exception
	 */
	String checkPwdChng(FLytLoginVO vo) throws Exception;
	
	/**
	 * 2차 로그인 인증정보 sms 발송 및 사용자 테이블에 저장
	 * @param map
	 * @return
	 * @throws Exception
	 *//*
	
	void insertLoginSms(Map map) throws Exception;
*/
}
