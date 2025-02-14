package DAO;

import java.util.List;

import DTO.GameDTO;

public interface GameDAO {
	List<GameDTO> selectAll() throws Exception;
	GameDTO selectById(int id) throws Exception;
}
