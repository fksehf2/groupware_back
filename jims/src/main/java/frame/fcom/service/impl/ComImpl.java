package frame.fcom.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.fcom.service.CodeVO;
import frame.fcom.service.ComService;
import frame.fcom.service.FileVO;
import frame.fexception.MException;
import frame.flyt.main.service.MenuVO;
import frame.futil.ExcelUtil;

@Service("comService")
public class ComImpl extends EgovAbstractServiceImpl implements ComService {

	protected Logger log = LoggerFactory .getLogger(ComImpl.class);

	
	@Resource(name="comDAO")
	private ComDAO comDAO;

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;
	
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
		return comDAO.selectCommonQueryString(map);
	}
	

/*	*//**
	 * 코드 정보를 가져온다
	 * @param CodeVO
	 * @return list
	 * @exception Exception
	 *//*
	public List selectCdList(CodeVO vo) throws Exception{
		return comDAO.selectCdList(vo);
	}

    *//**
     * 첨부파일 목록을 조회한다.
     * @param 조회할 첨부파일 ID, 파일일련번호
     * @return 첨부파일 목록
     * @exception Exception
     */
	public List<FileVO> selectAtchFileList(String atchFileId, String fileSno) throws Exception {

    	HashMap hm = new HashMap();

    	hm.put("atchFileId", atchFileId);
		hm.put("fileSno", fileSno);

		return comDAO.selectAtchFileList(hm);

    }

    /**
	 * 메뉴 조회한다.
	 * @param 조회할 정보가 담긴 Map
	 * @return 메뉴 목록
	 * @exception Exception
	 */
	public List<MenuVO> selectMenuList(Map map) throws Exception {

		List<MenuVO> resultVO = comDAO.selectMenuList(map);
        return resultVO;

    };

    /**
     * 첨부파일 등록
     * @param
     * @return
     * @exception Exception
     */
    public String insertAtchFile(String objNm, String atchFileDiv, String loginId, Map<String, Object> commandMap) throws Exception {

    	String returnVal = null;

    	if (commandMap.get(objNm+"OriFileNm") instanceof String || commandMap.get(objNm+"OriFileNm") instanceof Object ) {
        	HashMap hm = new HashMap();

        	hm.put("atchFileDiv", atchFileDiv);
        	hm.put("loginId", loginId);

        	//첨부파일 등록
        	comDAO.insertAtchFile(hm);
        	
        	int atchFileId = (int) hm.get("atchFileId");


        	//hm.put("atchFileId", atchFileId);

        	if (commandMap.get(objNm+"OriFileNm") instanceof String) {

        		hm.put("filePth", (String) commandMap.get(objNm+"FilePth"));
        		hm.put("fileNm", commandMap.get(objNm+"FileNm"));
        		hm.put("fileSize", commandMap.get(objNm+"FileSize"));
        		hm.put("oriFileNm", commandMap.get(objNm+"OriFileNm"));
        		hm.put("remark", commandMap.get(objNm+"Remark"));
        		int fileSno = comDAO.insertAtchFileDtl(hm);

        	}else {

        		List<String> filePth = (List<String>)commandMap.get(objNm+"FilePth");
        		List<String> fileNm = (List<String>)commandMap.get(objNm+"FileNm");
        		List<String> fileSize = (List<String>)commandMap.get(objNm+"FileSize");
        		List<String> oriFileNm = (List<String>)commandMap.get(objNm+"OriFileNm");
        		List<String> remark = (List<String>)commandMap.get(objNm+"Remark");

                for(int i=0;i<oriFileNm.size();i++){
    	    		hm.put("filePth", filePth.get(i));
    	    		hm.put("fileNm", fileNm.get(i));
    	    		hm.put("fileSize", fileSize.get(i));
    	    		hm.put("oriFileNm", oriFileNm.get(i));
    	    		hm.put("remark", "");

    	    		int fileSno = comDAO.insertAtchFileDtl(hm);
                }
        	}

        	returnVal = String.valueOf(atchFileId);

    	}

    	return returnVal;
    }

    /**
     * 첨부파일 수정
     * @param
     * @return
     * @exception Exception
     */
    public String updateAtchFile(String atchFileId, String objNm, String atchFileDiv, String loginId, Map<String, Object> commandMap) throws Exception {

    	String returnVal = null;
    	HashMap hm = new HashMap();

    	hm.put("atchFileId", atchFileId);
    	hm.put("atchFileDiv", atchFileDiv);
    	hm.put("loginId", loginId);

    	//sims_첨부파일상세 모두 삭제
    	comDAO.deleteAtchFileDtl(hm);

    	if (commandMap.get(objNm+"OriFileNm") instanceof String || commandMap.get(objNm+"OriFileNm") instanceof Object ) {

        	if (commandMap.get(objNm+"OriFileNm") instanceof String) {

        		hm.put("fileSno", (String) commandMap.get(objNm+"FileSno"));
        		hm.put("filePth", (String) commandMap.get(objNm+"FilePth"));
        		hm.put("fileNm", commandMap.get(objNm+"FileNm"));
        		hm.put("fileSize", commandMap.get(objNm+"FileSize"));
        		hm.put("oriFileNm", commandMap.get(objNm+"OriFileNm"));
        		hm.put("remark", commandMap.get(objNm+"Remark"));

        		comDAO.updateAtchFileDtl(hm);

        	}else {

        		List<String> fileSno = (List<String>)commandMap.get(objNm+"FileSno");
        		List<String> filePth = (List<String>)commandMap.get(objNm+"FilePth");
        		List<String> fileNm = (List<String>)commandMap.get(objNm+"FileNm");
        		List<String> fileSize = (List<String>)commandMap.get(objNm+"FileSize");
        		List<String> oriFileNm = (List<String>)commandMap.get(objNm+"OriFileNm");
        		List<String> remark = (List<String>)commandMap.get(objNm+"Remark");

                for(int i=0;i<oriFileNm.size();i++){

                	hm.put("fileSno", fileSno.get(i));
    	    		hm.put("filePth", filePth.get(i));
    	    		hm.put("fileNm", fileNm.get(i));
    	    		hm.put("fileSize", fileSize.get(i));
    	    		hm.put("oriFileNm", oriFileNm.get(i));
    	    		hm.put("remark", "");

    	    		comDAO.updateAtchFileDtl(hm);
                }
        	}

        	returnVal = atchFileId;

    	}

    	if (EgovStringUtil.isEmpty(returnVal)) {
        	//sims_첨부파일 모두 삭제
        	comDAO.deleteAtchFile(hm);
    	}

    	return returnVal;

    }

    /**
     * 코드 목록을 조회한다.
     * @param 조회할 CODE_ID
     * @return 코드 목록
     * @exception Exception
     */
	public List<CodeVO> selectCodeList(String cdId) throws Exception {


		//List<CodeVO> resultVO = comDAO.selectCodeList(hm);
		
		
		HashMap map = new HashMap();
		
    	String [] cdId2 = {"C15"}; //비밀번호 찾기
    	
    	map.put("cdId", cdId2);
    	map.put("sqlQueryId", "comDAO.queryCodeList");
    	List sysGrpList = comDAO.selectCommonQueryList(map);
		
        return sysGrpList;

    }
	
	/**
     * 코드 목록을 조회한다.
     * @param 조회할 CD
     * @return 코드 목록
     * @exception Exception
     */
	public List<CodeVO> selectCodeList1(String codeId) throws Exception {

    	HashMap map = new HashMap();

    	map.put("cdId", codeId.split(","));

    	List<CodeVO> resultVO = comDAO.selectCodeList(map);
        return resultVO;

    }

    /**
     * 코드 목록을 조회한다.
     * @param 조회할 CD
     * @return 코드 목록
     * @exception Exception
     */
	public List<CodeVO> selectCodeList2(String codeId) throws Exception {

    	HashMap hm = new HashMap();

    	hm.put("cd", codeId.split(","));

    	List<CodeVO> resultVO = comDAO.selectCodeList2(hm);
        return resultVO;

    }

    /**
     * 코드 목록을 조회한다.
     * @param 조회할 CD
     * @return 코드 목록
     * @exception Exception
     */
	public List<CodeVO> selectCodeRangeList(String codeId, String minerCode) throws Exception {

    	HashMap hm = new HashMap();

    	hm.put("cdId", codeId.split(","));
    	hm.put("minerCdId", minerCode.split(","));

    	List<CodeVO> resultVO = comDAO.selectCodeRangeList(hm);
        return resultVO;

    }


    /**
     * 페이지권한 조회
     * @param 조회할 정보가 담긴 Map
     * @return String
     * @exception Exception
     *//*
    public String selectAuthor(Map map) throws Exception {
    	return comDAO.selectAuthor(map);
    }


	public void insertAtchStrg(Map map) throws Exception {
		comDAO.insertAtchStrg(map);
	}

	public Map getFile(Map map) throws Exception{
		return comDAO.getFile(map);
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
		return comDAO.selectCommonQueryList(map);
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
     */
	public Map selectCommonQueryMap(Map map) throws Exception{
		return comDAO.selectCommonQueryMap(map);
	}



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
	public int selectCommonQueryListTotCnt(Map map) throws Exception {
		return comDAO.selectCommonQueryListTotCnt(map);
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
	public void insertCommonQuery(Map map) throws Exception{
		comDAO.insertCommonQuery(map);
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
	 *  2.1 실제 화면에서는 삭제기능이나 USE_YN = "N"의 변경같은 기능은 공통 update 기능 호출
	 *
	 * </pre>
	 *
	 */
	public void updateCommonQuery(Map map) throws Exception{
		comDAO.updateCommonQuery(map);
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
	 */
	@Override
	public void insertCommonQuery(Map map) throws Exception {
		// TODO Auto-generated method stub
		
	}


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
	@Override
	public ModelAndView queryMListExcel(HashMap reqParams) throws Exception {
		ModelAndView mav = new ModelAndView("excelView");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        
        String[] splitSearch = ((String) reqParams.get("splitSearch")).split(",");
        
        if(splitSearch != null && splitSearch.length > 0 && !splitSearch[0].equals("")) {
        	for (int i = 0; i < splitSearch.length; i++) {
        		reqParams.put(splitSearch[i], ((String) reqParams.get(splitSearch[i])).split(","));
        	}
        }
        
		List list = comDAO.selectCommonQueryList(reqParams);
		String column = (String) reqParams.get("columnArr");
		String columnVar = (String) reqParams.get("columnVarArr");
        String[] columnArr = column.split(",");
        String[] columnVarArr = columnVar.split(",");
                
        dataMap.put("columnArr", columnArr);
        dataMap.put("columnVarArr", columnVarArr);
        dataMap.put("sheetNm", reqParams.get("sheetNm"));    
        dataMap.put("list", list);
        
        mav.addObject("dataMap", dataMap);
        mav.addObject("filename", reqParams.get("filename"));
        
		return mav;
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
		comDAO.deleteCommonQuery(map);
	}

	public List sidoList(String sidoCd) throws Exception {
		// TODO Auto-generated method stub
		return comDAO.sidoList(sidoCd);
	}

    *//**
     * AJAX로 시군구 조회
     * @param 조회할 정보가 담긴 Map
     * @return 시군구 목록
     * @exception Exception
     *//*
    public List ajaxSigunList(Map map) throws Exception {
   	 return comDAO.ajaxSigunList(map);
    }


	*//**
	 * 엑셀다운로드 공통 함수
	 * @param sqlQueryId : 쿼리ID
	 * @param filename   : 다운로드 파일명
	 * @param titleinfo  : 엑셀 헤더정보
	 * @param response
	 * @throws Exception
	 *//*
	public void fileDownloadExcel(Map map, HttpServletResponse response) throws Exception {

		String sqlQueryId = (String)map.get("sqlQueryId");
		String csvFileNm  = (String)map.get("filename");

        long startPage     = 0;
        long endPage       = 999999999;

		map.put("firstIndex", startPage);
		map.put("lastIndex", endPage);

		//rowHandler을 이용하여 건벽 엑셀 로우 생성
		JimsRowHandler rowhandler = new JimsRowHandler(map);
		comDAO.rowHandlerCommonQuery(map, rowhandler);

        try {
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(csvFileNm+".xlsx", "UTF-8") + ";");
        	rowhandler.getWorkBook().write(response.getOutputStream());

        } catch(Exception e) {


            response.setHeader("Set-Cookie", "fileDownload=false; path=/");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Content-Type","text/html; charset=utf-8");

            OutputStream out = null;
            try {
                out = response.getOutputStream();
                byte[] data = new String("fail..").getBytes();
                out.write(data, 0, data.length);
            } catch(Exception ignore) {
                ignore.printStackTrace();
            } finally {
            	rowhandler.getWorkBook().dispose();
                if(out != null) try {
                	out.close();
                } catch(Exception ignore) {
                	out = null;
                }
            }

        } finally {
        	rowhandler.getWorkBook().dispose();
        }


	}

	*//**
	 * 엑셀다운로드 공통 함수
	 * @param sqlQueryId : 쿼리ID
	 * @param filename   : 다운로드 파일명
	 * @param titleinfo  : 엑셀 헤더정보
	 * @param response
	 * @throws Exception
	 *//*
	public void fileDownloadExcel2(Map map, HttpServletResponse response) throws Exception {

		String sqlQueryId = (String)map.get("sqlQueryId");
		String csvFileNm  = (String)map.get("filename");

        long startPage     = 0;
        long endPage       = 999999999;

		map.put("firstIndex", startPage);
		map.put("lastIndex", endPage);

		//rowHandler을 이용하여 건벽 엑셀 로우 생성
		JimsRowHandler2 rowhandler = new JimsRowHandler2(map);
		comDAO.rowHandlerCommonQuery(map, rowhandler);

        try {
        	response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        	response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(csvFileNm+".xlsx", "UTF-8") + ";");
        	rowhandler.getWorkBook().write(response.getOutputStream());

        } catch(Exception e) {


            response.setHeader("Set-Cookie", "fileDownload=false; path=/");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Content-Type","text/html; charset=utf-8");

            OutputStream out = null;
            try {
                out = response.getOutputStream();
                byte[] data = new String("fail..").getBytes();
                out.write(data, 0, data.length);
            } catch(Exception ignore) {
                ignore.printStackTrace();
            } finally {
            	rowhandler.getWorkBook().dispose();
                if(out != null) try {
                	out.close();
                } catch(Exception ignore) {
                	out = null;
                }
            }

        } finally {
        	rowhandler.getWorkBook().dispose();
        }


	}*/
	
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
	@Override
	public void updExcMList(MultipartHttpServletRequest request, HashMap<String, Object> reqParams) throws Exception {

		//파일 정보
		CommonsMultipartFile file = (CommonsMultipartFile)request.getFile("excelFile");

		if(!file.getOriginalFilename().toUpperCase().endsWith("XLSX") && !file.getOriginalFilename().toUpperCase().endsWith("XLS")) {
			throw new MException(egovMessageSource.getMessageArgs("common.excelUpload.fail.notExcel", new String[] {"XLSX, XLS"}));
		}
		
		//엑셀정보
		ExcelUtil eu = new ExcelUtil();
		int sheetNum = Integer.parseInt((String) reqParams.get("sheetNum"));		//1번째 시트 읽음 
		int strartRowNum = Integer.parseInt((String) reqParams.get("strartRowNum"));	//2번째 줄부터 읽음
		int startCelNum = Integer.parseInt((String) reqParams.get("startCelNum")); 	//1번째 셀부터 읽음(지역ID)
		int celCnt = Integer.parseInt((String) reqParams.get("celCnt")); 	    //셀 갯수
		String sqlQueryId = (String) reqParams.get("sqlQueryId");
		String [] culmnNmArr = ((String) reqParams.get("culmnNmArr")).split(",");
		String session_userid = (String) reqParams.get("session_userid");
		
		List<HashMap<Integer, String>> excelList = eu.excelReadSetValue(file, sheetNum, strartRowNum, startCelNum);

		HashMap map = null;

		//엑셀 Row 수 만큼 For문 조회 

		for(Object obj : excelList) {

			Map<Integer, String> mp = (Map<Integer, String>)obj;
			Set<Integer> keySet = mp.keySet();
			Iterator<Integer> iterator = keySet.iterator();
			map = new HashMap<>();

			for (int i = startCelNum; i < celCnt; i++) {
				if(i == iterator.next()) {
					map.put(culmnNmArr[i], egovframework.com.utl.fcc.service.EgovStringUtil.nullConvert(mp.get(i)));
				}
			}

			if(map != null && map.size() > 0) {
				map.put("sqlQueryId", sqlQueryId);
				map.put("session_userid", session_userid);
				comDAO.updateCommonQuery(map);
			}
			
		}
	}
	
}
