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
		// TODO Auto-generated method stub
		return null;
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
	
	//dao.insert(new MemberDTO(id,pw,name,ssn,email,phone,postcode,address1,address2,null)); //role은 정해진게없어서 null
	
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
	
	public boolean idVali(String id) throws Exception {// ID검증
		String sql = "select login_id from members where login_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setString(1, id);
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
	public int update(MemberDTO dto) throws Exception {	//mypage수정
		String sql = "update Members set nickname = ?, email = ?, phone = ?, zip_code = ?, address = ?, detail_address =? where login_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1, dto.getNickName());
			pstat.setString(2, dto.getEmail());
			pstat.setString(3, dto.getPhone());
			pstat.setInt(4, dto.getZipCode());
			pstat.setString(5, dto.getAddress());
			pstat.setString(6, dto.getDetailAddress());
			pstat.setString(7, dto.getLoginId());
			
			return pstat.executeUpdate();
		}
		// TODO Auto-generated method stub
	}

	@Override
	public int deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberDTO> selectByGender(GENDER gender) throws Exception {		
		String sql = "SELECT * FROM Members WHERE SSN LIKE ?";
		
		String target = String.format("%%-%d______", gender.getGenderFactor());
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
	
	public String getNickNameByLoginId(String loginId) throws Exception {	//DB에서 닉네임만 뽑아서 댓글쓰기
	    String sql = "SELECT nickname FROM members WHERE MEMBER_ID = ?";
	    
	    try (Connection conn = this.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, loginId);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getString("nickname");
	            }
	            return null; // 해당 login_id가 없는 경우
	        }
	    }
	}

}
