package DTO;

import java.sql.Timestamp;

public class PlaytimeDTO {
	
	private int playcountId;
	private int memberId;
	private int gameId;
	private Timestamp playtime;
	private Timestamp regDate;
	
	public PlaytimeDTO(int playcountId, int memberId, int gameId, Timestamp playtime, Timestamp regDate) {
		super();
		this.playcountId = playcountId;
		this.memberId = memberId;
		this.gameId = gameId;
		this.playtime = playtime;
		this.regDate = regDate;
	}

	public PlaytimeDTO() {
		super();
	}

	public int getPlaycountId() {
		return playcountId;
	}

	public void setPlaycountId(int playcountId) {
		this.playcountId = playcountId;
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

	public Timestamp getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Timestamp playtime) {
		this.playtime = playtime;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
