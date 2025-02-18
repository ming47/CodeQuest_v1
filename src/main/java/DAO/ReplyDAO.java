package DAO;

//public interface ReplyDAO {
//	List<ReplyDTO> selectAll() throws Exception;
//	ReplyDTO selectById(int id) throws Exception;
//	int insert(ReplyDTO dto) throws Exception;
//	int update(ReplyDTO dto) throws Exception;
//	int deleteById(int dto) throws Exception;
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

import DTO.ReplyDTO;

public class ReplyDAO {
	private static ReplyDAO INSTANCE;

	public synchronized static ReplyDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ReplyDAO();
		}
		return INSTANCE;
	}

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}


		public void createComment(ReplyDTO dto) throws Exception {
			String sql = "insert into reply values(reply_id_seq.nextval, ?, ?,sysdate, ?)";
			try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
		
				pstat.setString(1, dto.getName());
				pstat.setString(2, dto.getContents());
				pstat.setInt(3, dto.getBoardId());
				pstat.executeUpdate();
			}
		}

		public List<ReplyDTO> SelectAll(int target) throws Exception {
			List<ReplyDTO> list;
			
			String sql = "select * from reply where boardId =?";
			try (Connection con = this.getConnection();
					PreparedStatement pstat = con.prepareStatement(sql);
				)
				 {pstat.setInt(1,target);
				 
				 
				try(ResultSet rs = pstat.executeQuery()) {
				   list = new ArrayList<>();
				   while (rs.next()) {
					int reply_id = rs.getInt("id");
					String name = rs.getString("writer");
					int board_id = rs.getInt("board_id");
					String contents = rs.getString("contents");
					Timestamp regdate = rs.getTimestamp("reg_date");
				
					list.add(new ReplyDTO(reply_id, name,board_id, contents, regdate));
				   }
				}
			}
			return list;

		}
		
		public void deleteByid(int replyid)throws Exception{
			String sql = "delete reply where reply_id=?";
			try (Connection con = this.getConnection(); 
				PreparedStatement pstat = con.prepareStatement(sql)) {

				pstat.setInt(1, replyid);
				pstat.executeUpdate();
			}}
		
		
			public void updateByid(int replyid, String contents)throws Exception{
				String sql = "update reply set contents =? where reply_id=?";
				try (Connection con = this.getConnection(); 
					PreparedStatement pstat = con.prepareStatement(sql)) {

					pstat.setString(1, contents); 
			        pstat.setInt(2, replyid); 
					pstat.executeUpdate();
				}
		}

	}