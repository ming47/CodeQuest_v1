package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.Context;

import DAO.QnADAO;
import DTO.QnADTO;

public enum QnADAOImpl implements QnADAO {
	INSTACNE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:env/comp/");
		
		return ds.getConnection();
	}

	@Override
	public List<QnADTO> selectAll() throws Exception {
		String sql = "SELECT * FROM QNA Q INNER JOIN USERS U ON Q.MEMBER_ID = U.MEMBER_ID";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			
			List<QnADTO> dtos = new ArrayList<>();
			while(rs.next()) {
				dtos.add(QnADTO.of(rs));
			}
			
			return dtos;
		}
	}

	@Override
	public QnADTO selectById(int id) throws Exception {
		String sql = "SELECT * FROM QNA Q INNER JOIN USERS U ON Q.MEMBER_ID = U.MEMBER_ID WHERE QNA_ID=?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, id);
			
			try(ResultSet rs = pstat.executeQuery();) {
				rs.next();
				
				return QnADTO.of(rs);
			}
		}
	}

	@Override
	public int insert(QnADTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(QnADTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
