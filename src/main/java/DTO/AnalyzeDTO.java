package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyzeDTO {
	private double data;
	private String label;
	
	public AnalyzeDTO(double data, String label) {
		this.data = data;
		this.label = label;
	}
	
	public double getData() {
		return data;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static AnalyzeDTO of(ResultSet rs) throws SQLException {
		return new AnalyzeDTO(
				rs.getDouble(1),
				rs.getString(2));
	}
}
