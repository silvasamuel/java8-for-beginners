package Utils;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class Fibonacci implements IntSupplier {
	private int anterior = 0;
	private int proximo = 1;
	
	public int getAsInt() {
		proximo = proximo + anterior;
		anterior = proximo - anterior;
		return anterior;
	}
	
	public void showFibonacciSequence(int quantity) {
		IntStream.generate(new Fibonacci())
		.limit(quantity)
		.forEach(System.out::println);
	}
	
	public void showFirstBiggerThen(int number) {
		int biggerThen = IntStream
				.generate(new Fibonacci())
				.filter(f -> f > number)
				.findFirst()
				.getAsInt();
				
		System.out.println(biggerThen);
	}
}
