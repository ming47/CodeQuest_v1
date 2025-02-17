package DAO;

import java.util.List;

import DTO.QnADTO;

public interface QnADAO {
	List<QnADTO> selectAll() throws Exception;
	QnADTO selectById(int id) throws Exception;
	int insert(QnADTO dto) throws Exception;
	int update(QnADTO dto) throws Exception;
	int deleteById(int dto) throws Exception;
}
