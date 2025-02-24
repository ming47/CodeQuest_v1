package DAO;

import java.time.LocalDate;
import java.util.List;

import DTO.PlaytimeDTO;
import enums.GENDER;

public interface PlaytimeDAO {
	List<PlaytimeDTO> selectAll() throws Exception;
	PlaytimeDTO selectById(int id) throws Exception;
	int insert(PlaytimeDTO  dto) throws Exception;
	
	int sumAllPlaytime() throws Exception; // 전체 플레이타임의 합
	List<PlaytimeDTO> selectByMemberId(int memberId) throws Exception; // 유저 한명의 전체 플레이타임
	List<PlaytimeDTO> selectByGameId(int gameId) throws Exception;
	List<PlaytimeDTO> selectByMemberIdAndGameId(int memberId, int gameId) throws Exception;	// 유저 한명의 특정 게임 플레이타임
	
	double avgAllPlaytime() throws Exception;  // 전체 플레이타임의 평균
	List<PlaytimeDTO> selectByMemberGender(GENDER gender) throws Exception;	// 성별에 따른 유저 플레이타임
	List<PlaytimeDTO> selectByMemberAgeRange(int startAge, int endAge) throws Exception; // 나이대에 따른 유저 플레이타임
	List<PlaytimeDTO> selectByDate(LocalDate date) throws Exception; // 특정 날짜의 유저 플레이타임
	List<PlaytimeDTO> selectRecentByMemberId(int memberId) throws Exception; // 마이페이지 최근 플레이한 게임
}
