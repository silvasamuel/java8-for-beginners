package Utils;

import java.util.function.IntSupplier;

/**
 * @author samuel.silva
 */
public class Fibonacci implements IntSupplier {
	private int previous = 0;
	private int next = 1;
	
	public int getAsInt() {
		next = next + previous;
		previous = next - previous;
		return previous;
	}
}
