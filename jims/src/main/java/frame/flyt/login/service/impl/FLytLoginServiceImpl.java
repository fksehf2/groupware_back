package frame.flyt.login.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import frame.flyt.login.service.FLytLoginService;
import frame.flyt.login.service.FLytLoginVO;

/**
 * @Class Name : LoginServiceImpl.java
 * @Description : Login Business Implement class
 * @Modification Information
 *
 * @author 우성택
 * @since 2014.09.03
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Service("fLytLoginService")
public class FLytLoginServiceImpl extends EgovAbstractServiceImpl implements FLytLoginService {
	
	protected Logger log = LoggerFactory.getLogger(FLytLoginServiceImpl.class);
	
    @Resource(name="fLytLoginDAO")
    private FLytLoginDAO fLytLoginDAO;
    
    //@Resource(name = "comService")
	//private ComService comService;
    
   
    /**
  	 * 아이디 중복 체크
  	 * @param 아이디
  	 * @return 아이디 중복 정보
  	 * @exception Exception
  	 */
  /*	public List checkId(mainVO iupvo) throws Exception {
          return mainDAO.checkId(iupvo);
      }*/
  	 public int checkId(FLytLoginVO vo) {
  		 return fLytLoginDAO.checkId(vo);
  	 }	
  	 
  	 
    /**
	 * 일반 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public FLytLoginVO actionLogin(FLytLoginVO vo) throws Exception {
    	
    	FLytLoginVO loginVO = fLytLoginDAO.actionLogin(vo);
    	return loginVO;
    }
    
    /*
    *//**
	 * 관리자 사용자관리 로그인을 처리한다
	 * @param vo LoginVO
	 * @return LoginVO
	 * @exception Exception
	 */
    public FLytLoginVO actionAdminLogin(FLytLoginVO vo) throws Exception {
    	FLytLoginVO loginVO = fLytLoginDAO.actionAdminLogin(vo);
    	return loginVO;
    }
    
    /**
	 * 비밀번호 초기화 세팅을 한다.
	 * @param vo - 회원 정보가 담긴 VO
	 * @return
	 * @exception Exception
	 *//*
	public void findPw(LoginVO vo) throws Exception {
		fLytLoginDAO.findPw(vo);
    }
	
    	

	*//**
     * 아이디 찾기
     * @param 조회할 정보가 담긴 Map
     * @return 아이디
     * @exception Exception
     *//*
    public String ajaxfindId(Map map) throws Exception {
    	
    	String result = fLytLoginDAO.ajaxfindId(map);
    	return result;
    	
    } 
    
    *//**
	 * 접속이력을 남긴다.
	 * @param vo - LoginVO
	 * @return
	 * @exception Exception
	 */
    public void insertConctHist(Map map) throws Exception {
    	
    	fLytLoginDAO.insertConctHist(map);

    	
    } 
    
    /**
     * 비밀번호 변경주기를 체크한다.
     * @param vo - LoginVO
     * @return
     * @throws Exception
     */
    public String checkPwdChng(FLytLoginVO vo) throws Exception{
    	return fLytLoginDAO.checkPwdChng(vo);
    }
    
	/**
	 * 2차 로그인 인증정보 sms 발송 및 사용자 테이블에 저장
	 * @param map
	 * @return
	 * @throws Exception
	 *//*
    public void insertLoginSms(Map commandMap) throws Exception{
    	
    	//sms 인증을 위한 랜덤 번호 생성
        Random generator = new Random();        
        int smsNum;
        smsNum= generator.nextInt(999999);
        
        String Sid = (String) commandMap.get("sid");
        String serverType = EgovProperties.getProperty("Globals.serverType");
        
        if("Admin".equals(Sid) || !"REAL".equals(serverType)){
        	//Admin 계정의 경우 인증번호는 문자없이 123456으로 처리하며 향 후 필요시 인증번호 생성 후 SMS 발송
        	smsNum = 123456;
        }
        
        commandMap.put("sCertNum", smsNum);
        
        log.debug("Form 0 to 999999: " + smsNum);
        
        //SMS 발송 
        //사용자에게 인증 SMS 발송
        if(!"Admin".equals(Sid)){
	        //commandMap.put("sqlQueryId", "fLytLoginDAO.loginSmsSend");
	        //comService.insertCommonQuery(commandMap);
        }
        
        //사용자 테이블에 인증번호 저장
        //commandMap.put("sqlQueryId", "fLytLoginDAO.loginUserSms");
		//comService.insertCommonQuery(commandMap);
    	
    }*/
}
