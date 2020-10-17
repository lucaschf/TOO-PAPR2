package tsi.too.excercise2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tsi.too.excercise2.domain.Piece.Material;


public class MetalicStructure {
	private ArrayList<Piece> pieces = new ArrayList<>();
	
	public boolean addPiece(Piece piece) {
		if(piece == null)
			return false;
					
		return pieces.add(piece);
	}

	/**
	 * Calculates the total weight of the metal structure.
	 * 
	 * @return the weight in Kilograms.
	 */
	public double calculateTotalWeight() {
		return pieces.stream()
				.mapToDouble(p-> p.calculateWeightForQuantity())
				.sum();
	}
	
	/**
	 * Calculates the total volume of the metal structure.
	 * 
	 * @return the volume in cubic meters.
	 */
	public double calculateTotalVolume() {
		return pieces.stream()
				.mapToDouble(p -> p.calculateVolumeForQuantity()).sum();
	}
	
	/**
	 * Calculates the amount of paint needed to paint the structure.
	 * 
	 * @return
	 */
	public double calculatePaintConsumption() {
		return calculateTotalArea(Material.ALUMINIUM) * Material.ALUMINIUM.getPaintConsumption()
				+ calculateTotalArea(Material.IRON) * Material.IRON.getPaintConsumption();
	}
	
	/**
	 * Calculates the total volume of all parts of the metal structure that have the same type, defined by <code> type </code>.
	 * 
	 * @param type the piece type.
	 * @return the volume in cubic meters.
	 */
	public double calculateTotalVolumeByPiece(PieceType type) {		
		return filterByType(type)
				.mapToDouble(p -> p.calculateVolumeForQuantity())
				.sum();
	}
	
	/**
	 * Calculates the total area of all parts of the metal structure that have the same material, defined by <code> material </code>.
	 * 
	 * @param material the piece material.
	 * @return the area in square meters.
	 */
	public double calculateTotalArea(Material material) {
		return pieces.stream()
				.filter(p -> p.getMaterial() == material)
				.mapToDouble(p -> p.calculateAreaForQuantity())
				.sum();
	}
	
	/**
	 * Calculates the total area of all parts of the metal structure.
	 * 
	 * @return the volume in cubic meters.
	 */
	public double calculateTotalArea() {
		return calculateTotalArea(Material.ALUMINIUM) + calculateTotalArea(Material.IRON);
	}
	
	/**
	 * Calculates the total weight of all parts of the metal structure that have the same type, defined by <code> type </code>.
	 * 
	 * @param type the piece type. 
	 * @return the weight in Kilograms.
	 */
	public double calculateTotalWeightByPiece(PieceType type) {
		return filterByType(type)
				.mapToDouble(p -> p.calculateWeightForQuantity())
				.sum();
	}
	
	/**
	 * Retrieves all pieces of the metal structure that have the same type, defined by <code> type </code>.
	 * 
	 * @param type the piece type. 
	 * @return the retrieved pieces.
	 */
	private Stream<Piece> filterByType(PieceType type){
		return pieces.stream().filter(p -> p.getType() == type);
	}
	
	public List<Piece> getPieces() {
		return pieces.stream().map(this::tryClone).collect(Collectors.toList());
	}
	
	/**
	 * Tries to clone a piece.
	 * @param p the target piece
	 * 
	 * @return the cloned piece.
	 * @throws RuntimeException if the piece cannot be cloned
	 */
	private Piece tryClone(Piece p) throws RuntimeException{
		try {
			return p.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}
}
