package frame.flyt.login.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.fcom.service.ComService;
import frame.fexception.MException;
import frame.flyt.login.service.FLytLoginService;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.user.service.FsysUserService;
import frame.futil.JimsConst;
import frame.futil.JimsUtil;



/**
 * 
 * <pre>
 * 1. 클래스명 : FLytLoginController.java
 * 2. 작성일 : 2021. 4. 26.
 * 3. 작성자 : jmkim
 * 4. 설명 : 로그인 처리 Controller
 * </pre>
 */
@Controller
public class FLytLoginController {

	protected Logger log = LoggerFactory.getLogger(FLytLoginController.class);


	@Resource(name = "fLytLoginService")
	private FLytLoginService fLytLoginService;


	@Resource(name = "egovMessageSource")
	private EgovMessageSource egovMessageSource;

	@Resource(name = "fsysUserService")
	private FsysUserService fsysUserService;

	@Resource(name = "comService")
	private ComService comService;


	@Resource MappingJackson2JsonView ajaxMainView;



	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : index
	 * 2. 작성일 : 2021. 4. 26. 오후 12:06:03
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 로그인 화면으로 이동 (임시 , 기존에 URL 전달한 내용이 있어서.)
	 * </pre>
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @deprecated
	 */
	@RequestMapping("/login/login.do")
	public String login(@RequestParam Map<String, Object> commandMap, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if(log.isDebugEnabled()) {
			log.debug("/login/login.do called ");
		}

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(isAuthenticated){
			String rtnUrl = "redirect:/flyt/main/indexFLytMainIDtl.do"; // 로그인 후 화면 리로드 시 로그인 유지
			return rtnUrl;
		}

		// 로그인 체크
		model.addAttribute("reservLoginCheck", "2");
		//model.addAttribute("serverType", EgovProperties.getProperty("Globals.serverType"));

		Cookie cookie = new Cookie("AdminSessionId", JimsUtil.encryptSHA256((String) request.getSession().getId()));
		cookie.setMaxAge(60 * 60 * 24); // 쿠키 유지 기간(하루)
		cookie.setPath("/"); // 모든 경로에서 접근 가능하도록
		response.addCookie(cookie); // 쿠키저장

		if(log.isDebugEnabled()) {
			log.debug("login end ");
		}


		return "frame/flyt/login/flytLoginIDtl";
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFLytLoginIDtl
	 * 2. 작성일 : 2021. 4. 26. 오후 2:06:18
	 * 3. 작성자 : Eric
	 * 4. 설명 : 로그인 화면으로 이동 
	 * </pre>
	 * @param commandMap
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/flyt/login/indexFLytLoginIDtl.do")
	public String indexFLytLoginIDtl(@RequestParam Map<String, Object> commandMap, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if(log.isDebugEnabled()) {
			log.debug("/flyt/login/indexFLytLoginIDtl.do called ");
		}

		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if(isAuthenticated){
			String rtnUrl = "redirect:/flyt/main/indexFLytMainIDtl.do"; // 로그인 후 화면 리로드 시 로그인 유지
			return rtnUrl;
		}

		// 로그인 체크
		model.addAttribute("reservLoginCheck", "2");
		//model.addAttribute("serverType", EgovProperties.getProperty("Globals.serverType"));

		Cookie cookie = new Cookie("AdminSessionId", JimsUtil.encryptSHA256((String) request.getSession().getId()));
		cookie.setMaxAge(60 * 60 * 24); // 쿠키 유지 기간(하루)
		cookie.setPath("/"); // 모든 경로에서 접근 가능하도록
		response.addCookie(cookie); // 쿠키저장

		if(log.isDebugEnabled()) {
			log.debug("login end ");
		}

		return "frame/flyt/login/flytLoginIDtl";
	}
	

	

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : actionLogout
	 * 2. 작성일 : 2021. 4. 26. 오후 12:02:16
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 로그아웃 처리 
	 * </pre>
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/procFLytLoginLogoutPDtl.do")
	public String procFLytLoginLogoutPDtl(HttpServletRequest request, ModelMap model) throws Exception {

		//로그아웃 처리
		request.getSession().setAttribute("loginVO", null);
		request.getSession().invalidate(); //세션삭제

		return "redirect:/flyt/login/indexFLytLoginIDtl.do";
	}






	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : actionLogin
	 * 2. 작성일 : 2021. 4. 26. 오후 12:06:24
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 일반 로그인 처리 
	 * </pre>
	 * @param commandMap
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/flyt/login/procFLytLoginPDtl.do")
	public ModelAndView procFLytLoginPDtl(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, ModelMap model,
			HttpServletResponse response) throws Exception {


		if(log.isDebugEnabled()) {
			log.debug("/flyt/login/procFLytLoginPDtl.do ");
		}
		FLytLoginVO loginVO = new FLytLoginVO();
		loginVO.setUserId((String) commandMap.get("sid"));

		String islock = setIDActive(loginVO, request); // 아이디 존재 여부
		loginVO.setPwd(JimsUtil.encryptSHA256((String) commandMap.get("spw")));
		loginVO.setPwd((String) commandMap.get("spw"));
		//model.addAttribute("serverType", EgovProperties.getProperty("Globals.serverType"));


		if (islock.equals("OK")) {// 아이디 존재
			try {

				setSessionCheck(loginVO, request);
				Enumeration params = request.getParameterNames();
				log.info("[LOGIN SUCCESS]_________________[start]");
				log.info("ip : "+request.getRemoteAddr());
				while(params.hasMoreElements()) {
				  String name = (String) params.nextElement();
				  log.info(name + " : " + request.getParameter(name) + "     "); 
				}
				
				log.info("[LOGIN SUCCESS]__________________[end]");
				return new ModelAndView(ajaxMainView, model);

			} catch (Exception e) {
				e.printStackTrace();

				model.addAttribute("message", (e.getMessage()));
				model.addAttribute(JimsConst.Messages_UserErrMessage, (e.getMessage()));
				if((e.getMessage()).equals(egovMessageSource.getMessage("fail.common.login2"))){ //로그인 정보가 올바르지 않습니다.
					if (!loginVO.getUserId().equals("Admin")) {
						model.addAttribute("loginId", loginVO.getUserId());
					}
				}
				Enumeration params = request.getParameterNames();
				log.info("[LOGIN FAIL]_________________[start]");
				log.info("ip : "+request.getRemoteAddr());
				while(params.hasMoreElements()) {
				  String name = (String) params.nextElement();
				  log.info(name + " : " + request.getParameter(name) + "     "); 
				}
				
				log.info("[LOGIN FAIL]__________________[end]");
				return new ModelAndView(ajaxMainView, model);
			}
		} else if (islock.equals("NOTID")) {
			model.addAttribute("message", (egovMessageSource.getMessage("fail.common.login2")));//계정 정보가 일치하지 않습니다.
			model.addAttribute(JimsConst.Messages_UserErrMessage, (egovMessageSource.getMessage("fail.common.login2")));//계정 정보가 일치하지 않습니다.
			return new ModelAndView(ajaxMainView, model);
		}

		return new ModelAndView(ajaxMainView, model);

	}



	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : actionLoginStep2
	 * 2. 작성일 : 2021. 4. 26. 오후 12:06:39
	 * 3. 작성자 : Eric
	 * 4. 설명 : procFLytLoginPDtl와 통합해도 무방, 2 step 인증을 위해 남겨둠
	 * </pre>
	 * @param commandMap
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/flyt/login/procFLytLoginPDtl2.do" ,method=RequestMethod.POST)
	public String procFLytLoginPDtl2(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, ModelMap model,
			HttpServletResponse response) throws Exception {
		boolean isNomalLogin = true;// 일반 로그인 여부

		if(log.isDebugEnabled()) {
			log.debug("commandMap ",commandMap);
		}

		if (commandMap.get("spw").equals("AdminCompulsionLoginAccessWithNoSpwGoAheadRightNow")) {
			FLytLoginVO loginVO = new FLytLoginVO();
			loginVO.setUserId((String) commandMap.get("sid"));
			//loginVO.setPwd(JimsUtil.encryptSHA256((String) commandMap.get("spw")));
			loginVO.setPwd((String) commandMap.get("spw"));

			setAdminSession(loginVO, request);
			isNomalLogin = false;
		} else {
			FLytLoginVO loginVO = new FLytLoginVO();
			loginVO.setUserId((String) commandMap.get("sid"));
			//loginVO.setPwd(JimsUtil.encryptSHA256((String) commandMap.get("spw")));
			loginVO.setPwd((String) commandMap.get("spw"));
			try {
				// id, pass 조회하여 오류 처리 및 세션생성
				setSession(loginVO, request);
				isNomalLogin = true;

			} catch (Exception e) {
				model.addAttribute("message", (e.getMessage()));
				if((e.getMessage()).equals(egovMessageSource.getMessage("fail.common.login2"))){ //로그인 정보가 올바르지 않습니다.
					if (!loginVO.getUserId().equals("Admin")) {
						model.addAttribute("loginId", loginVO.getUserId());
					}
				}
				return "forward:/login/login.do";
			}
		}

		FLytLoginVO user = (FLytLoginVO) EgovUserDetailsHelper.getAuthenticatedUser();

		String userSno = user.getUserId();
		String userGb = user.getUserGb();

		String rtnUrl = "redirect:/flyt/main/indexFLytMainIDtl.do"; // 기본 로그인 : 로그인 후 공지사항으로
		// 이동

		if (userSno == null) {
			FLytLoginVO lvo = fsysUserService.myInfoList(user.getUserId());
			model.addAttribute("loginVO", lvo);

			// 일반사용자
			if (userGb.equals("C01001")) {

				//rtnUrl = "/main/user/UserShiprmembjoinIns";
			}
			// 업무담당자
			else if (userGb.equals("C01002")) {
				//rtnUrl = "/main/user/UserMsalemembjoinIns";
			}
			// 관리기관
			else if (userGb.equals("C01003")) {
				//rtnUrl = "/main/user/UserActnrmembjoinIns";
			}

		}

		// return "redirect:/login/login.do";

		if (isNomalLogin) {// 일반 로그인일 경우
			if (!user.getUserId().equals("Admin")) {// 아이디가 관리자가 아니면
				String pwdChng = fLytLoginService.checkPwdChng(user); //비밀번호 변경 주기 체크
				if (pwdChng.equals("OVER")) {// 비밀번호 변경 주기가 6개월 이상일 경우
					model.addAttribute("oldPwd", (String)commandMap.get("spw")); //이전 비밀번호

					//관리자가 버번을 초기화할경우 비밀번호 변경주기가 1일로 되며 암호를 변경할때까지 팝업창띄우기
					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("userId", user.getUserId());
					paramMap.put("sqlQueryId", "fLytLoginDAO.checkPwdClear");
					String pwdClr = comService.selectCommonQueryString(paramMap);
					user.setPwdClr(pwdClr);

					//rtnUrl = "forward:/user/UserPasswdChange.do";
					rtnUrl = "forward:/flyt/login/procFLytLoginOverPWPDtl.do";

				}
			}
		}

		Cookie cookie = new Cookie("AdminSessionId", JimsUtil.encryptSHA256((String) request.getSession().getId()));
		cookie.setMaxAge(60 * 60 * 24); // 쿠키 유지 기간(하루)
		cookie.setPath("/"); // 모든 경로에서 접근 가능하도록
		response.addCookie(cookie); // 쿠키저장

		return rtnUrl;
		// return "redirect:/main/authMain.do";
		// return "redirect:/login/login.do";

	}
	
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : procFLytLoginOverPWPDtl
	 * 2. 작성일 : 2021. 5. 3. 오후 4:13:31
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 3개월 후 초기화 
	 * </pre>
	 * @param commandMap
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/procFLytLoginOverPWPDtl.do")
	public String procFLytLoginOverPWPDtl(Map<String, Object> commandMap, ModelMap model, HttpServletRequest request) throws Exception{
		
		
		FLytLoginVO loginVO = (FLytLoginVO)request.getSession().getAttribute("loginVO");
		model.addAttribute("loginVO", loginVO);
		return "frame/flyt/login/flytLoginOverPWPDtl";

	}





	/**
	 * @author Kkh
	 * @param
	 * @return
	 * @throws Exception
	 *
	 * <pre>
	 *  Session이 끊어진경우 로그인 페이지로 이동
	 * </pre>
	 */
	@RequestMapping("/flyt/login/procFLytLoginSessoutPDtl.do")
	public String sessionOut(@RequestParam Map<String, Object> commandMap, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("loginVO", null);
		request.getSession().invalidate(); //세션삭제
		return "frame/flyt/login/flytLoginSessoutPDtl";
	}





	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFLytLoginFindIDPDtlPop
	 * 2. 작성일 : 2021. 4. 26. 오후 12:08:32
	 * 3. 작성자 : jmkkim
	 * 4. 설명 : ID 찾기 화면 
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/indexFLytLoginFindIDPDtlPop.do")
	public String indexFLytLoginFindIDPDtlPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {   	

		String resultUrl = "";

		//비밀번찾기질문    	
		List pwdFindQuesCodeList = comService.selectCodeList("C15");
		model.addAttribute("pwdFindQuesCodeList",pwdFindQuesCodeList);

		return "frame/flyt/login/flytLoginFindIDPDtlPop";

	}


	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : findIDFLytLoginFindIDPDtlPop
	 * 2. 작성일 : 2021. 4. 26. 오후 12:08:51
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 아이디 찾기 처리 
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/findIDFLytLoginFindIDPDtlPop.do")
	public ModelAndView findIDFLytLoginFindIDPDtlPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {


		map.put("sqlQueryId", "fLytLoginDAO.findIDFLytLoginFindIDPDtlPop");
		List userList = comService.selectCommonQueryList(map);

		model.addAttribute("userYn","N");

		//이름,질문코드,답변이 동일한 사람이 여러명일 경우 에러 발생
		if(userList.size() > 1){
			//throw new Exception();
			model.addAttribute("userYn","Y");
			return new ModelAndView(ajaxMainView, model);
		}

		model.addAttribute("findIdInfo",userList);

		return new ModelAndView(ajaxMainView, model);

	}   




	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : indexFLytLoginFindPWPDtlPop
	 * 2. 작성일 : 2021. 4. 26. 오후 12:09:09
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 비밀번호 찾기 화면
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/indexFLytLoginFindPWPDtlPop.do")
	public String indexFLytLoginFindPWPDtlPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {   	

		String resultUrl = "";

		//비밀번찾기질문    	
		List pwdFindQuesCodeList = comService.selectCodeList("C15");
		model.addAttribute("pwdFindQuesCodeList",pwdFindQuesCodeList);


		return "frame/flyt/login/flytLoginFindPWPDtlPop";

	}



	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : findPWFLytLoginFindPWPDtlPop
	 * 2. 작성일 : 2021. 4. 26. 오후 12:09:25
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 비밀번호 찾기 처리 
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/findPWFLytLoginFindPWPDtlPop.do")
	public ModelAndView findPWFLytLoginFindPWPDtlPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {

		map.put("sqlQueryId", "fLytLoginDAO.findPWFLytLoginFindPWPDtlPop");
		int cnt = comService.selectCommonQueryListTotCnt(map);

		model.addAttribute("cnt", cnt);

		return new ModelAndView(ajaxMainView, model);

	}



	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : chgPWFLytLoginFindPWPDtlPop
	 * 2. 작성일 : 2021. 4. 26. 오후 12:09:50
	 * 3. 작성자 : Eric
	 * 4. 설명 : 비밀번호 변경 (로그인 화면 )
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="flyt/login/chgPWFLytLoginFindPWPDtlPop.do")
	public ModelAndView chgPWFLytLoginFindPWPDtlPop(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception {

		map.put("sqlQueryId", "fLytLoginDAO.chgPWFLytLoginFindPWPDtlPop");
		map.put("userId",map.get("uId"));
		//map.put("pwd",SpmsUtil.encryptSHA256((String) map.get("pwd")));
		comService.updateCommonQuery(map);

		model.addAttribute("message", "success"); 		 

		return new ModelAndView(ajaxMainView, model);

	}





	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : pwClear
	 * 2. 작성일 : 2021. 4. 26. 오후 12:10:04
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 로그인 잠금 해제 ( 로그인 화면 )
	 * </pre>
	 * @param request
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/lckCncFLytLoginFindPWPDtlPop.do")
	public ModelAndView pwClear(HttpServletRequest request, ModelMap model, @RequestParam HashMap map) throws Exception { 

		map.put("sqlQueryId", "fLytLoginDAO.findPWFLytLoginFindPWPDtlPop");
		int cnt = comService.selectCommonQueryListTotCnt(map);

		if(cnt < 1){
			model.addAttribute("message", "fail");
		}else{
			map.put("sqlQueryId", "fLytLoginDAO.lckCncFLytLoginFindPWPDtlPop");
			comService.updateCommonQuery(map);

			model.addAttribute("message", "success");
		}

		return new ModelAndView(ajaxMainView, model);

	}
	



	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : chgPWFLytLoginOverPWPDtlPop
	 * 2. 작성일 : 2021. 5. 8. 오후 12:20:04
	 * 3. 작성자 : Eric
	 * 4. 설명 : 비밀번호 변경 
	 * </pre>
	 * @param vo
	 * @param model
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/chgPWFLytLoginOverPWPDtlPop.do")
	public ModelAndView chgPWFLytLoginOverPWPDtlPop(@ModelAttribute("loginVO") FLytLoginVO vo, ModelMap model, @RequestParam HashMap<String, String> reqParams, HttpServletRequest request) throws Exception {

		String oldPw = JimsUtil.encryptSHA256(JimsUtil.getMapString("chkPwd", reqParams));

		if(!"".equals(JimsUtil.getMapString("chkPwd", reqParams))) {
			reqParams.put("chkPwd",JimsUtil.encryptSHA256(reqParams.get("chkPwd")));
		}  

		String pwd = JimsUtil.encryptSHA256(JimsUtil.getMapString("pwd", reqParams));


		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		reqParams.put("userId", user.getUserId());
		reqParams.put("pwd", oldPw);
		reqParams.put("sqlQueryId", "fsysUserDAO.selectUserPw");
		int passChkCnt =  comService.selectCommonQueryListTotCnt(reqParams);

		if(passChkCnt < 1){
			//현재 비밀번호로 불일치시
			// model.addAttribute(JimsConst.Messages_UserErrMessage, egovMessageSource.getMessageArgs("fail.user.passwordUpdate1",new String[]{}, Locale.getDefault()));
			//return new ModelAndView(ajaxMainView, model);
			throw new MException(egovMessageSource.getMessage("fail.user.passwordUpdate1"));
		}

		//reqParams.put("sqlQueryId", "fsysUserDAO.updateUserPw");
		reqParams.put("sqlQueryId", "fsysUserDAO.chgPwFsysUserUserUpdPDtlPop");

		reqParams.put("pwd", pwd);
		comService.updateCommonQuery(reqParams);

		return new ModelAndView(ajaxMainView, model);
	}


	String message ="";
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : chgThrMonChgFLytLoginOverPWPDtlPop
	 * 2. 작성일 : 2021. 5. 8. 오후 12:20:43
	 * 3. 작성자 : Eric
	 * 4. 설명 : 비밀번호 3개월 
	 * </pre>
	 * @param vo
	 * @param model
	 * @param reqParams
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/chgThrMonChgFLytLoginOverPWPDtlPop.do")
	public ModelAndView chgThrMonChgFLytLoginOverPWPDtlPop(@ModelAttribute("loginVO") FLytLoginVO vo, ModelMap model, @RequestParam HashMap<String, String> reqParams, HttpServletRequest request) throws Exception {

		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

		//비밀번호가 1인지 체크
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("sqlQueryId", "fLytLoginDAO.checkPwdClear");
		String pwdClr = comService.selectCommonQueryString(paramMap);

		if("NOCLEAR".equals(pwdClr)){
			message = egovMessageSource.getMessage("admin.login.000");
			model.addAttribute("message", message);
		}else{
			reqParams.put("userId", user.getUserId());
			reqParams.put("sqlQueryId", "fsysUserDAO.updateUserPwDate");
			comService.insertCommonQuery(reqParams);
		}

		return new ModelAndView(ajaxMainView, model);
	}
	
	/**
	 * 아이디 존재 여부
	 *
	 * @param loginVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private String setIDActive(FLytLoginVO loginVO, HttpServletRequest request) throws Exception {

		String isLock = "";
		FLytLoginVO searchVO = new FLytLoginVO();
		searchVO.setSearchUserId(loginVO.getUserId());
		if (!loginVO.getUserId().equals("Admin")) {// 로그인 아이디가 관리자가 아닐 경우
			int resultCnt = fLytLoginService.checkId(searchVO);//아이디 존재여부
			if(log.isDebugEnabled()) {
				log.debug("ID 존재 여부 : __["+resultCnt+"]");
			}
			if (resultCnt > 0) {
				isLock = "OK";
			} else {
				isLock = "NOTID";
			}
		} else {
			isLock = "OK";
		}
		return isLock;
	}

	/**
	 * 로그인 정보 확인.
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return
	 * @exception Exception
	 */

	private FLytLoginVO setSessionCheck(FLytLoginVO loginVO, HttpServletRequest request) throws Exception {


		if(log.isDebugEnabled()) {
			log.debug("setSessionCheck called ");
		}
		// 로그인 처리
		FLytLoginVO resultVO = fLytLoginService.actionLogin(loginVO);

		if (resultVO != null && resultVO.getUserId() != null && !resultVO.getUserId().equals("")) {

			if (!EgovStringUtil.isNull(resultVO.getUserGb())) {

				int pwdErrCnt = fsysUserService.getPwdErrCnt(loginVO);//비밀번호 오류 횟수 조회
				if (pwdErrCnt >= 5) {// 비밀번호 오류 횟수가 5회 이상일경우
					// 로그인 정보가 올바르지 않습니다.
					throw new IOException(egovMessageSource.getMessage("fail.common.login2"));

				} else {

				}

			}
		} else {
			// 로그인 정보가 올바르지 않습니다.
			throw new IOException(egovMessageSource.getMessage("fail.common.login2"));

		}

		return resultVO;
	}


	/**
	 * 세션생성한다. - 관리자가 사용자관리 로그인시
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return
	 * @exception Exception
	 */
	private boolean setAdminSession(FLytLoginVO loginVO, HttpServletRequest request) throws Exception {

		Map<String, String> rtnMap = new HashMap<String, String>();


		if(log.isDebugEnabled()) {
			log.debug("setAdminSession called ");
		}

		// 로그인 처리
		FLytLoginVO resultVO = fLytLoginService.actionAdminLogin(loginVO);

		if (resultVO != null && resultVO.getUserId() != null && !resultVO.getUserId().equals("")) {
			if ("Y".equals(resultVO.getRegStatus())) {
				// 승인되지 않은 사용자 메세지
				throw new IOException(egovMessageSource.getMessage("fail.common.aprv_yn"));
			} else if (!EgovStringUtil.isNull(resultVO.getUserGb())) {

				// 접속이력 등록
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userGb", resultVO.getUserGb());
				map.put("userId", resultVO.getUserId());
				map.put("conctIp", request.getRemoteAddr());
				map.put("sessId", request.getSession().getId());

				fLytLoginService.insertConctHist(map);
			}

			// 로그인 정보를 세션에 저장
			request.getSession().setAttribute("loginVO", resultVO);
			/** 2017-03-24 추가 [MW-7.4. 불충분한 세션 만료 10분처리] **/
			// 세션시간 - 초단위 설정
			request.getSession().setMaxInactiveInterval(60 * 60 * 1);

		} else {
			// 로그인 정보가 올바르지 않습니다.
			throw new IOException(egovMessageSource.getMessage("fail.common.login2"));
		}

		return true;
	}

	/**
	 * 세션생성한다.
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return
	 * @exception Exception
	 */
	private boolean setSession(FLytLoginVO loginVO, HttpServletRequest request) throws Exception {


		if(log.isDebugEnabled()) {
			log.debug("setSession called ");
		}

		Map<String, String> rtnMap = new HashMap<String, String>();

		// 로그인 처리
		FLytLoginVO resultVO = fLytLoginService.actionAdminLogin(loginVO);

		if (resultVO != null && resultVO.getUserId() != null && !resultVO.getUserId().equals("")) {

			if (!EgovStringUtil.isNull(resultVO.getUserGb())) {

				int pwdErrCnt = fsysUserService.getPwdErrCnt(loginVO);//비밀번호 오류 횟수 조회
				if (pwdErrCnt < 5) {// 비밀번호 오류 횟수가 5회 미만일 경우
					// 접속이력 등록
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("userGb", resultVO.getUserGb());
					map.put("userId", resultVO.getUserId());
					map.put("conctIp", request.getRemoteAddr());
					map.put("sessId", request.getSession().getId());
					map.put("upmuGb", "ADM");

					//TODO KJM table 이 없어서 주석 처리 2021.04.06
					//fLytLoginService.insertConctHist(map);
					resultVO.setPwdErrCnt(Integer.toString(0));
					fsysUserService.updatePwdErrCnt(resultVO);// 비밀번호 오류 횟수 0으로 설정
				} else {
					// 로그인 정보가 올바르지 않습니다.
					throw new MException(egovMessageSource.getMessage("fail.common.login2"));

				}

			}

			// 로그인 정보를 세션에 저장
			request.getSession().setAttribute("loginVO", resultVO);

			/** 2017-03-24 추가 [MW-7.4. 불충분한 세션 만료 10분처리] **/
			// 세션시간 - 초단위 설정
			request.getSession().setMaxInactiveInterval(60 * 60 * 1);
		} else {
			// 로그인 정보가 올바르지 않습니다.
			throw new MException(egovMessageSource.getMessage("fail.common.login2"));

		}

		return true;
	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : checkPwdError
	 * 2. 작성일 : 2021. 5. 25. 오후 4:05:17
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 비밀번호 한도 체크 
	 * </pre>
	 * @param vo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/flyt/login/checkPwdError.do")
	public ModelAndView checkPwdError(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, ModelMap model,
			HttpServletResponse response) throws Exception {

		FLytLoginVO vo = new FLytLoginVO();
		vo.setUserId((String) commandMap.get("userId"));
		int pwdErrCnt = fsysUserService.getPwdErrCnt(vo);

		if(pwdErrCnt < 5){
			pwdErrCnt += 1;

			vo.setPwdErrCnt(Integer.toString(pwdErrCnt));

			fsysUserService.updatePwdErrCnt(vo);
		}

		model.addAttribute("pwdErrCnt", pwdErrCnt);
		return new ModelAndView(ajaxMainView, model);
	}

}
