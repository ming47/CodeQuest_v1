package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.ViewCountDAO;
import DTO.ViewCountDTO;

public enum ViewCountDAOImpl implements ViewCountDAO {
	INSTANCE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}

	@Override
	public List<ViewCountDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewCountDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ViewCountDTO dto) throws Exception {
		String sql = "INSERT INTO VIEW_COUNT(VIEW_COUNT_ID, BOARD_ID, MEMBER_ID) VALUES (VIEW_COUNT_ID_SEQ.NEXTVAL, ?, ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareCall(sql);) {
			pstat.setInt(1, dto.getBoardId());
			pstat.setInt(2, dto.getMemberId());
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public int deleteById(int dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByBoardId(int boardId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
