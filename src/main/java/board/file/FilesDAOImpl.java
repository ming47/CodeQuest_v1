package board.file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public enum FilesDAOImpl implements FilesDAO {
	INSTANCE;

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public FilesDTO selectById(int id) throws Exception {
		String sql = "select * from files where board_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setInt(1, id);
			try (ResultSet rs = pstat.executeQuery();) {
				FilesDTO fdto = null;
				while (rs.next()) {
					int fileId = rs.getInt("file_id");
					String oriName = rs.getString("oriname");
					String sysName = rs.getString("sysname");
					int boardId = rs.getInt("board_id");
					fdto = new FilesDTO(fileId, boardId, oriName, sysName);
				}
				return fdto;

			}
		}
	}

	@Override
	public int insert(FilesDTO dto) throws Exception {
		String sql = "insert into files(file_id,original_name,system_name, board_id) values(file_id_seq.nextval, ?, ?, ?)";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setString(1, dto.getOriname());
			pstat.setString(2, dto.getSysname());
			pstat.setInt(3, dto.getBoardId());
			return pstat.executeUpdate();
		}
	}

	@Override
	public List<FilesDTO> selectByBoardId(int boardId) throws Exception {
		String sql = "select * from files where board_id=?";

		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
			pstat.setInt(1, boardId);

			ResultSet rs = pstat.executeQuery();
			List<FilesDTO> dto = new ArrayList<FilesDTO>();

			while (rs.next()) {
				int fileId = rs.getInt("file_Id");
				String oriName = rs.getString("original_name");
				String sysName = rs.getString("system_name");
				int board_Id = rs.getInt("board_Id");

				FilesDTO fdto = new FilesDTO(fileId, board_Id, oriName, sysName);
				dto.add(fdto);
			}

			return dto;
		}
	}
}
