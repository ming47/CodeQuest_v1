package DTO;

import java.sql.Timestamp;

public class BoardDTO {
	private int boardId;
	private String writer;
	private String title;
	private Timestamp regDate;
	private String contents;

	public BoardDTO() {
		super();
	}

	public BoardDTO(int boardId, String writer, String title, Timestamp regDate, String contents) {
		super();
		this.boardId = boardId;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.contents = contents;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setReg_date(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getContents() {
		return contents;
	}

	public void setContens(String contents) {
		this.contents = contents;
	}
}
