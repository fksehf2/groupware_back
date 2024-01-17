package frame.fsys.code.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frame.flyt.main.service.MenuVO;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysCodeService.java
 * 2. 작성일 : 2021. 5. 13.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 코드관리
 * </pre>
 */
public interface FsysCodeService {

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
	String regFsysCodeDtlRDtl(HashMap reqParams) throws Exception;

}
