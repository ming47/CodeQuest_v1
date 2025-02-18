package DAOImpl;

import java.sql.Date;
import java.util.List;

import DAO.BlackListDAO;
import DTO.BlackListDTO;

public enum BlackListDAOImpl implements BlackListDAO {
	INSTANCE;

	@Override
	public List<BlackListDTO> selectAll() throws Exception {
		return null;
	}

	@Override
	public BlackListDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BlackListDTO dto) throws Exception {
		String sql = "INSERT INTO BLACK_LIST(BLACK_ID, MEMBER_ID, END_DATE) VALUES(BLACK_ID_SEQ.NEXTVAL, ?, ?)";
		
		
		return 0;
	}

	@Override
	public int update(BlackListDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BlackListDTO> selectByMemberId(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlackListDTO> selectByPeriod(Date startTime, Date endTime) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
