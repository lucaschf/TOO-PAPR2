package tsi.too.excercise2.controller;

import java.util.List;

import tsi.too.excercise2.domain.PaintCan;
import tsi.too.excercise2.util.Pair;

public interface Structure {
	public void addPiece();
	
	public void showTotalVolume();
	
	public void showTotalWeight();
	
	public void showPaintCansNeeded();
	
	public List<Pair<PaintCan, Integer>> paintCansNeeded();
	
	public void showTotalVolumeByPiece();
	
	public void showReport();
	
	public void showMaterialReport();
	
	public void showPieceTypeReport();
}
