package DAOImpl;

import java.util.List;

import DAO.PlaytimeDAO;
import DTO.MemberDTO;
import DTO.PlaytimeDTO;

public enum PlaytimeDAOImpl implements PlaytimeDAO {
	INSTANCE;

	@Override
	public List<PlaytimeDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaytimeDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(PlaytimeDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumByPlayTime() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PlaytimeDTO> selectByMemberId(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaytimeDTO> selectByGameId(int gameId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlaytimeDTO> selectByMemberIdAndGameId(int memberId, int gameId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int sumPlayTimeByMemberIds(List<MemberDTO> memberDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
