package frame.fsys.alam.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//import jims.site.rcv.service.SiteRcvService;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.string.EgovDateUtil;
import frame.fcom.service.ComService;
import frame.fcom.service.impl.ComDAO;
import frame.fexception.MException;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.alam.service.FsysAlamService;
import frame.futil.ValidateUtil;
import frame.futil.Websocket;

/**
 * 
 * <pre>
 * 1. 클래스명 : SiteRcvServiceImpl.java
 * 2. 작성일 : 2021. 4. 29.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 현장지원 관리 담당
 * </pre>
 */
@Service("fsysAlamService")
public class FsysAlamServiceImpl extends EgovAbstractServiceImpl implements  FsysAlamService{

	protected Logger log = LoggerFactory.getLogger(FsysAlamServiceImpl.class);

	@Resource(name="comDAO")
	private ComDAO comDAO;

	/*@Resource(name="anlsReqDAO")
	private AnlsReqDAO anlsReqDAO;*/

	@Resource(name = "validateUtil")
	private ValidateUtil validateUtil;

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regEvdcWarnAlam
	 * 2. 작성일 : 2021. 6. 2. 오전 11:34:21
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 미허가 장비 반출 알람 등록
	 * </pre>
	 * @param paramData
	 * @throws Exception
	 */
	@Override
	public void regEvdcWarnAlam(HashMap reqParams) throws Exception {
		
		//reqParams.put("eqpSno", "EQ00000020");
		//reqParams.put("vltnDiv", "C21001");
		
		
    	reqParams.put("sqlQueryId", "fsysAlamDAO.queryEvdcWarnCdNm");
		Map evdc = comDAO.selectCommonQueryMap(reqParams);
		
		reqParams.put("sqlQueryId", "fsysAlamDAO.regEvdcWarnAlam");
		comDAO.updateCommonQuery(reqParams);
		
		String msg = "발생일:" + String.valueOf(reqParams.get("vltnDt")) + ", " + 
					"일련번호:" + String.valueOf(reqParams.get("vltnSno")) + ", " + 
					String.valueOf(reqParams.get("eqpSno")) + ":" + String.valueOf(evdc.get("eqpNm")) + ", " + 
					String.valueOf(reqParams.get("vltnDiv")) + ":" + String.valueOf(evdc.get("vltnDivNm"));
		Websocket wsc = new Websocket();
		//wsc.broadCast("EQ00000020 : 레이저 광선검, C21001 : 무단반출");
		wsc.broadCast(msg);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEvdcWarnAlamCfrmY
	 * 2. 작성일 : 2021. 6. 2. 오전 11:34:21
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 미허가 장비 반출 알람 확인
	 * </pre>
	 * @param paramData
	 * @throws Exception
	 */
	@Override
	public void updEvdcWarnAlamCfrmY(HashMap reqParams) throws Exception {
		
		reqParams.put("sqlQueryId", "fsysAlamDAO.updEvdcWarnAlamCfrmY");
		comDAO.updateCommonQuery(reqParams);
		
	}
//
//	/**
//	 * 
//	 * <pre>
//	 * 1. 메소드명 : reqCnclSiteRcvUDtl
//	 * 2. 작성일 : 2021. 4. 30. 오후 2:15:08
//	 * 3. 작성자 : jij
//	 * 4. 설명 : @!@ 현장지원 요청취소
//	 * </pre>
//	 * @param paramData
//	 * @return
//	 * @throws Exception
//	 */
//	@Override
//	public HashMap reqCnclSiteRcvUDtl(String paramData) throws Exception {
//		
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String, Map<String, Object>> jsonObject = mapper.readValue(paramData, Map.class);
//		
//		List suppReqSnoArr = (List) jsonObject.get("rowDatas");
//		
//		Map<String, Object> infoMap = jsonObject.get("obj");
//		String pgsStatParam = (String) infoMap.get("pgsStat");
//		FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
//		String planPgsStat = "";
//		String failMsg = "";
//		switch (user.getUserGb()) {
//			case "C01001":
//				if(!pgsStatParam.equals("C03001") && !pgsStatParam.equals("C03002")) {
//					throw new MException(egovMessageSource.getMessageArgs("jims.site.rcv.008", null));
//				}
//				planPgsStat = "C03002";
//				failMsg = "승인요청";
//				break;
//			case "C01002":
//				if(!pgsStatParam.equals("C03002") && !pgsStatParam.equals("C03003")) {
//					throw new MException(egovMessageSource.getMessageArgs("jims.site.rcv.008", null));
//				}
//				planPgsStat = "C03003";
//				failMsg = "검사승인";
//				break;
//			case "C01003":
//				if(!pgsStatParam.equals("C03003") && !pgsStatParam.equals("C03005")) {
//					throw new MException(egovMessageSource.getMessageArgs("jims.site.rcv.008", null));
//				}
//				planPgsStat = "C03005";
//				failMsg = "문서접수";
//				break;
//			case "C01004":
//				if(!pgsStatParam.equals("C03005") && !pgsStatParam.equals("C03006")) {
//					throw new MException(egovMessageSource.getMessageArgs("jims.site.rcv.008", null));
//				}
//				planPgsStat = "C03006";
//				failMsg = "과장승인";
//				break;
//			default:
//				throw new MException(egovMessageSource.getMessageArgs("jims.site.rcv.008", null));
//		}
//		
//		HashMap resultMap = new HashMap<>();
//		String failList = "";
//		String succList = "";
//		
//		for (int i = 0; i < suppReqSnoArr.size(); i++) {
//			HashMap map = new HashMap<>();
//			String pgsStat = "";
//			map.put("sqlQueryId", "siteRcvDAO.querySiteRcvPgsStatChk");
//			map.put("suppReqSno", suppReqSnoArr.get(i));
//			pgsStat = comDAO.selectCommonQueryString(map);
//			if(!pgsStat.equals(planPgsStat)) {
//				failList += suppReqSnoArr.get(i) + ",";
//			}else{
//				map.put("sqlQueryId", "siteRcvDAO.reqCnclSiteRcvUDtl");
//				map.put("pgsStat", pgsStatParam);
//				map.put("session_userid", user.getUserId());
//				comDAO.updateCommonQuery(map);
//				succList += suppReqSnoArr.get(i) + ",";
//			}
//		}
//		if(!succList.equals("") && succList.length() > 0) {
//			succList = succList.substring(0, succList.length()-1);
//		}
//		if(!failList.equals("") && failList.length() > 0) {
//			failList = failList.substring(0, failList.length()-1);
//		}
//		resultMap.put("succList", succList);
//		resultMap.put("failList", failList);
//		resultMap.put("failMsg", failMsg);
//		return resultMap;
//    	
//	}

}