package tsi.too.excercise2.controller;

import static tsi.too.excercise2.Constants.PIECE;

import tsi.too.excercise2.Constants;
import tsi.too.excercise2.domain.Cube;

public class CubeController extends PieceController<Cube>{
	private static CubeController instance;
	
	private CubeController() {}
	
	public static CubeController getInstance() {
		synchronized (CubeController.class) {
			if(instance == null)
				instance = new CubeController();
			
			return instance;
		}
	}
	
	@Override
	public boolean readDimensions(Cube piece) {
		var edge = readDoubleGreaterThanZero(PIECE, Constants.EDGES_IN_METERS);
		if (edge == null)
			return false;
		
		piece.setEdge(edge);
		return true;
	}
}
