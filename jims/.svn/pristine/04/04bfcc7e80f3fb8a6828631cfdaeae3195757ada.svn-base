package frame.futil;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.flyt.login.service.FLytLoginVO;



/**
 * @Class Name : JimsUtil.java
 * @Description : JimsUtil class
 * @Modification Information
 *
 * @author 김규형
 * @since 2018.10.01
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

public class JimsUtil {
	
	private static final Logger log = LoggerFactory .getLogger(JimsUtil.class);
	
   
	
	public static String encryptSHA256(String data) throws Exception {
		String encData = "";
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		byte[] byteData = data.getBytes();
		
		md.update(byteData);
		
		byte[] digest = md.digest();
		
		for(int i=0; i < digest.length; i++){
			encData = encData + Integer.toString((digest[i] & 0xFF) + 0x100, 16).substring(1);
		}
				
		return encData;
	}
	
	/**
     * html의 특수문자를 표현하기 위해
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String getHtmlStrCnvr(String srcString) {

    	String tmpString = srcString;
    	
    	boolean contAlert = tmpString.contains("alert");
    	boolean contEmbed = tmpString.contains("embed");
    	
    	if(!(contAlert || contEmbed)){//script 가 아닌 html 태그 허용
			try
			{	
				tmpString = tmpString.replaceAll("&amp;", "&");
				tmpString = tmpString.replaceAll("&lt;", "<");
				tmpString = tmpString.replaceAll("&gt;", ">");
				tmpString = tmpString.replaceAll("&apos;", "\'");
				tmpString = tmpString.replaceAll("&quot;", "\"");
				//ckEditor 에서 중복으로 줄바뀜 문제 해결을 위해 주석처리.
				//tmpString = tmpString.replaceAll("\n", "<br/>");
				tmpString = tmpString.replaceAll("\u00A0", " ");
			}
			catch (Exception ex)
			{
			    //ex.printStackTrace();
			    throw new RuntimeException(ex);	// 2011.10.10 보안점검 후속조치
			}
    	}else{
    		try
			{	
				tmpString = tmpString.replaceAll("&amp;", "&");
				tmpString = tmpString.replaceAll("&apos;", "\'");
				tmpString = tmpString.replaceAll("&quot;", "\"");
				tmpString = tmpString.replaceAll("&lt;p&gt;", "<p>");
				tmpString = tmpString.replaceAll("&lt;/p&gt;", "</p>");
				tmpString = tmpString.replaceAll("&lt;p /&gt;", "<p />");
				tmpString = tmpString.replaceAll("&lt;P&gt;", "<P>");
				tmpString = tmpString.replaceAll("&lt;/P&gt;", "</P>");
				tmpString = tmpString.replaceAll("&lt;P /&gt;", "<P />");
				tmpString = tmpString.replaceAll("\n", "<br/>");
				tmpString = tmpString.replaceAll("\u00A0", " ");
			}
			catch (Exception ex)
			{
			    //ex.printStackTrace();
			    throw new RuntimeException(ex);	// 2011.10.10 보안점검 후속조치
			}
    	}

		return  tmpString;

	}
	
    /**
	 * @author Kkh
	 * @param  HttpServletRequest
	 * @return HashMap<String, Object>
	 * @throws Exception
	 * 
	 * <pre>
	 *  request를 HashMap으로 변환
	 * 
	 *  1. 배열인 경우 List로 Map에 저장
	 * </pre>
	 */
    public static HashMap<String, Object> convertMap(HttpServletRequest request) throws Exception {
    	HashMap<String, Object> parameterMap = new HashMap<String, Object>();

    	@SuppressWarnings("rawtypes")
    	Enumeration enums = request.getParameterNames();
    	parameterMap.put("remoteAddr", request.getRemoteAddr());
    		
    	while(enums.hasMoreElements()){ 
    		String paramName = (String)enums.nextElement(); 
    		String[] parameters = request.getParameterValues(paramName); 

    		// Parameter가 배열일 경우 
    		if(parameters.length > 1){ 
    			List<Object> parmList = new ArrayList<Object>();
    				
    			for(int i= 0; i<parameters.length;i++){
    				parmList.add(parmList.size(), parameters[i]);
    			}
    			parameterMap.put(paramName, parmList);
    			// Parameter가 배열이 아닌 경우 
    		}else{ 
    			parameterMap.put(paramName, parameters[0]);
    		} 
    	} 

    	return parameterMap; 
    }
    
    /**
     * 브라우저 구분 얻기.
     * 
     * @param request
     * @return
     */
    public static String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1) {
            return "MSIE";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }
    
    /**
     * 브라우져별 파일명 설정
     * 
     * @param request
     * @return
     */
    public static String encodedFilename(String filename, HttpServletRequest request) {
    	String browser = JimsUtil.getBrowser(request);
    	String encodedFilename = null;
    	
    	try{
    		if (browser.equals("MSIE")) {
        	    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        	} else if (browser.equals("Firefox")) {
        	    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        	} else if (browser.equals("Opera")) {
        	    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        	} else if (browser.equals("Chrome")) {
        	    StringBuffer sb = new StringBuffer();
        	    for (int i = 0; i < filename.length(); i++) {
        		char c = filename.charAt(i);
        		if (c > '~') {
        		    sb.append(URLEncoder.encode("" + c, "UTF-8"));
        		} else {
        		    sb.append(c);
        		}
        	    }
        	    encodedFilename = sb.toString();
        	} else {
        	    //throw new RuntimeException("Not supported browser");
        	    throw new IOException("Not supported browser");
        	}
		} catch (Exception e) {
			return null;
		}
    	
    	return encodedFilename;
    }
    
    /**
     * 사용자 체크해서 jsp root를 붙여주는 메서드(default: eat)
     * @param path 기본경로 
     * @return 
     */
	public static String rootPath(String path) {
		String returnValue = "";
		
    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
    	if(isAuthenticated) {
    		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    		
    		if (user.getUserGb().equals("3")) { //내부사용자
    			//returnValue = "manager"+path;
    			returnValue = path;
    		}else{
    			returnValue = path;
    		}
    	}
    	
    	if (EgovStringUtil.isNull(returnValue)) {
    		returnValue = path;
    	}
		
		return returnValue;
	}
	
	//맵에서 get시 NULL 에러 방지
    public static String getMapString(String getterName, Map map) throws Exception {
    	String retString = "";
    	
		if (EgovStringUtil.isNull((String)map.get(getterName))) {
			retString = "";
		} else {
			retString = (String)map.get(getterName);
		}
    	
    	return retString;
    }
    
}
