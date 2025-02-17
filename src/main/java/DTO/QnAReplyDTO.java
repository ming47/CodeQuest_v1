package DTO;

import java.sql.Timestamp;

public class QnAReplyDTO {
	private int qnaReplyId;
	private int qnaId;
	private int memberId;
	private Timestamp regDate;
	
	public QnAReplyDTO(int qnaReplyId, int qnaId, int memberId, Timestamp regDate) {
		super();
		this.qnaReplyId = qnaReplyId;
		this.qnaId = qnaId;
		this.memberId = memberId;
		this.regDate = regDate;
	}

	public int getQnaReplyId() {
		return qnaReplyId;
	}

	public int getQnaId() {
		return qnaId;
	}

	public int getMemberId() {
		return memberId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}
}
