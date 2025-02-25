package DTO;

import java.sql.Timestamp;

public class ViewCountDTO {
	
	private int viewCountId;
	private int boardId;
	private int memberId;
	private Timestamp regDate;
	
	public ViewCountDTO(int viewCountId, int boardId, int memberId, Timestamp regDate) {
		super();
		this.viewCountId = viewCountId;
		this.boardId = boardId;
		this.memberId = memberId;
		this.regDate = regDate;
	}
	
	public ViewCountDTO(int boardId, int memberId) {
		this.boardId = boardId;
		this.memberId = memberId;
	}
	
	public ViewCountDTO(int boardId) {
		this.boardId = boardId;
	}
	
	public ViewCountDTO() {
		super();
	}
	
	public int getViewCountId() {
		return viewCountId;
	}
	public void setViewCountId(int viewCountId) {
		this.viewCountId = viewCountId;
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
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
