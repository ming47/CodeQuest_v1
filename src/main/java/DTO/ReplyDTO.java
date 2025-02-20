package DTO;

import java.sql.Timestamp;

public class ReplyDTO {

	private int replyId;
	private int memberId;
	private int boardId;
	private String contents;
	private Timestamp regDate;
	
	
	public ReplyDTO(int replyId, int memberId, int boardId, String contents, Timestamp regDate) {
		super();
		this.replyId = replyId;
		this.memberId = memberId;
		this.boardId = boardId;
		this.contents = contents;
		this.regDate = regDate;
	}
	public ReplyDTO() {}
	
	public ReplyDTO(int replyId, String contents) {
		this.replyId = replyId;
		this.contents = contents;
	}
	public ReplyDTO(int replyId, String contents, int memberId) {	//댓글 출력
		this.replyId = replyId;
		this.contents = contents;
		this.memberId = memberId;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
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

	



}
