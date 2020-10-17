package tsi.too.excercise2.domain;

public class Parallelepiped extends Piece {

	private double height;
	private double width;
	private double depth;
	
	public Parallelepiped() {
		super(PieceType.PARALLELEPIPED);
	}

	public Parallelepiped(Material material, int quantity, double height,  double width, double depth) {
		super(material, PieceType.PARALLELEPIPED, quantity);	
		
		this.height = height;
		this.width = width;
		this.depth = depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}
	
	public double getDepth() {
		return depth;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getWidth() {
		return width;
	}
	
	@Override
	public double calculateArea() {
		return 2 * (height * width + height * depth + width * depth);
	}

	@Override
	public double calculateVolume() {
		return height * width * depth;
	}

	@Override
	public String toString() {
		return String.format(
				"%s, height: %1.2fm, width: %1.2fm, depth: %1.2fm",
				super.toString(), 
				height, 
				width, 
				depth
		);
	}
	
	@Override
	public Parallelepiped clone() {
		return  new Parallelepiped(getMaterial(), getQuantity(), height, width, depth);
	}
}