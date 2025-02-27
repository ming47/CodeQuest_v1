package service.qna;

import java.util.List;

public interface QnADAO {
	List<QnADTO> selectAll(int page) throws Exception;
	List<QnADTO> selectByMemberId(int memberId) throws Exception;
	List<QnADTO> selectByResponseYN(String responseYN) throws Exception;
	List<QnADTO> selectByResponseYN(String responseYN, int page) throws Exception;
	List<QnADTO> selectByWriterLike(String writer) throws Exception;
	List<QnADTO> selectByWriterLike(String writer, int page) throws Exception;
	List<QnADTO> selectByContentLike(String content) throws Exception;
	List<QnADTO> selectByContentLike(String content, int page) throws Exception;
	List<QnADTO> selectRecentByMemberId(int memberId) throws Exception;
	QnADTO selectById(int id) throws Exception;
	
	int insert(QnADTO dto) throws Exception;
	int update(QnADTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	
	void updateResponseYNById(int id, String responseYN) throws Exception;
	
	int getSize() throws Exception;	
}
