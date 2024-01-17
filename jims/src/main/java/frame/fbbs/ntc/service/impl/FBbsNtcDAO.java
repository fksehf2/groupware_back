/**
 * 
 * <pre>
 * 1. 클래스명 : FBbsNtcDAO.java
 * 2. 작성일 : 2021. 4. 15.
 * 3. 작성자 : Leeji
 * 4. 설명 : FBbsNtcDAO
 * </pre>
 * 
 */
package frame.fbbs.ntc.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("fBbsNtcDAO")
public class FBbsNtcDAO extends EgovComAbstractDAO {

	protected Logger log = LoggerFactory.getLogger(FBbsNtcDAO.class);

	/**
     * 
     * <pre>
     * 1. 메소드명 : selectFBbsNtcList
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 리스트 조회
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return Map
     * @throws Exception
     */
    public List selectFBbsNtcList(Map<String, Object> paramMap) throws Exception {

    	return (List) selectList("fBbsNtcDAO.selectFBbsNtcList", paramMap);
    }

    /**
     * 
     * <pre>
     * 1. 메소드명 : updateFBbsNtcCount
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 조회수 update 
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return void
     * @throws Exception
     */
    public void updateFBbsNtcCount(Map<String, Object> paramMap) throws Exception {
    	update("fBbsNtcDAO.updateFBbsNtcCount", paramMap);
    }

    /**
     * 
     * <pre>
     * 1. 메소드명 : selectFBbsNtcUser
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 사용자 조회
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return map
     * @throws Exception
     */
    public Map selectFBbsNtcUser(Map<String, Object> paramMap) throws Exception {

    	return (Map) selectByPk("fBbsNtcDAO.selectFBbsNtcUser", paramMap);
    }

    /**
     * 
     * <pre>
     * 1. 메소드명 : selectUserGb
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 사용자그룹 조회
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return String
     * @throws Exception
     */
	public String selectUserGb(String userId) throws Exception{
		return (String)selectOne("fBbsNtcDAO.selectUserGb", userId);
	}

	/**
     * 
     * <pre>
     * 1. 메소드명 : insertFBbsNtc
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 insert
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return int
     * @throws Exception
     */
    public int insertFBbsNtcs(Map<String, Object> paramMap) throws Exception {

    	return (Integer) insert("fBbsNtcDAO.insertFBbsNtc", paramMap);
    }

    /**
     * 
     * <pre>
     * 1. 메소드명 : updateFBbsNtc
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 update
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return void
     * @throws Exception
     */
    public void updateFBbsNtc(Map<String, Object> paramMap) throws Exception {
        update("fBbsNtcDAO.updateFBbsNtc", paramMap);
    }

    /**
     * 
     * <pre>
     * 1. 메소드명 : deleteFBbsNtc
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 delete
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return void
     * @throws Exception
     */
    public void deleteFBbsNtc(Map<String, Object> paramMap) throws Exception {
        update("fBbsNtcDAO.deleteFBbsNtc", paramMap);
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : selectFBbsNtcRDtl
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 상세 조회
     * </pre>
     * @param map
     * @param 
     * @param 
     * @return Map
     * @throws Exception
     */
    public Map selectFBbsNtcRDtl(Map<String, Object> paramMap) throws Exception {

    	return (Map) selectOne("fBbsNtcDAO.selectFBbsNtcRDtl", paramMap);
    }

}