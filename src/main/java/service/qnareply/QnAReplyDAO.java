package service.qnareply;

public interface QnAReplyDAO {
	QnAReplyDTO selectByQnAId(int qnaId) throws Exception;

	int insert(QnAReplyDTO dto) throws Exception;
	int update(QnAReplyDTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	
}
