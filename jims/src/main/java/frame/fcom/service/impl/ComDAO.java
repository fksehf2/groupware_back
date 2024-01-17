package frame.fcom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import frame.fcom.service.CodeVO;
import frame.fcom.service.FileVO;
import frame.flyt.main.service.MenuVO;

@Repository("comDAO")
public class ComDAO extends EgovComAbstractDAO {


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
	public String selectCommonQueryString(Map map) throws Exception{
		String sqlQueryId = (String)map.get("sqlQueryId");
		return (String)selectOne(sqlQueryId, map);
	}
	
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
	public Map selectCommonQueryMap(Map map) throws Exception{
		String sqlQueryId = (String)map.get("sqlQueryId");
		return selectOne(sqlQueryId, map);
	}

	
	/**
	 * 코드 정보를 가져온다
	 * @param CodeVO
	 * @return list
	 * @exception Exception
	 */
	public List selectCdList(CodeVO vo) throws Exception{
		return list("comDAO.selectCdList",vo);
	}

	/**
	 * 첨부파일 목록을 조회한다.
	 * @param 조회할 정보가 담긴 Map
	 * @return 첨부파일 목록
	 * @exception Exception
	 */
	public List<FileVO> selectAtchFileList(Map map) throws Exception {
		return selectList("comDAO.selectAtchFileList", map);
	}

	/**
	 * 메뉴 목록을 조회한다.
	 * @param 조회할 정보가 담긴 Map
	 * @return 메뉴 목록
	 * @exception Exception
	 */
	public List<MenuVO> selectMenuList(Map map) throws Exception {
		return selectList("comDAO.selectMenuList", map);
	}

	/**
	 * 첨부파일 등록
	 * @param
	 * @return
	 * @exception Exception
	 */
	public int insertAtchFile(Map map) throws Exception {
		return insert("comDAO.insertAtchFile", map);
	}

	/**
	 * 첨부파일상세 등록
	 * @param
	 * @return
	 * @exception Exception
	 */
	public int insertAtchFileDtl(Map map) throws Exception {
		return (Integer) insert("comDAO.insertAtchFileDtl", map);
	}

	/**
	 * 첨부파일 삭제
	 * @param
	 * @return
	 * @exception Exception
	 */
	public void deleteAtchFile(Map map) throws Exception {
		update("comDAO.deleteAtchFile", map);
	}

	/**
	 * 첨부파일상세 삭제
	 * @param
	 * @return
	 * @exception Exception
	 */
	public void deleteAtchFileDtl(Map map) throws Exception {
		update("comDAO.deleteAtchFileDtl", map);
	}

	/**
	 * 첨부파일상세 수정
	 * @param
	 * @return
	 * @exception Exception
	 */
	public void updateAtchFileDtl(Map map) throws Exception {
		update("comDAO.updateAtchFileDtl", map);
	}

	/**
	 * 코드 목록을 조회한다.
	 * @param 조회할 정보가 담긴 Map
	 * @return 코드 목록
	 * @exception Exception
	 */
	public List<CodeVO> selectCodeList(Map map) throws Exception {
		return (List<CodeVO>) list("comDAO.selectCodeList", map);
	}

	/**
	 * 코드 목록을 조회한다.
	 * @param 조회할 정보가 담긴 Map
	 * @return 코드 목록
	 * @exception Exception
	 */
	public List<CodeVO> selectCodeList2(Map map) throws Exception {
		return (List<CodeVO>) list("comDAO.selectCodeList2", map);
	}

	/**
	 * 코드 목록을 조회한다.
	 * @param 조회할 정보가 담긴 Map
	 * @return 코드 목록
	 * @exception Exception
	 */
	public List<CodeVO> selectCodeRangeList(Map map) throws Exception {
		return (List<CodeVO>) list("comDAO.selectCodeRangeList", map);
	}

	/**
	 * 페이지권한 조회
	 * @param 조회할 정보가 담긴 Map
	 * @return 조회 목록
	 * @exception Exception
	 *//*
	public String selectAuthor(Map map) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("comDAO.selectAuthor", map);
	}

	*//**
	 * BLOB으로 파일 저장
	 *
	 * @param 정보가 담긴 Map
	 * @throws Exception
	 */
	public void insertAtchStrg(Map map) throws Exception {
		insert("comDAO.insertAtchStrg", map);
	}

	/**
	 * BLOB으로 파일 불러오기
	 *
	 * @param 조회할 정보가 담긴 Map
	 * @return 조회 목록
	 * @throws Exception
	 *//*
	public Map getFile(Map map) throws Exception {
		return (Map)getSqlMapClientTemplate().queryForObject("comDAO.getFile", map);
	}

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
	public List selectCommonQueryList(Map map) throws Exception{
		String sqlQueryId = (String)map.get("sqlQueryId");
		return selectList(sqlQueryId,map);
	}

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
	 *//*
	public Map selectCommonQueryMap(Map map) throws Exception{
		String sqlQueryId = (String)map.get("sqlQueryId");
		return (Map)select(sqlQueryId,map);
	}



	*//**
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
	public int selectCommonQueryListTotCnt(Map map) {
		String sqlQueryId = (String)map.get("sqlQueryId");
		return (Integer)selectOne(sqlQueryId, map);
		//return (Integer)getSqlMapClientTemplate().queryForObject(sqlQueryId, map);
	}

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
	public Integer insertCommonQuery(Map map) throws Exception{
		String sqlQueryId = (String)map.get("sqlQueryId");
		return (Integer) insert(sqlQueryId, map);
	}

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
	 *  	2.1 실제 화면에서는 삭제기능이나 USE_YN = "N"의 변경같은 기능은 공통 update 기능 호출
	 *
	 * </pre>
	 *
	 */
	public void updateCommonQuery(Map map) throws Exception{
		String sqlQueryId = (String)map.get("sqlQueryId");
		update(sqlQueryId, map);
	}

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
	public void deleteCommonQuery(Map map) throws Exception{
		String sqlQueryId = (String)map.get("sqlQueryId");
		delete(sqlQueryId, map);
	}

	*//**
	 * 시도 정보를 가져온다.
	 * @param 시도정보 VO
	 * @return 시도 정보
	 * @exception Exception
	 *//*
	public List sidoList(String sidoCd) throws Exception {
		return list("comDAO.sidoList", sidoCd);
	}

	*//**
	 * AJAX로 시군구 조회
	 * @param 조회할 정보가 담긴 Map
	 * @return 시군구 목록
	 * @exception Exception
	 *//*
	public List ajaxSigunList(Map map) throws Exception {
		return list("comDAO.ajaxSigunList", map);
	}

	*//**
	 * @author Kimkh
	 * @param map
	 * @return
	 * @throws Exception
	 * <pre>
	 * 대용량 엑셀 다운로드를 위해 Ibatis rowHandler를 사용
	 * </pre>
	 *//*
	public void rowHandlerCommonQuery(Map map, JimsRowHandler rowHandler) throws Exception{

		String sqlQueryId = (String)map.get("sqlQueryId");
		getSqlMapClient().queryWithRowHandler(sqlQueryId, map, rowHandler);
	}

	*//**
	 * @author Kimkh
	 * @param map
	 * @return
	 * @throws Exception
	 * <pre>
	 * 대용량 엑셀 다운로드를 위해 Ibatis rowHandler를 사용
	 * </pre>
	 *//*
	public void rowHandlerCommonQuery(Map map, JimsRowHandler2 rowHandler) throws Exception{

		String sqlQueryId = (String)map.get("sqlQueryId");
		getSqlMapClient().queryWithRowHandler(sqlQueryId, map, rowHandler);
	}
*/
}