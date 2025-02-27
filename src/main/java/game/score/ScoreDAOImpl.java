package game.score;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public enum ScoreDAOImpl implements ScoreDAO {
	INSTANCE;

	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");

		return ds.getConnection();
	}

	@Override
	public int insert(ScoreDTO dto) throws Exception {
		String sql = "INSERT INTO SCORE(SCORE_ID, GAME_ID, MEMBER_ID, SCORE) VALUES (SCORE_ID_SEQ.NEXTVAL, ?, ?, ?)";

		try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getGameId());
			pstat.setInt(2, dto.getMemberId());
			pstat.setInt(3, (int) dto.getScore());

			return pstat.executeUpdate();
		}
	}

	@Override
	public List<ScoreDTO> selectByGameId(int gameId) throws Exception {
		String sql = "SELECT * "
				+ "FROM ( "
				+ "SELECT A.*, ROW_NUMBER() OVER(ORDER BY SCORE DESC) AS RNUM "
				+ "FROM ( "
				+ "SELECT * "
				+ "FROM SCORE S "
				+ "INNER JOIN MEMBERS M "
				+ "ON S.MEMBER_ID = M.MEMBER_ID "
				+ "WHERE GAME_ID = ?) A)"
				+ "WHERE RNUM <= ?";

		try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);
			pstat.setInt(2, (getSelectByGameIdSize(gameId) < 10) ? getSelectByGameIdSize(gameId) : 10);

			try (ResultSet rs = pstat.executeQuery()) {
				List<ScoreDTO> dto = new ArrayList<>();
				while (rs.next()) {
					dto.add(ScoreDTO.of(rs));
				}

				return dto;
			}
		}
	}

	@Override
	public List<ScoreDTO> selectByMemberId(int memberId) throws Exception {
		String sql = "SELECT * FROM SCORE S INNER JOIN MEMBERS M ON S.MEMBER_ID = M.MEMBER_ID WHERE MEMBER_ID = ?";

		try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);

			try (ResultSet rs = pstat.executeQuery()) {
				List<ScoreDTO> dto = new ArrayList<>();
				while (rs.next()) {
					dto.add(ScoreDTO.of(rs));
				}

				return dto;
			}
		}
	}

	@Override
	public List<ScoreDTO> selectByMemberIdAndGameId(int memberId, int gameId) throws Exception {
		String sql = "SELECT * " 
				+ "FROM SCORE S " 
				+ "INNER JOIN MEMBERS M " 
				+ "ON S.MEMBER_ID = M.MEMBER_ID "
				+ "WHERE S.MEMBER_ID = ? AND GAME_ID = ? " 
				+ "ORDER BY SCORE DESC";

		try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);
			pstat.setInt(2, gameId);

			try (ResultSet rs = pstat.executeQuery()) {
				List<ScoreDTO> dto = new ArrayList<>();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				for (int i = 1; i <= columnCount; i++) {
					String columnName = metaData.getColumnName(i);
					System.out.println("컬럼 " + i + ": " + columnName);
				}

				while (rs.next()) {
					dto.add(ScoreDTO.of(rs));
				}

				return dto;
			}
		}
	}

	@Override
	public int getSelectByGameIdSize(int gameId) throws Exception {
		String sql = "SELECT COUNT(*) "
				+ "FROM SCORE S "
				+ "INNER JOIN MEMBERS M "
				+ "ON S.MEMBER_ID = M.MEMBER_ID "
				+ "WHERE GAME_ID = ?";
		
		try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);

			try (ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getInt(1);
			}
		}
	}

}
