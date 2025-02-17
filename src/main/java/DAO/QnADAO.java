package DAO;

import java.util.List;

import DTO.QnADTO;

public interface QnADAO {
	List<QnADTO> selectAll() throws Exception;
	QnADTO selectById(int id) throws Exception;
	int insert(QnADTO dto) throws Exception;
	int update(QnADTO dto) throws Exception;
	int deleteById(int dto) throws Exception;
	
	List<QnADTO> selectByResponseYN(String responseYN) throws Exception;
	List<QnADTO> selectByWriterLike(String writer) throws Exception;
	List<QnADTO> selectByContentLike(String content) throws Exception;
	void updateResponseYNById(int id, String responseYN) throws Exception;
}
