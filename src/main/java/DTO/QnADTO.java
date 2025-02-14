package DTO;

import java.sql.Timestamp;

public class QnADTO {
	private int qna_id;
	private int member_id;
	private String contents;
	private Timestamp reg_date;
	private String response_yn;
	
	public int getQna_id() {
		return qna_id;
	}

	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getResponse_yn() {
		return response_yn;
	}

	public void setResponse_yn(String response_yn) {
		this.response_yn = response_yn;
	}

	public QnADTO() {
		super();
	}

	public QnADTO(int qna_id, int member_id, String contents, Timestamp reg_date, String response_yn) {
		super();
		this.qna_id = qna_id;
		this.member_id = member_id;
		this.contents = contents;
		this.reg_date = reg_date;
		this.response_yn = response_yn;
	}

}
