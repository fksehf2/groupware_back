package frame.fsys.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.fbbs.ntc.service.FBbsNtcService;
import frame.fbbs.ntc.service.FBbsNtcVO;
import frame.fcom.service.impl.ComDAO;
import frame.fsys.code.service.FsysCodeService;
import frame.fsys.menu.service.FsysMenuService;
import frame.futil.JimsConst;
import frame.futil.ValidateUtil;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysMenuServiceImpl.java
 * 2. 작성일 : 2021. 5. 14.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 메뉴 관리
 * </pre>
 */
@Service("fsysMenuService")
public class FsysMenuServiceImpl extends EgovAbstractServiceImpl implements FsysMenuService{

	protected Logger log = LoggerFactory.getLogger(FsysMenuServiceImpl.class);

	@Resource(name="comDAO")
	private ComDAO comDAO;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "validateUtil")
	private ValidateUtil validateUtil;

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regFsysMenuRDtl
	 * 2. 작성일 : 2021. 5. 13. 오후 8:04:03
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 등록
	 * </pre>
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	@Override
	public String[] regFsysMenuRDtl(HashMap reqParams) throws Exception {
		HashMap<String,String> requireParams = new HashMap();
		requireParams.put("menuNm", "메뉴명");
		if (reqParams.get("menuLvl").equals("lvl2")) {
			requireParams.put("upperMenuNo", "상위메뉴");
			requireParams.put("programId", "프로그램ID");
		}
    	validateUtil.check(reqParams, requireParams);
    	
    	int lvl2Cnt = 0;
    	String[] message = new String[2];

		reqParams.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuLvl2Cnt");
		lvl2Cnt = comDAO.selectCommonQueryListTotCnt(reqParams);
		
		if (reqParams.get("menuLvl").equals("lvl2") && lvl2Cnt > 98) {
			message[0] = JimsConst.Messages_UserErrMessage;
			message[1] = egovMessageSource.getMessageArgs("admin.menu.002", null);
			return message;
		}

		reqParams.put("sqlQueryId", "fsysMenuDAO.regFsysMenuRDtl");
		comDAO.updateCommonQuery(reqParams);

		reqParams.put("sqlQueryId", "fsysMenuDAO.updFsysMenuRDtlOrdr");
		comDAO.updateCommonQuery(reqParams);

		if (reqParams.get("menuLvl").equals("lvl2") && lvl2Cnt > 1) {
			reqParams.put("sqlQueryId", "fsysMenuDAO.updFsysMenuNoReMake");
			comDAO.updateCommonQuery(reqParams);
		}

		message[0] = JimsConst.Messages_SysSucMessage;
		message[1] = egovMessageSource.getMessageArgs("success.common.insert", null);
		
		return message;
	}

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updFsysMenuUDtl
	 * 2. 작성일 : 2021. 5. 13. 오후 8:04:03
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 메뉴 수정
	 * </pre>
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	@Override
	public String[] updFsysMenuUDtl(HashMap reqParams) throws Exception {
		
    	HashMap<String,String> requireParams = new HashMap();
		requireParams.put("menuNm", "메뉴명");
		if (reqParams.get("menuLvl").equals("lvl2")) {
			requireParams.put("upperMenuNo", "상위메뉴");
			requireParams.put("programId", "프로그램ID");
		}
    	validateUtil.check(reqParams, requireParams);
    	
		int lvl2Cnt = 0;
		String[] message = new String[2];
		
		if (!reqParams.get("upperMenuNo").equals(reqParams.get("oriUpperMenuNo"))) {
			reqParams.put("sqlQueryId", "fsysMenuDAO.queryFsysMenuLvl2Cnt");
			lvl2Cnt = comDAO.selectCommonQueryListTotCnt(reqParams);
			if (reqParams.get("menuLvl").equals("lvl2") && lvl2Cnt > 98) {
				message[0] = JimsConst.Messages_UserErrMessage;
				message[1] = egovMessageSource.getMessageArgs("admin.menu.002", null);
				return message;
			}
		}

		reqParams.put("sqlQueryId", "fsysMenuDAO.updFsysMenuUDtl");
		comDAO.updateCommonQuery(reqParams);

		reqParams.put("sqlQueryId", "fsysMenuDAO.updFsysMenuRDtlOrdr");
		comDAO.updateCommonQuery(reqParams);

		if (!reqParams.get("upperMenuNo").equals(reqParams.get("oriUpperMenuNo"))) {
			if (reqParams.get("menuLvl").equals("lvl2") && lvl2Cnt > 1) {
				reqParams.put("sqlQueryId", "fsysMenuDAO.updFsysMenuNoReMake");
				comDAO.updateCommonQuery(reqParams);
			}
		}
		
		message[0] = JimsConst.Messages_SysSucMessage;
		message[1] = egovMessageSource.getMessageArgs("success.common.update", null);
		
		return message;
	}
	
}