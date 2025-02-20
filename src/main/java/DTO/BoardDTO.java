package DTO;

import java.sql.Timestamp;

public class BoardDTO {
	private int boardId;
	private int memberId;
	private String title;
	private Timestamp regDate;
	private String contents;
	private int viewCount;
	private int replyCount;
	
	private String writer;
	private String role;


	public BoardDTO() {
		super();
	}

	public BoardDTO(int boardId, int memberId, String title, Timestamp regDate, String contents ,int viewCount ,int replyCount, String writer, String role) {
		super();
		this.boardId = boardId;
		this.memberId = memberId;
		this.title = title;
		this.regDate = regDate;
		this.contents = contents;
		this.viewCount = viewCount;
        this.replyCount = replyCount;
        this.writer = writer;
        this.role = role;
	}
	
	public BoardDTO(String title,  String contents ,int board_id) {
		this.boardId = board_id;
		this.title = title;
		this.contents = contents;
	}
	

	public BoardDTO(int boardId,String title,int memberId, String contents ) {

		this.boardId = boardId;
		this.title = title;
		this.memberId = memberId;
		this.contents = contents;
	}
	
	public BoardDTO(String title,Timestamp regDate, int viewCount) { //마이페이지
		this.title = title;
		this.regDate = regDate;
		this.viewCount = viewCount;
	}

	
	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
	
	public String getWriter() {
		return writer;
	}
	
	public String getRole() {
		return role;
	}
	
}
