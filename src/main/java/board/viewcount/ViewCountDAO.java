package board.viewcount;

import java.util.List;

import service.qna.QnADTO;

public interface ViewCountDAO {
	List<ViewCountDTO> selectRecentByMemberId(int memberId) throws Exception;
	
	int insert(ViewCountDTO dto) throws Exception;	
}
