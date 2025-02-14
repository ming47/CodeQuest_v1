package DAO;

import java.util.List;

import DTO.ViewCountDTO;

public interface ViewCountDAO {
	List<ViewCountDTO> selectAll() throws Exception;
	ViewCountDTO selectById(int id) throws Exception;
	int insert(ViewCountDTO dto) throws Exception;
	int deleteById(int dto) throws Exception;
	
	int countByBoardId(int boardId) throws Exception;  // 게시글의 조회수
}
