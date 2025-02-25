package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDTO {
	
	private int gameId;
	private String gameName;
	private String gameIntro;
	private String gameDescript;
	private String gameThumb;
	
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



	public String getGameIntro() {
		return gameIntro;
	}



	public void setGameIntro(String gameIntro) {
		this.gameIntro = gameIntro;
	}



	public String getGameDescript() {
		return gameDescript;
	}



	public void setGameDescript(String gameDescript) {
		this.gameDescript = gameDescript;
	}



	public String getGameThumb() {
		return gameThumb;
	}



	public void setGameThumb(String gameThumb) {
		this.gameThumb = gameThumb;
	}



	public GameDTO() {
		super();
	}



	public GameDTO(int gameId, String gameName, String gameIntro, String gameDescript, String gameThumb) {
		super();
		this.gameId = gameId;
		this.gameName = gameName;
		this.gameIntro = gameIntro;
		this.gameDescript = gameDescript;
		this.gameThumb = gameThumb;
	}



	public static GameDTO of(ResultSet rs) throws SQLException {
		return new GameDTO(
				rs.getInt("GAME_ID"),
				rs.getString("GAME_NAME"),
				rs.getString("intro"),
				rs.getString("descript"),
				rs.getString("thumb"));
	}
}
