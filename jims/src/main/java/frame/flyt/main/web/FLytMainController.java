package frame.flyt.main.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import frame.fcom.service.ComService;
import frame.flyt.login.service.FLytLoginVO;
import frame.flyt.main.service.MenuVO;

/**
 * @Class Name : MainController.java
 * @Description : Main Screen
 * @Modification Information
 *
 * @author 김규형
 * @since 2018.09.11
 * @version 1.1
 * @see
 *  
 *  Copyright JENIX (C) All right reserved.
 */

@Controller
public class FLytMainController {
	
    @Resource(name="comService")
    ComService comService;
    
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	
	@Resource MappingJackson2JsonView ajaxMainView;
    
	
	protected Logger log = LoggerFactory.getLogger(FLytMainController.class);
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFLytMainIDtl
	 * 2. 작성일 : 2021. 4. 26. 오후 2:26:17
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 메인 화면 페이지 로딩 
	 * </pre>
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/main/indexFLytMainIDtl.do")
	public String indexFLytMainIDtl(ModelMap model,HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();		
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();			
		
		if(isAuthenticated){
			
			if (user.getUserGb().equals("C01001")) {// 대표이사
        		map.put("upperMenuNo", "C01001");
        		
        	} else if (user.getUserGb().equals("C01002")) {// 부장
        		map.put("upperMenuNo", "C01002");	
        		
        	} else if (user.getUserGb().equals("C01003")) {// 차장
        		map.put("upperMenuNo", "C01003");	 
        	} else if (user.getUserGb().equals("C01004")) {// 과장
        		map.put("upperMenuNo", "C01004");	 
        	} else if (user.getUserGb().equals("C01005")) {// 대리
        		map.put("upperMenuNo", "C01005");	 
        	} else if (user.getUserGb().equals("C01006")) {// 사원
        		map.put("upperMenuNo", "C01006");	 
        	} else if (user.getUserGb().equals("C01999"))  {//관리자 메뉴 C01999
        		map.put("upperMenuNo", "C01999");	 
        	} else {
        		map.put("upperMenuNo", "0000000001");
        	}
			model.addAttribute("user", user);
			
			//소속기관가져오기
			/*if (user.getUserGb().equals("C01001") || user.getUserGb().equals("C01002")) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("sqlQueryId", "mainDAO.selectMlsvcInsttSelList");
				paramMap.put("userId", user.getUserId());
				List<Map<String, Object>> orgList = comService.selectCommonQueryList(paramMap);
				model.addAttribute("orgList", orgList);
			}*/
			
		} else {
    		map.put("upperMenuNo", "0000000001");
    	}
		
		
		List<MenuVO> menuList = comService.selectMenuList(map);
		model.addAttribute("menuList", menuList);
			
		//서버구분(로컬/개발/운영 : LOCAL/DEV/REAL)
		String serverType = EgovProperties.getProperty("Globals.serverType");
       	model.addAttribute("serverType", serverType);
       	model.addAttribute("pwdChng", (String)request.getParameter("pwdChng"));
       	model.addAttribute("pwdClr", (String)request.getParameter("pwdClr"));
		
		return "frame/flyt/main/flytMainIDtl";
	}
	

}
