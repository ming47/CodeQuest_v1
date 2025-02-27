package board.reply;

import java.util.List;

public interface ReplyDAO {
   List<ReplyDTO> selectAll() throws Exception;
   ReplyDTO selectById(int id) throws Exception;
   int insert(ReplyDTO dto) throws Exception;
   int update(ReplyDTO dto) throws Exception;
   int deleteById(int dto) throws Exception;
   
   List<ReplyDTO> selectByBoardId(int boardId) throws Exception;
   List<ReplyDTO> selectByBoardId(int boardId, int page) throws Exception;
   int getSelectByBoardIdSize(int boardId) throws Exception;
}