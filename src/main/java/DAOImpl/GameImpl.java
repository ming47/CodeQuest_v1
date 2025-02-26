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

import DAO.GameDAO;
import DTO.GameDTO;

public enum GameImpl implements GameDAO {
	INSTANCE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}

	@Override
	public List<GameDTO> selectAll() throws Exception {
		String sql = "SELECT * FROM GAMEs";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<GameDTO> dto = new ArrayList<>();
			while(rs.next()) {
				dto.add(GameDTO.of(rs));
			}
			
			return dto;
		}
	}

	@Override
	public GameDTO selectById(int id) throws Exception {
		String sql = "SELECT * FROM GAMEs WHERE GAME_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, id);
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return GameDTO.of(rs);
			}
		}		
	}

}
