package com.kh.borard.model.vo;


public class Reply {

	private int replyNo;
	private String replyCotent;
	private int replyBno;
	private String replyWriter;
	private String createDate;
	private String status;

	
	public Reply(){
		super();
	}


	public Reply(int replyNo, String replyCotent, int replyBno, String replyWriter, String createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.replyCotent = replyCotent;
		this.replyBno = replyBno;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
	}


	public Reply(int replyNo, String replyCotent, String replyWriter, String createDate) {
		super();
		this.replyNo = replyNo;
		this.replyCotent = replyCotent;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
	}


	public int getReplyNo() {
		return replyNo;
	}


	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}


	public String getReplyCotent() {
		return replyCotent;
	}


	public void setReplyCotent(String replyCotent) {
		this.replyCotent = replyCotent;
	}


	public int getReplyBno() {
		return replyBno;
	}


	public void setReplyBno(int replyBno) {
		this.replyBno = replyBno;
	}


	public String getReplyWriter() {
		return replyWriter;
	}


	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyCotent=" + replyCotent + ", replyBno=" + replyBno
				+ ", replyWriter=" + replyWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
	
}
