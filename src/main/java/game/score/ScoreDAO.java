package game.score;

import java.util.List;

public interface ScoreDAO {
	int insert(ScoreDTO dto) throws Exception;
	
	List<ScoreDTO> selectByGameId(int gameId) throws Exception;  // 게임별 점수
	List<ScoreDTO> selectByMemberId(int memberId) throws Exception; // 유저 개인의 게임 점수
	List<ScoreDTO> selectByMemberIdAndGameId(int memberId, int gameId) throws Exception;	// 유저 개인의 게임 하나의 점수
	
	int getSelectByGameIdSize(int gameId) throws Exception;
}
