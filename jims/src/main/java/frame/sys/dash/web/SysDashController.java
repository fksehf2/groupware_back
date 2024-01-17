package frame.sys.dash.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import frame.sys.dash.service.SysDashService;
import egovframework.com.cmm.EgovMessageSource;
import frame.fcom.service.ComService;
import frame.fexception.MException;
import frame.futil.JimsConst;
/**
 * 
 * <pre>
 * 1. 클래스명 : SysDashController.java
 * 2. 작성일 : 2021. 4. 26.
 * 3. 작성자 : jmkim
 * 4. 설명 : Dashboard
 * </pre>
 */
@Controller
public class SysDashController {

	protected Logger log = LoggerFactory.getLogger(SysDashController.class);

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "comService")
	private ComService comService;

	@Resource(name = "sysDashService")
	private SysDashService sysDashService;
	
    @Resource MappingJackson2JsonView ajaxMainView;

    /**
     * 
     * <pre>
     * 1. 메소드명 : indexSysDashIDtl
     * 2. 작성일 : 2021. 4. 26. 오후 4:50:37
     * 3. 작성자 : jmkim
     * 4. 설명 : Dashboard 화면 로딩
     * </pre>
     * @param request
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/sys/dash/indexSysDashIDtl.do")
    public String indexSysDashIDtl(HttpServletRequest request, ModelMap model, @RequestParam HashMap<String,String> map) throws Exception {
    	
        return "frame/sys/dash/sysDashIDtl";
        
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : querySysDashIDtl
     * 2. 작성일 : 2021. 5. 20. 오후 4:42:30
     * 3. 작성자 : jmkim
     * 4. 설명 : Dashboard 조회
     * </pre>
     * @param request
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/sys/dash/querySysDashIDtl.do")
	public ModelAndView querySysDashIDtl(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {
    	
		map.put("sqlQueryId", "sysDashDAO.querySysDashIDtlMonAnal");
		List userList = comService.selectCommonQueryList(map);

		model.addAttribute("findIdInfo",userList);
		
		map.put("sqlQueryId", "sysDashDAO.querySysDashIDtlReqInfo");
		List reqList = comService.selectCommonQueryList(map);

		model.addAttribute("reqList",reqList);

		map.put("sqlQueryId", "sysDashDAO.querySysDashIDtlAnalInfo");
		List analList = comService.selectCommonQueryList(map);

		model.addAttribute("analList",analList);
		
		map.put("sqlQueryId", "sysDashDAO.querySysDashIDtlMediaInfo");
		List mediaList = comService.selectCommonQueryList(map);
		
		model.addAttribute("mediaList",mediaList);
		
		map.put("sqlQueryId", "sysDashDAO.querySysDashIDtlEqpInfo");
		List eqpList = comService.selectCommonQueryList(map);
		
		model.addAttribute("eqpList",eqpList);

		return new ModelAndView(ajaxMainView, model);

	}
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexSysDashUDtl
     * 2. 작성일 : 2021. 5. 21. 오후 2:53:08
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 대시보드관리 화면이동
     * </pre>
     * @param request
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/sys/dash/indexSysDashUDtl.do")
    public String indexSysDashUDtl(HttpServletRequest request, ModelMap model, SessionStatus status) throws Exception {
    	
    	return "frame/sys/dash/sysDashUDtl";
    	
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : querySysDashUDtl
     * 2. 작성일 : 2021. 5. 21. 오후 2:53:22
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 대시보드관리 조회
     * </pre>
     * @param map
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/sys/dash/querySysDashUDtl.do")
    public ModelAndView querySysDashUDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
    		ModelMap model) throws Exception {

    	map.put("sqlQueryId", "sysDashDAO.querySysDashUDtl");
    	List list = comService.selectCommonQueryList(map);
    	model.addAttribute("list", list);

    	return new ModelAndView(ajaxMainView, model);

    }

    /**
     * 
     * <pre>
     * 1. 메소드명 : regSysDashUDtl
     * 2. 작성일 : 2021. 5. 21. 오후 2:53:36
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 대시보드관리 저장
     * </pre>
     * @param request
     * @param paramData
     * @param model
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/sys/dash/regSysDashUDtl.do")
    public ModelAndView regSysDashUDtl(HttpServletRequest request, @RequestBody String paramData, ModelMap model,  SessionStatus status) throws Exception {

    	sysDashService.regSysDashUDtl(paramData);

    	return new ModelAndView(ajaxMainView, model);

    }

}