package DTO;

import java.sql.Timestamp;

public class ScoreDTO {

	private int score_id;
	private int game_id;
	private int member_id;
	private int score;
	private Timestamp reg_date;
	public int getScore_id() {
		return score_id;
	}
	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public ScoreDTO(int score_id, int game_id, int member_id, int score, Timestamp reg_date) {
		super();
		this.score_id = score_id;
		this.game_id = game_id;
		this.member_id = member_id;
		this.score = score;
		this.reg_date = reg_date;
	}
	public ScoreDTO() {
		super();
	}

}
