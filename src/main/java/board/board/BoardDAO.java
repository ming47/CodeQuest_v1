package board.board;

import java.util.List;

public interface BoardDAO {
	List<BoardDTO> selectAll(int page) throws Exception;
	List<BoardDTO> selectByMemberId(int memberId) throws Exception;
	List<BoardDTO> selectBoardList(String searchField, String searchText, int page) throws Exception;
	List<BoardDTO> selectTop5Boardlist()throws Exception;
	List<BoardDTO> selectTop5WeekendBoardList() throws Exception;
	List<BoardDTO> selectRecentNotice() throws Exception;
	
	BoardDTO selectById(int id) throws Exception;
	
	int insert(BoardDTO dto) throws Exception;
	int update(BoardDTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	
	int getNextVal() throws Exception;	
	int getSize() throws Exception;
	int getSearchListSize(String searchField, String searchText)throws Exception;
	
	void increaseReplyCount(int boardId) throws Exception;
	void increaseViewCount(int boardId) throws Exception;
	void decreaseReplyCount(int boardId) throws Exception;
	
	
}