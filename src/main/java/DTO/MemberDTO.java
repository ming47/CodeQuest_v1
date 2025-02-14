package DTO;

import java.sql.Timestamp;

public class MemberDTO {
	private int member_id;
	private String id;
	private String pw;
	private String name;
	private String ssn;
	private String email;
	private String zip_code;
	private String address;
	private String detail_address;
	private String role;
	private Timestamp reg_date;
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public MemberDTO(int member_id, String id, String pw, String name, String ssn, String email, String zip_code,
			String address, String detail_address, String role, Timestamp reg_date) {
		super();
		this.member_id = member_id;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.ssn = ssn;
		this.email = email;
		this.zip_code = zip_code;
		this.address = address;
		this.detail_address = detail_address;
		this.role = role;
		this.reg_date = reg_date;
	}
	public MemberDTO() {
		super();
	}
	
	
}
