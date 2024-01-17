package frame.fcom.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.property.EgovPropertyService;
import frame.fcom.service.CodeVO;
import frame.fcom.service.ComService;
import frame.fcom.service.FileVO;
import frame.fexception.MException;
import frame.flyt.main.service.MenuVO;
import frame.fsys.user.service.FsysUserService;
import frame.futil.JimsConst;
import frame.futil.ExcelUtil;
import frame.futil.PageUtil;

@Controller
public class ComController {

	protected Logger log = LoggerFactory.getLogger(ComController.class);

	@Resource(name = "comService")
	private ComService comService;

	@Resource(name="fsysUserService")
    private FsysUserService fsysUserService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource
	MappingJackson2JsonView ajaxMainView;

//	public ModelMap common(HttpServletRequest request, ModelMap model) throws Exception {
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//		// String userGb="";
//		if (isAuthenticated) {
//
//			LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
//
//			if (user.getUserGb().equals("C01001")) {// 포털사용자
//				map.put("upperMenuNo", "C01001");
//			} else if (user.getUserGb().equals("C01002")) {// 담당자
//				map.put("upperMenuNo", "C01002");
//			} else if (user.getUserGb().equals("C01999")) {// 관리자 메뉴 C01999
//				map.put("upperMenuNo", "C01999");
//			} else if (user.getUserGb().equals("C01100")) {// 콜센터
//				map.put("upperMenuNo", "C01100");
//			} else {
//				map.put("upperMenuNo", "0000000001");
//			}
//
//		} else {
//			map.put("upperMenuNo", "0000000001");
//		}
//
//		String requestURI = request.getRequestURI().replace(request.getContextPath(), "");
//		if (requestURI.indexOf("PageLink") > -1) {
//			requestURI += "?" + request.getQueryString();
//			map.put("url", requestURI);
//		} else {
//			map.put("url", requestURI);
//		}
//
//		log.debug("Request URI :: " + requestURI);
//
//		// 페이지권한 조회
//		String author = comService.selectAuthor(map);
//
//		if (EgovStringUtil.isEmpty(author)) {
//			// ModelAndView modelAndView = new
//			// ModelAndView("forward:/PageLink.do?link=error/accessDeny", model
//			// );
//			// throw new ModelAndViewDefiningException(modelAndView);
//			author = "R";
//		}
//
//		boolean authorR = false;
//		boolean authorC = false;
//
//		// 읽기권한
//		if (author.indexOf("R") > -1) {
//			authorR = true;
//		}
//
//		// 쓰기권한
//		if (author.indexOf("C") > -1) {
//			authorR = true;
//			authorC = true;
//		}
//
//		log.debug("======페이지권한값 author====>>> " + author);
//		log.debug("======페이지권한값 authorR====>>> " + authorR);
//		log.debug("======페이지권한값 authorC====>>> " + authorC);
//
//		// 페이지권한값 전달.
//		model.addAttribute("author", author);
//		model.addAttribute("authorR", authorR);
//		model.addAttribute("authorC", authorC);
//
//		return model;
//
//		// boolean authorR = false;
//		/*
//		 * boolean authorC9 = false; boolean authorC4 = false; boolean authorC1
//		 * = false; boolean authorC2 = false; boolean authorC3 = false; boolean
//		 * authorC12 = false; //출하자(1)와 중도매인(2) 함께 쓰는 권한 boolean authorC34 =
//		 * false;
//		 */ // 경매사(3)와 법인(4) 함께 쓰는 권한
//
//		/*
//		 * if(userGb.equals("9")||userGb.equals("6")) { authorC9=true; } else
//		 * if(userGb.equals("4")) { authorC4 = true; authorC34 = true; } else
//		 * if(userGb.equals("1")) { authorC1 = true; authorC12 = true; } else
//		 * if(userGb.equals("2")) { authorC2 = true; authorC12 = true; } else
//		 * if(userGb.equals("3") ) { authorC3 = true; authorC34 = true; } else{
//		 * authorC9=false; authorC4=false; authorC1 = false; authorC2 = false;
//		 * authorC3 = false; }
//		 */
//
//		// //읽기권한
//		// if(author.indexOf("R") > -1){
//		// authorR = true;
//		// }
//		//
//		// //쓰기권한
//		// if(author.indexOf("C") > -1){
//		// authorR = true;
//		// authorC = true;
//		// }
//
//		// 페이지권한값 전달.
//		/*
//		 * model.addAttribute("author", author); model.addAttribute("authorR",
//		 * authorR); model.addAttribute("authorC9", authorC9);
//		 * model.addAttribute("authorC4", authorC4);
//		 * model.addAttribute("authorC1", authorC1);
//		 * model.addAttribute("authorC2", authorC2);
//		 * model.addAttribute("authorC3", authorC3);
//		 * model.addAttribute("authorC12", authorC12);
//		 * model.addAttribute("authorC34", authorC34);
//		 */
//
//		// return model;
//	}
//
	/**
	 * JSP 호출작업만 처리하는 공통 함수
	 */
	@RequestMapping(value = "/com/PageLink.do")
	/* public String moveToPage(@RequestParam("link") String linkPage){ */
	public String moveToPage(HttpServletRequest request, ModelMap model) throws Exception {
		String link = "";
		String linkStr = "";
		HashMap<String, String> paramMap = new HashMap<String, String>();

		// 공통으로 필요한 데이타.(권한체크등...)
		//model = common(request, model);

		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() != 0) {
					paramMap.put(paramName, paramValue);
				}

