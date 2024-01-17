package frame.fsys.log.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import frame.fcom.service.ComService;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.log.service.FsysLogService;
import frame.futil.PageUtil;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysLogController.java
 * 2. 작성일 : 2021. 4. 26.
 * 3. 작성자 : sjw
 * 4. 설명 :  로그 관리
 * </pre>
 */
@Controller
public class FsysLogController {

	protected Logger log = LoggerFactory.getLogger(FsysLogController.class);
	
	@Resource(name="comService")
	private ComService comService;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "fsysLogService")
	private FsysLogService fsysLogService;

	@Resource MappingJackson2JsonView ajaxMainView;	
	
	 /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /*@Resource(name="comController")
    ComController comController;    */

    /**
     * 
     * <pre>
     * 1. 메소드명 : indexFsysLogQList
     * 2. 작성일 : 2021. 4. 26.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 로그 관리 목록페이지 진입시 호출 
     * </pre>
     * @param request
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fsys/log/indexFsysLogQList.do")
    public String indexFsysLogQList(HttpServletRequest request, ModelMap model, @RequestParam HashMap<String,String> map) throws Exception {

    	return "frame/fsys/log/fsysLogQList";
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysLogQList
     * 2. 작성일 : 2021. 4. 26.
     * 3. 작성자 : sjw7240
     * 4. 설명 : 로그 리스트 조회 조회시 호출 
     * </pre>
     * @param model
     * @param request
     * @param reqParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/log/queryFsysLogQList.do")
    public ModelAndView queryBbsRschMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

       	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	reqParams.put("userGb", user.getUserGb());
    	reqParams.put("regrId", user.getUserId());
    	
		reqParams.put("sqlQueryId", "fsysLogDAO.queryFsysLogQListTotCnt");
    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
    	reqParams.put("totalCount", totCnt);
    	
    	PageUtil.calcPage(reqParams);

		reqParams.put("sqlQueryId", "fsysLogDAO.selectFsysLogList");
		List fsysLogList = comService.selectCommonQueryList(reqParams);
		reqParams.put("fsysLogList", fsysLogList);
		model.addAllAttributes(reqParams);
		
		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFsysLogDtlMList
     * 2. 작성일 : 2021. 4. 30
     * 3. 작성자 : sjw7240
     * 4. 설명 : 로그 상세 조회
     * </pre>
     * @param model
     * @param request
     * @param reqParams
     * @return
     * @throws Exception
     */
    @RequestMapping("/fsys/log/queryFsysLogDtlQList.do")
    public ModelAndView queryFsysLogDtlQList(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {
    	
    	reqParams.put("sqlQueryId", "fsysLogDAO.queryFsysLogDtlQList");
    	Map rtnMap = comService.selectCommonQueryMap(reqParams);
    	model.addAttribute("rtnMap", rtnMap);
		
		return new ModelAndView(ajaxMainView, model);

	}

}	