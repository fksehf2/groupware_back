package frame.flyt.login.service;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/***
 * @Class Name : LoginVO.java
 * @Description : Login VO class
 * @Modification Information
 *
 * @author 이종인
 * @since 2020.08.18
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */

public class FLytLoginVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 사용자ID */
	private String userId;
	/** 비밀번호 */
	private String pwd;
	/** 가입상태 */
	private String regStatus;
	/** 시스템그룹/사용자구분 C01001:급식기관, C01002:영양사, C01003:업체회원, C01004:유관기관, C01009:농식품부, C01999:관리자 */
	private String userGb;
	/** 사용자구분명 */
	private String userGbNm;
	/** 사용자명 */
	private String userNm;
	/** 전화번호 */
	private String telNo;
	/** 이메일 */
	private String email;
	/** 탈퇴일자 */
	private String leaveDt;
	/** 비밀번호 최종변경일자 */
	private String pwdChgDt;
	/** 비밀번호 변경횟수 카운드 */
	private String pwdErrCnt;
	/** 비밀번호찾기질문코드 */
	private String pwdFindQues;	
	/** 미빌번호찾기답 */
	private String pwdFindAsw;
	/** 소속기관코드 */
	private String insttCd;
	/** 소속기관명 */
	private String insttNm;
	/** 상위기관코드 */
	private String upInsttCd;
	/** 부서명 */
	private String deptNm;
	/** 약관동의여부 */
	private String policyAgmtYn;
	/** 핸드폰번호 */
	private String hpTelNo;
	
	/*** 접속ip */
	private String ip;
	private String sessionID;
	private String searchUserId;
	private String sName;
	private String sBirthDateReal;

	/** 관리자가 비밀번호 초기화후 변경유무 */
	private String pwdClr;
	

	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getPwd() {
		return pwd;
	}



	public void setPwd(String pwd) {
		this.pwd = pwd;
	}



	public String getRegStatus() {
		return regStatus;
	}



	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}



	public String getUserGb() {
		return userGb;
	}



	public void setUserGb(String userGb) {
		this.userGb = userGb;
	}



	public String getUserGbNm() {
		return userGbNm;
	}



	public void setUserGbNm(String userGbNm) {
		this.userGbNm = userGbNm;
	}



	public String getUserNm() {
		return userNm;
	}



	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}



	public String getTelNo() {
		return telNo;
	}



	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLeaveDt() {
		return leaveDt;
	}



	public void setLeaveDt(String leaveDt) {
		this.leaveDt = leaveDt;
	}



	public String getPwdChgDt() {
		return pwdChgDt;
	}



	public void setPwdChgDt(String pwdChgDt) {
		this.pwdChgDt = pwdChgDt;
	}



	public String getPwdErrCnt() {
		return pwdErrCnt;
	}



	public void setPwdErrCnt(String pwdErrCnt) {
		this.pwdErrCnt = pwdErrCnt;
	}
	

	public String getPwdFindQues() {
		return pwdFindQues;
	}



	public void setPwdFindQues(String pwdFindQues) {
		this.pwdFindQues = pwdFindQues;
	}



	public String getPwdFindAsw() {
		return pwdFindAsw;
	}



	public void setPwdFindAsw(String pwdFindAsw) {
		this.pwdFindAsw = pwdFindAsw;
	}



	public String getInsttCd() {
		return insttCd;
	}



	public void setInsttCd(String insttCd) {
		this.insttCd = insttCd;
	}



	public String getPolicyAgmtYn() {
		return policyAgmtYn;
	}



	public void setPolicyAgmtYn(String policyAgmtYn) {
		this.policyAgmtYn = policyAgmtYn;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getSessionID() {
		return sessionID;
	}



	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}



	public String getSearchUserId() {
		return searchUserId;
	}



	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}



	public String getsName() {
		return sName;
	}



	public void setsName(String sName) {
		this.sName = sName;
	}



	public String getsBirthDateReal() {
		return sBirthDateReal;
	}



	public void setsBirthDateReal(String sBirthDateReal) {
		this.sBirthDateReal = sBirthDateReal;
	}


	public String getPwdClr() {
		return pwdClr;
	}



	public void setPwdClr(String pwdClr) {
		this.pwdClr = pwdClr;
	}



	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}



	/**
	 * @return the insttNm
	 */
	public String getInsttNm() {
		return insttNm;
	}



	/**
	 * @param insttNm the insttNm to set
	 */
	public void setInsttNm(String insttNm) {
		this.insttNm = insttNm;
	}



	/**
	 * @return the upInsttCd
	 */
	public String getUpInsttCd() {
		return upInsttCd;
	}



	/**
	 * @param upInsttCd the upInsttCd to set
	 */
	public void setUpInsttCd(String upInsttCd) {
		this.upInsttCd = upInsttCd;
	}



	/**
	 * @return the deptNm
	 */
	public String getDeptNm() {
		return deptNm;
	}



	/**
	 * @param deptNm the deptNm to set
	 */
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}


	/**
	 * @return the hpTelNo
	 */
	public String getHpTelNo() {
		return hpTelNo;
	}


	/**
	 * @param deptNm the hpTelNo to set
	 */
	public void setHpTelNo(String hpTelNo) {
		this.hpTelNo = hpTelNo;
	}
	
	
}
