package service.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.naming.Context;

import game.playtime.PlaytimeDTO;
import utils.Statics;

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
		String sql = "UPDATE QNA SET CONTENT = ? WHERE QNA_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getContents());
			pstat.setInt(2, dto.getQnaId());

			int result = pstat.executeUpdate();
			
			if (result == 0) {
				throw new IllegalArgumentException(dto + "에 해당하는 데이터가 없습니다. id를 다시 확인해주세요.");
			} 
			
			return result;
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
	public int getNextVal() throws Exception {
		String sql = "SELECT QNA_ID_SEQ.NEXTVAL FROM DUAL";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			
			return rs.getInt(1);
		}
	}

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
		String sql = "SELECT * "
				+ "FROM ( "
				+ "SELECT A.*, ROW_NUMBER() OVER(ORDER BY QNA_ID DESC) AS RNUM "
				+ "FROM ( "
				+ "SELECT * "
				+ "FROM QNA Q "
				+ "INNER JOIN MEMBERS M "
				+ "ON Q.MEMBER_ID = M.MEMBER_ID "
				+ "WHERE RESPONSE_YN = ? ) A )"
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		int startIndex = (page - 1) * Statics.recordCountPerPage + 1;
		int endIndex = startIndex + Statics.recordCountPerPage - 1;

		endIndex = (endIndex > getSize(selectByResponseYN(responseYN))) ? 
				getSize(selectByResponseYN(responseYN)) : endIndex;

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, responseYN);
			pstat.setInt(2, startIndex);
			pstat.setInt(3, endIndex);
			
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
	public List<QnADTO> selectByWriterLike(String writer, int page) throws Exception {
		String sql = "SELECT * "
				+ "FROM ( "
				+ "SELECT A.*, ROW_NUMBER() OVER(ORDER BY QNA_ID DESC) AS RNUM "
				+ "FROM ( "
				+ "SELECT * "
				+ "FROM QNA Q "
				+ "INNER JOIN MEMBERS M "
				+ "ON Q.MEMBER_ID = M.MEMBER_ID "
				+ "WHERE NICKNAME LIKE ? ) A )"
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		int startIndex = (page - 1) * Statics.recordCountPerPage + 1;
		int endIndex = startIndex + Statics.recordCountPerPage - 1;

		endIndex = (endIndex > getSize(selectByWriterLike(writer))) ? 
				getSize(selectByWriterLike(writer)) : endIndex;

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, "%" + writer + "%");
			pstat.setInt(2, startIndex);
			pstat.setInt(3, endIndex);
			
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
	public List<QnADTO> selectByContentLike(String content, int page) throws Exception {
		String sql = "SELECT * "
				+ "FROM ( "
				+ "SELECT A.*, ROW_NUMBER() OVER(ORDER BY QNA_ID DESC) AS RNUM "
				+ "FROM ( "
				+ "SELECT * "
				+ "FROM QNA Q "
				+ "INNER JOIN MEMBERS M "
				+ "ON Q.MEMBER_ID = M.MEMBER_ID "
				+ "WHERE CONTENT LIKE ? ) A )"
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		int startIndex = (page - 1) * Statics.recordCountPerPage + 1;
		int endIndex = startIndex + Statics.recordCountPerPage - 1;

		endIndex = (endIndex > getSize(selectByContentLike(content))) ? 
				getSize(selectByContentLike(content)) : endIndex;

		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, "%" + content + "%");
			pstat.setInt(2, startIndex);
			pstat.setInt(3, endIndex);
			
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
	public List<QnADTO> selectRecentByMemberId(int inputMemberId) throws Exception { //마이페이지에서 작성한 문의내역
		String sql = "SELECT * FROM ( "
		           + "    SELECT q.qna_id, q.member_id, q.content, q.reg_date, q.response_yn, m.name "
		           + "      FROM qna q "
		           + "      JOIN members m ON q.member_id = m.member_id "
		           + "     WHERE q.member_id = ? "
		           + "     ORDER BY q.reg_date DESC "
		           + ") WHERE ROWNUM <= 5";
	    
	    try (Connection con = getConnection();
	         PreparedStatement pstat = con.prepareStatement(sql)) {
	        pstat.setInt(1, inputMemberId);
	        try (ResultSet rs = pstat.executeQuery()) {
	            List<QnADTO> list = new ArrayList<>();
	            while (rs.next()) {
	            	int qnaId = rs.getInt("qna_id");
	            	String contents = rs.getString("content");
	            	Timestamp regDate = rs.getTimestamp("reg_date");
	            	String responseYn = rs.getString("response_yn");
	            	QnADTO dto = new QnADTO(qnaId,contents,regDate,responseYn);
	            	list.add(dto);
	            }
	            return list;
	        }
	    }
	}

	@Override
	public int getSize(List<QnADTO> dto) {
		return dto.size();
	}
}
