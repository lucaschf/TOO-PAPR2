package tsi.too.excercise2.controller;

import static tsi.too.excercise2.Constants.CANCEL_PIECE_INSERTION;
import static tsi.too.excercise2.Constants.CHOOSE_A_MATERIAL;
import static tsi.too.excercise2.Constants.PIECE;
import static tsi.too.excercise2.Constants.QUANTITY;
import static tsi.too.excercise2.Constants.VALUE_MUST_BE_GREATER_THAN_ZERO;
import static tsi.too.message_dialog.InputDialog.InputValidator.DEFAULT_SUCCESS_MESSAGE;

import tsi.too.excercise2.domain.Piece;
import tsi.too.excercise2.domain.Piece.Material;
import tsi.too.message_dialog.InputDialog;
import tsi.too.message_dialog.MessageDialog;

public abstract class PieceController<E extends Piece> {
	@SuppressWarnings("unchecked")
	public boolean readData(Piece piece) {
		var quantity =  readQuantity(piece.getType().getName());
		if(quantity == null)
			return false;
		
		var material = readMaterial();
		if(material == null)
			return false;
		
		if(!readDimensions(((E) piece)))
			return false;
		
		piece.setMaterial(material);
		piece.setQuantity(quantity);
		
		return true;
	};
	
	public abstract boolean readDimensions(E p);
	
	private Integer readQuantity(String title) {
		return InputDialog.showIntegerInputDialog(
				title, 
				QUANTITY, 
				input -> input <= 0 ? VALUE_MUST_BE_GREATER_THAN_ZERO : DEFAULT_SUCCESS_MESSAGE
		);
	}
	
	protected Double readDoubleGreaterThanZero(String title, String message) {
		return InputDialog.showDoubleInputDialog(
				title, 
				message,
				input -> input <= 0 ? VALUE_MUST_BE_GREATER_THAN_ZERO : DEFAULT_SUCCESS_MESSAGE
		);
	}
	
	private Material readMaterial() {
		Material choosen; 
		
		do {
			choosen = InputDialog.showOptionDialog(PIECE, CHOOSE_A_MATERIAL, Material.values());
		}while(choosen == null && !MessageDialog.showConfirmationDialog(PIECE, CANCEL_PIECE_INSERTION));
		
		return choosen;
	}
}