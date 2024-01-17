package frame.fcom.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.Globals;
import egovframework.com.utl.fcc.service.EgovFormBasedFileUtil;
import egovframework.com.utl.fcc.service.EgovFormBasedFileVo;
import egovframework.rte.fdl.filehandling.EgovFileUtil;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.fcom.service.ComService;
import frame.fcom.service.FileVO;
import frame.fexception.MException;
import frame.futil.JimsFileUploadUtil;
import frame.futil.JimsUtil;


/**
 * @Class Name : FileUtilController.java
 * @Description : FileUtilController class
 * @Modification Information
 *
 * @author 우성택
 * @since 2014.07.22
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
@Controller
public class FileUtilController {
	
	protected Logger log = LoggerFactory.getLogger(FileUtilController.class);
	
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    @Resource(name = "comService")
    private ComService comService;
    
    @Resource MappingJackson2JsonView ajaxMainView;
    
    /** 첨부파일 위치 지정 */
    private final String fileStorePath = EgovProperties.getProperty("Globals.fileStorePath");
    private final String fileStorePathEditor = EgovProperties.getProperty("Globals.editFileStorePath");
    
    /** 첨부 최대 파일 크기 지정 1024 * 1024 * 4 (4M) */
    private final long maxFileSize = Long.parseLong(EgovProperties.getProperty("Globals.maxUploadFileSize"));

    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.
     * 
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/fileDown.do")    
    public void fileDownload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String type = (String)commandMap.get("type");
    	String filePath = "";
    	FileVO fileVO = new FileVO(); 

    	if ("1".equals(type)) {
    		//첨부파일ID, 일련번호로 다운로드
    		String atchFileId = (String)commandMap.get("atchFileId");
        	String fileSno = (String)commandMap.get("fileSno");
        	
        	List<FileVO> fileList = comService.selectAtchFileList(atchFileId, fileSno);
        	
        	if (fileList.size() == 1) {
    	    	fileVO = fileList.get(0);
    	    }
        	
        	filePath = fileStorePath;
    		
    	}else if ("2".equals(type)){
    		//첨부파일경로, 파일명으로 다운로드
    		fileVO.setFilePth((String)commandMap.get("filePth"));
    		fileVO.setFileNm((String)commandMap.get("fileNm"));
    		if (EgovStringUtil.isNull((String)commandMap.get("oriFileNm"))){
    			fileVO.setOriFileNm((String)commandMap.get("fileNm"));
    		}else {
    			fileVO.setOriFileNm((String)commandMap.get("oriFileNm"));
    		}
    		
    		filePath = fileStorePath;
    	}
    	
        boolean denyListChk = true;
        String[] eList = EgovProperties.getProperty("Globals.fileDownload.Extensions").toString().split("\\.");

	 	for(int k=0; k < eList.length ;k++){
	 		if (fileVO.getFileNm().indexOf("."+eList[k]) > -1){
   		 		denyListChk = false;
	 		}
	 	}
	 	
        if (denyListChk) {
        	
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
	
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println(egovMessageSource.getMessage("common.fileupload.fail002"));
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();

        }else {
        	
    		//윕 취약점 보안 관련
        	fileVO.setFilePth(fileVO.getFilePth().replace("..", ""));
        	fileVO.setFilePth(fileVO.getFilePth().replace("~", ""));
    		fileVO.setFileNm(fileVO.getFileNm().replace("..", ""));
    		fileVO.setFileNm(fileVO.getFileNm().replace("~", ""));
        	
        	File uFile = new File(filePath+"/"+fileVO.getFilePth()+"/", fileVO.getFileNm());
    	    int fSize = (int)uFile.length();
    	    if (fSize > 0) {
    			String mimetype = "application/x-msdownload";
    	
    			//response.setBufferSize(fSize);	// OutOfMemeory 발생
    			response.setContentType(mimetype);
    			//response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
    			JimsFileUploadUtil.setDisposition(fileVO.getOriFileNm(), request, response);
    			response.setContentLength(fSize);
    	
    			/*
    			 * FileCopyUtils.copy(in, response.getOutputStream());
    			 * in.close(); 
    			 * response.getOutputStream().flush();
    			 * response.getOutputStream().close();
    			 */
    			BufferedInputStream in = null;
    			BufferedOutputStream out = null;

    			try {
    			    in = new BufferedInputStream(new FileInputStream(uFile));
    			    out = new BufferedOutputStream(response.getOutputStream());
    	
    			    FileCopyUtils.copy(in, out);
    			    out.flush();
    			} catch (Exception ex) {
    			    //ex.printStackTrace();
    			    // 다음 Exception 무시 처리
    			    // Connection reset by peer: socket write error
    			    log.debug("IGNORED: " + ex.getMessage());   				
    				
    			} finally {
    			    if (in != null) {
    					try {
    					    in.close();
    					} catch (Exception ignore) {
    					    // no-op
    						log.debug("IGNORED: " + ignore.getMessage());
    					}
    			    }
    			    if (out != null) {
    					try {
    					    out.close();
    					} catch (Exception ignore) {
    					    // no-op
    						log.debug("IGNORED: " + ignore.getMessage());
    					}
    			    }
    			}

    	    }else{
    			response.setContentType("application/x-msdownload");
    			response.setCharacterEncoding("UTF-8");
    			response.setContentType("text/html; charset=UTF-8");
    	
    			PrintWriter printwriter = response.getWriter();
    			printwriter.println("<html>");
    			printwriter.println(egovMessageSource.getMessage("common.fileupload.notFile"));
    			printwriter.println("</html>");
    			printwriter.flush();
    			printwriter.close();
        		
        	}
    	    
    	    
    	    
    	    
    	    
    	    /* else {
    	    	
    	    	// 파일이 물리적 서버에 없을 경우 BLOB으로 호출
	        	Map map = new HashMap();
	        	map.put("filePth", fileVO.getFilePth());
	        	map.put("fileNm", fileVO.getFileNm());
	        	
	        	Map retMap = comService.getFile(map);
	        	if(retMap != null){
		        	byte[] blob = (byte[])retMap.get("blob");
		        		        	
		        	String mimetype = "application/x-msdownload";
		        	
	    			response.setContentType(mimetype);
	    			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileVO.getOriFileNm(), "utf-8") + "\"");
		        	
		        	BufferedInputStream in = null;
	    			BufferedOutputStream out = null;

	    			try {
	    			    in = new BufferedInputStream(new ByteArrayInputStream(blob));
	    			    out = new BufferedOutputStream(response.getOutputStream());
	    	
	    			    FileCopyUtils.copy(in, out);
	    			    out.flush();
	    			} catch (Exception ex) {
	    			    //ex.printStackTrace();
	    			    // 다음 Exception 무시 처리
	    			    // Connection reset by peer: socket write error
	    			    log.debug("IGNORED: " + ex.getMessage());   				
	    				
	    			} finally {
	    			    if (in != null) {
	    					try {
	    					    in.close();
	    					} catch (Exception ignore) {
	    					    // no-op
	    						log.debug("IGNORED: " + ignore.getMessage());
	    					}
	    			    }
	    			    if (out != null) {
	    					try {
	    					    out.close();
	    					} catch (Exception ignore) {
	    					    // no-op
	    						log.debug("IGNORED: " + ignore.getMessage());
	    					}
	    			    }
	    			}
	        	}else{
	    			response.setContentType("application/x-msdownload");
	    			response.setCharacterEncoding("UTF-8");
	    			response.setContentType("text/html; charset=UTF-8");
	    	
	    			PrintWriter printwriter = response.getWriter();
	    			printwriter.println("<html>");
	    			printwriter.println(egovMessageSource.getMessage("common.fileupload.notFile"));
	    			printwriter.println("</html>");
	    			printwriter.flush();
	    			printwriter.close();
	        		
	        	}
    			
    	    }*/
        }
	    
    }
    
    /**
     * 파일업로드 화면이동
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fileUpload.do", method=RequestMethod.GET)
    public String fileUploadView(HttpServletRequest request, ModelMap model) throws Exception {
    	
    	return "/frame/fcom/ifrFileUpload";
    }
    
    /**
     * 엑셀 업로드 화면이동
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/excelUpload.do", method=RequestMethod.GET)
    public String excelUploadView(HttpServletRequest request,@RequestParam Map<String, Object> commandMap, ModelMap model) throws Exception {
		
		model.addAttribute("actUrl", (String)commandMap.get("actUrl"));
    	
    	return JimsUtil.rootPath("/com/ifrExcelUpload");
    }
    
    /**
	 * 파일업로드 처리
	 * @param 
	 * @return "/com/ifrFileUpload"
	 * @exception Exception
	 */
	@RequestMapping(value="/fileUpload.do",method=RequestMethod.POST)
    public String fileUpload(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, ModelMap model) throws Exception {		
		
		try {
			
			List<EgovFormBasedFileVo> list = JimsFileUploadUtil.uploadFiles(request, fileStorePath, maxFileSize, (String)commandMap.get("type"));
			List<Map<String,Object>> mlist = JimsFileUploadUtil.uploadBlobs(request, fileStorePath, maxFileSize, (String)commandMap.get("type"));
			
			if (list.size() > 0) {
			    EgovFormBasedFileVo vo = list.get(0);	// 첫번째 이미지
			    Map<String,Object> map = mlist.get(0);
			    
			    model.addAttribute("fileVO", vo);
			    model.addAttribute("objName", (String)commandMap.get("objName"));
			    model.addAttribute("divName", (String)commandMap.get("divName"));
			    model.addAttribute("maxFileNum", (String)commandMap.get("maxFileNum"));
			    model.addAttribute("remark", (String)commandMap.get("remark"));
			    
			    map.put("filePth", vo.getServerSubPath());
			    map.put("fileNm", vo.getPhysicalName());
			    map.put("fileType", vo.getContentType());
			    //comService.insertAtchStrg(map);
			    
			    log.debug("fileUpload fileName:"+vo.getFileName());
			    log.debug("fileUpload contentType:"+vo.getContentType());
			    log.debug("fileUpload serverSubPath:"+vo.getServerSubPath());
			    log.debug("fileUpload physicalName:"+vo.getPhysicalName());
			    log.debug("fileUpload divName:"+(String)commandMap.get("divName"));
			    log.debug("fileUpload remark:"+(String)commandMap.get("remark"));
			}
			
		} catch (Exception e) {
			if ("001".equals(e.getMessage())) {
				long fileSize = Long.parseLong(EgovProperties.getProperty("Globals.maxUploadFileSize")) / (1024*1024);
				Object[] args = {fileSize};
				//업로드 파일은 {0} 이하여야 합니다.
				model.addAttribute("message", egovMessageSource.getMessageArgsLocale("common.fileupload.fail001", args, java.util.Locale.getDefault()));
				//throw new MException(egovMessageSource.getMessageArgsLocale("common.fileupload.fail001", args, java.util.Locale.getDefault()));
			}else if ("002".equals(e.getMessage())){
				//보안상 사용할 수 없는 파일 입니다
				model.addAttribute("message", egovMessageSource.getMessage("common.fileupload.fail002"));
				//throw new MException(egovMessageSource.getMessage("common.fileupload.fail002"));
			}else if ("003".equals(e.getMessage())){
				Object[] args = {EgovProperties.getProperty("Globals.imageFile").replace("|", ", ")};
				//이미지파일({0})만 업로드 가능합니다
				model.addAttribute("message", egovMessageSource.getMessageArgsLocale("common.fileupload.fail003", args, java.util.Locale.getDefault()));
				//throw new MException(egovMessageSource.getMessageArgsLocale("common.fileupload.fail003", args, java.util.Locale.getDefault()));
			}else {
				//파일 업로드 실패하였습니다.
				model.addAttribute("message", egovMessageSource.getMessage("common.fileupload.fail999"));
				//throw new MException(egovMessageSource.getMessage("common.fileupload.fail999"));
			}
		}

		return "/frame/fcom/ifrFileUpload";
		//return new ModelAndView(ajaxMainView, model);
	}
	
    /**
     * 첨부파일 미리보기
     * 
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/fileView.do")    
    public void fileView(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String type = (String)commandMap.get("type");
    	String filePath = "";
    	FileVO fileVO = new FileVO(); 
    	
    	if ("1".equals(type)) {
    		//첨부파일경로, 파일명으로 다운로드
    		String atchFileId = (String)commandMap.get("atchFileId");
        	String fileSno = (String)commandMap.get("fileSno");
        	
        	List<FileVO> fileList = comService.selectAtchFileList(atchFileId, fileSno);
        	
        	if (fileList.size() == 1) {
    	    	fileVO = fileList.get(0);
    	    }
        	
        	filePath = fileStorePath;
    		
    	}else if ("2".equals(type)){
    		//첨부파일경로, 파일명으로 다운로드
    		fileVO.setFilePth((String)commandMap.get("filePth"));
    		fileVO.setFileNm((String)commandMap.get("fileNm"));
    		
    		filePath = fileStorePath;
    	}
	    
    	String tmp = fileVO.getFileNm();
		String mimeTypeParam = null;
        if (tmp.lastIndexOf(".") >= 0) {
        	mimeTypeParam = EgovProperties.getProperty(Globals.FILE_FORMAT_PATH, tmp.substring(tmp.lastIndexOf(".")+1));
        }

        boolean denyListChk = true;
        String[] eList = EgovProperties.getProperty("Globals.fileDownload.Extensions").toString().split("\\.");
        
	 	for(int k=0; k < eList.length ;k++){
	 		if (fileVO.getFileNm().indexOf("."+eList[k]) > -1){
   		 		//허용 확장자
   		 		denyListChk = false;
	 		}
	 	}
	 	
        if (denyListChk) {
        	
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
	
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println(egovMessageSource.getMessage("common.fileupload.fail002"));
			printwriter.println("</html>");
			printwriter.flush();
			printwriter.close();

        }else {

	        try {
	    		//윕 취약점 보안 관련
	        	fileVO.setFilePth(fileVO.getFilePth().replace("..", ""));
	        	fileVO.setFilePth(fileVO.getFilePth().replace("~", ""));
	    		fileVO.setFileNm(fileVO.getFileNm().replace("..", ""));
	    		fileVO.setFileNm(fileVO.getFileNm().replace("~", ""));
	        	
	        	EgovFormBasedFileUtil.viewFile(response, filePath, fileVO.getFilePth(), fileVO.getFileNm(), mimeTypeParam);
	        } catch (Exception ex) {
	        	//등록된 이미지가 없습니다.
	        	EgovFormBasedFileUtil.viewFile(response, filePath, "", "img_noimage.gif", mimeTypeParam);
	        	// 파일이 물리적 서버에 없을 경우 BLOB으로 호출
/*	        	Map map = new HashMap();
	        	map.put("filePth", fileVO.getFilePth());
	        	map.put("fileNm", fileVO.getFileNm());
	        	
	        	Map retMap = comService.getFile(map);
	        	byte[] blob = (byte[])retMap.get("blob");
	        	
	        	InputStream in = new ByteArrayInputStream(blob);
	        	IOUtils.copy(in, response.getOutputStream());*/
	        }
	        
        }
    }
    
    /**
	 * 파일삭제
	 * @param 
	 * @return "/com/ifrFileUpload"
	 * @exception Exception
	 */
	@RequestMapping(value="/fileDel.do")
    public ModelAndView fileDel(@RequestParam Map<String, Object> commandMap, HttpServletRequest request, ModelMap model) throws Exception {
	
		String filePath = "";
		String fileNm = (String) commandMap.get("fileNm");
		String filePth = (String) commandMap.get("filePth");		
		
		filePath = fileStorePath + filePth + "\\" + fileNm;
		try {
			File file = new File(filePath);
			if(EgovFileUtil.isExistsFile(filePath)){
				EgovFileUtil.delete(file);			
			}
		} catch (IOException e) {
    		e.printStackTrace();
    	}
		
		return new ModelAndView(ajaxMainView, model);
		
	}

}
