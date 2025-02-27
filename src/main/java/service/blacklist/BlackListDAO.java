package service.blacklist;

import java.util.List;

public interface BlackListDAO {
	List<BlackListDTO> selectByMemberId(int memberId) throws Exception;	// 특정 유저의 차단 목록을 검색
	BlackListDTO selectBanByMmeberId(int memberId) throws Exception;
	int countBanByMemberId(int memberId) throws Exception;

	int insert(BlackListDTO dto) throws Exception;
	int permanentBan(BlackListDTO dto) throws Exception;
	int deleteById(int id) throws Exception;
	int deleteBanByMemberId(int memberId) throws Exception;
	
	boolean isBanned(int memberId) throws Exception;
	
}
