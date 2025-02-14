package DTO;

import java.sql.Timestamp;

public class QnADTO {
	private int qnaId;
	private int memberId;
	private String contents;
	private Timestamp regDate;
	private String responseYn;
	
	public QnADTO(int qnaId, int memberId, String contents, Timestamp regDate, String responseYn) {
		super();
		this.qnaId = qnaId;
		this.memberId = memberId;
		this.contents = contents;
		this.regDate = regDate;
		this.responseYn = responseYn;
	}

	public QnADTO() {
		super();
	}

	public int getQnaId() {
		return qnaId;
	}

	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getResponseYn() {
		return responseYn;
	}

	public void setResponseYn(String responseYn) {
		this.responseYn = responseYn;
	}

	
}
