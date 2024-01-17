package frame.fsys.menu.web;

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
import frame.fsys.menu.service.FsysMenuService;
import frame.futil.JimsConst;
import frame.futil.PageUtil;
import frame.futil.ValidateUtil;

/**
 * <pre>
 * 1. 클래스명 : FsysMenuController.java
 * 2. 작성일 : 2021. 4. 14.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 메뉴 관리 담당
 * </pre>
 */
@Controller
public class FsysMenuController {

	protected Logger log = LoggerFactory.getLogger(FsysMenuController.class);

	@Resource(name = "comService")
	private ComService comService;

	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource
	MappingJackson2JsonView ajaxMainView;
	
	@Resource(name = "validateUtil")
	private ValidateUtil validateUtil;

	@Resource(name = "fsysMenuService")
	private FsysMenuService fsysMenuService;

	/*
	 * @Resource(name="comController") ComController comController;
	 */

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFsysMenuMList
	 * 2. 작성일 : 2021. 4. 14. 오전 11:18:24
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 리스트 화면 이동
	 * </pre>
	 * 
	 * @param request
	 * @param menuvo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fsys/menu/indexFsysMenuMList.do")
	public String indexFsysMenuMList(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

		return "frame/fsys/menu/fsysMenuMList";
	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryFsysMenuMList
	 * 2. 작성일 : 2021. 4. 14. 오전 11:18:41
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 리스트 조회
	 * </pre>
	 * 
	 * @param uservo
	 * @param model
	 * @param request
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/menu/queryFsysMenuMList.do")
	public ModelAndView queryFsysMenuMList(ModelMap model,
			HttpServletRequest request, @RequestParam HashMap reqParams) throws Exception {

		reqParams.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuMListTotCnt");
		int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
 		reqParams.put("totalCount", totCnt);
 		
 		PageUtil.calcPage(reqParams);
		 
		reqParams.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuMList");

		List list = comService.selectCommonQueryList(reqParams);

		reqParams.put("list", list);
		model.addAllAttributes(reqParams);

		return new ModelAndView(ajaxMainView, model);

	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFsysMenuRDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:18:56
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 등록 화면 이동
	 * </pre>
	 * 
	 * @param map
	 * @param request
	 * @param menuvo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fsys/menu/indexFsysMenuRDtl.do")
	public String indexFsysMenuRDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

		return "frame/fsys/menu/fsysMenuRDtl";
	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryFsysMenuUpper
	 * 2. 작성일 : 2021. 4. 14. 오전 11:19:29
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 등록 필요 정보 조회
	 * </pre>
	 * 
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/menu/queryFsysMenuUpper.do")
	public ModelAndView queryFsysMenuUpper(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

		map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuUpperList");
		List sysGrpList = comService.selectCommonQueryList(map);
		model.addAttribute("sysGrpList", sysGrpList);

		map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuUpperId");
		Map rtnMap = comService.selectCommonQueryMap(map);
		model.addAttribute("topMenuNo", rtnMap.get("topMenuNo"));

		if (map.get("menuNo") != null && !map.get("menuNo").equals("")) {
			map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuMDtl");
			Map resultMap = comService.selectCommonQueryMap(map);
			model.addAttribute("resultMap", resultMap);
		}

		return new ModelAndView(ajaxMainView, model);

	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regFsysMenuRDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:20:03
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 등록
	 * </pre>
	 * 
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/menu/regFsysMenuRDtl.do")
	public ModelAndView regFsysMenuRDtl(@RequestParam HashMap<String, String> reqParams, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		String[] message = fsysMenuService.regFsysMenuRDtl(reqParams);

		model.addAttribute(message[0], message[1]);

		return new ModelAndView(ajaxMainView, model);

	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFsysMenuUDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:20:26
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 수정 화면 이동
	 * </pre>
	 * 
	 * @param map
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fsys/menu/indexFsysMenuUDtl.do")
	public String indexFsysMenuUDtl(@RequestParam HashMap<String, String> map, HttpServletRequest request,
			ModelMap model) throws Exception {

		return "frame/fsys/menu/fsysMenuUDtl";

	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updFsysMenuUDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:20:39
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 수정
	 * </pre>
	 * 
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/menu/updFsysMenuUDtl.do")
	public ModelAndView updFsysMenuUDtl(@RequestParam HashMap<String, String> reqParams, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		String[] message = fsysMenuService.updFsysMenuUDtl(reqParams);

		model.addAttribute(message[0], message[1]);

		return new ModelAndView(ajaxMainView, model);
	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : deleteAdminMenuDtl
	 * 2. 작성일 : 2021. 4. 14. 오전 11:20:53
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 관리 삭제
	 * </pre>
	 * 
	 * @param map
	 * @param model
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fsys/menu/delFsysMenuUDtl.do")
	public ModelAndView deleteAdminMenuDtl(@RequestParam HashMap<String, String> map, ModelMap model,
			HttpServletRequest request, SessionStatus status) throws Exception {

		map.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuLowerCnt");

		int totCnt = comService.selectCommonQueryListTotCnt(map);

		if (totCnt > 0) {

			model.addAttribute(JimsConst.Messages_UserErrMessage,
					egovMessageSource.getMessageArgs("admin.menu.001", null));

		} else {

			map.put("sqlQueryId", "fsysMenuDAO.delFsysMenuUDtl");
			comService.updateCommonQuery(map);
			model.addAttribute(JimsConst.Messages_SysSucMessage,
					egovMessageSource.getMessageArgs("success.common.delete", null));

		}

		return new ModelAndView(ajaxMainView, model);

	}

}