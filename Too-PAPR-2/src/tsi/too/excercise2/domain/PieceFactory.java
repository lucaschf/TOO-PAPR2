package tsi.too.excercise2.domain;

import tsi.too.excercise2.exception.NoSuchTypeException;

public abstract class PieceFactory {
	public static Piece create(PieceType type) {
		if(type == PieceType.CUBE)
			return new Cube();
		
		if(type == PieceType.CYLINDER)
			return new Cylinder();
		
		if(type == PieceType.PARALLELEPIPED)
			return new Parallelepiped();
		
		throw new NoSuchTypeException();
	}
}