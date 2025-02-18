package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDTO {
	
	private int gameId;
	private String gameName;
	
	public GameDTO() {}

	public GameDTO(int gameId, String gameName) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	public static GameDTO of(ResultSet rs) throws SQLException {
		return new GameDTO(
				rs.getInt("GAME_ID"),
				rs.getString("GAME_NAME"));
	}
}
