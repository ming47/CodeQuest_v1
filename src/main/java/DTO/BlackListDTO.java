package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BlackListDTO {

	private int blackId;
	private int memberId;
	private String reason;
	private Timestamp startDate;
	private Timestamp endDate;
	private int period;
	
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
	
	public int getPeriod() {
		return period;
	}
	
	public BlackListDTO(int blackId, int memberId, String reason, Timestamp startDate, Timestamp endDate) {
		super();
		this.blackId = blackId;
		this.memberId = memberId;
		this.reason = reason;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public BlackListDTO(int memberId, String reason, int period) {
		this.memberId = memberId;
		this.reason = reason;
		this.period = period;
	}
	
	public BlackListDTO() {
		super();
	}
	
	public static BlackListDTO of(ResultSet rs) throws SQLException {
		return new BlackListDTO(
				rs.getInt("BLACK_ID"),
				rs.getInt("MEMBER_ID"),
				rs.getString("REASON"),
				rs.getTimestamp("START_DATE"),
				rs.getTimestamp("END_DATE"));
	}
}
