package frame.flyt.main.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import frame.fcom.service.SearchVO;

/**
 * @Class Name : MenuVO.java
 * @Description : Menu VO class
 * @Modification Information
 *
 * @author 우성택
 * @since 2014.09.03
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

public class MenuVO extends SearchVO{

	private String menuNm;
	private String programNm;
	private String menuNo;
	private String upperMenuNo;
	private String menuOrdr;
	private String menuDc;
	private String relateImagePath;
	private String relateImageNm;
	private String menuAuthor;
	private String menuDisplayAt;
	private String menuDisplayYn;
	
    /** SYS_GRP */
    private String sysGrp;	
    /** SYS_GRP_NM */
    private String sysGrpNm;
    /** PROGRAM_ID */
    private String programId;
    /** PROGRAM_PATH */
    private String programPath;
    /** PROGRAM_EXPL */
    private String programExpl;
    
    /*검색조건*/
    /** src_Sys_Grp */
    private String srcSysGrp;
    /** src_MenuGrp */
    private String srcMenugrp;
    /** src_MenuNm */
    private String srcMenunm;
    /** src_Menu_Step */
    private String srcMenuStep;
    /** src_Sel_Menu */
    private String srcSelMenu; 
    
    /** 삭제키값 delkey */
    private String delkey;    
    
    /** MAX_MENU_NO */
    private String maxMenuNo;
    
    private List<MenuVO> subList;
    
    private int cnt;
    
    
    public String getSysGrpNm() {
		return sysGrpNm;
	}
	public void setSysGrpNm(String sysGrpNm) {
		this.sysGrpNm = sysGrpNm;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramPath() {
		return programPath;
	}
	public void setProgramPath(String programPath) {
		this.programPath = programPath;
	}
	public String getProgramExpl() {
		return programExpl;
	}
	public void setProgramExpl(String programExpl) {
		this.programExpl = programExpl;
	}
	public String getSrcSysGrp() {
		return srcSysGrp;
	}
	public void setSrcSysGrp(String srcSysGrp) {
		this.srcSysGrp = srcSysGrp;
	}
	public String getSrcMenugrp() {
		return srcMenugrp;
	}
	public void setSrcMenugrp(String srcMenugrp) {
		this.srcMenugrp = srcMenugrp;
	}
	public String getSrcMenunm() {
		return srcMenunm;
	}
	public void setSrcMenunm(String srcMenunm) {
		this.srcMenunm = srcMenunm;
	}
	public String getSrcMenuStep() {
		return srcMenuStep;
	}
	public void setSrcMenuStep(String srcMenuStep) {
		this.srcMenuStep = srcMenuStep;
	}
	public String getSrcSelMenu() {
		return srcSelMenu;
	}
	public void setSrcSelMenu(String srcSelMenu) {
		this.srcSelMenu = srcSelMenu;
	}
	public String getDelkey() {
		return delkey;
	}
	public void setDelkey(String delkey) {
		this.delkey = delkey;
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
	/*페이징 공통START*/
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
/*페이징 공통END*/
    

	
	private String depthFullname;
	private String progrmKoreanNm;
	private String url;
	private double lvl;
	
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo; 
	}
	public String getMenuNo() {
		return menuNo; 
	}
	public void setDepthFullname(String depthFullname) {
		this.depthFullname = depthFullname; 
	}
	public String getDepthFullname() {
		return depthFullname; 
	}
	public void setRelateImageNm(String relateImageNm) {
		this.relateImageNm = relateImageNm; 
	}
	public String getRelateImageNm() {
		return relateImageNm; 
	}
	public void setRelateImagePath(String relateImagePath) {
		this.relateImagePath = relateImagePath; 
	}
	public String getRelateImagePath() {
		return relateImagePath; 
	}
	public void setProgrmKoreanNm(String progrmKoreanNm) {
		this.progrmKoreanNm = progrmKoreanNm; 
	}
	public String getProgrmKoreanNm() {
		return progrmKoreanNm; 
	}
	public void setUpperMenuNo(String upperMenuNo) {
		this.upperMenuNo = upperMenuNo; 
	}
	public String getUpperMenuNo() {
		return upperMenuNo; 
	}
	public void setUrl(String url) {
		this.url = url; 
	}
	public String getUrl() {
		return url; 
	}
	public void setMenuOrdr(String menuOrdr) {
		this.menuOrdr = menuOrdr; 
	}
	public String getMenuOrdr() {
		return menuOrdr; 
	}
	
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm; 
	}
	public String getMenuNm() {
		return menuNm; 
	}
	
	public double getLvl() {
		return lvl;
	}
	public void setLvl(double lvl) {
		this.lvl = lvl;
	}
	public String getMenuDc() {
		return menuDc;
	}
	public void setMenuDc(String menuDc) {
		this.menuDc = menuDc;
	}
	public String getMenuAuthor() {
		return menuAuthor;
	}
	public void setMenuAuthor(String menuAuthor) {
		this.menuAuthor = menuAuthor;
	}
	public String getMenuDisplayAt() {
		return menuDisplayAt;
	}
	public void setMenuDisplayAt(String menuDisplayAt) {
		this.menuDisplayAt = menuDisplayAt;
	}
	public String getSysGrp() {
		return sysGrp;
	}
	public void setSysGrp(String sysGrp) {
		this.sysGrp = sysGrp;
	}
	
	public String getProgramNm() {
		return programNm;
	}
	public void setProgramNm(String programNm) {
		this.programNm = programNm;
	}
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public String getMenuDisplayYn() {
		return menuDisplayYn;
	}
	public void setMenuDisplayYn(String menuDisplayYn) {
		this.menuDisplayYn = menuDisplayYn;
	}
	public String getMaxMenuNo() {
		return maxMenuNo;
	}
	public void setMaxMenuNo(String maxMenuNo) {
		this.maxMenuNo = maxMenuNo;
	}

	private String SessionID="";
	
	public String getSessionID() {
		return SessionID;
	}
	public void setSessionID(String SessionID) {
		this.SessionID = SessionID;
    
	}
	public List<MenuVO> getSubList() {
		return subList;
	}
	public void setSubList(List<MenuVO> subList) {
		this.subList = subList;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
