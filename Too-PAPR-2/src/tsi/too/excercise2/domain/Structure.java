package tsi.too.excercise2.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tsi.too.excercise2.domain.Piece.Material;
import tsi.too.excercise2.util.Pair;


public class Structure {
	private ArrayList<Piece> pieces = new ArrayList<>();
	
	public boolean addPiece(Piece piece) {
		if(piece == null)
			return false;
					
		return pieces.add(piece);
	}
	
	public double getTotalWeight() {
		return pieces.stream()
				.mapToDouble(p-> p.calculateWeightForQuantity())
				.sum();
	}
	
	public double getTotalVolume() {
		return pieces.stream()
				.mapToDouble(p -> p.calculateVolumeForQuantity()).sum();
	}
	
	public double getPaintConsumption() {
		return getTotalArea(Material.ALUMINIUM) * Material.ALUMINIUM.getPaintConsumption()
				+ getTotalArea(Material.IRON) * Material.IRON.getPaintConsumption();
	}
	
	public double getTotalVolumeByPiece(PieceType type) {		
		return filterByType(type)
				.mapToDouble(p -> p.calculateVolumeForQuantity())
				.sum();
	}
	
	public double getTotalArea(Material material) {
		return pieces.stream()
				.filter(p -> p.getMaterial() == material)
				.mapToDouble(p -> p.calculateAreaForQuantity())
				.sum();
	}
	
	public double getTotalArea() {
		return getTotalArea(Material.ALUMINIUM) + getTotalArea(Material.IRON);
	}
	
	public double getTotalWeightByPiece(PieceType type) {
		return filterByType(type)
				.mapToDouble(p -> p.calculateWeightForQuantity())
				.sum();
	}
	
	private Stream<Piece> filterByType(PieceType type){
		return pieces.stream().filter(p -> p.getType() == type);
	}
	
	public List<Piece> getPieces() {
		return pieces.stream().map(this::tryClone).collect(Collectors.toList());
	}
	
	private Piece tryClone(Piece p) {
		try {
			return p.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}
}
