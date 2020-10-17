package tsi.too.excercise2.controller;

import static tsi.too.excercise2.Constants.DEPTH_IN_METERS;
import static tsi.too.excercise2.Constants.HEIGHT_IN_METERS;
import static tsi.too.excercise2.Constants.PIECE;
import static tsi.too.excercise2.Constants.WIDTH_IN_METERS;

import tsi.too.excercise2.domain.Parallelepiped;

public class ParallelepipedController extends PieceController<Parallelepiped> {

	private static ParallelepipedController instance;

	private ParallelepipedController() {}

	public static ParallelepipedController getInstance() {
		synchronized (ParallelepipedController.class) {
			if(instance == null)
				instance = new ParallelepipedController();
			
			return instance;
		}
	}
	
	@Override
	public boolean readDimensions(Parallelepiped piece) {
		var width = readDoubleGreaterThanZero(PIECE, WIDTH_IN_METERS);
		if(width == null)
			return false;

		var height = readDoubleGreaterThanZero(PIECE, HEIGHT_IN_METERS);
		if(height == null)
			return false;

		var depth = readDoubleGreaterThanZero(PIECE, DEPTH_IN_METERS);
		if(depth == null)
			return false;

		piece.setDepth(depth);
		piece.setHeight(height);
		piece.setWidth(width);

		return true;
	}
}