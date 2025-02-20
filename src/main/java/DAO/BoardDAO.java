package DAO;

import java.util.List;

import DTO.BoardDTO;
import DTO.MemberDTO;

public interface BoardDAO {
	List<BoardDTO> selectAll() throws Exception;
	BoardDTO selectById(int id) throws Exception;
	int insert(BoardDTO dto) throws Exception;
	int update(BoardDTO dto) throws Exception;
	int deleteById(int dto) throws Exception;
	
	int getNextVal() throws Exception;	// insert전에 다음 시퀸스값을 가져옴
	List<BoardDTO> selectAll(int page) throws Exception; // 페이징 처리된 값 가져오기
	
	BoardDTO selectByMemberId(int memberId) throws Exception;
}