				if ("link".equals(paramName)) {
					linkStr = paramValue;
				}
			} else {
				for (int i = 0; i < paramValues.length; i++) {
					paramMap.put(paramName, paramValues[i]);
				}
			}
		}

		// 서버구분(로컬/개발/운영 : LOCAL/DEV/REAL)
		String serverType = EgovProperties.getProperty("Globals.serverType");
		model.addAttribute("serverType", serverType);
		model.addAttribute("paramMap", paramMap);
		link = linkStr;
		// service 사용하여 리턴할 결과값 처리하는 부분은 생략하고 단순 페이지 링크만 처리함
		if (linkStr == null || linkStr.equals("")) {
			link = "error/Error";
		}
		return link;
	}
	
	
	@RequestMapping(value="/fcom/queryAjaxCodeList.do")
    public ModelAndView queryAjaxCodeList(HttpServletRequest request, ModelMap model, @RequestBody String paramData) throws Exception {

    	//공통으로 필요한 데이타.(권한체크등...)
    	//model = comController.common(request, model);

    	//model.addAttribute("sysGrpList",menuService.admMenuSysGrpList(menuvo));
		ObjectMapper mapper = new ObjectMapper();
      	Map<String, ArrayList<Map<String, Object>>> jsonObject = mapper.readValue(paramData, Map.class);

      	ArrayList<Map<String, Object>> paramRow = jsonObject.get("paramRow");

    	//사용자구분 코드
      	for (int i = 0; i < paramRow.size(); i++) {
      		HashMap map = new HashMap();
        	map.putAll(paramRow.get(i));
        	map.put("sqlQueryId", "comDAO.queryAjaxCodeList");
        	List list = comService.selectCommonQueryList(map);
        	model.addAttribute((String) paramRow.get(i).get("target"), list);
		}

    	return new ModelAndView(ajaxMainView, model);
    }
