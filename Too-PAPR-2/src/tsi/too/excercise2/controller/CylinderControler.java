package tsi.too.excercise2.controller;

import static tsi.too.excercise2.Constants.HEIGHT_IN_METERS;
import static tsi.too.excercise2.Constants.PIECE;
import static tsi.too.excercise2.Constants.RADIUS_IN_METERS;

import tsi.too.excercise2.domain.Cylinder;

public class CylinderControler extends PieceController<Cylinder>{

	private static CylinderControler instance;
	
	private CylinderControler() {}
	
	public static CylinderControler getInstance() {
		synchronized (CylinderControler.class) {
			if(instance == null)
				instance = new CylinderControler();
			
			return instance;
		}
	}
	
	@Override
	public boolean readDimensions(Cylinder piece) {
		var height = readDoubleGreaterThanZero(PIECE, HEIGHT_IN_METERS);
		if(height == null)
			return false;

		var radius = readDoubleGreaterThanZero(PIECE, RADIUS_IN_METERS);
		if(radius == null)
			return false;

		piece.setRadius(radius);
		piece.setHeight(height);

		return true;
	}
}
