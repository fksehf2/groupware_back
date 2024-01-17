package frame.fsys.user.web;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import frame.fcom.service.ComService;
import frame.fexception.MException;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.user.service.FsysUserService;
import frame.fsys.user.service.FsysUserVO;
import frame.futil.JimsConst;
import frame.futil.JimsUtil;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;



/**
 * <pre>
 * 1. 클래스명 : FsysUserController.java
 * 2. 작성일 : 2021. 4. 16.
 * 3. 작성자 : ilyong
 * 4. 설명 : 
 * </pre>
 */
@Controller
public class FsysUserController {

	protected Logger log = LoggerFactory.getLogger(FsysUserController.class);

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "comService")
	private ComService comService;
	
	@Resource(name = "fsysUserService")
	private FsysUserService fsysUserService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "validateUtil")
	private ValidateUtil validateUtil;
	

    @Resource MappingJackson2JsonView ajaxMainView;

    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexFsysUserMList
     * 2. 작성일 : 2021. 4. 14. 오전 10:49:11
     * 3. 작성자 : ilyong
     * 4. 설명 : 사용자관리 조회 이동
     * </pre>
     * @param request
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fsys/user/indexFsysUserMList.do")
    public String indexFsysUserMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap<String,String> map) throws Exception {
    	
    	model.addAttribute("message", request.getParameter("message"));
    	if(request.getParameter("msg") != null){
    		model.addAttribute("msg", request.getParameter("msg"));
    	}
    	return "frame/fsys/user/fsysUserMList";
    }


	/**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysUserMList
     * 2. 작성일 : 2021. 4. 14. 오전 10:49:36
     * 3. 작성자 : ilyong
     * 4. 설명 : 사용자관리 조회 처리
     * </pre>
     * @param request
     * @param model
     * @param reqParams
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fsys/user/queryFsysUserMList.do")
    public ModelAndView queryFsysUserMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

		reqParams.put("sqlQueryId", "fsysUserDAO.sysUserSelListTotCnt");
    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
    	reqParams.put("totalCount", totCnt);
    	
    	PageUtil.calcPage(reqParams);
    	
    	log.debug("=======reqParams searchNm========>>>"+reqParams.get("searchNm"));
    	log.debug("=======reqParams searchInstt========>>>"+reqParams.get("searchInstt"));
    	
		reqParams.put("sqlQueryId", "fsysUserDAO.sysUserSelList");
		List commNotcMList = comService.selectCommonQueryList(reqParams);
		reqParams.put("commNotcMList", commNotcMList);
		model.addAllAttributes(reqParams);
		
		return new ModelAndView(ajaxMainView, model);

    }
    
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexFsysUserRDtl
     * 2. 작성일 : 2021. 4. 16. 오전 9:15:21
     * 3. 작성자 : ilyong
     * 4. 설명 : 사용자관리 > 사용자 등록화면 이동
     * </pre>
     * @param map
     * @param request
     * @param fsysUserVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fsys/user/indexFsysUserRDtl.do")
    public String indexFsysUserRDtl(@RequestParam HashMap<String,String> map,HttpServletRequest request, @ModelAttribute("fsysUserVO") FsysUserVO fsysUserVO, ModelMap model) throws Exception {

    	//공통으로 필요한 데이타.(권한체크등...)
    	// model = comController.common(request, model);

    	//String userId = map.get("userId");

		/*map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuUpperList");
		List sysGrpList = comService.selectCommonQueryList(map);
		model.addAttribute("sysGrpList", sysGrpList);
		
		map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuUpperId");
		Map rtnMap = comService.selectCommonQueryMap(map);
		model.addAttribute("topMenuNo",rtnMap.get("topMenuNo"));*/

    	return "frame/fsys/user/fsysUserRDtl";
    }
    
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : regFsysUserRDtl
     * 2. 작성일 : 2021. 4. 16. 오전 9:46:21
     * 3. 작성자 : ilyong
     * 4. 설명 : 사용자관리 > 사용자 등록 처리
     * </pre>
     * @param map
     * @param model
     * @param request
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/user/regFsysUserRDtl.do")
	 public ModelAndView regFsysUserRDtl( @RequestParam HashMap<String,String> map, ModelMap model,
			 HttpServletRequest request, SessionStatus status) throws Exception {

		
		String errMsg = "";

    	/*try{*/
    		int lvl2Cnt = 0;
    		
//    		map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuLvl2Cnt");
//    		lvl2Cnt = comService.selectCommonQueryListTotCnt(map);
//    		if(map.get("menuLvl").equals("lvl2") && lvl2Cnt > 98){
//    			System.out.println("고마 느라 마이 느따 아이가");
//    			model.addAttribute(JimsConst.Messages_UserComMessage, egovMessageSource.getMessageArgs("admin.menu.001", null));
//    			return new ModelAndView(ajaxMainView, model);
//    		}
    		
    		map.put("pwd",JimsUtil.encryptSHA256(map.get("pwd")));
//    		map.put("sqlQueryId", "fsysUserDAO.regFsysUserRDtl");
//    		comService.updateCommonQuery(map);

			//필수값 확인
			HashMap<String,String> chkParams = new HashMap<String, String>();
			chkParams.putAll(map);

			HashMap<String,String> requireParams = new HashMap();
			requireParams.put("userGb", "사용자구분");
			requireParams.put("insttCd", "소속기관코드");
			requireParams.put("pwd", "패스워드");
			requireParams.put("userNm", "사용자명");
			requireParams.put("telNo", "전화번호");
			requireParams.put("hpTelNo", "휴대전화번호");
			requireParams.put("userId", "사용자ID");
			requireParams.put("pwdFindQues", "비밀번호찾기질문코드");
			requireParams.put("pwdFindAsw", "비밀번호찾기답");
			requireParams.put("regStatus", "가입상태");
			validateUtil.check(chkParams, requireParams);
    		
    		fsysUserService.regFsysUserRDtl(map);
    		
//    		map.put("sqlQueryId", "fsysMenuDAO.updFsysMenuRDtlOrdr");
//    		comService.updateCommonQuery(map);
//    		
//    		if(map.get("menuLvl").equals("lvl2") && lvl2Cnt > 1){
//    			map.put("sqlQueryId", "fsysMenuDAO.updFsysMenuNoReMake");
//    			comService.updateCommonQuery(map);
//    		}
    		
//    		model.addAttribute(JimsConst.Messages_UserComMessage, egovMessageSource.getMessageArgs("success.common.insert", null));
    	/*}catch (Exception e) {
    		e.printStackTrace();
    		errMsg = URLEncoder.encode(egovMessageSource.getMessage("fail.request.msg"), "UTF-8");
    	}*/

    	
		return new ModelAndView(ajaxMainView, model);
		
	}

    
    /**
     * 
     * <pre>
     * 1. 메소드명 : sysUserCheckId
     * 2. 작성일 : 2021. 4. 16. 오전 9:46:51
     * 3. 작성자 : ilyong
     * 4. 설명 : 사용자관리 > 사용자ID 중복 체크
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@RequestMapping("/fsys/user/sysUserCheckId.do")
	public  ModelAndView sysUserCheckId(@RequestParam HashMap<String,String> map, ModelMap model, HttpServletRequest request) throws Exception  {
	
		map.put("sqlQueryId", "fsysUserDAO.sysUserCheckId");

		List pgmList = comService.selectCommonQueryList(map);
		
		HashMap rtnMap = new HashMap<String,Object>();

		model.addAttribute(pgmList.get(0));


		return new ModelAndView(ajaxMainView, model);
	}
    
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFsysUserUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 9:48:15
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자관리 > 사용자 수정화면으로 이동
	 * </pre>
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/fsys/user/indexFsysUserUDtl.do")
	public String indexFsysUserUDtl(@RequestParam HashMap<String,String> map,HttpServletRequest request, ModelMap model) throws Exception {
	
		return "frame/fsys/user/fsysUserUDtl";
		
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryFsysUserUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 9:49:20
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자관리 > 사용자 상세정보 조회
	 * </pre>
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("/fsys/user/queryFsysUserUDtl.do")
	public ModelAndView queryFsysUserUDtl(@RequestParam HashMap<String,String> map, HttpServletRequest request, ModelMap model) throws Exception {

		//공통으로 필요한 데이타.(권한체크등...)
    	//model = comController.common(request, model);


		String errMsg = "";

    	try{
    		
//    		map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuUpperList");
//    		List sysGrpList = comService.selectCommonQueryList(map);
//    		model.addAttribute("sysGrpList", sysGrpList);
//    		
//    		map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuUpperId");
//    		Map rtnMap = comService.selectCommonQueryMap(map);
//    		model.addAttribute("topMenuNo",rtnMap.get("topMenuNo"));
    		
    		if(map.get("userId") != null && !map.get("userId").equals("")) {
    			map.put("sqlQueryId", "fsysUserDAO.queryFsysUserUDtl");
    			Map resultMap = comService.selectCommonQueryMap(map);
    			model.addAttribute("resultMap", resultMap);
    		}

    	}catch (Exception e) {
    		e.printStackTrace();
    		errMsg = URLEncoder.encode(egovMessageSource.getMessage("fail.request.msg"), "UTF-8");
    	}
    	
    	return new ModelAndView(ajaxMainView, model);

    }
	
    /**
	 * 
	 * <pre>
	 * 1. 메소드명 : updFsysMenuUDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:20:39
	 * 3. 작성자 : 
	 * 4. 설명 : 사용자관리 > 사용자 상세정보 수정 처리
	 * </pre>
	 * 
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/user/updFsysUserUDtl.do")
	public ModelAndView updFsysUserUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		
		// 1. 비밀번호 암호화
    	if(!"".equals(JimsUtil.getMapString("pwd", map))) {
    		map.put("pwd",JimsUtil.encryptSHA256(map.get("pwd")));
    	}
		
		//필수값 확인
		HashMap<String,String> chkParams = new HashMap<String, String>();
		chkParams.putAll(map);

		HashMap<String,String> requireParams = new HashMap();
		requireParams.put("userGb", "사용자구분");
		requireParams.put("insttCd", "소속기관코드");
		//requireParams.put("pwd", "패스워드");
		requireParams.put("userNm", "사용자명");
		requireParams.put("telNo", "전화번호");
		requireParams.put("hpTelNo", "휴대전화번호");
		requireParams.put("userId", "사용자ID");
		requireParams.put("pwdFindQues", "비밀번호찾기질문코드");
		requireParams.put("pwdFindAsw", "비밀번호찾기답");
		requireParams.put("regStatus", "가입상태");
		validateUtil.check(chkParams, requireParams);
		
		map.put("sqlQueryId", "fsysUserDAO.updFsysUserUDtl");
		comService.updateCommonQuery(map);

//		map.put("sqlQueryId", "fsysMenuDAO.updFsysMenuRDtlOrdr");
//		comService.updateCommonQuery(map);
//
//		if (!map.get("upperMenuNo").equals(map.get("oriUpperMenuNo"))) {
//			if (map.get("menuLvl").equals("lvl2") && lvl2Cnt > 1) {
//				map.put("sqlQueryId", "fsysMenuDAO.updFsysMenuNoReMake");
//				comService.updateCommonQuery(map);
//			}
//		}

		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.update", null));

		return new ModelAndView(ajaxMainView, model);
	}
    
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysUserUDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:20:53
	 * 3. 작성자 : jij
	 * 4. 설명 : 사용자관리 > 사용자 상세정보 삭제 처리
	 * </pre>
	 * 
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/menu/delFsysUserUDtl.do")
	public ModelAndView delFsysUserUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		
		fsysUserService.delFsysUserUDtl(map);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.delete", null));

		return new ModelAndView(ajaxMainView, model);

	}
	

    
    /**
	   *   
	   * <pre>
	   * 1. 메소드명 : indexFLytLoginUserUpdPDtlPop
	   * 2. 작성일 : 2021. 4. 16. 오후 12:21:34
	   * 3. 작성자 : jmkim
	   * 4. 설명 : 메인 화면 사용자 정보 수정 초기 페이지
	   * </pre>
	   * @param request
	   * @param model
	   * @param map
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value="/fsys/user/indexFLytLoginUserUpdPDtlPop.do")
	  public String indexFLytLoginUserUpdPDtlPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {

		  FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		  map.put("sqlQueryId", "fLytLoginDAO.userInfoList");
		  FsysUserVO userInfoList = fsysUserService.userInfoList(user.getUserId()); //(FsysUserVO) comService.selectCommonQueryList(map);
		   
		  
		  model.addAttribute("UserVO", userInfoList);

		  //비밀번찾기질문    	
		  List pwdFindQuesCodeList = comService.selectCodeList("C15");
		  model.addAttribute("pwdFindQuesCodeList",pwdFindQuesCodeList);

		  return "frame/fsys/user/fsysUserUserUpdPDtlPop";

	  }
	  
	  /**
	   * 
	   * <pre>
	   * 1. 메소드명 : queryFLytLoginUserUpdPDtlPop
	   * 2. 작성일 : 2021. 4. 20. 오후 2:33:08
	   * 3. 작성자 : jmkim
	   * 4. 설명 :  main 화면 사용자 정보 조회 
	   * </pre>
	   * @param request
	   * @param model
	   * @param map
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping(value="/fsys/user/queryFLytLoginUserUpdPDtlPop.do")
	  public ModelAndView queryFLytLoginUserUpdPDtlPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {

		  FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();


		  map.put("sqlQueryId", "fsysUserDAO.userInfoList");
		  Map resultMap = comService.selectCommonQueryMap(map);
		  model.addAttribute("resultMap", resultMap);

		  return new ModelAndView(ajaxMainView, model);



	  }


	  /**
	   * 
	   * <pre>
	   * 1. 메소드명 : updateAdminUserMod
	   * 2. 작성일 : 2021. 4. 16. 오후 4:57:44
	   * 3. 작성자 : jmkim
	   * 4. 설명 : 메인 사용자 정보 수정 
	   * </pre>
	   * @param map
	   * @param model
	   * @param request
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping("/fsys/user/updFsysUserUserUpdPDtlPop.do")
	  public ModelAndView updFsysUserUserUpdPDtlPop(@RequestParam HashMap<String,String> map, ModelMap model, HttpServletRequest request) throws Exception {

		 
		  HashMap<String,String> requireParams = new HashMap();
		  requireParams.put("userNm", "사용자명");
		  
		  validateUtil.check(map,requireParams);
		  
		  
		  map.put("sqlQueryId", "fsysUserDAO.updFsysUserUserUpdPDtlPop");
		  //map.put("updrUserId", user.getUserId());

		  comService.updateCommonQuery(map);

		  return new ModelAndView(ajaxMainView, model);
	  }
	  
	  /**
	   * 
	   * <pre>
	   * 1. 메소드명 : chgPwFsysUserUserUpdPDtlPop
	   * 2. 작성일 : 2021. 4. 26. 오전 10:55:41
	   * 3. 작성자 : jmkim
	   * 4. 설명 : 비밀번호 변경 
	   * </pre>
	   * @param map
	   * @param model
	   * @param request
	   * @return
	   * @throws Exception
	   */
	  @RequestMapping("/fsys/user/chgPwFsysUserUserUpdPDtlPop.do")
	  public ModelAndView chgPwFsysUserUserUpdPDtlPop(@RequestParam HashMap<String,String> map, ModelMap model, HttpServletRequest request) throws Exception {

		  
		  HashMap<String,String> requireParams = new HashMap();
		  requireParams.put("chkPwd", "현재비밀번호");
		  requireParams.put("pwdConfirm", "새비밀번호");
		  requireParams.put("pwdFindQues", "질문");
		  requireParams.put("pwdFindAsw", "답변");
		  
		  validateUtil.check(map,requireParams);		  
		  
		  
		  if(!"".equals(JimsUtil.getMapString("chkPwd", map))) {
			  map.put("chkPwd",JimsUtil.encryptSHA256(map.get("chkPwd")));
		  }   

		  map.put("sqlQueryId", "fsysUserDAO.selectUserPw");
		  int passChkCnt =  comService.selectCommonQueryListTotCnt(map);

		  if(passChkCnt < 1) {
			  //현재 비밀번호로 불일치시
			  throw new MException(egovMessageSource.getMessage("fail.user.passwordUpdate1"));
		  }
		  
		  //비밀번호 암호화
		  if(!"".equals(JimsUtil.getMapString("pwd", map))) {
			  map.put("pwd",JimsUtil.encryptSHA256(map.get("pwd")));
		  }   

		  map.put("sqlQueryId", "fsysUserDAO.chgPwFsysUserUserUpdPDtlPop");
		  //map.put("updrUserId", user.getUserId());

		  comService.updateCommonQuery(map);


		  return new ModelAndView(ajaxMainView, model);
	  }
	  
	  
	  /**
	     * 
	     * <pre>
	     * 1. 메소드명 : queryFsysUserFindMListPop
	     * 2. 작성일 : 2021. 4. 14. 오전 10:49:36
	     * 3. 작성자 : ilyong
	     * 4. 설명 : 사용자 찾기 조회 팝업
	     * </pre>
	     * @param request
	     * @param model
	     * @param reqParams
	     * @param response
	     * @return
	     * @throws Exception /fsys/user/queryFsysUserFindMListPop.do
	     */
	    @RequestMapping(value="/fsys/user/queryFsysUserFindMListPop.do")
	    public ModelAndView queryFsysUserFindMListPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

			reqParams.put("sqlQueryId", "fsysUserDAO.sysUserSelListTotCnt");
	    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
	    	reqParams.put("totalCount", totCnt);
	    	
	    	PageUtil.calcPage(reqParams);
	    	
	    	log.debug("=======reqParams schUserId========>>>"+reqParams.get("schUserId"));
	    	log.debug("=======reqParams schUserNm========>>>"+reqParams.get("schUserNm"));
	    	
			reqParams.put("sqlQueryId", "fsysUserDAO.sysUserSelList");
			List list = comService.selectCommonQueryList(reqParams);
			reqParams.put("list", list);
			model.addAllAttributes(reqParams);
			
			return new ModelAndView(ajaxMainView, model);

	    }
	    
	    /**
	     * 
	     * <pre>
	     * 1. 메소드명 : queryDeptFIndListPop
	     * 2. 작성일 : 2021. 4. 14. 오전 10:49:36
	     * 3. 작성자 : ilyong
	     * 4. 설명 : 부서 조회 팝업
	     * </pre>
	     * @param request
	     * @param model
	     * @param reqParams
	     * @param response
	     * @return							
	     * @throws Exception 
	     */
	    @RequestMapping(value="/fsys/user/queryDeptFIndListPop.do")
	    public ModelAndView queryDeptFIndListPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

			reqParams.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptMListTotCnt");
	    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
	    	reqParams.put("totalCount", totCnt);
	    	
	    	PageUtil.calcPage(reqParams);
	    	
//	    	log.debug("=======reqParams searchNm========>>>"+reqParams.get("searchNm"));
//	    	log.debug("=======reqParams searchInstt========>>>"+reqParams.get("searchInstt"));
	    	
			reqParams.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptMList");
			List list = comService.selectCommonQueryList(reqParams);
			reqParams.put("list", list);
			model.addAllAttributes(reqParams);
			
			return new ModelAndView(ajaxMainView, model);

	    }


}
