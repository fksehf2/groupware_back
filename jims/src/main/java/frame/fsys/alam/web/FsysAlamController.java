package frame.fsys.alam.web;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

//import jims.site.rcv.service.SiteRcvService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.property.EgovPropertyService;
import frame.fcom.service.ComService;
import frame.fexception.MException;
import frame.fsys.alam.service.FsysAlamService;
import frame.futil.JimsConst;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;
import frame.futil.Websocket;

/**
 * 
 * <pre>
 * 1. 클래스명 : fsysAlamController.java
 * 2. 작성일 : 2021. 6. 2.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 알람 담당
 * </pre>
 */
@Controller
public class FsysAlamController {

	protected Logger log = LoggerFactory.getLogger(FsysAlamController.class);
	
	@Resource(name="comService")
	private ComService comService;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource MappingJackson2JsonView ajaxMainView;	
	
	 /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name = "fsysAlamService")
	private FsysAlamService fsysAlamService;
    
    @Resource(name = "validateUtil")
	private ValidateUtil validateUtil;
    
    /*@Resource(name="comController")
    ComController comController;    */

    /**
     * 
     * <pre>
     * 1. 메소드명 : regEvdcWarnAlam
     * 2. 작성일 : 2021. 6. 2. 오전 11:32:14
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 미허가 장비 반출 알람 등록
     * </pre>
     * @param request
     * @param model
     * @param reqParams
     * @throws Exception
     */
    @RequestMapping(value="/fsys/alam/regEvdcWarnAlam.do")
    public void regEvdcWarnAlam(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams) throws Exception {
    	
    	fsysAlamService.regEvdcWarnAlam(reqParams);
    	
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : updEvdcWarnAlamCfrmY
     * 2. 작성일 : 2021. 6. 2. 오전 11:32:14
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 미허가 장비 반출 알람 확인
     * </pre>
     * @param request
     * @param model
     * @param reqParams
     * @throws Exception
     */
    @RequestMapping(value="/fsys/alam/updEvdcWarnAlamCfrmY.do")
    public ModelAndView updEvdcWarnAlamCfrmY(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams) throws Exception {
    	
    	fsysAlamService.updEvdcWarnAlamCfrmY(reqParams);
    	
//    	model.addAttribute(JimsConst.Messages_SysSucMessage,
//				egovMessageSource.getMessageArgs("success.common.insert", null));
    	
    	return new ModelAndView(ajaxMainView, model);
    	
    }
    
}	