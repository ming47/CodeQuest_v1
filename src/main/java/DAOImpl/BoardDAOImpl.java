package DAOImpl;

import java.util.List;

import DAO.BoardDAO;
import DTO.BoardDTO;

public enum BoardDAOImpl implements BoardDAO {
	INSTANCE;

	@Override
	public List<BoardDTO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		//select name.member, *.board from board inner member in board.writer = member.member_id;
		// select * from board
			
		return null;
	}

	@Override
	public BoardDTO selectById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(int dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNextVal() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
