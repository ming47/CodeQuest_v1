package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import DAO.QnAReplyDAO;
import DTO.QnAReplyDTO;

public enum QnAReplyDAOImpl implements QnAReplyDAO {
	INSTANCE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}


	@Override
	public List<QnAReplyDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QnAReplyDTO selectById(int id) throws Exception {
		return null;
	}

	@Override
	public int insert(QnAReplyDTO dto) throws Exception {
		String sql = "INSERT INTO QNA_REPLY(QNA_REPLY_ID, QNA_ID, MEMBER_ID, CONTEXT) "
				+ "VALUES(QNA_REPLY_ID_SEQ.NEXTVAL, ?, ? ,?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			
		}
		return 0;
	}

	@Override
	public int update(QnAReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public QnAReplyDTO selectByQnAId(int qnaId) throws Exception {
		String sql = "SELECT * FROM QNA_REPLY WHRER QNA_ID=?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, qnaId);
			try(ResultSet rs = pstat.executeQuery()) {
				
				return QnAReplyDTO.of(rs);
			}
		}
	}

}
