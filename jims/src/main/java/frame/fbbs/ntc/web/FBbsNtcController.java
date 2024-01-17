package frame.fbbs.ntc.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.fdl.string.EgovDateUtil;
import frame.fbbs.ntc.service.FBbsNtcService;
import frame.fcom.service.ComService;
import frame.fcom.service.FileVO;
import frame.flyt.login.service.FLytLoginVO;
import frame.futil.JimsConst;
import frame.futil.JimsUtil;
import frame.futil.PageUtil;

@Controller
public class FBbsNtcController {

	protected Logger log = LoggerFactory.getLogger(FBbsNtcController.class);

	@Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "comService")
	private ComService comService;
	
	@Resource(name = "fBbsNtcService")
	private FBbsNtcService fBbsNtcService;

	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    @Resource MappingJackson2JsonView ajaxMainView;

    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexFBbsNtcMList
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 목록페이지 진입시 호출 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/indexFBbsNtcMList.do")
    public String indexFBbsNtcMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap<String,String> map) throws Exception {

        return "frame/fcomm/ntc/fbbsNtcMList";
    }
    
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : queryFBbsNtcMList
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 목록 조회시 호출 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/queryFBbsNtcMList.do")
    public ModelAndView queryFBbsNtcMList(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {
    	
       	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	reqParams.put("userGb", user.getUserGb());
    	reqParams.put("regrId", user.getUserId());
    	
		reqParams.put("sqlQueryId", "fBbsNtcDAO.selectFBbsNtcListCnt");
    	int totCnt = comService.selectCommonQueryListTotCnt(reqParams);
    	reqParams.put("totalCount", totCnt);
    	
    	PageUtil.calcPage(reqParams);

		reqParams.put("sqlQueryId", "fBbsNtcDAO.selectFBbsNtcList");
		List fbbsNtcList = comService.selectCommonQueryList(reqParams);
		reqParams.put("fbbsNtcList", fbbsNtcList);
		model.addAllAttributes(reqParams);
		
		return new ModelAndView(ajaxMainView, model);
    	
    	
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexFBbsNtcRDtl
     * 2. 작성일 : 2021. 4. 15.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 등록페이지 진입시 호출 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/indexFBbsNtcRDtl.do")
    public String indexFBbsNtcRDtl(HttpServletRequest request, @RequestParam HashMap<String,String> map, ModelMap model, SessionStatus status) throws Exception {

    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	model.addAttribute("regNm",user.getUserNm());

    	return "frame/fcomm/ntc/fbbsNtcRDtl";
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : regFBbsNtcRDtl
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 등록시 호출 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/regFBbsNtcRDtl.do")
    public ModelAndView regFBbsNtcRDtl(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {
    	
    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	reqParams.put("regrId", user.getUserId());
    	reqParams.put("boardKind", "C23001");
    	
    	String sRturnResult = "";

    	String putupDt = EgovDateUtil.getCurrentDateAsString();    	
    	reqParams.put("putupDt", putupDt.replaceAll("-", ""));
    	
    	HashMap<String, Object> fileMap = JimsUtil.convertMap(request);

		sRturnResult = fBbsNtcService.insertFBbsNtc(reqParams,fileMap);


		if(sRturnResult.equals("2")){
			model.addAttribute(JimsConst.Messages_UserComMessage,"보안상 업로드할 수 없는 파일이 있어 저장하지 못하였습니다.");			
		}

        return new ModelAndView(ajaxMainView, model);
    	
    	
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : delFBbsNtcUDtl
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 삭제시 호출 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/delFBbsNtcUDtl.do")

    public ModelAndView delFBbsNtcUDtl(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {
    	
    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	reqParams.put("regrId", user.getUserId());
    	reqParams.put("boardKind", "C23001");

		fBbsNtcService.deleteFBbsNtc(reqParams);

        return new ModelAndView(ajaxMainView, model);
    	
    	
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : indexFBbsNtcUDtl
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 수정페이지 진입시 호출 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/indexFBbsNtcUDtl.do")

    public String indexFBbsNtcUDtl(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {

        return "frame/fcomm/ntc/fbbsNtcUDtl";    	
    	
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : goFBbsNtcUDtl
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 수정페이지 조회시 호출 
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/goFBbsNtcUDtl.do")

    public ModelAndView goFBbsNtcUDtl(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {
    	
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("putupSno", Integer.parseInt((String) reqParams.get("putupSno")));
        paramMap.put("boardKind", "C23001");

        Map fbbsNtcMap = fBbsNtcService.selectFBbsNtcRDtl(paramMap);
        String sTemp = JimsUtil.getHtmlStrCnvr((String) fbbsNtcMap.get("cnts"));
        fbbsNtcMap.put("cntsCvt", sTemp);

	    List<FileVO> atchFileList = comService.selectAtchFileList((String) fbbsNtcMap.get("atchFileId"),null);
	    fBbsNtcService.updateFBbsNtcCount(paramMap);
	    model.addAttribute("fbbsNtcMap", fbbsNtcMap);
	    model.addAttribute("atchFileList",atchFileList);
    	
    	 return new ModelAndView(ajaxMainView, model);
    	
    }
    
    /**
     * 
     * <pre>
     * 1. 메소드명 : updFBbsNtcUDtl
     * 2. 작성일 : 2021. 4. 14.
     * 3. 작성자 : leeji
     * 4. 설명 :  공지사항 수정시 호출
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/fbbs/ntc/updFBbsNtcUDtl.do")

    public ModelAndView updFBbsNtcUDtl(HttpServletRequest request, ModelMap model, @RequestParam HashMap reqParams, HttpServletResponse response) throws Exception {
    	
    	FLytLoginVO user = (FLytLoginVO)EgovUserDetailsHelper.getAuthenticatedUser();

    	reqParams.put("regrId", user.getUserId());
    	reqParams.put("boardKind", "C23001");
    	
    	String sRturnResult = "";
    	String putupDt = EgovDateUtil.getCurrentDateAsString();    	
    	reqParams.put("putupDt", putupDt.replaceAll("-", ""));
    	
    	HashMap<String, Object> fileMap = JimsUtil.convertMap(request);
		sRturnResult = fBbsNtcService.updateFBbsNtc(reqParams, fileMap);			

		if(sRturnResult.equals("2")){
			model.addAttribute(JimsConst.Messages_SysSucMessage,"보안상 업로드할 수 없는 파일이 있어 저장하지 못하였습니다.");			
		}

        return new ModelAndView(ajaxMainView, model);
    	
    }

}