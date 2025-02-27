package member.member;

import java.util.List;

public interface MemberDAO {
	List<MemberDTO> selectAll(int page) throws Exception;
	List<MemberDTO> selectByIsBanned(boolean isBanned) throws Exception;
	List<MemberDTO> selectByIsBanned(boolean isBanned, int page) throws Exception;
	MemberDTO selectById(int id) throws Exception;
	MemberDTO login(String id, String pw) throws Exception;
	MemberDTO easyLogin(String inputEmail) throws Exception; //간편로그인 처리

	int insert(MemberDTO dto) throws Exception;
	int update(MemberDTO dto) throws Exception;
	int deleteById(int id) throws Exception;

	int updatePw(String email,String pw) throws Exception; //비밀번호 재설정

	boolean getMemberByEmail(String inputEmail) throws Exception;
	boolean isDuplicate(String column, String value) throws Exception; 
	boolean isAdmin(int memberId) throws Exception;	// 관리자인지 확인 
	
	int getSize() throws Exception;	
	int getSelectByIsBannedSize(boolean isBanned) throws Exception;
}