//
//	/**
//	 * AJAX로 코드 조회
//	 * 
//	 * @param codeId
//	 *            CODE_ID
//	 * @return 코드 목록
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/ajaxCode.do")
//	protected ModelAndView codeList(String codeId, HttpServletRequest request) throws Exception {
//
//		Map<String, Object> model = new HashMap<String, Object>();
//
//		List<CodeVO> codeList = comService.selectCodeList(codeId);
//
//		String[] tempCodId = codeId.split(",");
//
//		model.put("codeList", codeList);
//
//		for (int y = 0; y < tempCodId.length; y++) {
//
//			CodeVO codeInfo = new CodeVO();
//			List<CodeVO> tempCodeList = new ArrayList<CodeVO>();
//
//			for (int i = 0; i < codeList.size(); i++) {
//				codeInfo = codeList.get(i);
//				if (tempCodId[y].equals(codeInfo.getCdId())) {
//					tempCodeList.add(codeInfo);
//				}
//			}
//
//			model.put(tempCodId[y], tempCodeList);
//		}
//
//		return new ModelAndView(ajaxMainView, model);
//	}
//
//	/**
//	 * AJAX로 코드 조회
//	 * 
//	 * @param CD
//	 *            CD
//	 * @return 코드 목록
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/ajaxCode2.do")
//	protected ModelAndView codeList2(String codeId, HttpServletRequest request) throws Exception {
//
//		Map<String, Object> model = new HashMap<String, Object>();
//
//		List<CodeVO> codeList = comService.selectCodeList2(codeId);
//
//		String[] tempCodId = codeId.split(",");
//
//		model.put("codeList", codeList);
//
//		for (int y = 0; y < tempCodId.length; y++) {
//
//			CodeVO codeInfo = new CodeVO();
//			List<CodeVO> tempCodeList = new ArrayList<CodeVO>();
//
//			for (int i = 0; i < codeList.size(); i++) {
//				codeInfo = codeList.get(i);
//				if (tempCodId[y].equals(codeInfo.getCdId())) {
//					tempCodeList.add(codeInfo);
//				}
//			}
//
//			model.put(tempCodId[y], tempCodeList);
//		}
//
//		return new ModelAndView(ajaxMainView, model);
//	}
//
//	/**
//	 * 이미지 보기 팝업
//	 * 
//	 * @param imgPath
//	 *            - 이미지경로
//	 * @return "com/imageViewPop"
//	 * @exception Exception
//	 */
//	@RequestMapping(value = "/imageViewPop.do")
//	public String imageView(@RequestParam("imgPath") String imgPath, ModelMap model) throws Exception {
//		model.addAttribute("author", "R");
//		model.addAttribute("authorR", "true");
//		model.addAttribute("authorC", "false");
//		model.addAttribute("imgPath", imgPath);
//		return SpmsUtil.rootPath("/com/imageViewPop");
//	}
//
//	/**
//	 * 오즈레포트 팝업
//	 * 
//	 * @param ozFileNm
//	 *            - OZ 파일명 ozParam - OZ Param
//	 * @return "com/ozViewPop"
//	 * @exception Exception
//	 */
//	@RequestMapping(value = "/ozViewPop.do")
//	public String ozView(Map<String, Object> commandMap, ModelMap model) throws Exception {
//
//		String ozParam = (String) commandMap.get("ozParam");
//
//		model.addAttribute("ozServerIP", EgovProperties.getProperty("Globals.ozReport.serverIp"));
//		model.addAttribute("ozServerPort", EgovProperties.getProperty("Globals.ozReport.serverPort"));
//		model.addAttribute("ozFileNm", (String) commandMap.get("ozFileNm"));
//		model.addAttribute("odiName", (String) commandMap.get("odiName"));
//		if (!EgovStringUtil.isNull(ozParam)) {
//			model.addAttribute("ozParam", ozParam.split(","));
//		}
//
//		return SpmsUtil.rootPath("/com/ozViewPop");
//
//	}
//
//	/**
//	 * AJAX로 시군 조회
//	 * 
//	 * @param sidoCd
//	 *            시도코드
//	 * @return 시군구 List
//	 * @throws Exception
//	 */
//
//	@RequestMapping(value = "/com/ajaxSigunList.do")
//	protected ModelAndView gunguList(String sidoCd, HttpServletRequest request) throws Exception {
//
//		Map<String, Object> model = new HashMap<String, Object>();
//		Map<String, Object> map = new HashMap<String, Object>();
//
//		map.put("sidoCd", sidoCd);
//
//		List sigunList = comService.ajaxSigunList(map);
//		model.put("sigunList", sigunList);
//
//		return new ModelAndView(ajaxMainView, model);
//	}
//
//	/**
//	 * 
//	 * 
//	 * @author BaekSeungJu
//	 * @param request
//	 * @param model
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 * 
//	 *             <pre>
//	 * admin > 산업단지 검색 초기화면
//	 *             </pre>
//	 */
//
//	@RequestMapping(value = "/popup/indexAdminIndsCpxListPop.do")
//	public String indexAdminIndsCpxListPop(HttpServletRequest request, ModelMap model,
//			@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception {
//
//		List sidoList = comService.sidoList("");
//		model.addAttribute("sidoList", sidoList);
//		model.addAttribute("paramMap", map);
//
//		return "/popup/adminIndsCpxListPop";
//
//	}
//
//	/**
//	 * 
//	 * 
//	 * @author BaekSeungJu
//	 * @param request
//	 * @param model
//	 * @param response
//	 * @param reqParams
//	 * @return
//	 * @throws Exception
//	 * 
//	 *             <pre>
//	 * admin > 산업단지 검색 팝업
//	 *             </pre>
//	 */
//
//	@RequestMapping(value = "/popup/selectAdminIndsCpxListPop.do")
//	public ModelAndView selectAdminIndsCpxListPop(HttpServletRequest request, ModelMap model,
//			HttpServletResponse response, @RequestParam Map<String, Object> map) throws Exception {
//
//		map.put("sqlQueryId", "comDAO.selectPortalIndsCpxListPop");
//		List indsCpxList = comService.selectCommonQueryList(map);
//
//		map.put(SPMSConst.Jqrid_rows, indsCpxList);
//		model.addAllAttributes(map);
//
//		return new ModelAndView(ajaxMainView, model);
//	}
//
//	/**
//	 * 
//	 * 
//	 * @author BaekSeungJu
//	 * @param request
//	 * @param model
//	 * @param response
//	 * @param map
//	 * @return
//	 * @throws Exception
//	 * 
//	 *             <pre>
//	 * admin > 입주사 검색 조회
//	 * 
//	 *             </pre>
//	 */
//	@RequestMapping(value = "/popup/selectAdminIndsCpxMoviListPop.do")
//	public ModelAndView selectAdminIndsCpxMoviListPop(HttpServletRequest request, ModelMap model,
//			HttpServletResponse response, @RequestParam HashMap map) throws Exception {
//
//		map.put("sqlQueryId", "comDAO.selectPortalIndsCpxMoviCnt");
//		int totCnt = comService.selectCommonQueryListTotCnt(map);
//		PageUtil.setPageing(map, totCnt);
//
//		map.put("sqlQueryId", "comDAO.selectPortalIndsCpxMoviListPop");
//		List indsCpxList = comService.selectCommonQueryList(map);
//
//		map.put(SPMSConst.Jqrid_rows, indsCpxList);
//		model.addAllAttributes(map);
//
//		return new ModelAndView(ajaxMainView, model);
//	}
//
//	/**
//	 * 2014. 파일다운로드
//	 * 
//	 * @param
//	 * @return
//	 * @exception @see
//	 */
//	@RequestMapping(value = "/com/util/fileDownloadExcel.do")
//	public void fileDownloadExcel(@RequestParam Map commandMap, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//
//		comService.fileDownloadExcel(commandMap, response);
//
//		Cookie cookie = new Cookie("fileDownload", "true");
//		cookie.setPath("/");
//		response.addCookie(cookie);
//		
//		String sqlQueryId = SpmsUtil.getMapString("sqlQueryId", commandMap);
//		if("adminUser.selectUserUserlist".equals(sqlQueryId)){
//	     	/***  개인정보 조회 이력 시작  ***/
//	    	WorkHistVO workHistVo = new WorkHistVO();
//	    	workHistVo.setCrudDiv("R");	//CRUD
//	    	workHistVo.setWorkPage("사용자관리 목록 엑셀다운로드");	//PROGRAM NAME
//	    	workHistVo.setConctIp(request.getRemoteAddr());//IP
//
//	    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//	    	workHistVo.setId(user.getUserId());//ID
//	    	
//	    	//adminUserService.workLogSp(request, workHistVo);
//	    	/***  개인정보 조회 이력 끝  ***/
//		}
//	}
	
	@RequestMapping(value="/com/util/queryMListExcel.do")
	public ModelAndView queryMListExcel(ModelMap model,
			HttpServletRequest request, HttpServletResponse response, @RequestParam HashMap reqParams) throws Exception {
		
		response.setHeader("Set-Cookie", "fileDownload=true; path=/");
		ModelAndView mav = comService.queryMListExcel(reqParams);
		
		return mav;

	}
	
