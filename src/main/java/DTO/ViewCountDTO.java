package DTO;

import java.sql.Timestamp;

public class ViewCountDTO {
	
	private int view_count_id;
	private int board_id;
	private int member_id;
	private Timestamp reg_date;
	public int getView_count_id() {
		return view_count_id;
	}
	public void setView_count_id(int view_count_id) {
		this.view_count_id = view_count_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public ViewCountDTO(int view_count_id, int board_id, int member_id, Timestamp reg_date) {
		super();
		this.view_count_id = view_count_id;
		this.board_id = board_id;
		this.member_id = member_id;
		this.reg_date = reg_date;
	}
	public ViewCountDTO() {
		super();
	}
	
	
}
