package DTO;

import java.sql.Timestamp;

public class BlackListDTO {

	private int blackId;
	private int memberId;
	private String reason;
	private Timestamp startDate;
	private Timestamp endDate;
	public int getBlackId() {
		return blackId;
	}
	public void setBlackId(int blackId) {
		this.blackId = blackId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMember_id(int memberId) {
		this.memberId = memberId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStart_date(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEnd_date(Timestamp endDate) {
		this.endDate = endDate;
	}
	
	public BlackListDTO(int blackId, int memberId, String reason, Timestamp startDate, Timestamp endDate) {
		super();
		this.blackId = blackId;
		this.memberId = memberId;
		this.reason = reason;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public BlackListDTO() {
		super();
	}
}
