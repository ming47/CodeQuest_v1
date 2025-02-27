package board.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import board.board.BoardDTO;
import utils.Statics;

public enum ReplyDAOImpl implements ReplyDAO {
	INSTANCE;

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public List<ReplyDTO> selectAll() throws Exception {
		String sql = "select * from Reply r inser join on members m on r.member_id = m.member_id";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			ResultSet rs = pstat.executeQuery();

			List<ReplyDTO> list = new ArrayList<ReplyDTO>();

			while (rs.next()) {
				int reply_id = rs.getInt("id");
				int member_id = rs.getInt("memberId");
				int board_id = rs.getInt("board_id");
				String contents = rs.getString("contents");
				Timestamp regdate = rs.getTimestamp("reg_date");
				String writer = rs.getString(rs.getString("nickName"));
				list.add(new ReplyDTO(reply_id, member_id, board_id, contents, regdate, writer));
			}
			rs.close();
			return list;
		}
	}

	@Override
	public ReplyDTO selectById(int id) throws Exception {

		String sql = "select * from Reply r inner join members m on r.member_id = m.member_id "
				+ "where reply_id=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, id); // 부모 게시글 ID(메서드 id라고 적혀있었는데 헷갈릴것같아서 물어보고 boardid로 변경)

			try (ResultSet rs = pstat.executeQuery();) {
				ReplyDTO rdto = null;
				while (rs.next()) {
					int replyId = rs.getInt("reply_Id");
					int memberId = rs.getInt("member_Id");
					String contents = rs.getString("contents");
					Timestamp regDate = rs.getTimestamp("reg_Date");
					int boardId = rs.getInt("board_Id");

					String writer = rs.getString("nickName");
					rdto = new ReplyDTO(replyId,memberId,boardId,contents ,regDate, writer);

				}
		        return rdto;
			}
		}
	}

	@Override
	public int insert(ReplyDTO dto) throws Exception { // DB에 저장
		String sql = "insert into reply(reply_id,member_id,contents,reg_date,board_id) values(reply_id_seq.nextval, ?, ?,sysdate, ?)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, dto.getMemberId());

			pstat.setString(2, dto.getContents());
			pstat.setInt(3, dto.getBoardId());
			return pstat.executeUpdate();
		}
	}

	@Override
	public int update(ReplyDTO dto) throws Exception {
		String sql = "update reply set contents =? where reply_id=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setString(1, dto.getContents());
			pstat.setInt(2, dto.getReplyId());

			return pstat.executeUpdate();
		}
	}
//	public int getSize() throws Exception {
//	    String sql = "SELECT COUNT(*) FROM reply";
//	    try (Connection con = this.getConnection();
//	         PreparedStatement pstat = con.prepareStatement(sql);
//	         ResultSet rs = pstat.executeQuery()) {
//	        
//	        if (rs.next()) {
//	            return rs.getInt(1);  // count 값 반환
//	        } else {
//	            return 0;  // 조회된 값이 없으면 기본값 0 반환
//	        }
//	        
//	    } catch (Exception e) {
//	        e.printStackTrace();  // 예외 로깅
//	        return 0;  // 오류 발생 시 0 반환
//	    }
//	}


	@Override
	public int deleteById(int dto) throws Exception {
		String sql = "delete reply where reply_id=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, dto);
			return pstat.executeUpdate();
		}
	}

	@Override
	public List<ReplyDTO> selectByBoardId(int boardId) throws Exception {	//댓글 출력
		String sql = "select * from Reply r inner join members m on r.member_id = m.member_id "
				+ "where board_id = ? order by r.reg_date desc";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setInt(1, boardId);
			try(ResultSet rs = pstat.executeQuery();){
				List<ReplyDTO> list = new ArrayList<>();
				while(rs.next()) {
					ReplyDTO dto = new ReplyDTO();
					dto.setReplyId(rs.getInt("reply_Id"));
					dto.setMemberId(rs.getInt("member_Id"));;
					dto.setBoardId(rs.getInt("board_Id"));
					dto.setContents(rs.getString("contents"));
					dto.setRegDate(rs.getTimestamp("reg_Date"));
					dto.setWriter(rs.getString("nickName"));
					list.add(dto);
				}
				return list;
			}
		}
	}

	@Override
	public List<ReplyDTO> selectByBoardId(int boardId, int page) throws Exception {
		String sql = "SELECT * "
				+ "FROM ( "
				+ "SELECT A.*, ROW_NUMBER() OVER(ORDER BY REPLY_ID DESC) AS RNUM "
				+ "FROM ( "
				+ "SELECT * "
				+ "FROM REPLY R "
				+ "INNER JOIN MEMBERS M "
				+ "ON R.MEMBER_ID = M.MEMBER_ID "
				+ "WHERE BOARD_ID = ?) A) "
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		int startIndex = (page - 1) * Statics.recordCountPerPage + 1;
		int endIndex = startIndex + Statics.recordCountPerPage - 1;

		endIndex = (endIndex > getSelectByBoardIdSize(boardId)) ? getSelectByBoardIdSize(boardId) : endIndex;
		
		try (Connection con = getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, boardId);
			pstat.setInt(2, startIndex);
			pstat.setInt(3, endIndex);

			try(ResultSet rs = pstat.executeQuery();){
				List<ReplyDTO> list = new ArrayList<>();
				while(rs.next()) {
					ReplyDTO dto = new ReplyDTO();
					dto.setReplyId(rs.getInt("reply_Id"));
					dto.setMemberId(rs.getInt("member_Id"));;
					dto.setBoardId(rs.getInt("board_Id"));
					dto.setContents(rs.getString("contents"));
					dto.setRegDate(rs.getTimestamp("reg_Date"));
					dto.setWriter(rs.getString("nickName"));
					list.add(dto);
				}
				return list;
			}
		}
	}

	@Override
	public int getSelectByBoardIdSize(int boardId) throws Exception {
		String sql = "SELECT COUNT(*) FROM REPLY R INNER JOIN MEMBERS M ON R.MEMBER_ID = M.MEMBER_ID WHERE BOARD_ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setInt(1, boardId);
			
			try(ResultSet rs = pstat.executeQuery()) {
				rs.next();
				
				return rs.getInt(1);
			}
		}
	}
	
}
