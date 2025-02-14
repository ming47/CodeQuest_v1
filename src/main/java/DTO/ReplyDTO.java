package DTO;

import java.sql.Timestamp;

public class ReplyDTO {

	private int reply_id;
	private String name;
	private int board_id;
	private String contents;
	private Timestamp reg_date;

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public ReplyDTO(int reply_id, String name, int board_id, String contents, Timestamp reg_date) {
		super();
		this.reply_id = reply_id;
		this.name = name;
		this.board_id = board_id;
		this.contents = contents;
		this.reg_date = reg_date;
	}

	public ReplyDTO() {
		super();
	}

}
