package frame.fsys.user.service.impl;

import java.util.HashMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import frame.flyt.login.service.FLytLoginVO;
import frame.fsys.user.service.FsysUserVO;

@Repository("fsysUserDAO")
@SuppressWarnings({"rawtypes","unchecked"})
public class FsysUserDAO extends EgovComAbstractDAO {


    /**
     * 내정보를 가져온다.
     * @param 내정보 VO
     * @return 내정보
     * @exception Exception
     */
      public FLytLoginVO myInfoList(String userId) throws Exception {
           return (FLytLoginVO)selectByPk("fsysUserDAO.myInfoList", userId);
      }
	
	/**
	 * 비밀번호 오류 횟수 업데이트
	 * @param vo - LoginVO
	 * @throws Exception
	 */
	public void updatePwdErrCnt(FLytLoginVO vo) throws Exception{
		update("fsysUserDAO.updatePwdErrCnt", vo);
	}

	/**
	 * 비밀번호 오류 횟수 조회
	 * @param vo - LoginVO
	 * @return
	 * @throws Exception
	 */
	public int getPwdErrCnt(FLytLoginVO vo) throws Exception{
		return (Integer)selectOne("fsysUserDAO.getPwdErrCnt", vo);
	}
	
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : insertAdmMenu
	 * 2. 작성일 : 2021. 4. 16. 오전 9:58:51
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자 등록처리 
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void regFsysUserRDtl(HashMap<String, String> map) throws Exception {
		insert("fsysUserDAO.regFsysUserRDtl", map);

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regFsysDeptRDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서별 사용자 등록처리
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void regFsysDeptUserRDtl(HashMap<String, String> map) throws Exception {
		insert("fsysUserDAO.regFsysDeptUserRDtl", map);

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysUserUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자 삭제 처리
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void delFsysUserUDtl(HashMap<String, String> map) throws Exception {
		insert("fsysUserDAO.delFsysUserUDtl", map);

	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysDeptUserRDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서별 사용자 삭제 처리
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void delFsysDeptUserRDtl(HashMap<String, String> map) throws Exception {
		insert("fsysUserDAO.delFsysDeptUserRDtl", map);

	}
	
	/**
	 * <pre>
	 * 1. 메소드명 : userInfoList
	 * 2. 작성일 : 2021. 4. 16. 오후 2:26:56
	 * 3. 작성자 : Eric
	 * 4. 설명 : 
	 * </pre>
	 * @param userId
	 * @return
	 */
	
	public FsysUserVO userInfoList(String userId) {
		return (FsysUserVO)selectOne("fsysUserDAO.userInfoList", userId);
	}
	
}
