package frame.fbbs.ntc.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import frame.futil.JimsUtil;

/**
 * @Class Name : BbsVO.java
 * @Description : Bbs VO class
 * @Modification Information
 *
 * @author 오일용
 * @since 2015.01.13
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

public class FBbsNtcVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//게시판
	private int putupSno;
	private int grpNo;
	private int lvlNo;
	private int rnum;
	private String title;
	private String cntsTyp;
	private String cnts;
    /** cnts 태그역변환 */
    private String cntsCvt;
    
	private String putupDt;
	private String adminId;
	private String userGb;
	private int selectNum;
	private String fixNoticeYn;
	private String boardKind;
	private String atchFileId;
	private String useYn;
	private String regrId;
	private String updDt;
	private String updrId;
	private String regDt;
	private String atchFileNm;
	private String filePth;
	private String fileNm;
	private int comtCount;
	private int fileCount;
	private String fileCount2="";
	//파일순번
	private String fileSno;
	private String userDivNm;
    /** ADMIN_NM */
    private String adminNm;
    /** REGR_NM */
    private String regrNm;
    /** UPDR_NM */
    private String updrNm;
    /** AREA_NM */
    private String areaNm;
    /** SIGUNGU_NM */
    private String sigunguNm;
    /** AREAR_NM */
    private String arearNm;
    
    private String reqerNo;
    
    private String vchAplnSno;
   
	//덧글
	private String userId;
	private int comt;
	private String comtCnts;
    /** USER_NM */
    private String userNm;
    /** 업무구분(1:홈페이지, 2:업무단) **/
    private String upmuGubun;
    /** 도매법인코드 **/
    private String corpCd;
    /** 도매법인명 **/
    private String corpNm;
    
	
	//지역
	private String sidoCd;
	private String sigunguCd;
	private int noticeAreaSno;
	private int sno;
	private String custCd;
	private String popupYn;
	private String sidoNm;
	private String areaCnt;
	private String gunguNm;
	private String gunguCd;	
	
	//조회
	private String searchStrtDt = "";
	private String searchEndDt = "";
	private String searchRegNm = "";
	private String searchUserDiv = "";
	private String searchFixNoticeYn = "";
	private String searchSidoCd = "";
	private String searchGunguCd = "";
	private String searchTitle = "";
	
	private String searchType = "";
	private String searchValue = "";
	//게시판구분
	private String searchBoardKind = "";
	//법인코드
	private String searchCorpCd = "";
	//도매시장 코드
	private String searchMarketSno = "";
	
	
	//공지 확인자
    /** CFRMR_SNO */
    private int cfrmrSno;
    /** CFRMR_ID */
    private String cfrmrId;
    /** CFRMR_DT */
    private String cfrmrDt;
    /** CFRMR_NM */
    private String cfrmrNm;
    /** CFRMR_CUST_NM */
    private String cfrmrCustNm;
    /** CFRMR_CUST_CD */
    private String cfrmrCustCd;
    
    
    /** 정렬옵션 */
    private String sortOpt; 	
    
    
	/** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수 */
    private int pageUnit = 10;
    
    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    private String searchFrstRegistPnttm = "";
    
    private String searchLastUpdtPnttm = "";
    
    private String workPlcNm;
    
    private String workPlcAddr;
    
    private String useTrm;
    
    private String dclerNm;
    
    private String searchDclerNm;
    
    private String searchWorkPlcNm;
    
    private String unUseReqerNm;
    
    
	public String getSearchMarketSno() {
		return searchMarketSno;
	}
	public void setSearchMarketSno(String searchMarketSno) {
		this.searchMarketSno = searchMarketSno;
	}
	
	public int getComtCount() {
		return comtCount;
	}
	public void setComtCount(int comtCount) {
		this.comtCount = comtCount;
	}
	public String get_UserGb() {
		return userGb;
	}
	public void setUserGb(String userGb) {
		this.userGb = userGb;
	}
	public String getSearchCorpCd() {
		return searchCorpCd;
	}
	public void setSearchCorpCd(String searchCorpCd) {
		this.searchCorpCd = searchCorpCd;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getPutupSno() {
		return putupSno;
	}
	public void setPutupSno(int putupSno) {
		this.putupSno = putupSno;
	}
	public int getGrpNo() {
		return grpNo;
	}
	public void setGrpNo(int grpNo) {
		this.grpNo = grpNo;
	}
	public int getLvlNo() {
		return lvlNo;
	}
	public void setLvlNo(int lvlNo) {
		this.lvlNo = lvlNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCnts() {
		return cnts;
	}
	public void setCnts(String cnts) {
		cntsCvt = JimsUtil.getHtmlStrCnvr(cnts);
		this.cnts = cnts;
	}
	
    public String getCntsCvt() {
        return this.cntsCvt;
    }

    public void setCntsCvt(String cntsCvt) {
    	this.cntsCvt = cntsCvt;
    }
	
	public String getPutupDt() {
		return putupDt;
	}
	public void setPutupDt(String putupDt) {
		this.putupDt = putupDt;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getuserGb() {
		return userGb;
	}
	public void setuserGb(String userGb) {
		this.userGb = userGb;
	}
	public int getSelectNum() {
		return selectNum;
	}
	public void setSelectNum(int selectNum) {
		this.selectNum = selectNum;
	}
	public String getFixNoticeYn() {
		return fixNoticeYn;
	}
	public void setFixNoticeYn(String fixNoticeYn) {
		this.fixNoticeYn = fixNoticeYn;
	}
	public String getBoardKind() {
		return boardKind;
	}
	public void setBoardKind(String boardKind) {
		this.boardKind = boardKind;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public String getUpdrId() {
		return updrId;
	}
	public void setUpdrId(String updrId) {
		this.updrId = updrId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getComt() {
		return comt;
	}
	public void setComt(int comt) {
		this.comt = comt;
	}
	public String getComtCnts() {
		return comtCnts;
	}
	public void setComtCnts(String comtCnts) {
		this.comtCnts = comtCnts;
	}
    public String getUserNm() {
        return this.userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }	
	
	public String getAtchFileNm() {
		return atchFileNm;
	}
	public void setAtchFileNm(String atchFileNm) {
		this.atchFileNm = atchFileNm;
	}
	public String getFileSno() {
		return fileSno;
	}
	public void setFileSno(String fileSno) {
		this.fileSno = fileSno;
	}
	public String getFilePth() {
		return filePth;
	}
	public void setFilePth(String filePth) {
		this.filePth = filePth;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getUserDivNm() {
		return userDivNm;
	}
	public void setUserDivNm(String userDivNm) {
		this.userDivNm = userDivNm;
	}
    public String getAdminNm() {
        return this.adminNm;
    }

    public void setAdminNm(String adminNm) {
        this.adminNm = adminNm;
    }
    public String getRegrNm() {
        return this.regrNm;
    }

    public void setRegrNm(String regrNm) {
        this.regrNm = regrNm;
    }
    public String getUpdrNm() {
        return this.updrNm;
    }

    public void setUpdrNm(String updrNm) {
        this.updrNm = updrNm;
    }
    public String getAreaNm() {
        return this.areaNm;
    }

    public void setAreaNm(String areaNm) {
        this.areaNm = areaNm;
    }	
    public String getSigunguNm() {
        return this.sigunguNm;
    }

    public void setSigunguNm(String sigunguNm) {
        this.sigunguNm = sigunguNm;
    }
    public String getArearNm() {
        return this.arearNm;
    }

    public void setArearNm(String arearNm) {
        this.arearNm = arearNm;
    }
    
	public String getSidoCd() {
		return sidoCd;
	}
	public void setSidoCd(String sidoCd) {
		this.sidoCd = sidoCd;
	}
	public String getSigunguCd() {
		return sigunguCd;
	}
	public void setSigunguCd(String sigunguCd) {
		this.sigunguCd = sigunguCd;
	}
	public int getNoticeAreaSno() {
		return noticeAreaSno;
	}
	public void setNoticeAreaSno(int noticeAreaSno) {
		this.noticeAreaSno = noticeAreaSno;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getCustCd() {
		return custCd;
	}
	public void setCustCd(String custCd) {
		this.custCd = custCd;
	}
	public String getPopupYn() {
		return popupYn;
	}
	public void setPopupYn(String popupYn) {
		this.popupYn = popupYn;
	}
	public String getSearchStrtDt() {
		return searchStrtDt;
	}
	public void setSearchStrtDt(String searchStrtDt) {
		this.searchStrtDt = searchStrtDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	public String getSearchRegNm() {
		return searchRegNm;
	}
	public void setSearchRegNm(String searchRegNm) {
		this.searchRegNm = searchRegNm;
	}
	public String getSearchUserDiv() {
		return searchUserDiv;
	}
	public void setSearchUserDiv(String searchUserDiv) {
		this.searchUserDiv = searchUserDiv;
	}
	public String getSearchFixNoticeYn() {
		return searchFixNoticeYn;
	}
	public void setSearchFixNoticeYn(String searchFixNoticeYn) {
		this.searchFixNoticeYn = searchFixNoticeYn;
	}
	public String getSearchSidoCd() {
		return searchSidoCd;
	}
	public void setSearchSidoCd(String searchSidoCd) {
		this.searchSidoCd = searchSidoCd;
	}
	public String getSearchGunguCd() {
		return searchGunguCd;
	}
	public void setSearchGunguCd(String searchGunguCd) {
		this.searchGunguCd = searchGunguCd;
	}
	 public String getSearchTitle() {
		return searchTitle;
	}
	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}
	
	public String getSearchType() {
			return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	
    public int getCfrmrSno() {
        return this.cfrmrSno;
    }

    public void setCfrmrSno(int cfrmrSno) {
        this.cfrmrSno = cfrmrSno;
    }
    public String getCfrmrId() {
        return this.cfrmrId;
    }

    public void setCfrmrId(String cfrmrId) {
        this.cfrmrId = cfrmrId;
    }
    public String getCfrmrDt() {
        return this.cfrmrDt;
    }

    public void setCfrmrDt(String cfrmrDt) {
        this.cfrmrDt = cfrmrDt;
    }	
    public String getCfrmrNm() {
        return this.cfrmrNm;
    }

    public void setCfrmrNm(String cfrmrNm) {
        this.cfrmrNm = cfrmrNm;
    }
    public String getCfrmrCustNm() {
        return this.cfrmrCustNm;
    }

    public void setCfrmrCustNm(String cfrmrCustNm) {
        this.cfrmrCustNm = cfrmrCustNm;
    }	
    public String getCfrmrCustCd() {
        return this.cfrmrCustCd;
    }

    public void setCfrmrCustCd(String cfrmrCustCd) {
        this.cfrmrCustCd = cfrmrCustCd;
    }    
    
    public String getSortOpt() {
        return this.sortOpt;
    }

    public void setSortOpt(String sortOpt) {
        this.sortOpt = sortOpt;
    } 
    
	public String getSearchUseYn() {
        return searchUseYn;
    }
    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageUnit() {
        return pageUnit;
    }
    public void setPageUnit(int pageUnit) {
        this.pageUnit = pageUnit;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
	public String getSearchFrstRegistPnttm() {
		return searchFrstRegistPnttm;
	}
	public void setSearchFrstRegistPnttm(String searchFrstRegistPnttm) {
		this.searchFrstRegistPnttm = searchFrstRegistPnttm;
	}
	public String getSearchLastUpdtPnttm() {
		return searchLastUpdtPnttm;
	}
	public void setSearchLastUpdtPnttm(String searchLastUpdtPnttm) {
		this.searchLastUpdtPnttm = searchLastUpdtPnttm;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	public String getSidoNm() {
		return sidoNm;
	}
	public void setSidoNm(String sidoNm) {
		this.sidoNm = sidoNm;
	}
	public String getAreaCnt() {
		return areaCnt;
	}
	public void setAreaCnt(String areaCnt) {
		this.areaCnt = areaCnt;
	}
	public void setGunguNm(String gunguNm) {
		this.gunguNm = gunguNm; 
	}
	public String getGunguNm() {
		return gunguNm; 
	}
	public void setGunguCd(String gunguCd) {
		this.gunguCd = gunguCd; 
	}
	public String getGunguCd() {
		return gunguCd; 
	}
	public String getSearchBoardKind() {
		return searchBoardKind;
	}
	public void setSearchBoardKind(String searchBoardKind) {
		this.searchBoardKind = searchBoardKind;
	}
	public String getUpmuGubun() {
		return upmuGubun;
	}
	public void setUpmuGubun(String upmuGubun) {
		this.upmuGubun = upmuGubun;
	}
	public String getCorpCd() {
		return corpCd;
	}
	public void setCorpCd(String corpCd) {
		this.corpCd = corpCd;
	}
	public String getCorpNm() {
		return corpNm;
	}
	public void setCorpNm(String corpNm) {
		this.corpNm = corpNm;
	}
	public String getFileCount2() {
		return fileCount2;
	}
	public void setFileCount2(String fileCount2) {
		this.fileCount2 = fileCount2;
	}
	public int getFileCount() {
		return fileCount;
	}
	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}
	public String getReqerNo() {
		return reqerNo;
	}
	public void setReqerNo(String reqerNo) {
		this.reqerNo = reqerNo;
	}
	public String getVchAplnSno() {
		return vchAplnSno;
	}
	public void setVchAplnSno(String vchAplnSno) {
		this.vchAplnSno = vchAplnSno;
	}
	public String getCntsTyp() {
		return cntsTyp;
	}
	public void setCntsTyp(String cntsTyp) {
		this.cntsTyp = cntsTyp;
	}
	public String getWorkPlcNm() {
		return workPlcNm;
	}
	public void setWorkPlcNm(String workPlcNm) {
		this.workPlcNm = workPlcNm;
	}
	public String getWorkPlcAddr() {
		return workPlcAddr;
	}
	public void setWorkPlcAddr(String workPlcAddr) {
		this.workPlcAddr = workPlcAddr;
	}
	public String getUseTrm() {
		return useTrm;
	}
	public void setUseTrm(String useTrm) {
		this.useTrm = useTrm;
	}
	public String getDclerNm() {
		return dclerNm;
	}
	public void setDclerNm(String dclerNm) {
		this.dclerNm = dclerNm;
	}
	public String getSearchDclerNm() {
		return searchDclerNm;
	}
	public void setSearchDclerNm(String searchDclerNm) {
		this.searchDclerNm = searchDclerNm;
	}
	public String getSearchWorkPlcNm() {
		return searchWorkPlcNm;
	}
	public void setSearchWorkPlcNm(String searchWorkPlcNm) {
		this.searchWorkPlcNm = searchWorkPlcNm;
	}
	public String getUnUseReqerNm() {
		return unUseReqerNm;
	}
	public void setUnUseReqerNm(String unUseReqerNm) {
		this.unUseReqerNm = unUseReqerNm;
	}
	
	
	
}
