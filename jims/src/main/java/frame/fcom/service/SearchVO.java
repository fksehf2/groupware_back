package frame.fcom.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class SearchVO implements Serializable {

	/**
	 * 검색 과 페이지 처리에 사용.
	 */
	private static final long serialVersionUID = 3445612582754239479L;

	private String searchNm = "";//조회명
	 private String searchMarket = "";//시장
	 private String searchCompany = "";//법인명
	 private String searchReg = "";//등록자
	 private String searchCategory = "";//품명
	 private String searchLocal = "";//지역
	 private String searchAution = "";//경매사
	 private String searchStt = "";//상태
	 private String searchUserId = "";//사용자ID
	 private String searchWork = "";//담당업무
	 private String searchStrtDt = "";//조회 시작일
	 private String searchEndDt = "";//조회 종료일
	 private String searchRegDt = "";//등록일
	 private String searchConts = "";//내용
	 private String searchItemNm = "";	// 출하자명
	 
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
	 /** 정렬옵션 */
	 private String sortOpt; 
	 
	 
	public String getSearchItemNm() {
		return searchItemNm;
	}
	public void setSearchItemNm(String searchItemNm) {
		this.searchItemNm = searchItemNm;
	}
	public String getSortOpt() {
		return sortOpt;
	}
	public void setSortOpt(String sortOpt) {
		this.sortOpt = sortOpt;
	}
	public String getSearchNm() {
		return searchNm;
	}
	public void setSearchNm(String searchNm) {
		this.searchNm = searchNm;
	}
	public String getSearchMarket() {
		return searchMarket;
	}
	public void setSearchMarket(String searchMarket) {
		this.searchMarket = searchMarket;
	}
	public String getSearchCompany() {
		return searchCompany;
	}
	public void setSearchCompany(String searchCompany) {
		this.searchCompany = searchCompany;
	}
	public String getSearchReg() {
		return searchReg;
	}
	public void setSearchReg(String searchReg) {
		this.searchReg = searchReg;
	}
	public String getSearchCategory() {
		return searchCategory;
	}
	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
	public String getSearchLocal() {
		return searchLocal;
	}
	public void setSearchLocal(String searchLocal) {
		this.searchLocal = searchLocal;
	}
	public String getSearchAution() {
		return searchAution;
	}
	public void setSearchAution(String searchAution) {
		this.searchAution = searchAution;
	}
	public String getSearchStt() {
		return searchStt;
	}
	public void setSearchStt(String searchStt) {
		this.searchStt = searchStt;
	}
	public String getSearchUserId() {
		return searchUserId;
	}
	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}
	public String getSearchWork() {
		return searchWork;
	}
	public void setSearchWork(String searchWork) {
		this.searchWork = searchWork;
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
	public String getSearchRegDt() {
		return searchRegDt;
	}
	public void setSearchRegDt(String searchRegDt) {
		this.searchRegDt = searchRegDt;
	}
	public String getSearchConts() {
		return searchConts;
	}
	public void setSearchConts(String searchConts) {
		this.searchConts = searchConts;
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
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}  
}
