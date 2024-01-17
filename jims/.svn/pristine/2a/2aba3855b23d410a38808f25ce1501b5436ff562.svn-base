package frame.flyt.login.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import frame.flyt.login.service.FLytLoginVO;

/**
 * @Class Name : LoginDAO.java
 * @Description : Login DAO Class
 * @Modification Information
 *
 * @author 우성택
 * @since 2014.09.03
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

@Repository("fLytLoginDAO")
public class FLytLoginDAO extends EgovComAbstractDAO {
	
	protected Logger log = LoggerFactory.getLogger(FLytLoginDAO.class);
	
		
	 /**
     * 아이디 중복 체크
     * @param 아이디
     * @return 아이디 중복 정보
     * @exception Exception
     */   
    public int checkId(FLytLoginVO vo) {
    	return selectOne("fLytLoginDAO.checkId", vo);
    }
    
    
	/**
	 * ID, PASS로 사용자 조회한다.
	 * @param vo LoginVO
	 * @return EgovMap
	 * @exception Exception
	 */
    public FLytLoginVO actionLogin(FLytLoginVO vo) throws Exception {
    	return (FLytLoginVO)selectByPk("fLytLoginDAO.actionLogin", vo);
    }
   
    /*
	*//**
	 * ID로 사용자 조회한다.
	 * @param vo LoginVO
	 * @return EgovMap
	 * @exception Exception
	 */
    public FLytLoginVO actionAdminLogin(FLytLoginVO vo) throws Exception {
    	return (FLytLoginVO)selectByPk("fLytLoginDAO.actionAdminLogin", vo);
    }
    
    public void findPw(FLytLoginVO vo) throws Exception {
    	
    }
    
   
    /**
     * 아이디찾기
     * @param 조회할 정보가 담긴 Map
     * @return 아이디
     * @exception Exception
     *//*
    public String ajaxfindId(Map map) throws Exception {
 	    return (String)selectOne("fLytLoginDAO.ajaxfindId", map);
    }
    
    *//**
	 * 접속이력을 남긴다.
	 * @param vo - LoginVO
	 * @return
	 * @exception Exception
	 */
    public int insertConctHist(Map map) throws Exception {
 	    return insert("fLytLoginDAO.insertConctHist", map);
    }
    
    /**
     * 비밀번호 변경주기를 체크한다.
     * @param vo - LoginVO
     * @return
     * @throws Exception
     */
    public String checkPwdChng(FLytLoginVO vo) throws Exception{
    	return (String)selectOne("fLytLoginDAO.checkPwdChng", vo);
    }
}
