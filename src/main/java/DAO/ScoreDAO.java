package DAO;

import java.util.List;

import DTO.ScoreDTO;

public interface ScoreDAO {
	List<ScoreDTO> selectAll() throws Exception;
	ScoreDTO selectById(int id) throws Exception;
	int insert(ScoreDTO dto) throws Exception;
	int deleteById(int dto) throws Exception;
	
	List<ScoreDTO> selectByGameId() throws Exception;  // 게임별 점수
	List<ScoreDTO> avergeByGameIdGroupByMemberId() throws Exception; // 게임 승수 조회
	List<ScoreDTO> selectByMemberId() throws Exception; // 유저 개인의 게임 점수
	List<ScoreDTO> selectByMemberIdAndGameId() throws Exception;	// 유저 개인의 게임 하나의 점수
}
