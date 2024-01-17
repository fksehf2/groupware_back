package frame.fsys.code.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.fbbs.ntc.service.FBbsNtcService;
import frame.fbbs.ntc.service.FBbsNtcVO;
import frame.fcom.service.ComService;
import frame.fcom.service.impl.ComDAO;
import frame.fsys.code.service.FsysCodeService;
import frame.futil.JimsConst;
import frame.futil.ValidateUtil;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysCodeService.java
 * 2. 작성일 : 2021. 5. 13.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 코드관리
 * </pre>
 */
@Service("fsysCodeService")
public class FsysCodeServiceImpl extends EgovAbstractServiceImpl implements FsysCodeService{

	protected Logger log = LoggerFactory.getLogger(FsysCodeServiceImpl.class);

	@Resource(name="comDAO")
	private ComDAO comDAO;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "validateUtil")
	private ValidateUtil validateUtil;

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regFsysCodeDtlRDtl
	 * 2. 작성일 : 2021. 5. 13. 오후 8:04:03
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 상세코드 등록
	 * </pre>
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	@Override
	public String regFsysCodeDtlRDtl(HashMap reqParams) throws Exception {
		HashMap<String,String> requireParams = new HashMap();
    	requireParams.put("cdNm", "상세코드명");
    	validateUtil.check(reqParams, requireParams);
    	
		reqParams.put("sqlQueryId", "fsysCodeDAO.regFsysCodeDtlRDtl");
		comDAO.updateCommonQuery(reqParams);
		
		if(reqParams.get("cdId").equals("C01")){
			reqParams.put("sysGrp", reqParams.get("cd"));
			reqParams.put("menuNm", reqParams.get("cdNm"));
			
			reqParams.put("sqlQueryId", "fsysMenuDAO.regFsysMenuRTopMenu");
			comDAO.updateCommonQuery(reqParams);
		}
		
		return egovMessageSource.getMessageArgs("success.common.insert", null);
	}

//	@Resource(name="fBbsNtcDAO")
//	private FBbsNtcDAO fBbsNtcDAO;


}