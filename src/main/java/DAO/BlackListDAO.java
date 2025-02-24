package DAO;

import java.sql.Date;
import java.util.List;

import DTO.BlackListDTO;

public interface BlackListDAO {
	List<BlackListDTO> selectAll() throws Exception;
	BlackListDTO selectById(int id) throws Exception;
	int insert(BlackListDTO dto) throws Exception;
	int update(BlackListDTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	
	List<BlackListDTO> selectByMemberId(int memberId) throws Exception;	// 특정 유저의 차단 목록을 검색
	List<BlackListDTO> selectByPeriod(Date startTime, Date endTime) throws Exception;	// 특정 기간의 차단 목록을 검색
	boolean isBanned(int memberId) throws Exception;
	
	BlackListDTO selectBanByMmeberId(int memberId) throws Exception;
	int countBanByMemberId(int memberId) throws Exception;
	int deleteBanByMemberId(int memberId) throws Exception;
	int permanentBan(BlackListDTO dto) throws Exception;
}
