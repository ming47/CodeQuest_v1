package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PlaytimeDTO {
	
	private int playtimeId;
	private int memberId;
	private int gameId;
	private int playtime;
	private Timestamp regDate;
	
	public PlaytimeDTO(int playtimeId, int memberId, int gameId, int playtime, Timestamp regDate) {
		super();
		this.playtimeId = playtimeId;
		this.memberId = memberId;
		this.gameId = gameId;
		this.playtime = playtime;
		this.regDate = regDate;
	}

	public PlaytimeDTO() {
		super();
	}

	public int getPlaytimeId() {
		return playtimeId;
	}

	public void setPlaytimeId(int playtimeId) {
		this.playtimeId = playtimeId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int getPlaytime() {
		return playtime;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	public static PlaytimeDTO of(ResultSet rs) throws SQLException {
		return new PlaytimeDTO(
				rs.getInt("PLAY_TIME_ID"),
				rs.getInt("MEMBER_ID"),
				rs.getInt("GAME_ID"),
				rs.getInt("PLAY_TIME"),
				rs.getTimestamp("REG_DATE"));
	}
}
