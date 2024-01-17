package frame.fsys.user.service.impl;

import java.util.HashMap;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.user.service.FsysUserService;
import frame.fsys.user.service.FsysUserVO;

@Service("fsysUserService")
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class FsysUserServiceImpl extends EgovAbstractServiceImpl implements	FsysUserService{
	
	@Resource(name="fsysUserDAO")
	private FsysUserDAO fsysUserDAO;
	

	/**
	 * 내정보를 가져온다.
	 * @param vo - 내정보가 담긴 vo
	 * @return 내정보
	 * @exception Exception
	 */
	public FLytLoginVO myInfoList(String userId) throws Exception {
       return fsysUserDAO.myInfoList(userId);
   }
	
	 
	
	/**
	 * 비밀번호 오류 횟수 업데이트
	 * @param vo - LoginVO
	 * @throws Exception
	 */
	public void updatePwdErrCnt(FLytLoginVO vo) throws Exception{
		fsysUserDAO.updatePwdErrCnt(vo);
	}

	/**
	 * 비밀번호 오류 횟수 조회
	 * @param vo - LoginVO
	 * @return
	 * @throws Exception
	 */
	public int getPwdErrCnt(FLytLoginVO vo) throws Exception{
		return fsysUserDAO.getPwdErrCnt(vo);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : myInfoList
	 * 2. 작성일 : 2021. 4. 16. 오전 10:20:13
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자관리 > 사용자 등록 처리
	 * </pre>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void regFsysUserRDtl(HashMap<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		
		//사용자 등록처리
		fsysUserDAO.regFsysUserRDtl(map);
		
		//부서별 사용자 등록처리
		fsysUserDAO.regFsysDeptUserRDtl(map);
				
				
//		if( "lvl1".equals(map.get("menuLvl"))) {
//			menuDAO.updateUpAdmMenuOrdr(map);
//		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysUserUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:20:13
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자관리 > 사용자 삭제 처리
	 * </pre>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void delFsysUserUDtl(HashMap<String, String> map) throws Exception{
		// TODO Auto-generated method stub
		
		//부서별 사용자 삭제 처리
		fsysUserDAO.delFsysDeptUserRDtl(map);
				
		//사용자 삭제 처리
		fsysUserDAO.delFsysUserUDtl(map);
		
		
				
				
//		if( "lvl1".equals(map.get("menuLvl"))) {
//			menuDAO.updateUpAdmMenuOrdr(map);
//		}
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : userInfoList
	 * 2. 작성일 : 2021. 4. 16. 오전 10:20:13
	 * 3. 작성자 : jmkim
	 * 4. 설명 : 
	 * </pre>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public FsysUserVO userInfoList(String userId) {
		// TODO Auto-generated method stub
		return fsysUserDAO.userInfoList(userId);
	}
	
	
}
