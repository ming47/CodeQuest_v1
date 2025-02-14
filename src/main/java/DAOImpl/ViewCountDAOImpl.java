package DAOImpl;

import java.util.List;

import DAO.ViewCountDAO;
import DTO.ViewCountDTO;

public enum ViewCountDAOImpl implements ViewCountDAO {
	INSTANCE;

	@Override
	public List<ViewCountDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViewCountDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ViewCountDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countByBoardId(int boardId) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
