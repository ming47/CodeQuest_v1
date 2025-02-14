package DTO;

import java.sql.Timestamp;

public class PlaytimeDTO {
	
	private int playcount_id;
	private int member_id;
	private int game_id;
	private Timestamp playtime;
	private Timestamp reg_time;
	public PlaytimeDTO(int playcount_id, int member_id, int game_id, Timestamp playtime, Timestamp reg_time) {
		super();
		this.playcount_id = playcount_id;
		this.member_id = member_id;
		this.game_id = game_id;
		this.playtime = playtime;
		this.reg_time = reg_time;
	}
	public PlaytimeDTO() {
		super();
	}
	public int getPlaycount_id() {
		return playcount_id;
	}
	public void setPlaycount_id(int playcount_id) {
		this.playcount_id = playcount_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public Timestamp getPlaytime() {
		return playtime;
	}
	public void setPlaytime(Timestamp playtime) {
		this.playtime = playtime;
	}
	public Timestamp getReg_time() {
		return reg_time;
	}
	public void setReg_time(Timestamp reg_time) {
		this.reg_time = reg_time;
	}
	
	
	
	
	
}
