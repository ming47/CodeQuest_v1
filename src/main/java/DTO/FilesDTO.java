package DTO;

public class FilesDTO {
	
	private int file_id;
	private int board_id;
	private String oriname;
	private String sysname;
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public FilesDTO() {
		super();
	}
	public FilesDTO(int file_id, int board_id, String oriname, String sysname) {
		super();
		this.file_id = file_id;
		this.board_id = board_id;
		this.oriname = oriname;
		this.sysname = sysname;
	}
}
