package game.game;

import java.util.List;

public interface GameDAO {
	List<GameDTO> selectAll() throws Exception;
	GameDTO selectById(int id) throws Exception;
}
