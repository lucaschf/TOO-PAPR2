package tsi.too.excercise2.domain;

public class Cube extends Piece {
	private double edge;	

	public Cube() {
		super(PieceType.CUBE);
	}
	
	public Cube(Material material, int quantity, double edge) {
		super(material, PieceType.CUBE, quantity);
		this.edge = edge;
	}

	public void setEdge(double edge) {
		this.edge = edge;
	}
	
	public double getEdge() {
		return edge;
	}
	
	@Override
	public double calculateArea() {
		return 6 * Math.pow(edge, 2);
	}

	@Override
	public double calculateVolume() {		
		return Math.pow(edge, 3);
	}

	@Override
	public String toString() {
		return String.format("%s, edge: %1.2fm",super.toString(), edge);
	}

	@Override
	public Cube clone() {
		return new Cube(getMaterial(), getQuantity(), edge);
	}		
}