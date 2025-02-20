package DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import DAO.FilesDAO;
import DTO.FilesDTO;

public enum FilesDAOImpl implements FilesDAO {
	INSTANCE;

	private Connection getConnection() throws Exception {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();
	}

	@Override
	public List<FilesDTO> selectAll() throws Exception {
		String sql = "select * from Files";
		try (Connection con = this.getConnection();
				PreparedStatement pstat = con.prepareStatement(sql);
				ResultSet rs = pstat.executeQuery()) {

			List<FilesDTO> dtoList = new ArrayList<>();

			while (rs.next()) {

				int fileId = rs.getInt(1);
				int boardId = rs.getInt(2);
				String oriname = rs.getString(3);
				String sysname = rs.getString(4);

				FilesDTO dto = new FilesDTO(fileId, boardId, oriname, sysname);

				dtoList.add(dto);
			}

			return dtoList;
		}
	}

	@Override
	public FilesDTO selectById(int id) throws Exception {
		String sql = "select * from files where board_id = ?";
		try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
			pstat.setInt(1, id);
			try (ResultSet rs = pstat.executeQuery();) {
				FilesDTO fdto = null;
				while (rs.next()) {
					int fileId = rs.getInt("id");
					String oriName = rs.getString("oriname");
					String sysName = rs.getString("sysname");
					int boardId = rs.getInt("parent_seq");
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
	public int update(FilesDTO dto) throws Exception {

		return 0;
	}

	@Override
	public int deleteById(int dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
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

	public int getSize() throws Exception {
		List<FilesDTO> list = selectAll();
		return list.size();
	}
}

//public int insert(FilesDTO dto) throws Exception {
//	String sql = "insert into files values(files_seq.nextval, ?, ?, ?)";
//	try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql);) {
//		pstat.setString(1, dto.getOriginName());
//		pstat.setString(2, dto.getSysName());
//		pstat.setInt(3, dto.getParent_seq());
//		return pstat.executeUpdate();
//	}
//}
//
//public List<FilesDTO> findById(int boardId) throws Exception {
//	String sql = "select * from files where board_id = ?";
//	try (Connection con = this.getConnection(); PreparedStatement pstat = con.prepareStatement(sql)) {
//		pstat.setInt(1, boardId);
//		try (ResultSet rs = pstat.executeQuery();) {
//			List<FilesDTO> list = new ArrayList<>();
//			while (rs.next()) {
//				
//				int id = rs.getInt("file_id");
//				String oriname = rs.getString("original_name");
//		
//				String name = rs.getString("system_name");
//				int boardId = rs.getInt("board_id");
//				list.add(new FilesDTO(fileId, oriname, name, boardId));
//			}
//			return list;
//		}
//	}
//}
//}
