package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.PlaytimeDAO;
import DTO.AnalyzeDTO;
import DTO.PlaytimeDTO;
import enums.GENDER;

public enum PlaytimeDAOImpl implements PlaytimeDAO {
	INSTANCE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}


	@Override
	public List<PlaytimeDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaytimeDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(PlaytimeDTO dto) throws Exception {
		String sql = "INSERT INTO PLAY_TIME(PLAY_TIME_ID, MEMBER_ID, GAME_ID, PLAY_TIME) VALUES(PLAY_TIME_ID_SEQ.NEXTVAL, ?, ?, ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getMemberId());
			pstat.setInt(2, dto.getGameId());
			pstat.setInt(3, dto.getPlaytime());
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public int sumAllPlaytime() throws Exception {
		String sql = "SELECT SUM(PLAY_TIME) FROM PLAY_TIME";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			
			return rs.getInt("1");
		}
	}

	@Override
	public List<PlaytimeDTO> selectByMemberId(int memberId) throws Exception {
		String sql = "SELECT * FROM PLAY_TIME WHERE MEMBER_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				
				List<PlaytimeDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(PlaytimeDTO.of(rs));
				}
				
				return dto;
			}
		}
	}

	@Override
	public List<PlaytimeDTO> selectByGameId(int gameId) throws Exception {
		String sql = "SELECT * FROM PLAY_TIME WHERE GAME_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				
				List<PlaytimeDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(PlaytimeDTO.of(rs));
				}
				
				return dto;
			}
		}
	}

	@Override
	public List<PlaytimeDTO> selectByMemberIdAndGameId(int memberId, int gameId) throws Exception {
		String sql = "SELECT * FROM PLAY_TIME WHERE MEMBER_ID = ? AND GAME_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);
			pstat.setInt(2, gameId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				
				List<PlaytimeDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(PlaytimeDTO.of(rs));
				}
				
				return dto;
			}
		}
	}

	@Override
	public double avgAllPlaytime() throws Exception {
		String sql = "SELECT AVG(PLAY_TIME) FROM PLAY_TIME";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			
			return rs.getDouble("1");
		}
	}


	@Override
	public List<PlaytimeDTO> selectByMemberGender(GENDER gender) throws Exception {
		String sql = "SELECT * "
				+ 	"FROM PLAY_TIME P "
				+ 	"INNER JOIN MEMBERS M "
				+ 	"ON P.MEMBER_ID = M.MEMBER_ID "
				+ 	"WHERE SSN LIKE ?";
	
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, "%" + gender.getGenderFactor() + "______");
			
			try(ResultSet rs = pstat.executeQuery()) {
				
				List<PlaytimeDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(PlaytimeDTO.of(rs));
				}
				
				return dto;
			}
		}
	}


	@Override
	public List<PlaytimeDTO> selectByMemberAgeRange(int startAge, int endAge) throws Exception {
		String sql = "SELECT * "
				+ "FROM PLAY_TIME "
				+ "WHERE MEMBER_ID IN ("
				+ "SELECT MEMBER_ID "
				+ "FROM MEMBERS"
				+ "WHERE floor(floor(months_between(sysdate, to_date(substr(ssn, 0, 6), 'rrmmdd')))/12) between ? and ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, startAge);
			pstat.setInt(2, endAge);
			
			try(ResultSet rs = pstat.executeQuery()) {
				
				List<PlaytimeDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(PlaytimeDTO.of(rs));
				}
				
				return dto;
			}	
		}
	}


	@Override
	public List<PlaytimeDTO> selectByDate(LocalDate date) throws Exception {
		String sql = "SELECT * "
				+ "FROM PLAY_TIME "
				+ "WHERE REG_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') + 0.99999";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, date.toString());
			pstat.setString(2, date.toString());
			
			try(ResultSet rs = pstat.executeQuery()) {
				
				List<PlaytimeDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(PlaytimeDTO.of(rs));
				}
				
				return dto;
			}	
		}
	}


	@Override
	public double selectAnaByDate(String type, LocalDate date) throws Exception {
		String insertString = "";
		
		type = type.toLowerCase();
		if(type.equals("sum")) {
			insertString = "SUM(PLAY_TIME)";
		} else if(type.equals("avg")) {
			insertString = "AVG(PLAY_TIME)";
		} else if(type.equals("count")) {
			insertString = "COUNT(*)";
		}
		
		String sql = "SELECT " + insertString + " FROM PLAY_TIME WHERE REG_DATE BETWEEN TO_DATE(?, 'YYYY-MM-DD') AND TO_DATE(?, 'YYYY-MM-DD') + 0.99999";
				
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, date.toString());
			pstat.setString(2, date.toString());
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getDouble(1);
			}
		}
	}


	@Override
	public List<AnalyzeDTO> selectAna7daysByDate(String type, Timestamp date) throws Exception {
		String insertString = "";
		
		type = type.toLowerCase();
		if(type.equals("sum")) {
			insertString = "SUM(PLAY_TIME)";
		} else if(type.equals("avg")) {
			insertString = "AVG(PLAY_TIME)";
		} else if(type.equals("count")) {
			insertString = "COUNT(*)";
		}
		
		String sql = "SELECT " + insertString + ", TRUNC(REG_DATE) AS 일자"
				+ " FROM PLAY_TIME "
				+ "WHERE TRUNC(REG_DATE) "
				+ "BETWEEN TRUNC(? - INTERVAL '7' DAY) AND TRUNC(?) "
				+ "GROUP BY TRUNC(REG_DATE) "
				+ "ORDER BY 일자";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setTimestamp(1, date);
			pstat.setTimestamp(2, date);
			
			List<AnalyzeDTO> dto = new ArrayList<>();
			try(ResultSet rs = pstat.executeQuery()) {
				while(rs.next()) {					
					dto.add(AnalyzeDTO.of(rs));
				}
			}
			
			return dto;
		}
	}
}
