package tsi.too.excercise2.domain;

import tsi.too.excercise2.Constants;

public enum PieceType{
	CUBE(Constants.CUBE),
	CYLINDER(Constants.CYLINDER),
	PARALLELEPIPED(Constants.PARALLELEPIPED);
	
	private String name;
	
	public String getName() {
		return name;
	}

	private PieceType(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}