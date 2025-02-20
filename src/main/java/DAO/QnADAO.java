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
	List<QnADTO> selectByMemberId(int memberId) throws Exception;
	
	int getSize() throws Exception;
<<<<<<< HEAD
	int getNextVal() throws Exception;
=======
	List<QnADTO> selectAll(int page) throws Exception;
	List<QnADTO> selectByResponseYN(String responseYN, int page) throws Exception;
	List<QnADTO> selectByWriterLike(String writer, int page) throws Exception;
	List<QnADTO> selectByContentLike(String content, int page) throws Exception;
>>>>>>> 76514e1abb1178ddf5870ad8e530e6b272fdcb03
}
