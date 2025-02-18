package DTO;

import java.sql.Timestamp;

public class BoardDTO {
	private int board_id;
	private String writer;
	private String title;
	private Timestamp reg_date;
	private String contents;
	private int viewCount;
	private int replyCount;


	public BoardDTO() {
		super();
	}

	public BoardDTO(int board_id, String writer, String title, Timestamp reg_date, String contents ,int viewCount ,int replyCount) {
		super();
		this.board_id = board_id;
		this.writer = writer;
		this.title = title;
		this.reg_date = reg_date;
		this.contents = contents;
		this.viewCount = viewCount;
        this.replyCount = replyCount;

	}
	
	public BoardDTO(String title,  String contents ,int board_id) {

		this.board_id = board_id;
	
		this.title = title;

		this.contents = contents;


	}
	
	public BoardDTO(int board_id,String title,String writer, String contents ) {

		this.board_id = board_id;
		this.title = title;
		this.writer = writer;
		this.contents = contents;


	}
	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
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

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getContents() {
		return contents;
	}

	public void setContens(String contents) {
		this.contents = contents;
	}
	
	
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	
}
