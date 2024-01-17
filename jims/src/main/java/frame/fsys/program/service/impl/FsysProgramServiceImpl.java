package frame.fsys.program.service.impl;

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
import frame.fsys.program.service.FsysProgramService;
import frame.futil.JimsConst;
import frame.futil.ValidateUtil;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysProgramServiceImpl.java
 * 2. 작성일 : 2021. 5. 13.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 프로그램 관리
 * </pre>
 */
@Service("fsysProgramService")
public class FsysProgramServiceImpl extends EgovAbstractServiceImpl implements FsysProgramService{

	protected Logger log = LoggerFactory.getLogger(FsysProgramServiceImpl.class);

	@Resource(name="comDAO")
	private ComDAO comDAO;
	
	@Resource(name="egovMessageSource")
	EgovMessageSource egovMessageSource;
	
	@Resource(name = "validateUtil")
	private ValidateUtil validateUtil;

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysProgramUDtl
	 * 2. 작성일 : 2021. 5. 13. 오후 8:04:03
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 프로그램 관리 삭제
	 * </pre>
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	@Override
	public String delFsysProgramUDtl(HashMap reqParams) throws Exception {
		reqParams.put("sqlQueryId", "fsysProgramDAO.delFsysProgramUDtl");
		comDAO.updateCommonQuery(reqParams);
		
		reqParams.put("sqlQueryId", "fsysMenuDAO.updFsysMenuUProgramId");
		comDAO.updateCommonQuery(reqParams);
		
		return egovMessageSource.getMessageArgs("success.common.delete", null);
	}

//	@Resource(name="fBbsNtcDAO")
//	private FBbsNtcDAO fBbsNtcDAO;


}