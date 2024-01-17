package frame.fsys.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import frame.flyt.main.service.MenuVO;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysMenuService.java
 * 2. 작성일 : 2021. 5. 14.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 메뉴관리
 * </pre>
 */
public interface FsysMenuService {

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
	String[] regFsysMenuRDtl(HashMap reqParams) throws Exception;
	
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
	String[] updFsysMenuUDtl(HashMap reqParams) throws Exception;

}
