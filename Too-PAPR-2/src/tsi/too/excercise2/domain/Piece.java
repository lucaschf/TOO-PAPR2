package tsi.too.excercise2.domain;

import tsi.too.excercise2.Constants;

public abstract class Piece implements Cloneable{
	private Material material;
	private final PieceType type;
	private int quantity;
		
	public Piece(PieceType type) {
		super();
		this.type = type;
	}

	public Piece(Material material, PieceType type, int quantity) {
		super();
		this.type = type;
		this.material = material;
		this.quantity = quantity;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public PieceType getType() {
		return type;
	}
	
	public abstract double calculateArea();
	
	public double calculateAreaForQuantity() {
		return calculateArea() * getQuantity();
	}
	
	public double calculateVolumeForQuantity() {
		return calculateVolume() * getQuantity();
	}
	
	public abstract double calculateVolume();	
	
	public double calculateWeight() {
		return calculateVolume() * material.getWeight();
	}
	
	public double calculateWeightForQuantity() {
		return calculateWeight() * getQuantity();
	}
	
	@Override
	public String toString() {
		return String.format(
				"Type: %s, material: %s, quantity: %d",
				type.getName(), 
				material.getName(), 
				quantity
		);
	}

	@Override
	protected Piece clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	public enum Material {
		IRON(Constants.IRON, 7.8, 0.7),
		ALUMINIUM(Constants.ALUMINIUM, 2.7, 0.5);

		private String name;
		private double weight;
		private double paintConsumption;

		public String getName() {
			return name;
		}

		public double getWeight() {
			return weight;
		}

		public double getPaintConsumption() {
			return paintConsumption;
		}
		
		private Material(String name, double weight, double inkConsumption) {
			this.name = name;
			this.weight = weight;
			this.paintConsumption = inkConsumption;
		}
		
		@Override
		public String toString() {
			return getName();
		}
	}
}