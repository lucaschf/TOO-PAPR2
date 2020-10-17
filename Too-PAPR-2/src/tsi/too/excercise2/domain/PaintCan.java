package tsi.too.excercise2.domain;

/**
 * Represents all accepted paint can sizes
 *  
 * @author Lucas Cristovam
 */
public enum PaintCan {
	HALF_LITER(0.5),
	TWO_LITERS(2),
	FIVE_LITERS(5);
	
	private double capacity;

	public double getCapacity() {
		return capacity;
	}
	
	private PaintCan(double capacity) {
		this.capacity = capacity;
	}
}