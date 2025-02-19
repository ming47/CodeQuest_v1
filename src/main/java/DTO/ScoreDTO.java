package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ScoreDTO {

	private int scoreId;
	private int gameId;
	private int memberId;
	private int score;
	private Timestamp regDate;
	
	public ScoreDTO(int scoreId, int gameId, int memberId, int score, Timestamp regDate) {
		super();
		this.scoreId = scoreId;
		this.gameId = gameId;
		this.memberId = memberId;
		this.score = score;
		this.regDate = regDate;
	}
	
	public ScoreDTO() {
		super();
	}
	
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
	public int getScore() {
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
	
	public static ScoreDTO of(ResultSet rs) throws SQLException {
		return new ScoreDTO(
				rs.getInt("SCORE_ID"),
				rs.getInt("GAME_ID"),
				rs.getInt("MEMBER_ID"),
				rs.getInt("SCORE"),
				rs.getTimestamp("REG_DATE"));
	}
}
