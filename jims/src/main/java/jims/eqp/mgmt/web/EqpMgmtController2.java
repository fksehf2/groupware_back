package jims.eqp.mgmt.web;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import jims.eqp.mgmt.service.EqpDto;
import jims.eqp.mgmt.service.EqpMgmtService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import frame.fcom.service.ComService;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.user.service.FsysUserVO;
import frame.futil.JimsConst;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;


/**
 * 
 * <pre>
 * 1. 클래스명 : EqpMgmtController.java
 * 2. 작성일 : 2021. 7. 19.
 * 3. 작성자 : sjw7240
 * 4. 설명 : 장비 관리 컨트롤러
 * </pre>
 */
@RestController
public class EqpMgmtController2 {

	protected Logger log = LoggerFactory.getLogger(EqpMgmtController2.class);

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "comService")
	private ComService comService;
	
	@Resource(name = "eqpMgmtService")
	private EqpMgmtService eqpMgmtService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "validateUtil")
	private ValidateUtil validateUtil;

    @Resource MappingJackson2JsonView ajaxMainView;

    
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexEqpMgmtMList
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 목록 진입 컨트롤러
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
//    @RequestMapping(value="/eqp/mgmt/indexEqpMgmtMList.do")
//    public String indexEqpMgmtMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap<String,String> map) throws Exception {
//    	
//    	model.addAttribute("message", request.getParameter("message"));
//    	if(request.getParameter("msg") != null){
//    		model.addAttribute("msg", request.getParameter("msg"));
//    	}
//    	return "jims/eqp/mgmt/eqpMgmtMList";
//    }


	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryEqpMgmtMList
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 목록 조회 컨트롤러
	 * </pre>
	 * @param request
	 * @param model
	 * @param reqParams
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @GetMapping("/erpList")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List eqpList (@RequestParam int offset, @RequestParam int limit) throws Exception{
    	
//    	reqParams.put("sqlQueryId", "eqpMgmtDAO.eqpMgmtMListTotCnt");
//    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
//    	reqParams.put("totalCount", totCnt);
//    	
    	
//		reqParams.put("sqlQueryId", "eqpMgmtDAO.eqpMgmtMList");
    	System.out.println("Received offset: " + offset + ", limit: " + limit);
    	int totCnt = eqpMgmtService.getErpTotCnt();
		List eqpMgmtMList = eqpMgmtService.getEqpList(offset,limit);
		eqpMgmtMList.add(totCnt);
		return eqpMgmtMList;
    	
    }
    
    @GetMapping("/erpCode/{cdId}")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List erpCode(@PathVariable String cdId) throws Exception {
    	System.out.println("cdId     "+ cdId);
    	List erpCode = eqpMgmtService.getCode(cdId); 
		return erpCode;
    }
    
    @GetMapping("/erpDtl")
    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    public List erpDtl(@RequestParam String eqpSno) throws Exception{
    	System.out.println("eqpSno     "+ eqpSno);
    	List erpDtl = eqpMgmtService.erpDtl(eqpSno);
    	return erpDtl;
    }
    
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexEqpMgmtRDtl
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 :  장비 관리 등록 진입 컨트롤러
     * </pre>
     * @param map
     * @param request
     * @param fsysUserVO
     * @param model
     * @return
     * @throws Exception
     */
//    @RequestMapping(value="/eqp/mgmt/indexEqpMgmtRDtl.do")
//    public String indexEqpMgmtRDtl(@RequestParam HashMap<String,String> map,HttpServletRequest request, @ModelAttribute("fsysUserVO") FsysUserVO fsysUserVO, ModelMap model) throws Exception {
//
//    	return "jims/eqp/mgmt/eqpMgmtRDtl";
//    }
//    
	
    	/**
    	 * 
    	 * <pre>
    	 * 1. 메소드명 : regEqpMgmtRDtl
    	 * 2. 작성일 : 2021. 7. 19.
    	 * 3. 작성자 : sjw7240
    	 * 4. 설명 : 장비 관리 등록 컨트롤러 
    	 * </pre>
    	 * @param map
    	 * @param model
    	 * @param request
    	 * @param status
    	 * @return
    	 * @throws Exception
    	 */
