package frame.fsys.dept.web;

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
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import frame.fcom.service.ComService;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.dept.service.FsysDeptService;
import frame.futil.JimsConst;
import frame.futil.JimsUtil;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysDeptController.java
 * 2. 작성일 : 2021. 4. 15.
 * 3. 작성자 : ilyong
 * 4. 설명 : @!@ 부서 관리 담당
 * </pre>
 */
@Controller
public class FsysDeptController {

	protected Logger log = LoggerFactory.getLogger(FsysDeptController.class);
	
	@Resource(name="comService")
	private ComService comService;
	
	@Resource(name="fsysDeptService")
	private FsysDeptService fsysDeptService;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource MappingJackson2JsonView ajaxMainView;	
	
	 /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "validateUtil")
	private ValidateUtil validateUtil;
    
    /*@Resource(name="comController")
    ComController comController;    */

    /**
     * 
     * <pre>
     * 1. 메소드명 : indexFsysDeptMList
     * 2. 작성일 : 2021. 4. 15. 오후 12:43:51
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 부서 관리 리스트 화면 이동
     * </pre>
     * @param request
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fsys/dept/indexFsysDeptMList.do")
    public String indexFsysDeptMList(HttpServletRequest request, ModelMap model, SessionStatus status) throws Exception {

    	//공통으로 필요한 데이타.(권한체크등...)
//    	model = comController.common(request, model);
//
//    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
//    	model.addAttribute("SessionID", loginVO.getUserId());

//    	status.setComplete();

    	return "frame/fsys/dept/fsysDeptMList";
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysDeptMList
     * 2. 작성일 : 2021. 4. 15. 오후 12:44:55
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 부서 관리 리스트 조회
     * </pre>
     * @param model
     * @param request
     * @param reqParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/queryFsysDeptMList.do")
	public ModelAndView queryFsysDeptMList(ModelMap model,
			HttpServletRequest request, @RequestParam HashMap reqParams) throws Exception {

		reqParams.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptMListTotCnt");
		int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
 		reqParams.put("totalCount", totCnt);
 		
 		PageUtil.calcPage(reqParams);
		 
		reqParams.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptMList");

		List list = comService.selectCommonQueryList(reqParams);

		reqParams.put("list", list);
		model.addAllAttributes(reqParams);

		return new ModelAndView(ajaxMainView, model);

	}

    /**
     * 
     * <pre>
     * 1. 메소드명 : regFsysDeptRDtl
     * 2. 작성일 : 2021. 4. 19. 오전 11:51:45
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 부서 관리 > 부서 등록 처리
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/regFsysDeptRDtl.do")
    public ModelAndView regFsysDeptRDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,  SessionStatus status) throws Exception {
    	
    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	reqParams.put("regrId", user.getUserId());
		reqParams.put("sqlQueryId", "fsysDeptDAO.regFsysDeptRDtl");

        //필수값 확인
		HashMap<String,String> chkParams = new HashMap<String, String>();
		chkParams.putAll(reqParams);

        HashMap<String,String> requireParams = new HashMap();
        requireParams.put("deptLvl", "부서레벨");
        requireParams.put("deptNm", "과명");
        requireParams.put("upInsttCd", "상위기관코드");
        requireParams.put("insttNm", "기관명");
        requireParams.put("teamNm", "팀명");
        validateUtil.check(chkParams, requireParams);

		comService.updateCommonQuery(reqParams);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.insert", null));
		
        return new ModelAndView(ajaxMainView, model);
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysDeptMDtl
     * 2. 작성일 : 2021. 4. 19. 오전 10:43:39
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 부서 관리 > 부서 상세 정보 조회
     * </pre>
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/queryFsysDeptMDtl.do")
	public ModelAndView queryFsysDeptMDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

    	map.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptMDtl");
    	Map rtnMap = comService.selectCommonQueryMap(map);
		model.addAttribute("rtnMap", rtnMap);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : updFsysDeptUDtl
     * 2. 작성일 : 2021. 4. 19. 오전 11:16:02
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 부서 관리 > 부서 상세 정보 수정 처리
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/updFsysDeptUDtl.do")
    public ModelAndView updFsysDeptUDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,  SessionStatus status) throws Exception {
    	
    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	reqParams.put("regrId", user.getUserId());
		reqParams.put("sqlQueryId", "fsysDeptDAO.updFsysDeptUDtl");
		comService.updateCommonQuery(reqParams);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.update", null));
		
        return new ModelAndView(ajaxMainView, model);
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : delFsysDeptUDtl
     * 2. 작성일 : 2021. 4. 19. 오전 11:50:38
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 부서 관리 > 부서 상세 정보 삭제 처리
     * </pre>
     * @param map
     * @param model
     * @param request
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/delFsysDeptUDtl.do")
	public ModelAndView delFsysDeptUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

//		map.put("sqlQueryId", "fsysDeptDAO.delFsysDeptUDtl");
//		comService.updateCommonQuery(map);
    	fsysDeptService.deleteFsysDeptUDtl(map);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.delete", null));


		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysDeptUserMList
     * 2. 작성일 : 2021. 4. 15. 오후 3:44:37
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 부서별 사용자 조회 리스트
     * </pre>
     * @param model
     * @param request
     * @param reqParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/queryFsysDeptUserMList.do")
	public ModelAndView queryFsysDeptUserMList(ModelMap model,
			HttpServletRequest request, @RequestParam HashMap reqParams) throws Exception {

		reqParams.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptUserMListTotCnt");
		int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
 		reqParams.put("totalCount", totCnt);
 		
 		PageUtil.calcPage(reqParams);
		 
		reqParams.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptUserMList");

		List list = comService.selectCommonQueryList(reqParams);

		reqParams.put("list", list);
		model.addAllAttributes(reqParams);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : regFsysDeptUserRDtl
     * 2. 작성일 : 2021. 4. 19. 오후 2:46:18
     * 3. 작성자 : ilyong
     * 4. 설명 : 부서별 사용자 등록 처리
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/regFsysDeptUserRDtl.do")
	public ModelAndView regFsysDeptUserRDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	
		map.put("regrId", user.getUserId());
		map.put("userGb", user.getUserGb());
		
		log.debug("=======regFsysDeptUserRDtl user.getUserId() =======>>>"+user.getUserId());
		log.debug("=======regFsysDeptUserRDtl user.getUserGb() =======>>>"+user.getUserGb());
		
		fsysDeptService.insertFsysDeptUDtl(map);
		//fsysDeptService.modifyFsysDeptUDtl(map);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.insert", null));

		return new ModelAndView(ajaxMainView, model);

	}
    
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysDeptUserMDtl
     * 2. 작성일 : 2021. 4. 19. 오전 10:44:01
     * 3. 작성자 : ilyong
     * 4. 설명 : 부서별 사용자 상세 정보 조회
     * </pre>
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception fsysDeptUserUDtlPop
     */
    @RequestMapping("/fsys/dept/queryFsysDeptUserMDtl.do")
	public ModelAndView queryFsysDeptUserMDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

    	map.put("sqlQueryId", "fsysDeptDAO.queryFsysDeptUserMDtl");
    	Map rtnMap = comService.selectCommonQueryMap(map);
		model.addAttribute("rtnMap", rtnMap);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : updFsysCodeDtlUDtl
     * 2. 작성일 : 2021. 4. 19. 오후 2:46:36
     * 3. 작성자 : ilyong
     * 4. 설명 : 부서별 사용자 상세 정보 수정 처리
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/updFsysDeptUserUDtl.do")
    public ModelAndView updFsysDeptUserUDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,  SessionStatus status) throws Exception {
    	
    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	
    	reqParams.put("regrId", user.getUserId());
    	reqParams.put("userGb", user.getUserGb());
		reqParams.put("sqlQueryId", "fsysDeptDAO.updFsysDeptUserUDtl");
		
		comService.updateCommonQuery(reqParams);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.update", null));
		
        return new ModelAndView(ajaxMainView, model);
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : delFsysDeptUserUDtl
     * 2. 작성일 : 2021. 4. 19. 오후 2:46:47
     * 3. 작성자 : ilyong
     * 4. 설명 : 부서별 사용자 상세 정보 삭제 처리
     * </pre>
     * @param map
     * @param model
     * @param request
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/delFsysDeptUserUDtl.do")
	public ModelAndView delFsysDeptUserUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		map.put("sqlQueryId", "fsysDeptDAO.delFsysDeptUserUDtl");
		comService.updateCommonQuery(map);
		
		//fsysDeptService.modifyFsysDeptUDtl(map);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.delete", null));


		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryUprDeptMList
     * 2. 작성일 : 2021. 4. 15. 오후 3:44:37
     * 3. 작성자 : ilyong
     * 4. 설명 : @!@ 상위부서 리스트 조회
     * </pre>
     * @param model
     * @param request
     * @param reqParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/dept/queryUprDeptMList.do")
	public ModelAndView queryUprDeptMList(ModelMap model,
			HttpServletRequest request, @RequestParam HashMap reqParams) throws Exception {
    	
    	
    	log.debug("===========queryUprDeptMList  deptLvl=============>>>"+reqParams.get("deptLvl"));
    	log.debug("===========queryUprDeptMList  selDeptLvl=============>>>"+reqParams.get("selDeptLvl"));
    	reqParams.put("deptLvl", reqParams.get("selDeptLvl"));
		reqParams.put("sqlQueryId", "fsysDeptDAO.queryUprDeptMList");
		
		//List list = null;
		List list = comService.selectCommonQueryList(reqParams);

		reqParams.put("list", list);
		model.addAllAttributes(reqParams);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
	 * 
	 * <pre>
	 * 1. 메소드명 : modifyFsysDeptUDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:20:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 상세 정보 수정
	 * </pre>
	 * 
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/dept/modifyFsysDeptUDtl.do")
	public ModelAndView modifyFsysDeptUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	   	
		map.put("regrId", user.getUserId());
		log.debug("=======modifyFsysDeptUDtl user.getUserId() =======>>>"+user.getUserId());

        HashMap<String,String> requireParams = new HashMap();
        requireParams.put("teamNm", "팀명");
        validateUtil.check(map,requireParams);

		fsysDeptService.modifyFsysDeptUDtl(map);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.update", null));

		return new ModelAndView(ajaxMainView, model);

	}
    

}	