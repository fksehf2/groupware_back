package frame.fcom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import frame.flyt.main.service.MenuVO;

public interface ComService {


	
    /**
    *
    * @author KimKh
    * @param  Map
    * @return String
    * @throws Exception
    *
    * <pre>
    *  공통 조회 서비스
    *
    *  1. 각 컨트롤러에서 Map에 쿼리ID를 담아주면 공통 DAO에서 해당 쿼리ID를 호출 후 조회대상 Return
    *  	1.1 쿼리ID -> map.put("sqlQueryId", "조회대상 쿼리ID");
    *  2. 단건 String 조회시 사용
    *
    * </pre>
    *
    */
	String selectCommonQueryString(Map map) throws Exception;

	/*
	*//**
	 * 코드 정보를 가져온다
	 * @param CodeVO
	 * @return list
	 * @exception Exception
	 *//*
	List selectCdList(CodeVO vo) throws Exception;

	*//**
     * 첨부파일 목록을 조회한다.
     * @param 조회할 첨부파일 ID, 파일일련번호
     * @return 첨부파일 목록
     * @exception Exception
     */
	List<FileVO> selectAtchFileList(String atchFileId, String fileSno) throws Exception;

	/**
	 * 메뉴 조회한다.
	 * @param 조회할 정보가 담긴 Map
	 * @return 메뉴 목록
	 * @exception Exception
	 */
	List<MenuVO> selectMenuList(Map map) throws Exception;

	 /**
     * 첨부파일 등록
     * @param
     * @return
     * @exception Exception
     */
    String insertAtchFile(String objNm, String atchFileDiv, String loginId, Map<String, Object> commandMap) throws Exception;

    /**
     * 첨부파일 수정
     * @param
     * @return
     * @exception Exception
     */
    String updateAtchFile(String atchFileId, String objNm, String atchFileDiv, String loginId, Map<String, Object> commandMap) throws Exception;

    /**
   	 * 코드 조회한다.
   	 * @param 조회할 CODE_ID
   	 * @return 코드 목록
   	 * @exception Exception
   	 */
   	List<CodeVO> selectCodeList(String codeId) throws Exception;

   	/**
   	 * 코드 조회한다.
   	 * @param 조회할 CODE_ID
   	 * @return 코드 목록
   	 * @exception Exception
   	 */
   	List<CodeVO> selectCodeList1(String codeId) throws Exception;
   	
   	/**
   	 * 코드 조회한다.
   	 * @param 조회할 CD
   	 * @return 코드 목록
   	 * @exception Exception
   	 */
   	List<CodeVO> selectCodeList2(String codeId) throws Exception;

    /**
   	 * 코드 조회한다.
   	 * @param 조회할 CODE_ID
   	 * @return 코드 목록
   	 * @exception Exception
   	 */
   	List<CodeVO> selectCodeRangeList(String codeId, String minerCodeId) throws Exception;

   	/**
     * 페이지권한 조회
     * @param 조회할 정보가 담긴 Map
     * @return String
     * @exception Exception
     *//*
    String selectAuthor(Map map) throws Exception;

    void insertAtchStrg(Map map) throws Exception;

    Map getFile(Map map) throws Exception;

    *//**
     *
     * @author KimKh
     * @param  Map
     * @return 조회대상 List
     * @throws Exception
     *
     * <pre>
     *  공통 조회 서비스
     *
     *  1. 각 컨트롤러에서 Map에 쿼리ID를 담아주면 공통 DAO에서 해당 쿼리ID를 호출 후 조회대상 Return
     *  	1.1 쿼리ID -> map.put("sqlQueryId", "조회대상 쿼리ID");
     *  2. List 조회시 사용
     *
     * </pre>
     *
     */
	List selectCommonQueryList(Map map) throws Exception;

    /**
     *
     * @author KimKh
     * @param  Map
     * @return 조회대상을 Map으로 Return
     * @throws Exception
     *
     * <pre>
     *  공통 조회 서비스
     *
     *  1. 각 컨트롤러에서 Map에 쿼리ID를 담아주면 공통 DAO에서 해당 쿼리ID를 호출 후 조회대상 Return
     *  	1.1 쿼리ID -> map.put("sqlQueryId", "조회대상 쿼리ID");
     *  2. 상세 조회시 사용
     *
     * </pre>
     *
     */
	Map selectCommonQueryMap(Map map) throws Exception;