//    @RequestMapping("/eqp/mgmt/regEqpMgmtRDtl.do")
//	 public ModelAndView regEqpMgmtRDtl( @RequestParam HashMap<String,String> map, ModelMap model,
//			 HttpServletRequest request, SessionStatus status) throws Exception {
//
//    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//
//        //필수값 확인
//        HashMap<String,String> requireParams = new HashMap();
//		requireParams.put("eqpBuyDiv", "장비도입구분");
//		requireParams.put("eqpNm", "장비명");
//		requireParams.put("eqpTyp", "장비유형");
//		requireParams.put("mdlNm", "모델명");
//		requireParams.put("hldPlc", "보유장소");
//        validateUtil.check(map, requireParams);
//    	
//    	eqpMgmtService.regEqpMgmtRDtl(map);
//    	
//		return new ModelAndView(ajaxMainView, model);
//		
//	}
    

    
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexEqpMgmtUDtl
     * 2. 작성일 : 2021. 7. 19.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 장비 관리 상세 진입 컨트롤러
     * </pre>
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
//	@RequestMapping(value="/eqp/mgmt/indexEqpMgmtUDtl.do")
//	public String indexEqpMgmtUDtl(@RequestParam HashMap<String,String> map,HttpServletRequest request, ModelMap model) throws Exception {
//	
//		return "jims/eqp/mgmt/eqpMgmtUDtl";
//		
//	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryEqpMgmtUDtl
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 상세 조회 컨트롤러
	 * </pre>
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="/eqp/mgmt/queryEqpMgmtUDtl.do")
//	public ModelAndView queryEqpMgmtUDtl(@RequestParam HashMap map,HttpServletRequest request, ModelMap model) throws Exception {
//	
//		map.put("sqlQueryId", "eqpMgmtDAO.queryEqpMgmtUDtl");
//		Map eqpMgmtInfoMap = comService.selectCommonQueryMap(map);
//		map.put("eqpMgmtInfoMap", eqpMgmtInfoMap);
//	  	
//	 	model.addAllAttributes(map); 
//	 	 
//	 	return new ModelAndView(ajaxMainView, model);
//		
//	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEqpMgmtUDtl
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 수정 컨트롤러
	 * </pre>
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/eqp/mgmt/updEqpMgmtUDtl.do")
//	public ModelAndView updEqpMgmtUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
//			HttpServletRequest request, SessionStatus status) throws Exception {
//		
//    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//
//		HashMap<String,String> requireParams = new HashMap();
//		requireParams.put("eqpBuyDiv", "장비도입구분");
//		requireParams.put("eqpNm", "장비명");
//		requireParams.put("eqpTyp", "장비유형");
//		requireParams.put("mdlNm", "모델명");
//		requireParams.put("hldPlc", "보유장소");
//        validateUtil.check(map, requireParams);
//		
//		eqpMgmtService.updEqpMgmtUDtl(map);
//
//		return new ModelAndView(ajaxMainView, model);
//	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delEqpMgmtUDtl
	 * 2. 작성일 : 2021. 7. 19.
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 장비 관리 삭제 컨트롤러
	 * </pre>
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/eqp/mgmt/delEqpMgmtUDtl.do")
//	public ModelAndView delEqpMgmtUDtl(@RequestParam HashMap<String, String> map, ModelMap model,
//			HttpServletRequest request, SessionStatus status) throws Exception {
//		
//    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//		
//		eqpMgmtService.delEqpMgmtUDtl(map);
//
//		return new ModelAndView(ajaxMainView, model);
//	}
	
	/**
     * 
     * <pre>
     * 1. 메소드명 : queryEqpMgmtFIndEqupQListPop
     * 2. 작성일 : 2021. 7. 20. 오전 10:49:36
     * 3. 작성자 : ilyong
     * 4. 설명 : 장비 조회 팝업
     * </pre>
     * @param request
     * @param model
     * @param reqParams
     * @param response
     * @return
     * @throws Exception /eqp/mgmt/queryEqpMgmtFIndEqupQListPop.do
     */
//    @RequestMapping(value="/eqp/mgmt/queryEqpMgmtFIndEqupQListPop.do")
//    public ModelAndView queryEqpMgmtFIndEqupQListPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {
//
//		reqParams.put("sqlQueryId", "eqpMgmtDAO.eqpMgmtMListPopTotCnt");
//    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
//    	reqParams.put("totalCount", totCnt);
//    	
//    	PageUtil.calcPage(reqParams);
//    	
//    	log.debug("=======reqParams schEqpNmPop========>>>"+reqParams.get("schEqpNmPop"));
//    	log.debug("=======reqParams schEqpTypPop========>>>"+reqParams.get("schEqpTypPop"));
//    	
//		reqParams.put("sqlQueryId", "eqpMgmtDAO.eqpMgmtMListPop");
//		List list = comService.selectCommonQueryList(reqParams);
//		reqParams.put("list", list);
//		model.addAllAttributes(reqParams);
//		
//		return new ModelAndView(ajaxMainView, model);
//
//    }
}
