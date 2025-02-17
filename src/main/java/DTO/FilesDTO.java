package DTO;

public class FilesDTO {
	
	private int fileId;
	private int boardId;
	private String oriname;
	private String sysname;
	
	public FilesDTO(int fileId, int boardId, String oriname, String sysname) {
		super();
		this.fileId = fileId;
		this.boardId = boardId;
		this.oriname = oriname;
		this.sysname = sysname;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
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
}
