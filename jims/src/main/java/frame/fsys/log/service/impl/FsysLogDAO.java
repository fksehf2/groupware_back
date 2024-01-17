/**
 * 
 * <pre>
 * 1. 클래스명 : BbsRschDAO.java
 * 2. 작성일 : 2021. 4. 15.
 * 3. 작성자 : Leeji
 * 4. 설명 : BbsRschDAO
 * </pre>
 * 
 */
package frame.fsys.log.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;


@Repository("fsysLogDAO")
public class FsysLogDAO extends EgovComAbstractDAO {

	protected Logger log = LoggerFactory.getLogger(FsysLogDAO.class);

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : selectFsysLogList
	 * 2. 작성일 : 2021. 4. 26. 
	 * 3. 작성자 : sjw7240
	 * 4. 설명 : 로그 목록 조회
	 * </pre>
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
    public List selectFsysLogList(Map<String, Object> paramMap) throws Exception {

    	return (List) selectList("fsysLogDAO.selectFsysLogQList", paramMap);
    }
    
    public List selectFsysLogListDtl(Map map) throws Exception{
    	return (List) selectList("fsysLogDAO.queryFsysLogDtlQList", map);
    }

}