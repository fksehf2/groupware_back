package frame.fcom.service;

/**
 * @Class Name : FileVO.java
 * @Description : File VO class
 * @Modification Information
 *
 * @author 우성택
 * @since 2014.09.03
 * @version 1.0
 * @see
 *  
 *  Copyright (C)  All right reserved.
 */
public class FileVO {

	private static final long serialVersionUID = 1L;
	
	private String atchFileId;
	private String fileSno;
	private String atchFileDiv;
	private String filePth;
	private String fileNm;
	private String fileSize;
	private String oriFileNm;
	private String remark;
		
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getFileSno() {
		return fileSno;
	}
	public void setFileSno(String fileSno) {
		this.fileSno = fileSno;
	}
	public String getAtchFileDiv() {
		return atchFileDiv;
	}
	public void setAtchFileDiv(String atchFileDiv) {
		this.atchFileDiv = atchFileDiv;
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
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getOriFileNm() {
		return oriFileNm;
	}
	public void setOriFileNm(String oriFileNm) {
		this.oriFileNm = oriFileNm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "FileVO [atchFileId=" + atchFileId + ", fileSno=" + fileSno + ", atchFileDiv=" + atchFileDiv
				+ ", filePth=" + filePth + ", fileNm=" + fileNm + ", fileSize=" + fileSize + ", oriFileNm=" + oriFileNm
				+ ", remark=" + remark + "]";
	}
	
	
	
}
