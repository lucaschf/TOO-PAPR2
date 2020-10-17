package tsi.too.excercise2.controller;

import static tsi.too.excercise2.Constants.CANS_OF;
import static tsi.too.excercise2.Constants.CHOOSE_A_TYPE;
import static tsi.too.excercise2.Constants.METALIC_STRUCTURE;
import static tsi.too.excercise2.Constants.NOTHING_TO_SHOW;
import static tsi.too.excercise2.Constants.PAINT_CONSUMPTION;
import static tsi.too.excercise2.Constants.PIECE;
import static tsi.too.excercise2.Constants.REPORT_BY_MATERIAL;
import static tsi.too.excercise2.Constants.REPORT_BY_PIECE_TYPE;
import static tsi.too.excercise2.Constants.TOTAL_ALUMINIUM_AREA;
import static tsi.too.excercise2.Constants.TOTAL_AREA;
import static tsi.too.excercise2.Constants.TOTAL_IRON_AREA;
import static tsi.too.excercise2.Constants.TOTAL_VOLUME;
import static tsi.too.excercise2.Constants.TOTAL_VOLUME_BY_PIECE;
import static tsi.too.excercise2.Constants.TOTAL_WEIGHT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import tsi.too.excercise2.Constants;
import tsi.too.excercise2.domain.PaintCan;
import tsi.too.excercise2.domain.Piece;
import tsi.too.excercise2.domain.Piece.Material;
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
		var type = InputDialog.showOptionDialog(PIECE, CHOOSE_A_TYPE, PieceType.values());
		
		if(type == null)
			return null;
		
		Piece piece = PieceFactory.create(type);
		var<Piece> controller = PieceControllerFactory.create(type);
		
		return controller.readData(piece) ? piece : null;
	}
	
	public void showTotalVolume() {
		MessageDialog.showInformationDialog(METALIC_STRUCTURE, createTotalVolumeMessage());
	}
	
	private String createTotalVolumeMessage() {
		return String.format("%s: %1.2f m³", TOTAL_VOLUME, structure.getTotalVolume());
	}
	
	public void showTotalWeight() {
		MessageDialog.showInformationDialog(METALIC_STRUCTURE, createTotalWeightMessage());
	}
	
	private String createTotalWeightMessage() {
		return String.format("%s: %1.2f Kg", TOTAL_WEIGHT, structure.getTotalWeight());
	}
	
	public void showPaintCansNeeded() {		
		MessageDialog.showTextMessage(PAINT_CONSUMPTION, createPaintCansMessage());
	}
	
	private String createPaintCansMessage() {
		var message = new StringBuilder(String.format("%s: %1.2f L", PAINT_CONSUMPTION, structure.getPaintConsumption()));
		
		for(Pair<PaintCan, Integer> p : paintCansNeeded())
			message.append(String.format("\n%s %1.1f L: %d", CANS_OF, p.getFirst().getCapacity(), p.getSecond()));
		
		return message.toString();
	}
	
	public List<Pair<PaintCan, Integer>> paintCansNeeded(){
		var availableCans = Arrays.asList(PaintCan.values());
		var neededCans = new ArrayList<Pair<PaintCan, Integer>>();
		double totalPaintNeeded = structure.getPaintConsumption();
		int quantity;

		Collections.sort(availableCans, (arg0, arg1) -> arg0.getCapacity() < arg1.getCapacity() ? 1 : -1);		
		
		for(PaintCan can : availableCans) {		
			if(totalPaintNeeded == 0) {
				quantity = 0;
			}
			else if(can.getCapacity() > totalPaintNeeded) {
				totalPaintNeeded = 0;
				quantity = 1;
			} else{
				quantity = (int)(totalPaintNeeded / can.getCapacity());
				totalPaintNeeded = totalPaintNeeded % can.getCapacity();
			}
			
			neededCans.add(new Pair<PaintCan, Integer>(can, quantity));
		}
				
		return neededCans;
	}
	
	public void showTotalVolumeByPiece() {
		MessageDialog.showTextMessage(TOTAL_VOLUME_BY_PIECE, createVolumeByPieceMessage());
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
		
		message.append(String.format("%s\n", createTotalVolumeMessage()))
			.append(String.format("%s\n", createTotalWeightMessage()))
			.append(String.format("\n%s\n%s" ,TOTAL_VOLUME_BY_PIECE, createVolumeByPieceMessage()))
			.append(String.format("\n%s\n", createAreaMessage()))
			.append(String.format("\n%s", createPaintCansMessage()))
			;
		
		MessageDialog.showTextMessage(Constants.REPORT, message.toString());
	}
	
	private String createAreaMessage() {
		var message = new StringBuilder()
				.append(String.format("%s: %1.2f m²", TOTAL_ALUMINIUM_AREA, structure.getTotalArea(Material.ALUMINIUM)))
				.append(String.format("\n%s: %1.2f m²", TOTAL_IRON_AREA, structure.getTotalArea(Material.IRON)))
				.append(String.format("\n%s: %1.2f m²", TOTAL_AREA, structure.getTotalArea()));

		return message.toString();
	}

	public void showMaterialReport() {
		var pieces = structure.getPieces();
		pieces.sort((piece, another) -> piece.getMaterial().getName().compareTo(another.getMaterial().getName()));
		
		if(pieces.isEmpty()){
			MessageDialog.showAlertDialog(REPORT_BY_PIECE_TYPE, NOTHING_TO_SHOW);
			return;
		}
		
		MessageDialog.showTextMessage(
				REPORT_BY_MATERIAL, 
				String.join("\n", pieces.stream().map(p -> p.toString()).collect(Collectors.toList()))
		);
	}
	
	public void showPieceTypeReport() {
		var pieces = structure.getPieces();
		
		if(pieces.isEmpty()){
			MessageDialog.showAlertDialog(REPORT_BY_PIECE_TYPE, NOTHING_TO_SHOW);
			return;
		}
		
		pieces.sort((piece, another) -> piece.getType().getName().compareTo(another.getType().getName()));
		
		MessageDialog.showTextMessage(
				REPORT_BY_PIECE_TYPE, 
				String.join("\n", pieces.stream().map(p -> p.toString()).collect(Collectors.toList()))
		);
	}
}