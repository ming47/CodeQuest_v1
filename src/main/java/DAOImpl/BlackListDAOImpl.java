package DAOImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DAO.BlackListDAO;
import DTO.BlackListDTO;

public enum BlackListDAOImpl implements BlackListDAO {
	INSTANCE;
	
	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public List<BlackListDTO> selectAll() throws Exception {
		return null;
	}

	@Override
	public BlackListDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BlackListDTO dto) throws Exception {
		String sql = "INSERT INTO BLACK_LIST(BLACK_ID, MEMBER_ID, END_DATE) VALUES(BLACK_ID_SEQ.NEXTVAL, ?, ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getMemberId());
			pstat.setTimestamp(2, dto.getEndDate());
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public int update(BlackListDTO dto) throws Exception {
		String sql = "UPDATE BLACK_LIST SET END_DATE = ? WHERE BLACK_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setTimestamp(1, dto.getEndDate());
			pstat.setInt(2, dto.getMemberId());
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public int deleteById(int id) throws Exception {
		String sql = "DELETE FROM BLACK_LIST WHERE BLACK_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, id);
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public List<BlackListDTO> selectByMemberId(int memberId) throws Exception {
		String sql = "SELECT * FROM BLACK_LIST WHERE MEMBER_ID = ?";
		return null;
	}

	@Override
	public List<BlackListDTO> selectByPeriod(Date startTime, Date endTime) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBanned(int memberId) throws Exception {
		String sql = "SELECT COUNT(*) FROM BLACK_LIST WHERE MEMBER_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getInt(1) != 0;
			}
		}
	}

}
