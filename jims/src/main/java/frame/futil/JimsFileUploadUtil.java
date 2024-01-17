package frame.futil;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import egovframework.com.cmm.EgovWebUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.utl.fcc.service.EgovFormBasedFileUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileVo;

/**
 * @Class Name : SpmsFileUploadUtil.java
 * @Description : SpmsFileUploadUtil class
 * @Modification Information
 *
 * @author 김규형
 * @since 2018.10.10
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class JimsFileUploadUtil extends EgovFormBasedFileUtil {
	
	private static final Logger log = LoggerFactory .getLogger(JimsFileUploadUtil.class);
    /**
     * 파일을 Upload 처리한다.
     *
     * @param request
     * @param where
     * @param maxFileSize
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static List<EgovFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize, String type) throws Exception {
	List<EgovFormBasedFileVo> list = new ArrayList<EgovFormBasedFileVo>();

	//MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
	MultipartHttpServletRequest mptRequest = WebUtils.getNativeRequest(request,MultipartHttpServletRequest.class);
	
	Iterator fileIter = mptRequest.getFileNames();

	while (fileIter.hasNext()) {
	    MultipartFile mFile = mptRequest.getFile((String)fileIter.next());

	    EgovFormBasedFileVo vo = new EgovFormBasedFileVo();
	    Map<String,Object> map = new HashMap<String, Object>();

	    String tmp = mFile.getOriginalFilename();

        if (tmp.lastIndexOf("\\") >= 0) {
    	tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
        }

        vo.setFileName(tmp);
        vo.setContentType(mFile.getContentType());
        vo.setServerSubPath(getTodayString()+"/"+getDayString());
        vo.setPhysicalName(getTimeStamp());
        vo.setSize(mFile.getSize());
        
        map.put("fileType", mFile.getContentType());
        map.put("filePth", getTodayString()+"/"+getDayString());
        map.put("fileNm", getTimeStamp());
        map.put("atchStrg", mFile.getBytes());

        if (tmp.lastIndexOf(".") >= 0) {
   	 	vo.setPhysicalName(vo.getPhysicalName() + tmp.substring(tmp.lastIndexOf(".")));
        }
        
        if ( mFile.getSize() > maxFileSize ) {
        	//업로드 파일은 4M 이하여야 합니다.
        	throw new IOException("001"); 
        }
        
        boolean denyListChk = true;
        //String[] denyList = EgovProperties.getProperty("Globals.deniedExtensionsFile").toString().split("\\|");
        String[] extenList = EgovProperties.getProperty("Globals.fileUpload.Extensions").toString().split("\\.");        
        
	 	for(int k=0; k < extenList.length ;k++){
	 		if (vo.getFileName().toLowerCase().indexOf("."+extenList[k]) > -1){
   		 		//허용되지 않은 확장자의 경우 업로드 불가
   		 		denyListChk = false;
	 		}else {
	 			denyListChk = true;
	 		}
	 	}
        
        if (denyListChk) {
        	//보안상 업로드할 수 없는 파일 입니다.
    		throw new IOException("002");
        }
        
        /*if ("image".equals(type)) {
        	
        	boolean imageFileListChk = true;
            String[] imageFileList = EgovProperties.getProperty("Globals.imageFile").toString().split("\\|");
            
   		 	for(int k=0; k < imageFileList.length ;k++){
   		 		if (vo.getFileName().toLowerCase().indexOf("."+imageFileList[k]) > -1){
   		 			imageFileListChk = false;
   		 		}
   		 	}
        	
   		 	if (imageFileListChk) {
   		 		//이미지파일(jpg, gif, jpeg, png, bmp)만 업로드 가능합니다.
   		 		throw new IOException("003");
   		 	}
        }*/

        if (mFile.getSize() > 0) {
        	saveFile(mFile.getInputStream(), new File(EgovWebUtil.filePathBlackList(where+"/"+vo.getServerSubPath()+"/"+vo.getPhysicalName())));
        	list.add(vo);
        }
	}

	return list;
    }
    
    /**
     * 파일을 Blob 처리한다.
     *
     * @param request
     * @param where
     * @param maxFileSize
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static List<Map<String,Object>> uploadBlobs(HttpServletRequest request, String where, long maxFileSize, String type) throws Exception {
	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

	//MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest)request;
	MultipartHttpServletRequest mptRequest = WebUtils.getNativeRequest(request,MultipartHttpServletRequest.class);
	Iterator fileIter = mptRequest.getFileNames();

	while (fileIter.hasNext()) {
	    MultipartFile mFile = mptRequest.getFile((String)fileIter.next());
	    Map<String,Object> map = new HashMap<String, Object>();
        
        map.put("atchStrg", mFile.getBytes());

        if (mFile.getSize() > 0) {
        	list.add(map);
        }
	}

	return list;
    }
    
    
    /**
     * 오늘 날짜(년월) 문자열 취득.
     * ex) 200901
     * @return
     */
    public static String getTodayString() {
	SimpleDateFormat format = new SimpleDateFormat("yyyyMM", Locale.getDefault());

	return format.format(new Date());
    }
    
    /**
     * 오늘 날짜(일자) 문자열 취득.
     * ex) 01
     * @return
     */
    public static String getDayString() {
	SimpleDateFormat format = new SimpleDateFormat("dd", Locale.getDefault());

	return format.format(new Date());
    }
    
    /**
     * 2011.08.09
     * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함
     * 응용어플리케이션에서 고유값을 사용하기 위해 시스템에서17자리의TIMESTAMP값을 구하는 기능
     *
     * @param
     * @return Timestamp 값
     * @exception MyException
     * @see
     */
    public static String getTimeStamp() {

	String rtnStr = null;

	// 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
	String pattern = "yyyyMMddhhmmssSSS";

	try {
	    SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());

	    rtnStr = sdfCurrent.format(ts.getTime());
	} catch (Exception e) {
	    //e.printStackTrace();
		
	    //throw new RuntimeException(e);	// 보안점검 후속조치
		log.debug("IGNORED: " + e.getMessage());
	}

	return rtnStr;
    }
    
    /**
     * Disposition 지정하기.
     * 
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    public static void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String browser = JimsUtil.getBrowser(request);
		
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = JimsUtil.encodedFilename(filename, request);
		
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
	
		if ("Opera".equals(browser)){
		    response.setContentType("application/octet-stream;charset=UTF-8");
		}
    }
}
