package frame.fsys.log.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//import jims.bbs.rsch.service.impl.BbsRschDAO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.string.EgovStringUtil;
import frame.fbbs.ntc.service.FBbsNtcService;
import frame.fbbs.ntc.service.FBbsNtcVO;
import frame.fcom.service.ComService;
import frame.fsys.log.service.FsysLogService;


@Service("fsysLogService")
public class FsysLogServiceImpl extends EgovAbstractServiceImpl implements FsysLogService{

	protected Logger log = LoggerFactory.getLogger(FsysLogServiceImpl.class);

	@Resource(name="comService")
	private ComService comService;

	@Resource(name="fsysLogDAO")
	private FsysLogDAO fsysLogDAO;

	/**
     * 
     * <pre>
     * 1. 메소드명 : selectFsysLog
     * 2. 작성일 : 2021. 4. 26.
     * 3. 작성자 : sjw7240
     * 4. 설명 :  로그 리스트 조회
     * </pre>
     * @param map
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
	@Override
	public List selectFsysLogList(Map<String, Object> paramMap) throws Exception {

		return (List) fsysLogDAO.selectFsysLogList(paramMap);
	}

	@Override
	public List selectFsysLogQListDtl(Map map) throws Exception {
		// TODO Auto-generated method stub
		return (List) fsysLogDAO.selectFsysLogListDtl(map);
	}

}