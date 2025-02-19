package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.ScoreDAO;
import DTO.ScoreDTO;

public enum ScoreDAOImpl implements ScoreDAO {
	INSTANCE;

	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}
	
	@Override
	public List<ScoreDTO> selectAll() throws Exception {
		String sql = "SELECT * FROM SCORE";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<ScoreDTO> dto = new ArrayList<>();
			while(rs.next()) {
				dto.add(null);
			}
			
			return dto;
		}
	}

	@Override
	public ScoreDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ScoreDTO dto) throws Exception {
		String sql = "INSERT INTO SCORE(SCORE_ID, GAME_ID, MEMBER_ID, SCORE) VALUES (SCORE_ID_SEQ.NEXTVAL, ?, ?, ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getGameId());
			pstat.setInt(2, dto.getMemberId());
			pstat.setInt(3, (int) dto.getScore());
		
			return pstat.executeUpdate();
		}
	}

	@Override
	public int deleteById(int dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreDTO> selectByGameId(int gameId) throws Exception {
		String sql = "";
		if (gameId < 800004) {			
			sql = "SELECT * "
					+ "FROM SCORE S "
					+ "INNER JOIN MEMBERS M "
					+ "ON S.MEMBER_ID = M.MEMBER_ID "
					+ "WHERE GAME_ID = ? "
					+ "ORDER BY SCORE DESC";
		} else {
			sql = "SELECT * "
					+ "FROM MEMBERS M "
					+ "INNER JOIN "
					+ "(SELECT AVG(SCORE) AS \"SCORE\", MEMBER_ID, GAME_ID "
					+ "FROM SCORE "
					+ "WHERE GAME_ID = 800005 "
					+ 	"AND MEMBER_ID IN ( "
					+ 	"SELECT MEMBER_ID "
					+ 	"FROM SCORE "
					+ 	"WHERE GAME_ID = ? "
					+ 	"GROUP BY MEMBER_ID, GAME_ID "
					+ 	"HAVING COUNT(*) > 10) "
					+ "GROUP BY MEMBER_ID, GAME_ID) S "
					+ "ON M.MEMBER_ID = S.MEMBER_ID "
					+ "ORDER BY \"SCORE\" DESC";
		}
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				List<ScoreDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(ScoreDTO.of(rs));
				}
				
				return dto;
			}
		}
	}

	@Override
	public List<ScoreDTO> selectByMemberId(int memberId) throws Exception {
		String sql = "SELECT * FROM SCORE WHERE MEMBER_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				List<ScoreDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(null);
				}
				
				return dto;
			}
		}
	}

	@Override
	public List<ScoreDTO> selectByMemberIdAndGameId(int memberId, int gameId) throws Exception {
		String sql = "SELECT * FROM SCORE WHERE GAME_ID = ? AND MEMBER_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, gameId);
			pstat.setInt(2, memberId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				List<ScoreDTO> dto = new ArrayList<>();
				while(rs.next()) {
					dto.add(null);
				}
				
				return dto;
			}
		}
	}

}
