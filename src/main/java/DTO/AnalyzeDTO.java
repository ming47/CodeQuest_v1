package DTO;

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
}
