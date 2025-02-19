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

import DAO.PlaytimeDAO;
import DTO.MemberDTO;
import DTO.PlaytimeDTO;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumByPlayTime() throws Exception {
		String sql = "SELECT SUM(PLAY_TIME) FROM PLAY_TIME";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			
			return rs.getInt("SUM(PLAY_TIME)");
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
	public int sumPlayTimeByMemberIds(List<MemberDTO> memberDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
