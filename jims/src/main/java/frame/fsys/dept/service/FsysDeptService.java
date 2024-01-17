package frame.fsys.dept.service;

import java.util.HashMap;
import frame.flyt.login.service.FLytLoginVO;


/*
 * 
 */
@SuppressWarnings("rawtypes")
public interface FsysDeptService {

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : insertFsysDeptUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:17:54
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서별 사용자 등록 처리
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void insertFsysDeptUDtl(HashMap<String, String> map)  throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : modifyFsysDeptUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:17:54
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 상세 정보 수정
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void modifyFsysDeptUDtl(HashMap<String, String> map)  throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : deleteFsysDeptUDtl
	 * 2. 작성일 : 2021. 4. 16. 오전 10:17:54
	 * 3. 작성자 : ilyong
	 * 4. 설명 : 부서관리 > 부서 정보 삭제
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void deleteFsysDeptUDtl(HashMap<String, String> map)  throws Exception;

	
	
	
}
