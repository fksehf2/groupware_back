package frame.fsys.dept.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.string.EgovStringUtil;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import frame.fexception.MException;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.dept.service.FsysDeptService;
import frame.fsys.dept.web.FsysDeptController;
import frame.fcom.service.ComService;
import frame.fcom.service.impl.ComDAO;


@Service("fsysDeptService")
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class FsysDeptServiceImpl extends EgovAbstractServiceImpl implements	FsysDeptService{
	
	protected Logger log = LoggerFactory.getLogger(FsysDeptController.class);
	
	@Resource(name="comService")
	private ComService comService;
	
	@Resource(name="comDAO")
	private ComDAO comDAO;
	
	@Resource(name="fsysDeptDAO")
	private FsysDeptDAO fsysDeptDAO;
	
	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : insertFsysDeptUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:20:13
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서별 사용자 등록 처리
	 * </pre>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void insertFsysDeptUDtl(HashMap<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Map<String, Object> infoMap = new HashMap<String, Object>();
		
		String userGb = (String) map.get("userGb");
		String pBeforeInsInsttCd = (String)map.get("pBeforeInsInsttCd");
		String paramInsttCd = (String)map.get("paramInsttCd");
		String regrId = (String)map.get("regrId");
		String userId = (String)map.get("userId");
		String aprvDt = (String)map.get("aprvDt");
		String mainCrgrYn = (String)map.get("mainCrgrYn");
		String rtirDt = (String)map.get("rtirDt");
		String ioficStatCd = (String)map.get("ioficStatCd");
		String aprvAuthYn = (String)map.get("aprvAuthYn");
		String useYn = (String)map.get("useYn");
		String modRtirDt = EgovDateUtil.getToday();
		
		log.debug("======insertFsysDeptUDtl pBeforeInsInsttCd =====>>>"+pBeforeInsInsttCd);
		log.debug("======insertFsysDeptUDtl paramInsttCd =====>>>"+paramInsttCd);
		log.debug("======insertFsysDeptUDtl regrId =====>>>"+regrId);
		log.debug("======insertFsysDeptUDtl userId =====>>>"+userId);
		log.debug("======insertFsysDeptUDtl aprvDt =====>>>"+aprvDt);
		
		log.debug("======insertFsysDeptUDtl mainCrgrYn =====>>>"+mainCrgrYn);
		log.debug("======insertFsysDeptUDtl rtirDt =====>>>"+rtirDt);
		log.debug("======insertFsysDeptUDtl ioficStatCd =====>>>"+ioficStatCd);
		log.debug("======insertFsysDeptUDtl aprvAuthYn =====>>>"+aprvAuthYn);
		log.debug("======insertFsysDeptUDtl useYn =====>>>"+useYn);
		
		
		log.debug("======insertFsysDeptUDtl map.toString() =====>>>"+map.toString());
		//log.debug("======modifyFsysDeptUDtl deptLvl =====>>>"+deptLvl);
		
		//권한체크  (사용자구분 C01001:수사관, C01002:군검사, C01003:포렌식수사관, C01004:수사과장, C01999:관리자)    
		if(!"C01999".equals(userGb)) {
			throw new MException(egovMessageSource.getMessageArgs("jims.anls.req.000", new String[]{"관리자"}));
		}
		
		
		//신규 부서별 사용자 등록 전에 사용자ID가 다른 부서에 존재하는지 확인
		infoMap.clear();
		infoMap.put("sqlQueryId", "fsysDeptDAO.selectDeptUserExiCnt");
		infoMap.put("insInsttCd"	, pBeforeInsInsttCd); 
		infoMap.put("userId"	, userId);
		
		int chkBeforeCnt = comDAO.selectCommonQueryListTotCnt(infoMap);
		
		log.debug("========insertFsysDeptUDtl 다른 부서에 존재하는 확인 카운트 chkBeforeCnt ===========>>>"+chkBeforeCnt);
		
		if(chkBeforeCnt>0) {
			paramMap.clear();
			paramMap.put("insInsttCd"	, pBeforeInsInsttCd); 
			paramMap.put("userId"	, userId);
			paramMap.put("useYn"	, 'N');
			paramMap.put("rtirDt"	, modRtirDt); //정지일자
			
			//부서별 사용자 등록 후 이전 사용자 정보 수정
			fsysDeptDAO.modifyFsysDeptUserDetail(paramMap);
		}
		
		//신규 부서별 사용자 등록 전에 사용자ID가 현재 등록하려는 부서에 존재하는지 확인
		infoMap.clear();
		infoMap.put("sqlQueryId", "fsysDeptDAO.selectDeptUserExiCnt");
		infoMap.put("insInsttCd"	, paramInsttCd); 
		infoMap.put("userId"	, userId);
		
		int chkAfterCnt = comDAO.selectCommonQueryListTotCnt(infoMap);
		
		log.debug("========insertFsysDeptUDtl 다른 부서에 존재하는 확인 카운트 chkAfterCnt ===========>>>"+chkAfterCnt);
		
		if(chkAfterCnt>0) {
			paramMap.clear();
			paramMap.put("insInsttCd"	, paramInsttCd); 
			paramMap.put("userId"	, userId);
			paramMap.put("useYn"	, 'Y');
			paramMap.put("rtirDt"	, ""); //정지일자
			
			//부서별 사용자 등록 후 이전 사용자 정보 수정
			fsysDeptDAO.modifyFsysDeptUserDetail(paramMap);
		}else {
			paramMap.clear();
			paramMap.put("paramInsttCd"	, paramInsttCd); 
			paramMap.put("userId"	, userId);
			paramMap.put("aprvDt"	, aprvDt);
			paramMap.put("mainCrgrYn"	, mainCrgrYn);
			paramMap.put("rtirDt"	, rtirDt);
			paramMap.put("ioficStatCd"	, ioficStatCd);
			paramMap.put("aprvAuthYn"	, aprvAuthYn);
			paramMap.put("useYn"	, useYn);
			paramMap.put("regrId"	, regrId);
			
			//부서별 사용자 등록
			fsysDeptDAO.insertFsysDeptUserInfo(paramMap); //regFsysDeptUserRDtl
		}
		
		
		paramMap.clear();
		paramMap.put("paramInsttCd"	, paramInsttCd); 
		paramMap.put("regrId"	, regrId);
		paramMap.put("userId"	, userId);
		
		//사용자 정보의 부서코드를 새로 등록한 부서코드로 수정
		fsysDeptDAO.modifyFsysUserDetail(paramMap);
		
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : modifyFsysDeptUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:20:13
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 상세 정보 수정
	 * </pre>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void modifyFsysDeptUDtl(HashMap<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		
		String beforeDeptNm = (String)map.get("beforeDeptNm");
		String deptNm = (String)map.get("deptNm");
		String deptLvl = (String)map.get("deptLvl");
		
		log.debug("======modifyFsysDeptUDtl beforeDeptNm =====>>>"+beforeDeptNm);
		log.debug("======modifyFsysDeptUDtl deptNm =====>>>"+deptNm);
		log.debug("======modifyFsysDeptUDtl deptLvl =====>>>"+deptLvl);
		
		//과명 수정
		fsysDeptDAO.modifyFsysDeptNm(map);
		
		if(deptLvl.equals("2") && !beforeDeptNm.equals(deptNm)) {
			//상위부서의 과명 수정시 해당과에 속하는 팀의 과명 모두 수정
			fsysDeptDAO.modifyFsysDeptNmAll(map);
		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : deleteFsysDeptUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:20:13
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 정보 삭제
	 * </pre>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void deleteFsysDeptUDtl(HashMap<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		String insttCd = (String)map.get("insttCd");
		String deptNm = (String)map.get("deptNm");
		
		log.debug("======deleteFsysDeptUDtl insttCd =====>>>"+insttCd);
		log.debug("======deleteFsysDeptUDtl deptNm =====>>>"+deptNm);
		
		
		paramMap.clear();
		paramMap.put("insttCd"	, insttCd); 
		
		//삭제할려는 부서가 상위부서인 하위부서 유무 조회
		int chkDeptCnt=  fsysDeptDAO.selectLowerDeptCount(paramMap);
		
		if(chkDeptCnt > 0) {
			throw new MException(egovMessageSource.getMessageArgs("jims.dept.000",new String[]{deptNm}));
		}
		
		//부서 삭제
		fsysDeptDAO.delFsysDept(map);
		
		
	}
	
	
	
	
}
