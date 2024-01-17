package frame.fbbs.ntc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.fbbs.ntc.service.FBbsNtcService;
import frame.fbbs.ntc.service.FBbsNtcVO;
import frame.fcom.service.ComService;


@Service("fBbsNtcService")
public class FBbsNtcServiceImpl extends EgovAbstractServiceImpl implements FBbsNtcService{

	protected Logger log = LoggerFactory.getLogger(FBbsNtcServiceImpl.class);

	@Resource(name="comService")
	private ComService comService;

	@Resource(name="fBbsNtcDAO")
	private FBbsNtcDAO fBbsNtcDAO;

	/**
     * 
     * <pre>
     * 1. 메소드명 : selectFBbsNtcList
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 리스트 조회
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public List selectFBbsNtcList(Map<String, Object> paramMap) throws Exception {
		
		return fBbsNtcDAO.selectFBbsNtcList(paramMap);
	}

	/**
     * 
     * <pre>
     * 1. 메소드명 : updateFBbsNtcCount
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 조회수 update 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public void updateFBbsNtcCount(Map<String, Object> paramMap) throws Exception {
		
		fBbsNtcDAO.updateFBbsNtcCount(paramMap);
		
	}

	/**
     * 
     * <pre>
     * 1. 메소드명 : selectFBbsNtcRDtl
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  자유게시판 상세 조회
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public Map selectFBbsNtcRDtl(Map<String, Object> paramMap) throws Exception {
		
		return fBbsNtcDAO.selectFBbsNtcRDtl(paramMap);
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
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public Map selectFBbsNtcUser(Map<String, Object> paramMap) throws Exception {
		
		return fBbsNtcDAO.selectFBbsNtcUser(paramMap);
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
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public String selectUserGb(Map<String, Object> paramMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public String insertFBbsNtc(Map<String, Object> paramMap, Map<String, Object> fileMap) throws Exception {
		String result = "";

		boolean denyListChk = true;
		String[] eList = EgovProperties.getProperty("Globals.fileUpload.Extensions").toString().split("\\.");

        if(fileMap.get("fileList4OriFileNm") != null){
            if (fileMap.get("fileList4OriFileNm") instanceof String) {
            	String fileList = (String) fileMap.get("fileList4OriFileNm");
                	for(int k=0; k < eList.length ;k++){
            	 		if (fileList.toLowerCase().indexOf("."+eList[k]) > -1){
               		 		denyListChk = false;
            	 		}
            	 	}
        	}else {
        		List<String> fileList = (List<String>) fileMap.get("fileList4OriFileNm");
        		for (int i = 0; i < fileList.size(); i++) {
                	for(int k=0; k < eList.length ;k++){
            	 		if (fileList.get(i).toLowerCase().indexOf("."+eList[k]) > -1){
               		 		denyListChk = false;
            	 		}
            	 	}
    			}
        	}
        }

	 	if (denyListChk && fileMap.get("fileList4OriFileNm") != null) {
	 		result = "2";
        }else{
	    	//첨부파일등록
			String loginId = (String) paramMap.get("regrId");
	    	String atchFileId = (String) fileMap.get("atchFileId");

	    	if(EgovStringUtil.isNull(atchFileId)){
	    		atchFileId = comService.insertAtchFile("fileList4", "10", loginId, fileMap);
	    	}else{
	    		atchFileId = comService.updateAtchFile(atchFileId, "fileList4", "10", loginId, fileMap);
	    	}
	    	paramMap.put("atchFileId", atchFileId);
	    	fBbsNtcDAO.insertFBbsNtcs(paramMap);
	    	
	    	result = "1";
        }

        return result;
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
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public String updateFBbsNtc(Map<String, Object> paramMap, Map<String, Object> fileMap) throws Exception {
		
		String result = "";
		String loginId = (String) paramMap.get("regrId");
    	String atchFileId = (String) fileMap.get("atchFileId");

    	boolean denyListChk = true;
        String[] eList = EgovProperties.getProperty("Globals.fileUpload.Extensions").toString().split("\\.");

        if(fileMap.get("fileList4OriFileNm") != null){
            if (fileMap.get("fileList4OriFileNm") instanceof String) {
            	String fileList = (String) fileMap.get("fileList4OriFileNm");
                	for(int k=0; k < eList.length ;k++){
            	 		if (fileList.toLowerCase().indexOf("."+eList[k]) > -1){
               		 		denyListChk = false;
            	 		}
            	 	}
        	}else {
        		List<String> fileList = (List<String>) fileMap.get("fileList4OriFileNm");
        		for (int i = 0; i < fileList.size(); i++) {
                	for(int k=0; k < eList.length ;k++){
            	 		if (fileList.get(i).toLowerCase().indexOf("."+eList[k]) > -1){
               		 		denyListChk = false;
            	 		}
            	 	}
    			}
        	}
        }

	 	if (denyListChk && fileMap.get("fileList4OriFileNm") != null) {
	 		result = "2";
        }else{
	       	if(EgovStringUtil.isNull(atchFileId)){
	       		atchFileId = comService.insertAtchFile("fileList4", "10", loginId, fileMap);
	       	}else{
	       		atchFileId = comService.updateAtchFile(atchFileId, "fileList4", "10", loginId, fileMap);
	       	}

	       	paramMap.put("atchFileId", atchFileId);
	       	fBbsNtcDAO.updateFBbsNtc(paramMap);
	    	result = "1";
        }

    	return result;
    	
	}

	/**
     * 
     * <pre>
     * 1. 메소드명 : deleteFBbsNtc
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  자유게시판 delete
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public void deleteFBbsNtc(Map<String, Object> paramMap) throws Exception {
		
		fBbsNtcDAO.deleteFBbsNtc(paramMap);
		
	}

	


}