package DTO;

import java.sql.Timestamp;

public class BoardDTO {
	private int boardId;
	private String writer;
	private String title;
	private Timestamp regDate;
	private String contents;
	private int viewCount;
	private int replyCount;


	public BoardDTO() {
		super();
	}

	public BoardDTO(int boardId, String writer, String title, Timestamp regDate, String contents ,int viewCount ,int replyCount) {
		super();
		this.boardId = boardId;
		this.writer = writer;
		this.title = title;
		this.regDate = regDate;
		this.contents = contents;
		this.viewCount = viewCount;
        this.replyCount = replyCount;

	}
	
	public BoardDTO(String title,  String contents ,int board_id) {

		this.boardId = board_id;
	
		this.title = title;

		this.contents = contents;


	}
	
	public BoardDTO(int boardId,String title,String writer, String contents ) {

		this.boardId = boardId;
		this.title = title;
		this.writer = writer;
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
	
	public int getViewCount() {
		return viewCount;
	}
	
	
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
}
