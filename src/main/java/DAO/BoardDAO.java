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
	List<BoardDTO> selectAll(int page) throws Exception; // 페이징 처리된 값 가져오기
	
	List<BoardDTO> selectByMemberId(int memberId) throws Exception;
	void increaseReplyCount(int boardId) throws Exception;
	void increaseViewCount(int boardId) throws Exception;
	List<BoardDTO> selectAllNotice() throws Exception;
	void decreaseReplyCount(int boardId) throws Exception;
	
	int getSize() throws Exception;
	List<BoardDTO> selectBoardList(String searchField, String searchText,int page)throws Exception;
	int searchListgetSize(String searchField, String searchText)throws Exception;
	List<BoardDTO> selectTop5Boardlist()throws Exception;
	
	List<BoardDTO> selectTop5WeekendBoardList() throws Exception;
}