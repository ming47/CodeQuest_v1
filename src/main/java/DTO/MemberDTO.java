package DTO;

import java.sql.Timestamp;

public class MemberDTO {
	private int memberId;
	private String id;
	private String pw;
	private String name;
	private String nickName;
	private String ssn;
	private String email;
	private String phone;
	private int zipCode;
	private String address;
	private String detailAddress;
	private String role;
	private Timestamp regDate;
		
	public MemberDTO(String id, String pw, String name, String nickName, String ssn, String email, String phone, int zipCode,
			String address, String detailAddress, String role) { //회원 가입
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickName = nickName;
		this.ssn = ssn;
		this.email = email;
		this.phone = phone;
		this.zipCode = zipCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.role = role;
	}
		
	public MemberDTO(String id, String name, String nickName, String ssn, String email, String phone, int zipCode,
			String address, String detailAddress, String role, Timestamp regDate ) { //로그인
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.ssn = ssn;
		this.email = email;
		this.phone = phone;
		this.zipCode = zipCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.role = role;
		this.regDate = regDate;
	}
		public MemberDTO(String id, String name, String ssn, String email, String phone, int zipCode,
			String address, String detailAddress, String role, Timestamp regDate ) { //성별로 찾는다
		super();
		this.id = id;
		this.name = name;
		this.ssn = ssn;
		this.email = email;
		this.phone = phone;
		this.zipCode = zipCode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.role = role;
		this.regDate = regDate;
	}

	public MemberDTO() {
		super();
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
	
	
}
