package game.playtime;

import java.util.List;

import utils.AnalyzeDTO;

public interface PlaytimeDAO {
	List<PlaytimeDTO> selectRecentByMemberId(int memberId) throws Exception; // 마이페이지 최근 플레이한 게임

	int insert(PlaytimeDTO  dto) throws Exception;
	
	int sumAllPlaytime() throws Exception;
	double avgAllPlaytime() throws Exception; 
	
	List<AnalyzeDTO> selectAnaRecent7days(String type) throws Exception;
	List<AnalyzeDTO> selectAnaRecent7days(String type, int gemaId) throws Exception;
	List<AnalyzeDTO> selectAnaRecent12Months(String type) throws Exception; 
	List<AnalyzeDTO> selectAnaGroupByGender(String type) throws Exception;
	List<AnalyzeDTO> selectAnaGroupByAges(String type) throws Exception;
	List<AnalyzeDTO> selectAnaGroupByGameId(String type) throws Exception;
	
	double selectAnaByMinusDate(String type, int date) throws Exception;
	double selectTodayAna(String type) throws Exception;
	double selectTodayAnaByGameId(String type, int gameId) throws Exception;
	double selectAnaByMinusMonth(String type, int month) throws Exception; 
	double selectAnaByMinusDateAndGameId(String type, int date, int gameId) throws Exception;
	double selectAnaByMinusMonthAndGameId(String type, int date, int gameId) throws Exception;


}
