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

/**
 * Convenience class for receiving piece data via user input.
 *
 * @author Lucas Cristovam
 *
 * @param <E> the Piece type
 */

public abstract class PieceController<E extends Piece> {
	
	/**
	 * Reads all the piece data and returns the reading status.
	 * 
	 * @param piece Reference of the piece where the data will be stored.
	 * @return true if valid data is entered, false otherwise.
	 */
	@SuppressWarnings("unchecked") // Assuming that is passing an object of type E.
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
	
	/**
	 * Read the piece dimensions.
	 * 
	 * @param piece Reference of the piece where the data will be stored.
	 * @return true if valid data is entered, false otherwise.
	 */
	public abstract boolean readDimensions(E piece);
	
	/**
	 * Gets the quantity of the piece.
	 * 
	 * @param title the title to be shown.
	 * @return the quantity.
	 */
	private Integer readQuantity(String title) {
		return InputDialog.showIntegerInputDialog(
				title, 
				QUANTITY, 
				input -> input <= 0 ? VALUE_MUST_BE_GREATER_THAN_ZERO : DEFAULT_SUCCESS_MESSAGE
		);
	}
	
	/**
	 * Convenience method for reading a real value accepting only values ​​greater than zero.
	 * 
	 * @param title the title of the dialog. 
	 * @param message the message for user.
	 * @return the user's input
	 */
	protected Double readDoubleGreaterThanZero(String title, String message) {
		return InputDialog.showDoubleInputDialog(
				title, 
				message,
				input -> input <= 0 ? VALUE_MUST_BE_GREATER_THAN_ZERO : DEFAULT_SUCCESS_MESSAGE
		);
	}
	
	/**
	 * Convenience method for choosing the piece material.
	 *  
	 * @return the chosen material 
	 */
	private Material readMaterial() {
		Material choosen; 
		
		do {
			choosen = InputDialog.showOptionDialog(PIECE, CHOOSE_A_MATERIAL, Material.values());
		} while(choosen == null && !MessageDialog.showConfirmationDialog(PIECE, CANCEL_PIECE_INSERTION));
		
		return choosen;
	}
}