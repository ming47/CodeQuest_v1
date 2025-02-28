package board.viewcount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public enum ViewCountDAOImpl implements ViewCountDAO {
	INSTANCE;
	
	private Connection getConnection() throws NamingException, SQLException {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		
		return ds.getConnection();
	}

	@Override
	public int insertMember(ViewCountDTO dto) throws Exception {
		String sql = "INSERT INTO VIEW_COUNT(VIEW_COUNT_ID, BOARD_ID, MEMBER_ID) VALUES (VIEW_COUNT_ID_SEQ.NEXTVAL, ?, ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareCall(sql);) {
			pstat.setInt(1, dto.getBoardId());
			pstat.setInt(2, dto.getMemberId());
			
			return pstat.executeUpdate();
		}
	}
	
	@Override
	public List<ViewCountDTO> selectRecentByMemberId(int memberId) throws Exception { //마이페이지 최근에 본 게시글 6개 가져오기
	    String sql = "SELECT * FROM ( "
	               + "    SELECT * FROM board b "
	               + "    INNER JOIN ( "
	               + "        SELECT board_id, COUNT(*) AS cnt, MAX(reg_date) AS latest_date "
	               + "        FROM view_count "
	               + "        WHERE member_id = ? "
	               + "        GROUP BY board_id "
	               + "    ) v ON b.board_id = v.board_id "
	               + "    ORDER BY v.latest_date DESC "
	               + ") WHERE ROWNUM <= 6";
	    
	    try (Connection con = getConnection();
	         PreparedStatement pstat = con.prepareStatement(sql)) {
	        pstat.setInt(1, memberId);
	        
	        try (ResultSet rs = pstat.executeQuery()) {
	            List<ViewCountDTO> list = new ArrayList<>();
	            while (rs.next()) {
	                int boardId = rs.getInt("board_id");
	                String title = rs.getString("title");
	                int reply_count = rs.getInt("reply_count");
	                Timestamp latestDate = rs.getTimestamp("latest_date");
	                
	                ViewCountDTO dto = new ViewCountDTO(boardId, title, reply_count, latestDate);
	                list.add(dto);
	            }
	            return list;
	        }
	    }
	}

	@Override
	public int insert(ViewCountDTO dto) throws Exception {
		String sql = "INSERT INTO VIEW_COUNT(VIEW_COUNT_ID, BOARD_ID) VALUES (VIEW_COUNT_ID_SEQ.NEXTVAL, ?)";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareCall(sql);) {
			pstat.setInt(1, dto.getBoardId());
			
			return pstat.executeUpdate();
		}
	}
}