    /**
     *
     * @author KimKh
     * @param  Map
     * @return Integer
     * @throws Exception
     *
     * <pre>
     *  공통 조회 서비스
     *
     *  1. 각 컨트롤러에서 Map에 쿼리ID를 담아주면 공통 DAO에서 해당 쿼리ID를 호출 후 조회대상 Return
     *  	1.1 쿼리ID -> map.put("sqlQueryId", "조회대상 쿼리ID");
     *  2. 페이징 조회 시 전체 조회건수 조회시 사용
     *
     * </pre>
     *
     */
	int selectCommonQueryListTotCnt(Map map) throws Exception;

	/**
	 *
	 * @author KimKh
	 * @param  Map
	 * @return void
	 * @throws Exception
	 *
	 * <pre>
	 *  공통 조회 서비스
	 *
	 *  1. 각 컨트롤러에서 Map에 쿼리ID를 담아주면 공통 DAO에서 해당 쿼리ID를 호출 후 조회대상 Return
	 *  	1.1 쿼리ID -> map.put("sqlQueryId", "조회대상 쿼리ID");
	 *  2. 단순 insert시 사용
	 *
	 * </pre>
	 *
	 *//*
	void insertCommonQuery(Map map) throws Exception;


	*//**
	 *
	 * @author KimKh
	 * @param  Map
	 * @return void
	 * @throws Exception
	 *
	 * <pre>
	 *  공통 조회 서비스
	 *
	 *  1. 각 컨트롤러에서 Map에 쿼리ID를 담아주면 공통 DAO에서 해당 쿼리ID를 호출 후 조회대상 Return
	 *  	1.1 쿼리ID -> map.put("sqlQueryId", "조회대상 쿼리ID");
	 *  2. 단순 update시 사용
	 *
	 * </pre>
	 *
	 */
	void updateCommonQuery(Map map) throws Exception;


	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : insertCommonQuery
	 * 2. 작성일 : 2021. 5. 3. 오후 4:48:43
	 * 3. 작성자 : Eric
	 * 4. 설명 : 
	 * </pre>
	 * @param map
	 * @throws Exception
	 */
	void insertCommonQuery(Map map) throws Exception;

	
	/**
	 *
	 * @author KimKh
	 * @param  Map
	 * @return void
	 * @throws Exception
	 *
	 * <pre>
	 *  공통 조회 서비스
	 *
	 *  1. 각 컨트롤러에서 Map에 쿼리ID를 담아주면 공통 DAO에서 해당 쿼리ID를 호출 후 조회대상 Return
	 *  	1.1 쿼리ID -> map.put("sqlQueryId", "조회대상 쿼리ID");
	 *  2. 단순 delete시 사용
	 *
	 * </pre>
	 *
	 *//*
	void deleteCommonQuery(Map map) throws Exception;

	*//**
	 * 지역(시도) 정보를 가져온다
	 * @param vo - VO
	 * @return 시도 정보
	 * @exception Exception
	 *//*
	List sidoList(String sidoCd) throws Exception;

	*//**
     * AJAX로 시군구 조회
     * @param 조회할 정보가 담긴 Map
     * @return 시군구 목록
     * @exception Exception
     *//*
	List ajaxSigunList(Map map) throws Exception;

	void fileDownloadExcel(
			Map map,
			HttpServletResponse res) throws Exception;

	void fileDownloadExcel2(
			Map map,
			HttpServletResponse res) throws Exception;*/
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : queryMListExcel
	 * 2. 작성일 : 2021. 5. 11. 오후 5:29:55
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 엑셀다운로드
	 * </pre>
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	ModelAndView queryMListExcel(HashMap reqParams) throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updExcSysCrimeCodeMList
	 * 2. 작성일 : 2021. 5. 12. 오후 5:13:19
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 엑셀업로드 
	 * </pre>
	 * @param request
	 */
	void updExcMList(MultipartHttpServletRequest request, HashMap<String, Object> reqParams) throws Exception;
	
}
