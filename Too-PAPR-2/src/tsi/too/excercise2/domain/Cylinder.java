package tsi.too.excercise2.domain;

public class Cylinder extends Piece {
	private double radius;
	private double height;
	
	public Cylinder() {
		super(PieceType.CYLINDER);
	}

	public Cylinder(Material material, int quantity, double radius, double height) {
		super(material, PieceType.CYLINDER, quantity);
		this.height = height;
		this.radius = radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	@Override
	public double calculateArea() {
		return 2 * Math.PI * radius * (radius + height);
	}

	@Override
	public double calculateVolume() {
		return Math.PI * Math.pow(radius, 2) * height;
	}

	@Override
	public String toString() {
		return String.format("%s, radius: %1.2fm, height: %1.2fm",super.toString(), radius, height);
	}
	
	@Override
	public Cylinder clone() {
		return new Cylinder(getMaterial(), getQuantity(), radius, height);
	}
}