//	/**
//	 * 남은시간 연장을 위한 더미 컨트롤
//	 * 
//	 * @param
//	 * @return
//	 * @exception @see
//	 */
//	@RequestMapping(value = "/com/loginTimeReset.do")
//	public void loginTimeReset(@RequestParam Map commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//		log.debug("/com/loginTimeReset.do :: " + isAuthenticated);
//	}
//	
//	/**
//	 * 메인화면 급식기관선택
//	 * 
//	 * @param
//	 * @return
//	 * @exception @see
//	 */
//	@RequestMapping(value = "/com/modInsttCd.do")
//	public void selInsttCd(@RequestParam Map commandMap, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
//    	LoginVO loginVO = (LoginVO)request.getSession().getAttribute("loginVO");
//    	
//    	loginVO.setInsttCd((String) commandMap.get("insttCd"));    	
//    	request.getSession().setAttribute("loginVO", loginVO);
//
//	}
	
	
	@RequestMapping(value = "/ajaxCode.do")
	public ModelAndView codeList(HttpServletRequest request, @RequestBody String paramData, ModelMap model) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper(); 
    	Map<String, ArrayList<Map<String, Object>>> jsonObject = mapper.readValue(paramData, Map.class);     	
    	
    	ArrayList<Map<String, Object>> codeInfoList = jsonObject.get("codeInfo");
		
    	Map<String, List<CodeVO>> tpMap = new HashMap<String, List<CodeVO>>();
    	
    	String type = "";
    	List<CodeVO> tempCodeList = null;
    	
    	for(int i = 0;i < codeInfoList.size(); i++) {
    		
    		type = (String) codeInfoList.get(i).get("type");
    		
    		switch (type) {
			case "1":			
				tempCodeList = comService.selectCodeList1((String) codeInfoList.get(i).get("cdId"));
				break;
			case "2":				
				tempCodeList = comService.selectCodeList2((String) codeInfoList.get(i).get("cd"));
				break;
			case "3":				
				tempCodeList = comService.selectCodeRangeList((String) codeInfoList.get(i).get("cdId"), (String) codeInfoList.get(i).get("minerCodeId"));
				break;
			case "4":				
				tempCodeList = comService.selectCommonQueryList(codeInfoList.get(i));
				break;
			}
    		String strCdId = (String) codeInfoList.get(i).get("cdId");
    		tpMap.put(strCdId, tempCodeList);
    	}
        	
    	model.addAllAttributes(tpMap);

		return new ModelAndView(ajaxMainView, model);
	}
	
	/**
     * 
     * <pre>
     * 1. 메소드명 : updExcSysCrimeCodeMList
     * 2. 작성일 : 2021. 5. 12. 오후 4:33:41
     * 3. 작성자 : jij
     * 4. 설명 : @!@ 엑셀업로드 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @param status
     * @return
     * @throws Exception
     */
    @RequestMapping("/com/util/updExcMList.do")
    public ModelAndView updExcMList(@RequestParam HashMap<String, Object> reqParams, ModelMap model,
    		MultipartHttpServletRequest request, SessionStatus status) throws Exception {

    	comService.updExcMList(request, reqParams);

    	return new ModelAndView(ajaxMainView, model);

    }
    
    @RequestMapping("/com/util/queryAtchFileList.do")
    public ModelAndView queryAtchFileList(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

    	List<FileVO> atchFileList = comService.selectAtchFileList((String) reqParams.get("atchFileId"),null);
	    model.addAttribute("atchFileList",atchFileList);

    	return new ModelAndView(ajaxMainView, model);

    }

}
