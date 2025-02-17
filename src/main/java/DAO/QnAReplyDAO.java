package DAO;

import java.util.List;

import DTO.QnAReplyDTO;

public interface QnAReplyDAO {
	List<QnAReplyDTO> selectAll() throws Exception;
	QnAReplyDTO selectById(int id) throws Exception;
	int insert(QnAReplyDTO dto) throws Exception;
	int update(QnAReplyDTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	
	QnAReplyDTO selectByQnAId(int qnaId) throws Exception;
}
