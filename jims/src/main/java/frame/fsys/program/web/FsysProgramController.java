package frame.fsys.program.web;

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
import frame.fcom.service.ComService;
import frame.fsys.program.service.FsysProgramService;
import frame.futil.JimsConst;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;

/**
 * <pre>
 * 1. 클래스명 : FsysProgramController.java
 * 2. 작성일 : 2021. 4. 14.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 프로그램 관리 담당
 * </pre>
 */
@Controller
public class FsysProgramController {

	protected Logger log = LoggerFactory.getLogger(FsysProgramController.class);

	@Resource(name = "comService")
	private ComService comService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource
	MappingJackson2JsonView ajaxMainView;
	
	@Resource(name = "validateUtil")
	private ValidateUtil validateUtil;
	
	@Resource(name = "fsysProgramService")
	private FsysProgramService fsysProgramService;

	/*
	 * @Resource(name="comController") ComController comController;
	 */

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFsysProgramMList
	 * 2. 작성일 : 2021. 4. 14. 오전 11:16:36
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 리스트 화면 이동
	 * </pre>
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fsys/program/indexFsysProgramMList.do")
	public String indexFsysProgramMList(HttpServletRequest request, ModelMap model) throws Exception {

		return "frame/fsys/program/fsysProgramMList";
	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryFsysProgramMList
	 * 2. 작성일 : 2021. 4. 14. 오전 11:17:00
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그래 관리 리스트 조회
	 * </pre>
	 * 
	 * @param request
	 * @param model
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fsys/program/queryFsysProgramMList.do")
	public ModelAndView queryFsysProgramMList(HttpServletRequest request, ModelMap model,
			@RequestParam HashMap reqParams) throws Exception {

		reqParams.put("sqlQueryId", "fsysProgramDAO.queryFsysProgramMListTotCnt");
		int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
 		reqParams.put("totalCount", totCnt);
 		
 		PageUtil.calcPage(reqParams);
 		
		reqParams.put("sqlQueryId", "fsysProgramDAO.queryFsysProgramMList");

		List list = comService.selectCommonQueryList(reqParams);

		reqParams.put("list", list);
		model.addAllAttributes(reqParams);

		return new ModelAndView(ajaxMainView, model);

	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFsysProgramRDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:54:43
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 등록 화면 이동
	 * </pre>
	 * 
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fsys/program/indexFsysProgramRDtl.do")
	public String indexFsysProgramRDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

		return "frame/fsys/program/fsysProgramRDtl";
	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regFsysProgramRDtl
	 * 2. 작성일 : 2021. 4. 14. 오후 12:46:41
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 등록
	 * </pre>
	 * 
	 * @param request
	 * @param reqParams
	 * @param model
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/program/regFsysProgramRDtl.do")
	public ModelAndView regFsysProgramRDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,
			SessionStatus status) throws Exception {

		HashMap<String,String> requireParams = new HashMap();
		requireParams.put("programPath", "프로그램 PATH");
		requireParams.put("programNm", "프로그램 (영문)");
		requireParams.put("programExpl", "프로그램 (한글)");
		requireParams.put("url", "URL");
		validateUtil.check(reqParams, requireParams);

		reqParams.put("sqlQueryId", "fsysProgramDAO.regFsysProgramRDtl");

		comService.updateCommonQuery(reqParams);

		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.insert", null));

		return new ModelAndView(ajaxMainView, model);

	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFsysProgramUDtl
	 * 2. 작성일 : 2021. 4. 14. 오후 4:26:28
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 수정 화면 이동
	 * </pre>
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fsys/program/indexFsysProgramUDtl.do")
	public String indexFsysProgramUDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

		return "frame/fsys/program/fsysProgramUDtl";
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryFsysProgramRDtl
	 * 2. 작성일 : 2021. 4. 14. 오후 6:45:07
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 상세 조회
	 * </pre>
	 * @param request
	 * @param reqParams
	 * @param model
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/program/queryFsysProgramRDtl.do")
	public ModelAndView queryFsysProgramRDtl(HttpServletRequest request, @RequestParam HashMap reqParams, ModelMap model,
			SessionStatus status) throws Exception {

		reqParams.put("sqlQueryId", "fsysProgramDAO.queryFsysProgramRDtl");

		Map resultMap = comService.selectCommonQueryMap(reqParams);
		model.addAttribute("resultMap", resultMap);

		return new ModelAndView(ajaxMainView, model);

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updFsysProgramUDtl
	 * 2. 작성일 : 2021. 5. 14. 오전 10:54:02
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 수정
	 * </pre>
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/program/updFsysProgramUDtl.do")
	public ModelAndView updFsysProgramUDtl(@RequestParam HashMap<String, String> reqParams, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		HashMap<String,String> requireParams = new HashMap();
		requireParams.put("programPath", "프로그램 PATH");
		requireParams.put("programNm", "프로그램 (영문)");
		requireParams.put("programExpl", "프로그램 (한글)");
		requireParams.put("url", "URL");
		validateUtil.check(reqParams, requireParams);
		
		reqParams.put("sqlQueryId", "fsysProgramDAO.updFsysProgramUDtl");
		comService.updateCommonQuery(reqParams);

		model.addAttribute(JimsConst.Messages_SysSucMessage,
				egovMessageSource.getMessageArgs("success.common.update", null));

		return new ModelAndView(ajaxMainView, model);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysProgramUDtl
	 * 2. 작성일 : 2021. 5. 14. 오전 10:54:19
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 삭제
	 * </pre>
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/program/delFsysProgramUDtl.do")
	public ModelAndView delFsysProgramUDtl(@RequestParam HashMap<String, String> reqParams, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		model.addAttribute(JimsConst.Messages_SysSucMessage, fsysProgramService.delFsysProgramUDtl(reqParams));

		return new ModelAndView(ajaxMainView, model);

	}
}