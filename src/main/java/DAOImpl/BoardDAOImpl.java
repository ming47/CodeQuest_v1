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

import DAO.BoardDAO;
import DTO.BoardDTO;


public enum BoardDAOImpl implements BoardDAO {
	INSTANCE;

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	//
	public List<BoardDTO> selectAll() throws Exception {
		String sql = "select * from board b inner join members m on b.member_id = m.member_id";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			ResultSet rs = pstat.executeQuery();

			List<BoardDTO> dto = new ArrayList<BoardDTO>();

			while (rs.next()) {

				int id = rs.getInt("board_id");
				int memberId = rs.getInt("member_id");
				String writer = rs.getString("nickname");
				String title = rs.getString("title");
				String contents = rs.getString("contents");
				Timestamp regDate = rs.getTimestamp("reg_date");
				int viewCount = rs.getInt("view_count");
				int replyCount = rs.getInt("reply_count");
				String role = rs.getString("role");
				
				dto.add(new BoardDTO(id, memberId, title, regDate, contents, viewCount,replyCount, writer, role));
			}
			rs.close();
			return dto;
		}

	}// 게시물 전체 가져오기

	public List<BoardDTO> selectFromto(int start, int end) throws Exception {
		/*
		String sql = "SELECT * FROM ( SELECT board.*, ROW_NUMBER() OVER (ORDER BY board_id DESC) AS rnum FROM board ) WHERE rnum BETWEEN ? AND ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, start);
			pstat.setInt(2, end);
			try (ResultSet rs = pstat.executeQuery();) {
				List<BoardDTO> list = new ArrayList<>();
				while (rs.next()) {
					int board_id = rs.getInt("board_id");
					String title = rs.getString("title");
					String writer = rs.getString("name");
					Timestamp reg_date = rs.getTimestamp("reg_date");
					String contents = rs.getString("contents");
					int viewCount = rs.getInt("view_count");
					int replyCount = rs.getInt("reply_count");
					
					\
					list.add(new BoardDTO(board_id, title, writer, reg_date, contents, viewCount, replyCount));
				}
				return list;
			}
		}
		*/
		
		return null;
	}// 게시물 가져올 범위

	public BoardDTO selectById(int seq) throws Exception {
		String sql = "select * from board where seq = ?";
		/*
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			pstat.setInt(1, seq);

			BoardDTO dto = null;

			try (ResultSet rs = pstat.executeQuery()) {
				if (rs.next()) {
					int board_id = rs.getInt("seq");
					String title = rs.getString("title");
					String writer = rs.getString("writer");

					Timestamp reg_date = rs.getTimestamp("reg_date");
					String contents = rs.getString("contents");
					int view = rs.getInt("view");
					int reply = rs.getInt("reply");

					dto = new BoardDTO(board_id, title, writer, reg_date, contents, view,reply);
				}
				return dto;
			}
		}
		*/
		return null;
	}
	
	public int getSize() throws Exception {
		String sql = "select count(*) from board";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		}
	}

	public int deleteById(int seq) throws Exception {
		String sql = "delete from board WHERE seq = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setInt(1, seq);
			return pstat.executeUpdate();//리턴값이 0이면 데이터 변경x  
		}
	}

	
	public void viewCount(int boardId) throws Exception {
		String sql = "update board set view_count = view_count + 1 where board_Id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, boardId);
			pstat.executeUpdate();
		}
	}// 조회수 증가 메서드

	@Override
	public int insert(BoardDTO dto) throws Exception {
		String sql = "insert into board (board_id, title, name, contents, reg_date, view_count,reply_count) values (?, ?, ?, ?, sysdate, 0,0)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getBoardId());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getWriter());
			pstat.setString(4, dto.getContents());
			
			return pstat.executeUpdate();
		}
	}

	@Override
	public int update(BoardDTO dto) throws Exception {
		String sql = "update board set title =?, contents=? where seq = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContents());
			pstat.setInt(3, dto.getBoardId());

			return pstat.executeUpdate();//리턴값이 0이면 데이터 변경x  
		}
	}

	@Override
	public int getNextVal() throws Exception {
		String sql = "select board_seq.nextval from dual";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		}
	}
	


}


