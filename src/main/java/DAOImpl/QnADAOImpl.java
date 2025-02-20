package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Common.Statics;

import javax.naming.Context;

import DAO.QnADAO;
import DTO.QnADTO;

public enum QnADAOImpl implements QnADAO {
	INSTACNE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}

	@Override
	public List<QnADTO> selectAll() throws Exception {
		String sql = "SELECT * FROM QNA Q INNER JOIN MEMBERS U ON Q.MEMBER_ID = U.MEMBER_ID";
		
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
		String sql = "SELECT * FROM QNA Q INNER JOIN MEMBERS U ON Q.MEMBER_ID = U.MEMBER_ID WHERE QNA_ID=?";
		
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
		String sql = "INSERT INTO QNA(QNA_ID, MEMBER_ID, CONTENT, RESPONSE_YN) VALUES (QNA_ID_SEQ.NEXTVAL, ?, ?, 'N')";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getMemberId());
			pstat.setString(2, dto.getContents());
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public int update(QnADTO dto) throws Exception {
		String sql = "UPDATE QNA SET CONTENT WHERE QNA_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getQnaId());
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public int deleteById(int dto) throws Exception {
		String sql = "DELETE FROM QNA WHERE QNA_ID=?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto);
			
			int result = pstat.executeUpdate();
			
			if (result == 0) {
				throw new IllegalArgumentException(dto + "에 해당하는 데이터가 없습니다. id를 다시 확인해주세요.");
			} 
			
			return result;
		}
	}

	@Override
	public List<QnADTO> selectByResponseYN(String responseYN) throws Exception {
		String sql = "SELECT * FROM QNA Q INNER JOIN MEMBERS U ON Q.MEMBER_ID = U.MEMBER_ID WHERE RESPONSE_YN=?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, responseYN);
			
			try(ResultSet rs = pstat.executeQuery();) {
				List<QnADTO> dtos = new ArrayList<>();
				while(rs.next()) {
					dtos.add(QnADTO.of(rs));
				}
			
				return dtos;
			}
		}
	}

	@Override
	public List<QnADTO> selectByWriterLike(String writer) throws Exception {
		String sql = "SELECT * FROM QNA Q INNER JOIN MEMBERS U ON Q.MEMBER_ID = U.MEMBER_ID WHERE U.NAME LIKE ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, "%" + writer + "%");
			
			try(ResultSet rs = pstat.executeQuery();) {
				List<QnADTO> dtos = new ArrayList<>();
				while(rs.next()) {
					dtos.add(QnADTO.of(rs));
				}
			
				return dtos;
			}
		}
	}

	@Override
	public List<QnADTO> selectByContentLike(String content) throws Exception {
		String sql = "SELECT * FROM QNA Q INNER JOIN MEMBERS U ON Q.MEMBER_ID = U.MEMBER_ID WHERE Q.CONTENT LIKE ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, "%" + content + "%");
			
			try(ResultSet rs = pstat.executeQuery();) {
				List<QnADTO> dtos = new ArrayList<>();
				while(rs.next()) {
					dtos.add(QnADTO.of(rs));
				}
			
				return dtos;
			}
		}
	}

	@Override
	public void updateResponseYNById(int id, String responseYN) throws Exception {
		String sql = "UPDATE QNA SET RESPONSE_YN = ? WHERE QNA_ID=?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, responseYN);
			pstat.setInt(2, id);
			
			int result = pstat.executeUpdate();
			
			if (result == 0) {
				throw new IllegalArgumentException(id + "에 해당하는 데이터가 없습니다. id를 다시 확인해주세요.");
			} 
		}
	}

	@Override
	public List<QnADTO> selectByMemberId(int memberId) throws Exception {
		String sql = "SELECT * FROM QNA WHERE MEMBER_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, memberId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				List<QnADTO> dtos = new ArrayList<>();
				while(rs.next()) {
					dtos.add(QnADTO.of(rs));
				}
			
				return dtos;
			}
		}
	}

	@Override
	public int getSize() throws Exception {
		String sql = "SELECT COUNT(*) FROM QNA";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			
			return rs.getInt(1);
		}
	}

	@Override
	public List<QnADTO> selectAll(int page) throws Exception {
		String sql = "select * "
				+ "from "
				+ "(select a.*, ROW_NUMBER() OVER (ORDER BY qna_id DESC) AS rnum "
				+ "from "
				+ "(select * "
				+ "from qna q "
				+ "inner join members m "
				+ "on q.member_id = m.member_id) a) "
				+ "WHERE rnum BETWEEN ? and ?";
		
		int startIndex = (page - 1) * Statics.recordCountPerPage + 1;
		int endIndex = startIndex + Statics.recordCountPerPage - 1;

		endIndex = (endIndex > getSize()) ? getSize() : endIndex;

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, startIndex);
			pstat.setInt(2, endIndex);
			
			try(ResultSet rs = pstat.executeQuery()) {				
				List<QnADTO> dtos = new ArrayList<>();
				while(rs.next()) {
					dtos.add(QnADTO.of(rs));
				}
				
				return dtos;
			}
		}
			
	}

	@Override
	public List<QnADTO> selectByResponseYN(String responseYN, int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnADTO> selectByWriterLike(String writer, int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnADTO> selectByContentLike(String content, int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
