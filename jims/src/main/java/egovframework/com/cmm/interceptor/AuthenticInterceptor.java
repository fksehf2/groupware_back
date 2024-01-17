package egovframework.com.cmm.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.com.cmm.filter.HTMLTagFilterRequestWrapper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import frame.flyt.login.service.FLytLoginVO;
/**
 * 인증여부 체크 인터셉터
 * @author 공통서비스 개발팀 서준식
 * @since 2011.07.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2011.07.01  서준식          최초 생성
 *  2011.09.07  서준식          인증이 필요없는 URL을 패스하는 로직 추가
 *  2017.08.31  장동한          인증된 사용자 체크로직 변경 및 관리자 권한 체크 로직 추가 
 *  </pre>
 */


public class AuthenticInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private Environment environment;
	
	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticInterceptor.class);
	
	/** 관리자 접근 권한 패턴 목록 */
	private List<String> adminAuthPatternList;
	
	public List<String> getAdminAuthPatternList() {
		return adminAuthPatternList;
	}

	public void setAdminAuthPatternList(List<String> adminAuthPatternList) {
		this.adminAuthPatternList = adminAuthPatternList;
	}
	

	/**
	 * 인증된 사용자 여부로 인증 여부를 체크한다.
	 * 관리자 권한에 따라 접근 페이지 권한을 체크한다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("[AuthenticInterceptor is called]");
		}
		
		
		/* 
		ModifiableHttpServletRequest abc = new ModifiableHttpServletRequest(request);
		//HTMLTagFilterRequestWrapper cachingRequest = (HTMLTagFilterRequestWrapper) request;
		abc.setParameter("session_userid11", "test"); 
		abc.setParameter("__GUID__", UUID.randomUUID().toString()); 
		abc.setAttribute("session_userid11", "test"); 
		abc.setAttribute("__GUID__", UUID.randomUUID().toString()); 
		// GUID 추가 
		request = abc;
		 */
        
		FLytLoginVO loginVO = (FLytLoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		String webRoot = request.getRequestURL().toString().replace(request.getRequestURI(),"");
		webRoot = webRoot + request.getContextPath();

		//String server = EgovProperties.getProperty("Globals.serverType"); //request.getServerName();

		HTMLTagFilterRequestWrapper cachingRequest = (HTMLTagFilterRequestWrapper) request;
		
		if(loginVO != null){
			cachingRequest.setParameter("session_userid", loginVO.getUserId() == null ? "" : loginVO.getUserId());
			cachingRequest.setParameter("session_insttcd", loginVO.getInsttCd() == null ? "" : loginVO.getInsttCd());
			cachingRequest.setParameter("session_usernm", loginVO.getUserNm() == null ? "" : loginVO.getUserNm());
			cachingRequest.setParameter("session_usergb", loginVO.getUserGb() == null ? "" : loginVO.getUserGb());
			cachingRequest.setParameter("session_usergbnm", loginVO.getUserGbNm() == null ? "" : loginVO.getUserGbNm());
			cachingRequest.setParameter("session_insttnm", loginVO.getInsttNm() == null ? "" : loginVO.getInsttNm());
			cachingRequest.setParameter("session_deptnm", loginVO.getDeptNm() == null ? "" : loginVO.getDeptNm());
			cachingRequest.setParameter("session_telno", loginVO.getTelNo() == null ? "" : loginVO.getTelNo());
			cachingRequest.setParameter("session_hptelno", loginVO.getHpTelNo() == null ? "" : loginVO.getHpTelNo());
			
		}
		
		//cachingRequest.setParameter("test", "AAAAAA");
		
		request = cachingRequest;
		
		if( LOGGER.isDebugEnabled()) {
			//LOGGER.debug("[request] ______["+request+"]");
			
			LOGGER.debug("[request uri]_________________{}",request.getRequestURI());
			
			Enumeration params = request.getParameterNames();
			LOGGER.debug("[request parameter]_________________[start]");
			while(params.hasMoreElements()) {
			  String name = (String) params.nextElement();
			  LOGGER.debug(name + " : " + request.getParameter(name) + "     "); 
			}
			
			LOGGER.debug("[request parameter]__________________[end]");
			
			LOGGER.debug("[Session loginVO]____{}",loginVO);

		}
		
	
		if(loginVO != null){
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("[Session Valid]");
			}
			request.setAttribute("session.userid", loginVO.getUserId());
			return true;
		} else {
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("[Session Closed]");
			}
			response.sendRedirect(webRoot + "/flyt/login/procFLytLoginSessoutPDtl.do");
			return false;
		}

	}
	
	
	/**
	 * 인증된 사용자 여부로 인증 여부를 체크한다.
	 * 관리자 권한에 따라 접근 페이지 권한을 체크한다.
	 */
	/*
	@Override
	public boolean preHandle_orginal(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//인증된사용자 여부
		boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();	
		//미민증사용자 체크
		if(!isAuthenticated) {
			ModelAndView modelAndView = new ModelAndView("redirect:/uat/uia/egovLoginUsr.do");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		//인증된 권한 목록
		List<String> authList = (List<String>)EgovUserDetailsHelper.getAuthorities();
		//관리자인증여부
		boolean adminAuthUrlPatternMatcher = false;
		//AntPathRequestMatcher
		AntPathRequestMatcher antPathRequestMatcher = null;
		//관리자가 아닐때 체크함
		for(String adminAuthPattern : adminAuthPatternList){
			antPathRequestMatcher = new AntPathRequestMatcher(adminAuthPattern);
			if(antPathRequestMatcher.matches(request)){
				adminAuthUrlPatternMatcher = true;
			}
		}
		//관리자 권한 체크
		if(adminAuthUrlPatternMatcher && !authList.contains("ADMIN")){
			ModelAndView modelAndView = new ModelAndView("redirect:/uat/uia/egovLoginUsr.do?auth_error=1");
			throw new ModelAndViewDefiningException(modelAndView);
		}
		return true;
	}
	*/
	

}
