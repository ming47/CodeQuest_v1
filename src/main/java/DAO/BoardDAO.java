package DAO;

import java.util.List;

import DTO.BoardDTO;

public interface BoardDAO {
	List<BoardDTO> selectAll() throws Exception;
	BoardDTO selectById(int id) throws Exception;
	int insert(BoardDTO dto) throws Exception;
	int update(BoardDTO dto) throws Exception;
	int deleteById(int dto) throws Exception;
	
	int getNextVal() throws Exception;	// insert전에 다음 시퀸스값을 가져옴
}
