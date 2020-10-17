package tsi.too.excercise2.controller;

import java.util.stream.Collectors;

import tsi.too.excercise2.Constants;
import tsi.too.excercise2.domain.PaintCan;
import tsi.too.excercise2.domain.Piece;
import tsi.too.excercise2.domain.PieceFactory;
import tsi.too.excercise2.domain.PieceType;
import tsi.too.excercise2.domain.Structure;
import tsi.too.excercise2.util.Pair;
import tsi.too.message_dialog.InputDialog;
import tsi.too.message_dialog.MessageDialog;

public class StructureController {

	private static StructureController instance; 
	
	private Structure structure;
	
	private StructureController() {
		structure = new Structure();
	}
	
	public static StructureController getInstance() {
		synchronized (StructureController.class) {
			if(instance == null)
				instance = new StructureController();
			
			return instance;
		}
	}
	
	public void addPiece() {
		structure.addPiece(readPieceData());
	}
	
	private Piece readPieceData() {
		var type = InputDialog.showOptionDialog(Constants.PIECE, Constants.CHOOSE_A_TYPE, PieceType.values());
		
		if(type == null)
			return null;
		
		Piece piece = PieceFactory.create(type);
		var<Piece> controller = PieceControllerFactory.create(type);
		
		return controller.readData(piece) ? piece : null;
	}
	
	public void showTotalVolume() {
		MessageDialog.showInformationDialog(Constants.METALIC_STRUCTURE, createTotalVolumeMessage());
	}
	
	private String createTotalVolumeMessage() {
		return String.format("%s: %1.2f m³", Constants.TOTAL_VOLUME, structure.getTotalVolume());
	}
	
	public void showTotalWeight() {
		MessageDialog.showInformationDialog(Constants.METALIC_STRUCTURE, createTotalWeightMessage());
	}
	
	private String createTotalWeightMessage() {
		return String.format("%s: %1.2f Kg", Constants.TOTAL_WEIGHT, structure.getTotalWeight());
	}
	
	public void showPaintCansNeeded() {		
		MessageDialog.showTextMessage(Constants.PAINT_CONSUMPTION, createPaintCansMessage());
	}
	
	private String createPaintCansMessage() {
		var message = new StringBuilder(String.format("%s: %1.2f L", Constants.PAINT_CONSUMPTION, structure.getPaintConsumption()));
		
		for(Pair<PaintCan, Integer> p : structure.paintCansNeeded())
			message.append(String.format("\n%s %1.1f L: %d",Constants.CANS_OF, p.getFirst().getCapacity(), p.getSecond()));
		
		return message.toString();
	}
	
	public void showTotalVolumeByPiece() {
		MessageDialog.showTextMessage(Constants.TOTAL_VOLUME_BY_PIECE, createVolumeByPieceMessage());
	}
	
	private String createVolumeByPieceMessage() {
		var message = new StringBuilder();
		
		for(PieceType type : PieceType.values()) {
			message.append(String.format("%s: %1.2f m³\n", type.getName(), structure.getTotalVolumeByPiece(type)));
		}
		
		return message.toString();
	}
	
	public void showReport() {
		var message = new StringBuilder();
		
		message.append(String.format("%s\n" , createTotalVolumeMessage()))
			.append(String.format("%s\n" , createTotalWeightMessage()))
			.append(String.format("\n%s\n%s\n" ,Constants.TOTAL_VOLUME_BY_PIECE, createVolumeByPieceMessage()))
			.append(String.format("%s\n", createPaintCansMessage()))
			;
		
		MessageDialog.showTextMessage(Constants.REPORT, message.toString());
	}
	
	public void showMaterialReport() {
		var pieces = structure.getPieces();
		pieces.sort((piece, another) -> piece.getMaterial().getName().compareTo(another.getMaterial().getName()));
		
		if(pieces.isEmpty()){
			MessageDialog.showAlertDialog(Constants.REPORT_BY_PIECE_TYPE, Constants.NOTHING_TO_SHOW);
			return;
		}
		
		MessageDialog.showTextMessage(Constants.REPORT_BY_MATERIAL, 
				String.join("\n", pieces.stream().map(p -> p.toString()).collect(Collectors.toList())));
	}
	
	public void showPieceTypeReport() {
		var pieces = structure.getPieces();
		
		if(pieces.isEmpty()){
			MessageDialog.showAlertDialog(Constants.REPORT_BY_PIECE_TYPE, Constants.NOTHING_TO_SHOW);
			return;
		}
		
		pieces.sort((piece, another) -> piece.getType().getName().compareTo(another.getType().getName()));
		
		MessageDialog.showTextMessage(Constants.REPORT_BY_PIECE_TYPE, 
				String.join("\n", pieces.stream().map(p -> p.toString()).collect(Collectors.toList())));
	}
}