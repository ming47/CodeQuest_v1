package DTO;

import java.sql.Timestamp;

public class ReplyDTO {

	private int replyId;
	private String name;
	private int boardId;
	private String contents;
	private Timestamp regDate;
	
	public ReplyDTO(int replyId, String name, int boardId, String contents, Timestamp regDate) {
		super();
		this.replyId = replyId;
		this.name = name;
		this.boardId = boardId;
		this.contents = contents;
		this.regDate = regDate;
	}

	public ReplyDTO() {
		super();
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
