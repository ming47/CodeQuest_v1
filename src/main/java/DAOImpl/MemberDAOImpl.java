package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Common.Statics;
import DAO.MemberDAO;
import DTO.MemberDTO;
import enums.GENDER;

public enum MemberDAOImpl implements MemberDAO {
	INSTANCE;

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public List<MemberDTO> selectAll() throws Exception {
		String sql = "select * from members where role='user'";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			try (ResultSet rs = pstat.executeQuery()) {

				List<MemberDTO> dto = new ArrayList<>();
				while (rs.next()) {
					int memberId = rs.getInt("member_id");
					String loginId = rs.getString("login_id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");

					dto.add(new MemberDTO(memberId,loginId,name,nickName,ssn,
							email,phone,postcode,address,detail_address, role,date));
				}

				return dto;
			}
		}
	}

	@Override
	public MemberDTO selectById(int id) throws Exception { //회원정보 수정 후 수정한 데이터로 세션 업데이트
		String sql = "select * from members where member_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, id);
			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					int memberId = rs.getInt("member_id");
					String loginId = rs.getString("login_id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");
					MemberDTO member = new MemberDTO(memberId,loginId,name,nickName,ssn,
							email,phone,postcode,address,detail_address,
							role,date);
					return member;

				}
			}
			return null;
		}
	}

	@Override
	public int insert(MemberDTO dto) throws Exception {
		String sql = "insert into members "
				+ "(member_id, login_id, password, name, nickname, ssn, email, "
				+ "phone, zip_code, address, detail_address, role, reg_date)values "
				+ "(member_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getLoginId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			pstat.setString(4, dto.getNickName());
			pstat.setString(5, dto.getSsn());
			pstat.setString(6, dto.getEmail());
			pstat.setString(7, dto.getPhone());
			pstat.setInt(8, dto.getZipCode());
			pstat.setString(9, dto.getAddress());
			pstat.setString(10, dto.getDetailAddress());
			pstat.setString(11, dto.getRole());

			return pstat.executeUpdate();
		}
	}

	public boolean getMemberByEmail(String inputEmail) throws Exception { // EMAIL검증
		String sql = "select * from members where email = ? and password is null";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, inputEmail);
			try (ResultSet rs = pstat.executeQuery()) {
				return rs.next();
			}
		}
	}

	public boolean isDuplicate(String column, String value) throws Exception {
		String sql = "select " + column + " from members where " + column + " = ?";
		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, value);
			try (ResultSet rs = pstat.executeQuery()) {
				return rs.next();
			}
		}
	}

	@Override
	public MemberDTO login(String inputId, String inputPw) throws Exception {
		String sql = "select * from members where login_id = ? and password = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, inputId);
			pstat.setString(2, inputPw);
			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					int memberId = rs.getInt("member_id");
					String loginId = rs.getString("login_id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");

					MemberDTO member = new MemberDTO(memberId,loginId,name,nickName,ssn,
							email,phone,postcode,address,detail_address,
							role,date);
					return member;

				}
			}
			return null;
		}
	}
	
	@Override
	public MemberDTO easyLogin(String inputEmail) throws Exception {
		String sql = "select * from members where email = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, inputEmail);
			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					int memberId = rs.getInt("member_id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");

					MemberDTO member = new MemberDTO(memberId,name,nickName,ssn,
							email,phone,postcode,address,detail_address,
							role,date);
					return member;

				}
			}
			return null;
		}
	}

	@Override
	public int update(MemberDTO dto) throws Exception {	//mypage수정
		String sql = "update members set nickname = ?, email = ?, phone = ?, zip_code = ?, address = ?, detail_address =? where member_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, dto.getNickName());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, dto.getPhone());
			pstat.setInt(4, dto.getZipCode());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getDetailAddress());
			pstat.setInt(7, dto.getMemberId());
			System.out.println("DAO에서의 MEMBERID값"+dto.getMemberId());

			return pstat.executeUpdate();
		}
	}

	@Override
	public int deleteById(int id) throws Exception { //회원탈퇴
		String sql = "delete from members where member_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, id);
			int result = pstat.executeUpdate();
			return result;
		}
	}

	@Override
	public List<MemberDTO> selectByGender(GENDER gender) throws Exception {		
		String sql = "SELECT * FROM Members WHERE SSN LIKE ?";

		String target = String.format("%%%d______", gender.getGenderFactor());
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, target);

			List<MemberDTO> dto = new ArrayList<>();
			try (ResultSet rs = pstat.executeQuery()) {
				while (rs.next()) {
					String id = rs.getString("user_id");
					String name = rs.getString("name");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");

					dto.add(new MemberDTO(id,name,ssn,email,phone,postcode,address,detail_address,role,date));

				}

				return dto;
			}
		}
	}

	@Override
	public List<MemberDTO> selectByAgeRange(int startAge, int endAge) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdmin(int memberId) throws Exception {
		String sql = "SELECT * FROM MEMBERS WHERE MEMBER_ID = ?";

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);

			try(ResultSet rs = pstat.executeQuery()) {
				return (rs.getString("ROLE").equals("ADMIN"));
			}
		}
	}

	@Override
	public List<MemberDTO> selectByIsBanned(boolean isBanned) throws Exception {
		String insertString = (isBanned) ? "" : "not";
		String sql = "SELECT * FROM MEMBERS WHERE MEMBER_ID " + insertString + " IN (SELECT MEMBER_ID FROM BLACK_LIST WHERE END_DATE >= SYSTIMESTAMP)";
		
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			try (ResultSet rs = pstat.executeQuery()) {
				
				List<MemberDTO> dto = new ArrayList<>();
				while (rs.next()) {
					int memberId = rs.getInt("member_id");
					String loginId = rs.getString("login_id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");
					
					dto.add(new MemberDTO(memberId,loginId,name,nickName,ssn,
						email,phone,postcode,address,detail_address, role,date));
				}
				
				return dto;
			}
		}
	}


	@Override
	public int updatePw(String email, String pw) throws Exception {
		String sql = "update members set password = ? where email = ?";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, pw);
			pstat.setString(2, email);
			return pstat.executeUpdate();
		}
	}
	
	public int getSize() throws Exception {
		String sql = "SELECT COUNT(*) FROM MEMBERS WHERE ROLE='user'";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			
			return rs.getInt(1);
		}
	}

	@Override
	public List<MemberDTO> selectAll(int page) throws Exception {
		String sql = "SELECT * FROM (SELECT M.*, ROW_NUMBER() OVER(ORDER BY MEMBER_ID) AS RNUM FROM MEMBERS M WHERE ROLE='user') A WHERE A.RNUM BETWEEN ? AND ?";
		
		int startIndex = (page - 1) * Statics.recordCountPerPage + 1;
		int endIndex = startIndex + Statics.recordCountPerPage - 1;

		endIndex = (endIndex > getSize()) ? getSize() : endIndex;

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, startIndex);
			pstat.setInt(2, endIndex);

			try(ResultSet rs = pstat.executeQuery()) {
				List<MemberDTO> dto = new ArrayList<>();
				while (rs.next()) {
					int memberId = rs.getInt("member_id");
					String loginId = rs.getString("login_id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");

					dto.add(new MemberDTO(memberId,loginId,name,nickName,ssn,
							email,phone,postcode,address,detail_address, role,date));
				}

				return dto;
			}
		}
	}

	@Override
	public List<MemberDTO> selectByIsBanned(boolean isBanned, int page) throws Exception {
		String insertString = (isBanned) ? "" : "not";
		String sql = "SELECT *  FROM "
				+ "(SELECT M.*, ROW_NUMBER() OVER(ORDER BY MEMBER_ID) AS RNUM "
				+ "FROM MEMBERS M WHERE ROLE='user' AND "
				+ "MEMBER_ID " + insertString + " IN (SELECT MEMBER_ID FROM BLACK_LIST WHERE END_DATE >= SYSTIMESTAMP)) A "
				+ "WHERE A.RNUM BETWEEN ? AND ?";
		
		int startIndex = (page - 1) * Statics.recordCountPerPage + 1;
		int endIndex = startIndex + Statics.recordCountPerPage - 1;

		endIndex = (endIndex > getSelectByIsBannedSize(isBanned)) ? getSelectByIsBannedSize(isBanned) : endIndex;
		
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, startIndex);
			pstat.setInt(2, endIndex);
			
			try (ResultSet rs = pstat.executeQuery()) {
				
				List<MemberDTO> dto = new ArrayList<>();
				while (rs.next()) {
					int memberId = rs.getInt("member_id");
					String loginId = rs.getString("login_id");
					String name = rs.getString("name");
					String nickName = rs.getString("nickname");
					String ssn = rs.getString("ssn");
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					int postcode = rs.getInt("zip_code");
					String address = rs.getString("address");
					String detail_address = rs.getString("detail_address");
					String role = rs.getString("role");
					Timestamp date = rs.getTimestamp("reg_date");
					
					dto.add(new MemberDTO(memberId,loginId,name,nickName,ssn,
						email,phone,postcode,address,detail_address, role,date));
				}
				
				return dto;
			}
		}
	}

	@Override
	public int getSelectByIsBannedSize(boolean isBanned) throws Exception {
		String insertString = (isBanned) ? "" : "not";
		String sql = "SELECT COUNT(*) FROM MEMBERS WHERE MEMBER_ID " + insertString 
				+ " IN (SELECT MEMBER_ID FROM BLACK_LIST WHERE END_DATE >= SYSTIMESTAMP)";
		
		try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
				
				rs.next();
				
				return rs.getInt(1);
			}
	}

}
