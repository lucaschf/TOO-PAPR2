package tsi.too.excercise2.controller;

import java.util.List;

import tsi.too.excercise2.domain.PaintCan;
import tsi.too.excercise2.util.Pair;

/**
 * Convenience interface for handling the actions over an <code>MetalicStruture</code>.
 * 
 * @author Lucas Cristovam
 */
public interface Structure {
	/**
	 * Adds a new piece in the structure.
	 */
	public void addPiece();
	
	/**
	 * Displays in a dialog box the total volume of the structure.
	 */
	public void showTotalVolume();
	
	/**
	 * Displays in a dialog box the total weight of the structure.
	 */
	public void showTotalWeight();
	
	/**
	 * Displays in a dialog box the total paint cans needed to paint the structure.
	 */
	public void showPaintCansNeeded();
	
	public List<Pair<PaintCan, Integer>> paintCansNeeded();
	
	/**
	 * Displays in a dialog box the total volume by piece in the structure.
	 */
	public void showTotalVolumeByPiece();
	
	/**
	 * Displays a general report.
	 */
	public void showReport();
	
	/**
	 * Displays all data for all parts of the metal structure grouped by material type.
	 */
	public void showMaterialReport();
	
	/**
	 * Displays all data of all pieces of the metal structure grouping by its type. 
	 */
	public void showPieceTypeReport();
}
