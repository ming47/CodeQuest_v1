package DAO;

import java.util.List;

import DTO.ReplyDTO;

public interface ReplyDAO {
   List<ReplyDTO> selectAll() throws Exception;
   ReplyDTO selectById(int id) throws Exception;
   int insert(ReplyDTO dto) throws Exception;
   int update(ReplyDTO dto) throws Exception;
   int deleteById(int dto) throws Exception;
   
   List<ReplyDTO> selectByBoardId(int boardId) throws Exception;
}