package tsi.too.excercise2.controller;

import tsi.too.excercise2.domain.PieceType;
import tsi.too.excercise2.exception.NoSuchTypeException;

public abstract class PieceControllerFactory {
	public static PieceController<?> create(PieceType type){
		if(type == PieceType.CUBE)
			return CubeController.getInstance();
		
		if(type == PieceType.CYLINDER)
			return CylinderControler.getInstance();
		
		if(type == PieceType.PARALLELEPIPED)
			return ParallelepipedController.getInstance();
		
		throw new NoSuchTypeException();
	}
}
