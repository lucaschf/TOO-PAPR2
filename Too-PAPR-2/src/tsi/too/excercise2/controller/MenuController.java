package tsi.too.excercise2.controller;

import static tsi.too.excercise2.Constants.EXIT;
import static tsi.too.excercise2.Constants.METALIC_STRUCTURE;
import static tsi.too.excercise2.Constants.PAINT_CAN_QUANTITY;
import static tsi.too.excercise2.Constants.PIECES;
import static tsi.too.excercise2.Constants.REPORT;
import static tsi.too.excercise2.Constants.REPORT_BY_MATERIAL;
import static tsi.too.excercise2.Constants.REPORT_BY_PIECE_TYPE;
import static tsi.too.excercise2.Constants.TOTAL_VOLUME;
import static tsi.too.excercise2.Constants.TOTAL_VOLUME_BY_PIECE;
import static tsi.too.excercise2.Constants.TOTAL_WEIGHT;

import java.util.Arrays;
import java.util.List;

import tsi.too.message_dialog.InputDialog;

/**
 * Convenience class for displaying the menu.
 * 
 * @author Lucas Cristovam
 *
 */

public class MenuController {

	private static MenuController instance;
	
	private StructureController structureController;
	
	private MenuController() {
		structureController = StructureController.getInstance();
	}
	
	public static MenuController getInstance() {
		synchronized (MenuController.class) {
			if(instance == null)
				instance = new MenuController();
			
			return instance;		
		}
	}
	
	public void Menu() {
		List<String> options = Arrays.asList(
				PIECES,
				TOTAL_VOLUME,
				TOTAL_WEIGHT,
				PAINT_CAN_QUANTITY,
				TOTAL_VOLUME_BY_PIECE,
				REPORT,
				REPORT_BY_PIECE_TYPE,
				REPORT_BY_MATERIAL
		);
			
		InputDialog.showMenuDialog(
				METALIC_STRUCTURE, 
				"Choose an option", 
				options, 
				EXIT,
				this::execute
		);
	}
	
	private void execute(String action) {
		if(action == null)
			return;
		
		switch (action) {
			case PIECES:
				structureController.addPiece();
				break;
			case TOTAL_VOLUME:
				structureController.showTotalVolume();
				break;
			case TOTAL_WEIGHT:
				structureController.showTotalWeight();
				break;
			case PAINT_CAN_QUANTITY:
				structureController.showPaintCansNeeded();
				break;
			case TOTAL_VOLUME_BY_PIECE:
				structureController.showTotalVolumeByPiece();
				break;
			case REPORT:
				structureController.showReport();
				break;
			case REPORT_BY_MATERIAL:
				structureController.showMaterialReport();
				break;
			case REPORT_BY_PIECE_TYPE:
				structureController.showPieceTypeReport();
				break;
			default:
				break;
		}
	}
}
