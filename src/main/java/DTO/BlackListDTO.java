package DTO;

import java.sql.Timestamp;

public class BlackListDTO {

	private int black_id;
	private int member_id;
	private String reason;
	private Timestamp start_date;
	private Timestamp end_date;
	public int getBlack_id() {
		return black_id;
	}
	public void setBlack_id(int black_id) {
		this.black_id = black_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getStart_date() {
		return start_date;
	}
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}
	public Timestamp getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}
	public BlackListDTO(int black_id, int member_id, String reason, Timestamp start_date, Timestamp end_date) {
		super();
		this.black_id = black_id;
		this.member_id = member_id;
		this.reason = reason;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public BlackListDTO() {
		super();
	}
	
	
}
