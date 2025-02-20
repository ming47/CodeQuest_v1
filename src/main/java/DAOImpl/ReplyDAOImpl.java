package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DAO.ReplyDAO;
import DTO.ReplyDTO;

public enum ReplyDAOImpl implements ReplyDAO {
	INSTANCE;

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}
	
	@Override
	public List<ReplyDTO>selectAll()throws Exception {
		String sql = "select * from reply";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			ResultSet rs = pstat.executeQuery();

			List<ReplyDTO> list = new ArrayList<ReplyDTO>();

			while (rs.next()) {
					int reply_id = rs.getInt("id");
					String name = rs.getString("writer");
					int board_id = rs.getInt("board_id");
					String contents = rs.getString("contents");
					Timestamp regdate = rs.getTimestamp("reg_date");

					list.add(new ReplyDTO(reply_id, name, board_id, contents, regdate));
				}
			rs.close();
			return list;
			}
		}

	@Override
	public ReplyDTO selectById(int id) throws Exception {
		String sql = "select * from reply where board_id=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, id); // 부모 게시글 ID(메서드 id라고 적혀있었는데 헷갈릴것같아서 물어보고 boardid로 변경)
		
			try (ResultSet rs = pstat.executeQuery();) {
				ReplyDTO rdto = null;
				while (rs.next()) {
					int replyId = rs.getInt("reply_Id");
					String name = rs.getString("name");
					String contents = rs.getString("contents");
					Timestamp regDate = rs.getTimestamp("reg_Date");
					int boardId = rs.getInt("board_Id");
					rdto = new ReplyDTO(replyId,name,boardId,contents ,regDate);
				}
				return rdto;
			}
		}
	}

	@Override
	public int insert(ReplyDTO dto) throws Exception {
		String sql = "insert into reply values(reply_id_seq.nextval, ?, ?,sysdate, ?)";
		
		
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setString(1, dto.getName());
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

	@Override
	public int deleteById(int dto) throws Exception {
		String sql = "delete reply where reply_id=?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {

			pstat.setInt(1, dto);
			return pstat.executeUpdate();
		}
	}//ap만들어진 메서드가 dto로 지정되어있는데 나중에 수정하자고 하셔서 변수이름 dto로 사용
	//댓글 아이디를 의미함 

	@Override
	public List<ReplyDTO> selectByBoardId(int boardId) throws Exception {	//댓글 출력
		String sql = "select * from Reply where = board_id = ? order by reg_date desc";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setInt(1, boardId);
			try(ResultSet rs = pstat.executeQuery()){
				List<ReplyDTO> list = new ArrayList<>();
				while(rs.next()) {
					ReplyDTO dto = new ReplyDTO();
					dto.setReplyId(rs.getInt("replyId"));
					dto.setName(rs.getString("name"));
					dto.setBoardId(rs.getInt("boardId"));
					dto.setContents(rs.getString("contents"));
					dto.setRegDate(rs.getTimestamp("regDate"));
					list.add(dto);
				}
				return list;
			}
		}
	}

}
