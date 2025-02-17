package DAO;

import java.util.List;

import DTO.FilesDTO;

public interface FilesDAO {
	List<FilesDTO> selectAll() throws Exception;
	FilesDTO selectById(int id) throws Exception;
	int insert(FilesDTO dto) throws Exception;
	int update(FilesDTO dto) throws Exception;
	int deleteById(int dto) throws Exception;
	
	List<FilesDTO> selectByBoardId(int boardId) throws Exception;	// 특정 게시글의 파일들을 검색
}
