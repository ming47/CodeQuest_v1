package service.qnareply;

import java.util.List;

public interface QnAReplyDAO {
	List<QnAReplyDTO> selectAll() throws Exception;
	QnAReplyDTO selectById(int id) throws Exception;
	int insert(QnAReplyDTO dto) throws Exception;
	int update(QnAReplyDTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	
	QnAReplyDTO selectByQnAId(int qnaId) throws Exception;
}
