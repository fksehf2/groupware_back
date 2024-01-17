package frame.fsys.code.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import frame.fcom.service.ComService;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.code.service.FsysCodeService;
import frame.futil.JimsConst;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysCodeController.java
 * 2. 작성일 : 2021. 4. 15.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 코드 관리 담당
 * </pre>
 */
@Controller
public class FsysCodeController {

	protected Logger log = LoggerFactory.getLogger(FsysCodeController.class);
	
	@Resource(name="comService")
	private ComService comService;
	
	@Resource(name="fsysCodeService")
	private FsysCodeService fsysCodeService;
	
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
     * 1. 메소드명 : indexFsysCodeMList
     * 2. 작성일 : 2021. 4. 15. 오후 12:43:51
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 코드 관리 리스트 화면 이동
     * </pre>
     * @param request
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fsys/code/indexFsysCodeMList.do")
    public String indexFsysCodeMList(HttpServletRequest request, ModelMap model, SessionStatus status) throws Exception {

    	//공통으로 필요한 데이타.(권한체크등...)
//    	model = comController.common(request, model);
//
//    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
//    	model.addAttribute("SessionID", loginVO.getUserId());

//    	status.setComplete();

    	return "frame/fsys/code/fsysCodeMList";
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysCodeMList
     * 2. 작성일 : 2021. 4. 15. 오후 12:44:55
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 그룹 코드 관리 리스트 조회
     * </pre>
     * @param model
     * @param request
     * @param reqParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/queryFsysCodeMList.do")
	public ModelAndView queryFsysCodeMList(ModelMap model,
			HttpServletRequest request, @RequestParam HashMap reqParams) throws Exception {

		reqParams.put("sqlQueryId", "fsysCodeDAO.queryFsysCodeMListTotCnt");
		int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
 		reqParams.put("totalCount", totCnt);
 		
 		PageUtil.calcPage(reqParams);
		 
		reqParams.put("sqlQueryId", "fsysCodeDAO.queryFsysCodeMList");

		List list = comService.selectCommonQueryList(reqParams);

		reqParams.put("list", list);
		model.addAllAttributes(reqParams);

		return new ModelAndView(ajaxMainView, model);

	}

    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysCodeDtlMList
     * 2. 작성일 : 2021. 4. 15. 오후 3:44:37
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 상세 코드 리스트 조회 
     * </pre>
     * @param model
     * @param request
     * @param reqParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/queryFsysCodeDtlMList.do")
	public ModelAndView queryFsysCodeDtlMList(ModelMap model,
			HttpServletRequest request, @RequestParam HashMap reqParams) throws Exception {

		reqParams.put("sqlQueryId", "fsysCodeDAO.queryFsysCodeDtlMListTotCnt");
		int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
 		reqParams.put("totalCount", totCnt);
 		
 		PageUtil.calcPage(reqParams);
		 
		reqParams.put("sqlQueryId", "fsysCodeDAO.queryFsysCodeDtlMList");

		List list = comService.selectCommonQueryList(reqParams);

		reqParams.put("list", list);
		model.addAllAttributes(reqParams);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysCodeMDtl
     * 2. 작성일 : 2021. 4. 19. 오전 10:43:39
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 그룹 코드 조회
     * </pre>
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/queryFsysCodeMDtl.do")
	public ModelAndView queryFsysCodeMDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

    	map.put("sqlQueryId", "fsysCodeDAO.queryFsysCodeMDtl");
    	Map rtnMap = comService.selectCommonQueryMap(map);
		model.addAttribute("rtnMap", rtnMap);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : regFsysCodeRDtl
     * 2. 작성일 : 2021. 4. 19. 오전 11:51:45
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 그룹 코드 관리 등록
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/regFsysCodeRDtl.do")
    public ModelAndView regFsysCodeRDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,  SessionStatus status) throws Exception {
    	
    	HashMap<String,String> requireParams = new HashMap();
    	requireParams.put("cdIdNm", "그룹코드명");
    	validateUtil.check(reqParams, requireParams);
    	
		reqParams.put("sqlQueryId", "fsysCodeDAO.regFsysCodeRDtl");
		comService.updateCommonQuery(reqParams);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.insert", null));
		
        return new ModelAndView(ajaxMainView, model);
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : updFsysCodeUDtl
     * 2. 작성일 : 2021. 4. 19. 오전 11:16:02
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 그룹 코드 관리 수정
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/updFsysCodeUDtl.do")
    public ModelAndView updFsysCodeUDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,  SessionStatus status) throws Exception {
    	
    	HashMap<String,String> requireParams = new HashMap();
    	requireParams.put("cdIdNm", "그룹코드명");
    	validateUtil.check(reqParams, requireParams);
    	
		reqParams.put("sqlQueryId", "fsysCodeDAO.updFsysCodeUDtl");
		comService.updateCommonQuery(reqParams);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.update", null));
		
        return new ModelAndView(ajaxMainView, model);
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : deleteAdminMenuDtl
     * 2. 작성일 : 2021. 4. 19. 오전 11:50:38
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 그룹 코드 관리 삭제 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/delFsysCodeUDtl.do")
	public ModelAndView delFsysCodeUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		map.put("sqlQueryId", "fsysCodeDAO.delFsysCodeUDtl");
		comService.updateCommonQuery(map);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.delete", null));


		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysCodeDtlMDtl
     * 2. 작성일 : 2021. 4. 19. 오전 10:44:01
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 상세 코드 조회 
     * </pre>
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/queryFsysCodeDtlMDtl.do")
	public ModelAndView queryFsysCodeDtlMDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

    	map.put("sqlQueryId", "fsysCodeDAO.queryFsysCodeDtlMDtl");
    	Map rtnMap = comService.selectCommonQueryMap(map);
		model.addAttribute("rtnMap", rtnMap);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : regFsysCodeDtlRDtl
     * 2. 작성일 : 2021. 4. 19. 오후 2:46:18
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 상세 코드 등록 
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/regFsysCodeDtlRDtl.do")
    public ModelAndView regFsysCodeDtlRDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,  SessionStatus status) throws Exception {
    	
		model.addAttribute(JimsConst.Messages_SysSucMessage, fsysCodeService.regFsysCodeDtlRDtl(reqParams));
		
        return new ModelAndView(ajaxMainView, model);
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : updFsysCodeDtlUDtl
     * 2. 작성일 : 2021. 4. 19. 오후 2:46:36
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 상세 코드 수정 
     * </pre>
     * @param request
     * @param reqParams
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/updFsysCodeDtlUDtl.do")
    public ModelAndView updFsysCodeDtlUDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,  SessionStatus status) throws Exception {
    	
    	HashMap<String,String> requireParams = new HashMap();
    	requireParams.put("cdNm", "상세코드명");
    	validateUtil.check(reqParams, requireParams);
    	
		reqParams.put("sqlQueryId", "fsysCodeDAO.updFsysCodeDtlUDtl");
		comService.updateCommonQuery(reqParams);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.update", null));
		
        return new ModelAndView(ajaxMainView, model);
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : delFsysCodeDtlUDtl
     * 2. 작성일 : 2021. 4. 19. 오후 2:46:47
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 상세 코드 삭제 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/code/delFsysCodeDtlUDtl.do")
	public ModelAndView delFsysCodeDtlUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		map.put("sqlQueryId", "fsysCodeDAO.delFsysCodeDtlUDtl");
		comService.updateCommonQuery(map);
		
		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.delete", null));


		return new ModelAndView(ajaxMainView, model);

	}

}	