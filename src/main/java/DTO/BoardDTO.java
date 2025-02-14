package DTO;

import java.sql.Timestamp;

public class BoardDTO {
	private int board_id;
	private int writer;
	private String title;
	private Timestamp reg_date;
	private String contens;

	public BoardDTO() {
		super();
	}

	public BoardDTO(int board_id, int writer, String title, Timestamp reg_date, String contens) {
		super();
		this.board_id = board_id;
		this.writer = writer;
		this.title = title;
		this.reg_date = reg_date;
		this.contens = contens;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getWriter() {
		return writer;
	}

	public void setWriter(int writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getContens() {
		return contens;
	}

	public void setContens(String contens) {
		this.contens = contens;
	}
}
