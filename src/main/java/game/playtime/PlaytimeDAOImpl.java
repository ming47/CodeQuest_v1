package game.playtime;

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

import utils.AnalyzeDTO;

public enum PlaytimeDAOImpl implements PlaytimeDAO {
	INSTANCE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}

	@Override
	public int insert(PlaytimeDTO dto) throws Exception {
		String sql = "INSERT INTO PLAY_TIME(PLAYTIME_ID, MEMBER_ID, GAME_ID, PLAY_TIME) "
				+ "VALUES(PLAYTIME_ID_SEQ.NEXTVAL, ?, ?, ?)";
		
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
	public List<PlaytimeDTO> selectRecentByMemberId(int inputMemberId) throws Exception { //마이페이지에서 최근 플레이한 게임
	    String sql = "SELECT * FROM ( " +
	                 "    SELECT PLAYTIME_ID, MEMBER_ID, GAME_ID, PLAY_TIME, REG_DATE " +
	                 "      FROM PLAY_TIME " +
	                 "     WHERE MEMBER_ID = ? " +
	                 "     ORDER BY REG_DATE DESC " +
	                 ") WHERE ROWNUM <= 5";
	    
	    try (Connection con = getConnection();
	         PreparedStatement pstat = con.prepareStatement(sql)) {
	        pstat.setInt(1, inputMemberId);
	        
	        try (ResultSet rs = pstat.executeQuery()) {
	            List<PlaytimeDTO> list = new ArrayList<>();
	            while (rs.next()) {
	            	int playtimeId = rs.getInt("playtime_id");
	            	int memberId = rs.getInt("member_id");
	            	int gameId = rs.getInt("game_id");
	            	int playtime = rs.getInt("play_time");
	            	Timestamp regDate = rs.getTimestamp("reg_date");
	            	PlaytimeDTO dto = new PlaytimeDTO(playtimeId,memberId,gameId,playtime,regDate);
	            	list.add(dto);
	            }
	            return list;
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
	public double selectAnaByMinusDate(String type, int date) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + " FROM PLAY_TIME "
				+ "WHERE TRUNC(REG_DATE) = TRUNC(SYSTIMESTAMP - NUMTODSINTERVAL(?, 'DAY'))";
				
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, String.valueOf(date));
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getDouble(1);
			}
		}
	}


	@Override
	public List<AnalyzeDTO> selectAnaRecent7days(String type) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + ", TRUNC(REG_DATE) AS 일자"
				+ " FROM PLAY_TIME "
				+ "WHERE TRUNC(REG_DATE) "
				+ "BETWEEN TRUNC(systimestamp - INTERVAL '7' DAY) AND TRUNC(systimestamp) "
				+ "GROUP BY TRUNC(REG_DATE) "
				+ "ORDER BY 일자";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {
			
			List<AnalyzeDTO> dto = new ArrayList<>();
			LocalDate now = LocalDate.now();  // 2025-2-25
			
			rs.next();
			String date = rs.getString(2);
			
			for (int i = 7; i >= 0; i--) {
				String label = now.minusDays(i).toString(); 
				
				date = date.split(" ")[0];
				
				double data = 0;
				if (label.equals(date)) {
					data = rs.getDouble(1);
					
					boolean hasNext = rs.next();
					if(hasNext) {						
						date = rs.getString(2);
					}
				}				
				dto.add(new AnalyzeDTO(data, label));
			}
			return dto;
		}			
	}
	
	@Override
	public List<AnalyzeDTO> selectAnaRecent12Months(String type) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + ", TO_CHAR(REG_DATE, 'YYYY/MM') AS 월별 "
				+ "FROM PLAY_TIME "
				+ "WHERE TO_CHAR(REG_DATE, 'YYYY/MM') "
				+ "BETWEEN TO_CHAR(SYSTIMESTAMP - INTERVAL '12' MONTH, 'YYYY/MM') AND TO_CHAR(SYSTIMESTAMP, 'YYYY/MM') "
				+ "GROUP BY TO_CHAR(REG_DATE, 'YYYY/MM') "
				+ "ORDER BY 월별";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<AnalyzeDTO> dto = new ArrayList<>();
			while(rs.next()) {
				dto.add(new AnalyzeDTO(rs.getDouble(1) / 1000, rs.getString(2)));
			}
			return dto;
		}	
	}

	@Override
	public List<AnalyzeDTO> selectAnaGroupByAges(String type) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + ", "
				+ "SUBSTR(TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, to_date(substr(ssn, 0, 6), 'RRMMDD'))) / 12), 0, 1) "
				+ "FROM PLAY_TIME p "
				+ "INNER JOIN MEMBERS m "
				+ "ON p.MEMBER_ID = m.MEMBER_ID "
				+ "GROUP BY SUBSTR(TRUNC(TRUNC(MONTHS_BETWEEN(SYSDATE, to_date(substr(ssn, 0, 6), 'RRMMDD'))) / 12), 0, 1) "
				+ "ORDER BY 2";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<AnalyzeDTO> dto = new ArrayList<>();
			while(rs.next()) {
				String label = rs.getString(2) + "0대";
				dto.add(new AnalyzeDTO(rs.getDouble(1), label));
			}
			return dto;
		}	
	}


	@Override
	public double selectTodayAna(String type) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + " FROM PLAY_TIME WHERE TRUNC(REG_DATE) = TRUNC(SYSTIMESTAMP)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			
			return rs.getDouble(1);
		}
	}


	@Override
	public double selectTodayAnaByGameId(String type, int gameId) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + " FROM PLAY_TIME "
				+ "WHERE GAME_ID = ? AND TRUNC(REG_DATE) = TRUNC(SYSTIMESTAMP)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
			
				return rs.getDouble(1);
			}
		}
	}


	@Override
	public double selectAnaByMinusMonth(String type, int month) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + " FROM PLAY_TIME "
				+ "WHERE TO_CHAR(REG_DATE, 'YYYY/MM') = TO_CHAR(SYSTIMESTAMP - NUMTOYMINTERVAL(?, 'MONTH'), 'YYYY/MM')";
				
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, String.valueOf(month));
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getDouble(1);
			}
		}
	}


	@Override
	public List<AnalyzeDTO> selectAnaRecent7days(String type, int gemaId) throws Exception {		
		String sql = "SELECT " + getInsertQuery(type) + ", TRUNC(REG_DATE) AS 일자"
				+ " FROM PLAY_TIME "
				+ "WHERE GAME_ID = ? AND "
				+ "TRUNC(REG_DATE) BETWEEN TRUNC(systimestamp - INTERVAL '7' DAY) AND TRUNC(systimestamp) "
				+ "GROUP BY TRUNC(REG_DATE) "
				+ "ORDER BY 일자";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gemaId);
			
			try(ResultSet rs = pstat.executeQuery();) {
				List<AnalyzeDTO> dto = new ArrayList<>();
				LocalDate now = LocalDate.now();
			
				rs.next();
				String date = rs.getString(2);
			
				for (int i = 7; i >= 0; i--) {
					String label = now.minusDays(i).toString();
				
					date = date.split(" ")[0];
				
					double data = 0;
					if (label.equals(date)) {
						data = rs.getDouble(1);
					
						boolean hasNext = rs.next();
						if(hasNext) {						
							date = rs.getString(2);
						}
					}				
					dto.add(new AnalyzeDTO(data, label));
				}
				return dto;
			}
		}	
	}


	@Override
	public double selectAnaByMinusDateAndGameId(String type, int date, int gameId) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + " FROM PLAY_TIME "
				+ "WHERE GAME_ID = ? AND TRUNC(REG_DATE) = TRUNC(SYSTIMESTAMP - NUMTODSINTERVAL(?, 'DAY'))";
				
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);
			pstat.setString(2, String.valueOf(date));
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getDouble(1);
			}
		}
	}


	@Override
	public double selectAnaByMinusMonthAndGameId(String type, int month, int gameId) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + " FROM PLAY_TIME "
				+ "WHERE GAME_ID = ? AND TO_CHAR(REG_DATE, 'YYYY/MM') = TO_CHAR(SYSTIMESTAMP - NUMTOYMINTERVAL(?, 'MONTH'), 'YYYY/MM')";
				
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);
			pstat.setString(2, String.valueOf(month));
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getDouble(1);
			}
		}
	}

	@Override
	public List<AnalyzeDTO> selectAnaGroupByGender(String type) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + ", SUBSTR(SSN, 8, 1) "
					+ "FROM PLAY_TIME p "
					+ "INNER JOIN MEMBERS m "
					+ "ON p.MEMBER_ID = m.MEMBER_ID "
					+ "GROUP BY SUBSTR(SSN, 8, 1)";
					
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<AnalyzeDTO> dto = new ArrayList<>();
			while(rs.next()) {
				dto.add(AnalyzeDTO.of(rs));
			}
			return dto;
		}		
	}

	@Override
	public List<AnalyzeDTO> selectAnaGroupByGameId(String type) throws Exception {
		String sql = "SELECT " + getInsertQuery(type) + ", GAME_NAME "
				+ "FROM PLAY_TIME P "
				+ "INNER JOIN GAMES G "
				+ "ON P.GAME_ID = G.GAME_ID "
				+ "GROUP BY G.GAME_ID, GAME_NAME";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<AnalyzeDTO> dto = new ArrayList<>();
			while(rs.next()) {
				String label = rs.getString(2);
				dto.add(new AnalyzeDTO(rs.getDouble(1), label));
			}
			return dto;
		}	
	}
	
	private String getInsertQuery(String type) {
		type = type.toLowerCase();
		if(type.equals("sum")) {
			return "SUM(PLAY_TIME)";
		} else if(type.equals("avg")) {
			return "AVG(PLAY_TIME)";
		} else {
			return "COUNT(*)";
		}
	}
}
