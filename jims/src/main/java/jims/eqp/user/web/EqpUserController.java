package jims.eqp.user.web;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

//import jims.eqp.user.service.EqpUserService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import frame.fcom.service.ComService;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.user.service.FsysUserVO;
import frame.futil.JimsConst;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;
import jims.eqp.user.service.EqpUserService;


/**
 * 
 * <pre>
 * 1. 클래스명 : EqpUserController.java
 * 2. 작성일 : 2021. 7. 19.
 * 3. 작성자 : ilyong
 * 4. 설명 : 장비 사용자 관리 컨트롤러
 * </pre>
 */
@Controller
public class EqpUserController {

	protected Logger log = LoggerFactory.getLogger(EqpUserController.class);

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "comService")
	private ComService comService;
	
	@Resource(name = "eqpUserService")
	private EqpUserService eqpUserService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "validateUtil")
	private ValidateUtil validateUtil;

    @Resource MappingJackson2JsonView ajaxMainView;

    
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexEqpUserMList
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 장비 지원 관리 > 장비 사용자 관리 조회 진입
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/eqp/user/indexEqpUserMList.do")
    public String indexEqpUserMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap<String,String> map) throws Exception {
    	
    	model.addAttribute("message", request.getParameter("message"));
    	if(request.getParameter("msg") != null){
    		model.addAttribute("msg", request.getParameter("msg"));
    	}
    	return "jims/eqp/user/eqpUserMList";
    }


	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryEqpUserMList
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 장비 지원 관리 > 장비 사용자 목록 조회 처리
	 * </pre>
	 * @param request
	 * @param model
	 * @param reqParams
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/eqp/user/queryEqpUserMList.do")
    public ModelAndView queryEqpUserMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

		reqParams.put("sqlQueryId", "eqpUserDAO.eqpUserMListTotCnt");
    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
    	reqParams.put("totalCount", totCnt);
    	
    	PageUtil.calcPage(reqParams);
    	
		reqParams.put("sqlQueryId", "eqpUserDAO.eqpUserMList");
		List eqpUserMList = comService.selectCommonQueryList(reqParams);
		reqParams.put("eqpUserMList", eqpUserMList);
		model.addAllAttributes(reqParams);
		
		return new ModelAndView(ajaxMainView, model);

    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryEqpUserPop
     * 2. 작성일 : 2021. 7. 20.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 사용자 조회 팝업
     * </pre>
     * @param request
     * @param model
     * @param reqParams
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/eqp/user/queryEqpUserPop.do")
    public ModelAndView queryEqpUserPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

 	    FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
 	   	reqParams.put("regrId", user.getUserId());
 	   	reqParams.put("insttCd", user.getInsttCd());
		reqParams.put("sqlQueryId", "eqpUserDAO.eqpUserListPopCnt");
		
		int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
		reqParams.put("totalCount", totCnt);

		PageUtil.calcPage(reqParams);
		
		
		reqParams.put("sqlQueryId", "eqpUserDAO.eqpUserListPop");
		List popList = comService.selectCommonQueryList(reqParams);		
 		reqParams.put("popList", popList);
 		model.addAllAttributes(reqParams);
 		
 		return new ModelAndView(ajaxMainView, model);

    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexEqpMgmtRDtl
     * 2. 작성일 : 2021. 7. 20.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 사용자 등록 진입 컨트롤러
     * </pre>
     * @param map
     * @param request
     * @param fsysUserVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/eqp/user/indexEqpUserRDtl.do")
    public String indexEqpMgmtRDtl(@RequestParam HashMap<String,String> map,HttpServletRequest request, @ModelAttribute("fsysUserVO") FsysUserVO fsysUserVO, ModelMap model) throws Exception {

    	return "jims/eqp/user/eqpUserRDtl";
    }

    /**
     * 
     * <pre>
     * 1. 메소드명 : regEqpUserRDtl
     * 2. 작성일 : 2021. 7. 21.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 사용자 등록 컨트롤러
     * </pre>
     * @param map
     * @param model
     * @param request
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/eqp/user/regEqpUserRDtl.do")
	 public ModelAndView regEqpUserRDtl( @RequestParam HashMap<String,String> map, ModelMap model,
			 HttpServletRequest request, SessionStatus status, @RequestBody String paramData) throws Exception {

    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Map<String, Map<String, Object>> jsonObject = mapper.readValue(paramData, Map.class);
    	Map<String, Map<String, String>> jsonObject2 = mapper.readValue(paramData, Map.class);
    	
    	Map<String, Object> formDataMap = (Map<String, Object>) jsonObject.get("formDatas1");
        HashMap<String,String> chkParams = (HashMap<String, String>)jsonObject2.get("formDatas1");
        
        HashMap<String, String> paramMap = new HashMap<String, String>();
    	ArrayList<Map<String, Object>> tpList = (ArrayList<Map<String, Object>>) jsonObject.get("rowDatas");
    	
        //필수값 확인
        HashMap<String,String> requireParams = new HashMap();

		paramMap.putAll(chkParams);
		requireParams.put("userId", "사용자명");
		requireParams.put("pgsStat", "상태");
		requireParams.put("rcvDt", "수령일자");

        validateUtil.check(chkParams, requireParams);
        
        HashMap querySnoMap = new HashMap<>(); 
        querySnoMap.put("sqlQueryId", "selectEqpUserSno");
        
        String eqpUserSno =  comService.selectCommonQueryString(querySnoMap); 
    	for(int i = 0; i < tpList.size(); i++) {
    		paramMap.clear();
    		if(tpList.get(i).get("chk").toString().equals("Y")) {
        		paramMap.putAll(chkParams);
        		paramMap.put("eqpUserSno", eqpUserSno);
                paramMap.put("regrId", user.getUserId());
                paramMap.put("updrId", user.getUserId());
                paramMap.put("eqpSno", tpList.get(i).get("eqpSno").toString());
                eqpUserService.regEqpUserRDtl(paramMap);
                eqpUserService.updEqpUserBiz(paramMap);
    		}
    	}
    	
		return new ModelAndView(ajaxMainView, model);
		
	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexEqpUserUDtl
     * 2. 작성일 : 2021. 7. 22.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 사용자 관리 상세 진입 컨트롤러
     * </pre>
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/eqp/user/indexEqpUserUDtl.do")
	public String indexEqpUserUDtl(@RequestParam HashMap<String,String> map,HttpServletRequest request, ModelMap model) throws Exception {
	
		return "jims/eqp/user/eqpUserUDtl";
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 22.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 관리 상세 조회 컨트롤러 
	 * </pre>
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/eqp/user/queryEqpUserUDtl.do")
	public ModelAndView queryEqpUserUDtl(@RequestParam HashMap map,HttpServletRequest request, ModelMap model) throws Exception {
	
		map.put("sqlQueryId", "eqpUserDAO.queryEqpUserUDtl");
		List eqpUserInfoMap = comService.selectCommonQueryList(map);
		map.put("eqpUserInfoMap", eqpUserInfoMap);
	  	
	 	model.addAllAttributes(map); 
	 	 
	 	return new ModelAndView(ajaxMainView, model);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 22.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 관리 수정 컨트롤러
	 * </pre>
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eqp/user/updEqpUserUDtl.do")
	public ModelAndView updEqpUserUDtl( @RequestParam HashMap<String,String> map, ModelMap model,
			 HttpServletRequest request, SessionStatus status, @RequestBody String paramData) throws Exception {

    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	Map<String, Map<String, Object>> jsonObject = mapper.readValue(paramData, Map.class);
    	Map<String, Map<String, String>> jsonObject2 = mapper.readValue(paramData, Map.class);
    	
    	Map<String, Object> formDataMap = (Map<String, Object>) jsonObject.get("formDatas1");
        HashMap<String,String> chkParams = (HashMap<String, String>)jsonObject2.get("formDatas1");
        
        HashMap<String, String> paramMap = new HashMap<String, String>();
    	ArrayList<Map<String, Object>> tpList = (ArrayList<Map<String, Object>>) jsonObject.get("rowDatas");
    	
        //필수값 확인
        HashMap<String,String> requireParams = new HashMap();

		paramMap.putAll(chkParams);
		requireParams.put("userId", "사용자명");
		requireParams.put("pgsStat", "상태");
		requireParams.put("rcvDt", "수령일자");

        validateUtil.check(chkParams, requireParams);
        
        //기존 일련번호 불러오기
        String eqpUserSno = formDataMap.get("eqpUserSno").toString();
        eqpUserService.delEqpUserUDtl(map);
        
    	for(int i = 0; i < tpList.size(); i++) {
    		paramMap.clear();
    		if(tpList.get(i).get("chk").toString().equals("Y")) {
        		paramMap.putAll(chkParams);
        		paramMap.put("eqpUserSno", eqpUserSno);
                paramMap.put("eqpSno", tpList.get(i).get("eqpSno").toString());
                eqpUserService.regEqpUserRDtl(paramMap);
                eqpUserService.updEqpUserBiz(paramMap);
    		}
    	}
		
		
		return new ModelAndView(ajaxMainView, model);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delEqpUserUDtl
	 * 2. 작성일 : 2021. 7. 22.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 사용자 관리 삭제 컨트롤러
	 * </pre>
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/eqp/user/delEqpUserUDtl.do")
	public ModelAndView delEqpUserUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {
		
		eqpUserService.delEqpUserUDtl(map);
		return new ModelAndView(ajaxMainView, model);
	}
    
    
	

}
