package egovframework.com.sym.log.lgm.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import frame.flyt.login.service.FLytLoginVO;

/**
 * @Class Name : EgovSysLogAspect.java
 * @Description : 시스템 로그 생성을 위한 ASPECT 클래스
 * @Modification Information
 *
 *    수정일         수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 11.   이삼섭         최초생성
 *    2011. 7. 01.   이기하         패키지 분리(sym.log -> sym.log.lgm)
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */

public class EgovSysLogAspect {

	@Resource(name="EgovSysLogService")
	private EgovSysLogService sysLogService;

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 insert로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logInsert(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); // request 정보를 가져온다.

		
		
		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "C";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

			System.out.println("logInsert __ joinPoint.getArgs();__"+joinPoint.getArgs());
			
			  // Method Information
	        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	        // Method args
	        /*System.out.println("Method args names:");
	        Arrays.stream(signature.getParameterNames())
	          .forEach(s -> System.out.println("arg name: " + s));

	        System.out.println("Method args types:");
	        Arrays.stream(signature.getParameterTypes())
	          .forEach(s -> System.out.println("arg type: " + s));*/

	        System.out.println("Method args values:");
	        //Arrays.stream(joinPoint.getArgs()).forEach(o -> System.out.println("arg value: " + o.toString()));
			
			
	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	// jmkim Filter의 GUID 사용 예정
	        /*
	        if(isAuthenticated.booleanValue()) {
	    		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				uniqId = UUID.randomUUID().toString();//. user.getUniqId();
				ip = user.getIp();
	    	}
	    	*/

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);
			Object[] args = joinPoint.getArgs();
			if(args[0] instanceof Map) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[0]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}else if(args[0] instanceof HttpServletRequest) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[1]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}

			try {
				sysLogService.logInsertSysLog(sysLog);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 update로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logUpdate(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "U";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	        /*
	    	if(isAuthenticated.booleanValue()) {
	    		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    		uniqId = UUID.randomUUID().toString();//. user.getUniqId();
				ip = user.getIp();
	    	}*/

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);
			Object[] args = joinPoint.getArgs();
			
			if(args[0] instanceof Map) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[0]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}else if(args[0] instanceof HttpServletRequest) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[1]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}

			try {
				sysLogService.logInsertSysLog(sysLog);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 delete로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logDelete(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "D";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	/*if(isAuthenticated.booleanValue()) {
	    		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				uniqId = UUID.randomUUID().toString();//. user.getUniqId();
				ip = user.getIp();
	    	}*/

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);
			Object[] args = joinPoint.getArgs();
			if(args[0] instanceof Map) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[0]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}else if(args[0] instanceof HttpServletRequest) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[1]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}

			try {
				sysLogService.logInsertSysLog(sysLog);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 시스템 로그정보를 생성한다.
	 * sevice Class의 select로 시작되는 Method
	 *
	 * @param ProceedingJoinPoint
	 * @return Object
	 * @throws Exception
	 */
	public Object logSelect(ProceedingJoinPoint joinPoint) throws Throwable {

		

		System.out.println("logSelect_________________________________________________");
		StopWatch stopWatch = new StopWatch();

		try {
			stopWatch.start();

			Object retValue = joinPoint.proceed();
			return retValue;
		} catch (Throwable e) {
			throw e;
		} finally {
			stopWatch.stop();

			SysLog sysLog = new SysLog();
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String processSeCode = "R";
			String processTime = Long.toString(stopWatch.getTotalTimeMillis());
			String uniqId = "";
			String ip = "";

			System.out.println("joinPoint.getArgs();__"+joinPoint.getArgs());
	    	/* Authenticated  */
	        Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	/*if(isAuthenticated.booleanValue()) {
	    		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
				uniqId = UUID.randomUUID().toString();//. user.getUniqId();
				ip = user.getIp();
	    	}*/

			sysLog.setSrvcNm(className);
			sysLog.setMethodNm(methodName);
			sysLog.setProcessSeCode(processSeCode);
			sysLog.setProcessTime(processTime);
			sysLog.setRqesterId(uniqId);
			sysLog.setRqesterIp(ip);
			Object[] args = joinPoint.getArgs();
			if(args[0] instanceof Map) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[0]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}else if(args[0] instanceof HttpServletRequest) {
				HashMap map = new HashMap<>();
				map.putAll((Map) args[1]);
				sysLog.setGuid((String) map.get("__GUID__"));
				sysLog.setRqesterId((String) map.get("session_userid"));
				sysLog.setInsttCode((String) map.get("session_insttcd"));
			}

			try {
				sysLogService.logInsertSysLog(sysLog);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
