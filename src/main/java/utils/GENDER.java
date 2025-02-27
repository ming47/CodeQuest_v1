package utils;

public enum GENDER {
	MALE(1), FEMALE(2);
	
	private GENDER(int genderFactor) {
		this.genderFactor = genderFactor;
	}
	
	private final int genderFactor;
	
	public int getGenderFactor() {
		return genderFactor;
	}
}
