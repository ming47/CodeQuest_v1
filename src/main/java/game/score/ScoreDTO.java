package game.score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ScoreDTO {

	private int scoreId;
	private int gameId;
	private int memberId;
	private int score;
	private Timestamp regDate;
	private String user;
	
	public ScoreDTO(int gameId, int memberId, int score, Timestamp regDate, String user) {
		this.gameId = gameId;
		this.memberId = memberId;
		this.score = score;
		this.regDate = regDate;
		this.user = user;
	}
	
	public ScoreDTO(int scoreId, int gameId, int memberId, int score, Timestamp regDate, String user) {
		super();
		this.scoreId = scoreId;
		this.gameId = gameId;
		this.memberId = memberId;
		this.score = score;
		this.regDate = regDate;
		this.user = user;
	}
	
	public ScoreDTO(int gameId, int memberId, int score) {
		this.gameId = gameId;
		this.memberId = memberId;
		this.score = score;
	}
	
	public ScoreDTO() {}
	
	public int getScoreId() {
		return scoreId;
	}
	
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Timestamp getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	public String getUser() {
		return user;
	}
	
	public static ScoreDTO of(ResultSet rs) throws SQLException {
		return new ScoreDTO(
			rs.getInt("GAME_ID"),
			rs.getInt("MEMBER_ID"),
			rs.getInt("SCORE"),
			rs.getTimestamp("REG_DATE"),
			rs.getString("NICKNAME"));
	}
}
