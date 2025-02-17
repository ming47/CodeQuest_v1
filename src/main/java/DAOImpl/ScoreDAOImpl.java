package DAOImpl;

import java.util.List;

import DAO.ScoreDAO;
import DTO.ScoreDTO;

public enum ScoreDAOImpl implements ScoreDAO {
	INSTANCE;

	@Override
	public List<ScoreDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScoreDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ScoreDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ScoreDTO> selectByGameId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreDTO> avergeByGameIdGroupByMemberId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreDTO> selectByMemberId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ScoreDTO> selectByMemberIdAndGameId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
