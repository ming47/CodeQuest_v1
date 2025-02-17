package DAO;

import java.util.List;

import DTO.MemberDTO;
import DTO.PlaytimeDTO;

public interface PlaytimeDAO {
	List<PlaytimeDTO> selectAll() throws Exception;
	PlaytimeDTO selectById(int id) throws Exception;
	int insert(PlaytimeDTO  dto) throws Exception;
	
	int sumByPlayTime() throws Exception; // 전체 플레이타임
	List<PlaytimeDTO> selectByMemberId(int memberId) throws Exception; // 유저 한명의 전체 플레이타임
	List<PlaytimeDTO> selectByGameId(int gameId) throws Exception;
	List<PlaytimeDTO> selectByMemberIdAndGameId(int memberId, int gameId) throws Exception;	// 유저 한명의 특정 게임 플레이타임
	int sumPlayTimeByMemberIds(List<MemberDTO> memberDTO) throws Exception;  // 특정 유저군의 플레이타임 총합
}
