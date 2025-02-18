package DAO;

import java.util.List;

import DTO.BoardDTO;

//public interface BoardDAO {
//	List<BoardDTO> selectAll() throws Exception;
//	BoardDTO selectById(int id) throws Exception;
//	int insert(BoardDTO dto) throws Exception;
//	int update(BoardDTO dto) throws Exception;
//	int deleteById(int dto) throws Exception;
//	
//	int getNextVal() throws Exception;	// insert전에 다음 시퀸스값을 가져옴
//}


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	private static BoardDAO INSTANCE;

	public synchronized static BoardDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BoardDAO();
		}
		return INSTANCE;
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	public int getBoardId() throws Exception {// board 테이블에 시퀀스 불러오기
		String sql = "select board_seq.nextval from dual";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		}

	}

	public void createContents(BoardDTO dto) throws Exception {
		String sql = "insert into board (board_id, title, name, contents, reg_date, view_count,reply_count) values (?, ?, ?, ?, sysdate, 0,0)";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, dto.getBoard_id());
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getWriter());
			pstat.setString(4, dto.getContents());
			pstat.executeUpdate();
		}
	}

	//
	public List<BoardDTO> selectAll() throws Exception {
		String sql = "select * from board";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {

			ResultSet rs = pstat.executeQuery();

			List<BoardDTO> dto = new ArrayList<BoardDTO>();

			while (rs.next()) {

				int id = rs.getInt(1);
				String writer = rs.getString(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				Timestamp reg_date = rs.getTimestamp(5);
				int viewCount = rs.getInt(6);
				int replyCount = rs.getInt(7);
				dto.add(new BoardDTO(id, writer, title, reg_date, contents, viewCount,replyCount));
			}
			rs.close();
			return dto;
		}

	}// 게시물 전체 가져오기

	public List<BoardDTO> selectFromto(int start, int end) throws Exception {
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
					list.add(new BoardDTO(board_id, title, writer, reg_date, contents, viewCount, replyCount));
				}
				return list;
			}
		}
	}// 게시물 가져올 범위

	public BoardDTO selectById(int seq) throws Exception {
		String sql = "select * from board where seq = ?";
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
	}
	
	public int getSize() throws Exception {
	    List<BoardDTO> list = selectAll(); 
	    return list.size(); // 게시글 개수
	}
	
	
	public int updateById(BoardDTO dto) throws Exception {
		String sql = "update board set title =?, contents=? where seq = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContents());
			pstat.setInt(3, dto.getBoard_id());

			return pstat.executeUpdate();//리턴값이 0이면 데이터 변경x  
		}
	}

	public int delete(int seq) throws Exception {
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

	
	public int getRecordTotalCount() throws Exception {
		String sql = "select count(*) from board";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery();) {
			rs.next();
			return rs.getInt(1);
		}
			// 게시물 세는 메서드
	}
	


}