/**
 *
 * <pre>
 * 1. 클래스명 : AnlsReqService.java
 * 2. 작성일 : 2021. 4. 15.
 * 3. 작성자 : Leeji
 * 4. 설명 : 분석지원요청 Service
 * </pre>
 *
 */
package frame.fsys.alam.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * 1. 클래스명 : FsysAlamService.java
 * 2. 작성일 : 2021. 6. 2.
 * 3. 작성자 : jij
 * 4. 설명 : @!@ 알람 담당
 * </pre>
 */
public interface FsysAlamService {

	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : regEvdcWarnAlam
	 * 2. 작성일 : 2021. 6. 2. 오전 11:34:21
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 미허가 장비 반출 알람 등록
	 * </pre>
	 * @param paramData
	 * @throws Exception
	 */
	void regEvdcWarnAlam(HashMap reqParams) throws Exception;
	
	/**
	 * 
	 * <pre>
	 * 1. 메소드명 : updEvdcWarnAlamCfrmY
	 * 2. 작성일 : 2021. 6. 2. 오전 11:34:21
	 * 3. 작성자 : jij
	 * 4. 설명 : @!@ 미허가 장비 반출 알람 확인
	 * </pre>
	 * @param paramData
	 * @throws Exception
	 */
	void updEvdcWarnAlamCfrmY(HashMap reqParams) throws Exception;
	
//
//	/**
//	 * 
//	 * <pre>
//	 * 1. 메소드명 : reqCnclSiteRcvUDtl
//	 * 2. 작성일 : 2021. 4. 30. 오후 2:15:08
//	 * 3. 작성자 : jij
//	 * 4. 설명 : @!@ 현장지원 요청취소
//	 * </pre>
//	 * @param paramData
//	 * @return
//	 * @throws Exception
//	 */
//	HashMap reqCnclSiteRcvUDtl(String paramData) throws Exception;

}