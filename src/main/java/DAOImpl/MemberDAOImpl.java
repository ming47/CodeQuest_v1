package DAOImpl;

import java.util.List;

import DAO.MemberDAO;
import DTO.MemberDTO;
import enums.GENDER;

public enum MemberDAOImpl implements MemberDAO {
	INSTANCE;

	@Override
	public List<MemberDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MemberDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberDTO> selectByGender(GENDER gender) throws Exception {
		// TODO Auto-generated method stub
		int genderFactor = gender.getGenderFactor();
		
		String sql = "?";
		
		return null;
	}

	@Override
	public List<MemberDTO> selectByAgeRange(int startAge, int endAge) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAdmin(String role) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
