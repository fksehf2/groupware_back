package frame.fsys.dept.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import frame.flyt.login.service.FLytLoginVO;


@Repository("fsysDeptDAO")
@SuppressWarnings({"rawtypes","unchecked"})
public class FsysDeptDAO extends EgovComAbstractDAO {


	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : insertFsysDeptUser
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서별 사용자 등록 처리
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void insertFsysDeptUserInfo(Map map) throws Exception {
		update("fsysDeptDAO.insertFsysDeptUser", map);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : modifyFsysDeptUserDetail
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서별 사용자 등록 후 이전 사용자 정보 수정
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void modifyFsysDeptUserDetail(Map map) throws Exception {
		update("fsysDeptDAO.modifyFsysDeptUserDetail", map);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : modifyFsysUserDetail
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 사용자 정보의 부서코드를 새로 등록한 부서코드로 수정
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void modifyFsysUserDetail(Map map) throws Exception {
		update("fsysDeptDAO.modifyFsysUserDetail", map);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : modifyFsysDeptNm
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 상세 정보 수정(과명 수정)
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void modifyFsysDeptNm(HashMap<String, String> map) throws Exception {
		update("fsysDeptDAO.updFsysDeptUDtl", map);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : modifyFsysDeptNm
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 상세 정보 수정(상위부서의 과명 수정시 해당과에 속하는 팀의 과명 모두 수정)
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void modifyFsysDeptNmAll(HashMap<String, String> map) throws Exception {
		update("fsysDeptDAO.modifyFsysDeptNmAll", map);
	}
	
	/**
    *
    * <pre>
    * 1. 메소드명 : selectLowerDeptCount
    * 2. 작성일 : 2021. 4. 21.
    * 3. 작성자 : ilyong
    * 4. 설명 :  삭제할려는 부서에 속한 하위부서 유무 조회
    * </pre>
    * @param map
    * @return int
    * @throws Exception
    */
	public int selectLowerDeptCount(Map map) throws Exception{
		return selectOne("fsysDeptDAO.selectLowerDeptCount", map);
	}
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : delFsysDept
	 * 2. 작성일 : 2021. 4. 16. 오전 10:48:53
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 삭제
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	public void delFsysDept(HashMap<String, String> map) throws Exception {
		update("fsysDeptDAO.delFsysDeptUDtl", map);
	}
	
}
