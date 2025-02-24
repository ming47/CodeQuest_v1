package DTO;

import java.sql.Timestamp;

public class MemberDTO {
	private int memberId;
	private String loginId;
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
	
	
	public boolean isIsbanned() {
		return isbanned;
	}

	public void setIsbanned(boolean isbanned) {
		this.isbanned = isbanned;
	}

	private boolean isbanned;
		
	public MemberDTO(String loginId, String pw, String name, String nickName, String ssn, String email, String phone, int zipCode,
			String address, String detailAddress, String role) { //회원 가입
		super();
		this.loginId = loginId;
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
		
	public MemberDTO(int memberId,String loginId, String name, String nickName, String ssn, String email, String phone, int zipCode,
			String address, String detailAddress, String role, Timestamp regDate ) { //로그인
		super();
		this.memberId = memberId;
		this.loginId = loginId;
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
	public MemberDTO(int memberId, String name, String nickName, String ssn, String email, String phone, int zipCode,
			String address, String detailAddress, String role, Timestamp regDate ) { //로그인
		super();
		this.memberId = memberId;
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
		public MemberDTO(String loginId, String name, String ssn, String email, String phone, int zipCode,
			String address, String detailAddress, String role, Timestamp regDate ) { //성별로 찾는다
		super();
		this.loginId = loginId;
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
	public MemberDTO(String loginId, String nickname,String email, String phone, int zipCode, String address, String detailAddress) {//회원 수정
		this.loginId = loginId;
		this.nickName = nickname;
		this.email = email;
		this.phone = phone;
		this.zipCode = zipCode;
		this.address = address;
		this.detailAddress = detailAddress;
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
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
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