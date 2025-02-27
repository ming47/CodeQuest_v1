package board.file;

import java.util.List;

public interface FilesDAO {
	FilesDTO selectById(int id) throws Exception;
	int insert(FilesDTO dto) throws Exception;

	List<FilesDTO> selectByBoardId(int boardId) throws Exception;	// 특정 게시글의 파일들을 검색
	}


