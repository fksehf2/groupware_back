package egovframework.com.sym.log.wlg.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.filter.HTMLTagFilterRequestWrapper;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.sym.log.wlg.service.EgovWebLogService;
import egovframework.com.sym.log.wlg.service.WebLog;
import frame.flyt.login.service.FLytLoginVO;

/**
 * @Class Name : EgovWebLogInterceptor.java
 * @Description : 웹로그 생성을 위한 인터셉터 클래스
 * @Modification Information
 *
 *    수정일        수정자         수정내용
 *    -------      -------     -------------------
 *    2009. 3. 9.   이삼섭         최초생성
 *    2011. 7. 1.   이기하         패키지 분리(sym.log -> sym.log.wlg)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 9.
 * @version
 * @see
 *
 */
public class EgovWebLogInterceptor extends HandlerInterceptorAdapter {

	@Resource(name="EgovWebLogService")
	private EgovWebLogService webLogService;
	
	private ObjectMapper objectMapper;


	protected Logger log = LoggerFactory.getLogger(EgovWebLogInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if( log.isDebugEnabled()) {
			log.debug("EgovWebLogInterceptor is called____________");
		}
		
		//System.out.println("pre handle_________________________________");
		
		WebLog webLog = new WebLog();
		String reqURL = request.getRequestURI();
		String uniqId = "";

		/* Authenticated  */
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(isAuthenticated.booleanValue()) {
			FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			uniqId = user.getUserId();// getUniqId();
		}

		webLog.setUrl(reqURL);
		webLog.setRqesterId(uniqId);
		webLog.setRqesterIp(request.getRemoteAddr());
		String reqParamStr = "";
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
			String name = (String) params.nextElement();
			reqParamStr += name + "=" + request.getParameter(name) + "\n";
			//System.out.println(name + " : " + request.getParameter(name) + "==================== reqURL : " + reqURL);
			if(name.equals("rqesterMenuId")) {
				webLog.setRqesterMenuId(request.getParameter(name));
			}else if(name.equals("__GUID__")) {
				webLog.setGuid(request.getParameter(name));
			}
		}
		webLog.setRqesterParam(reqParamStr);

		//System.out.println("+++++++++++++++++++++++++++++++++++++++++++");

		//HttpServletRequest request2 = request;
		HTMLTagFilterRequestWrapper cachingRequest = (HTMLTagFilterRequestWrapper) request; 
		
		
		if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {

			//System.out.println("__________________________________________111");
			
			ServletInputStream is = cachingRequest.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(is));
	        StringBuilder builder = new StringBuilder();
	        String buffer;
	        while ((buffer = input.readLine()) != null) {
	            if (builder.length() > 0) {
	                builder.append("\n");
	            }
	            builder.append(buffer);
	        }
	        //System.out.println("0000____"+builder.toString());
	        String reqBodyParam = builder.toString();
	        ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> jsonObject = mapper.readValue(reqBodyParam, Map.class);
			if(jsonObject.get("rqesterMenuId") != null) {
				String rqesterMenuId = (String) jsonObject.get("rqesterMenuId");
				webLog.setRqesterMenuId(rqesterMenuId);
			}
	        webLog.setRqesterParam(reqBodyParam);
		}

		
		
		 
		

		try {
			webLogService.InsertLogWebLog(webLog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

		return true;
	}
	/**
	 * 웹 로그정보를 생성한다.
	 * 
	 * @param HttpServletRequest request, HttpServletResponse response, Object handler 
	 * @return 
	 * @throws IOException 
	 * @throws Exception 
	 */
	
	

	
}
