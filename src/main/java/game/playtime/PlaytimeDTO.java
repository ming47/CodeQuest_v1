package game.playtime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PlaytimeDTO {
	
	private int playtimeId;
	private int memberId;
	private int gameId;
	private int playtime;
	private Timestamp regDate;
	private String formatTime;
	
	public PlaytimeDTO(int playtimeId, int memberId, int gameId, int playtime, Timestamp regDate) {
		this.playtimeId = playtimeId;
		this.memberId = memberId;
		this.gameId = gameId;
		this.playtime = playtime;
		this.regDate = regDate;
		this.formatTime = convertToTimeString(playtime);
	}
	
	public PlaytimeDTO(int playtimeId, int memberId, int gameId, int playtime, Timestamp regDate, String formatTime) {
		this.playtimeId = playtimeId;
		this.memberId = memberId;
		this.gameId = gameId;
		this.playtime = playtime;
		this.regDate = regDate;
		this.formatTime = formatTime;
	}
	
	public PlaytimeDTO(int gameId, int memberId, int playtime) {
		this.gameId = gameId;
		this.memberId = memberId;
		this.playtime = playtime;
	}
	
    private String convertToTimeString(int Seconds) {
        int totalSeconds = Seconds / 1000;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return minutes + "분 " + seconds + "초";
    }
    
	public String getFormatTime() {
		return formatTime;
	}

	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
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
				rs.getInt("PLAYTIME_ID"),
				rs.getInt("MEMBER_ID"),
				rs.getInt("GAME_ID"),
				rs.getInt("PLAY_TIME"),
				rs.getTimestamp("REG_DATE"));
	}
}
