package nl.jrwer.challenge.advent;

import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Part Two ---
//
//The grove coordinate values seem nonsensical. While you ponder the mysteries of Elf encryption, you suddenly remember the rest of the decryption routine you overheard back at camp.
//
//First, you need to apply the decryption key, 811589153. Multiply each number by the decryption key before you begin; this will produce the actual list of numbers to mix.
//
//Second, you need to mix the list of numbers ten times. The order in which the numbers are mixed does not change during mixing; the numbers are still moved in the order they appeared in the original, pre-mixed list. (So, if -3 appears fourth in the original list of numbers to mix, -3 will be the fourth number to move during each round of mixing.)
//
//Using the same example as above:
//
//Initial arrangement:
//811589153, 1623178306, -2434767459, 2434767459, -1623178306, 0, 3246356612
//
//After 1 round of mixing:
//0, -2434767459, 3246356612, -1623178306, 2434767459, 1623178306, 811589153
//
//After 2 rounds of mixing:
//0, 2434767459, 1623178306, 3246356612, -2434767459, -1623178306, 811589153
//
//After 3 rounds of mixing:
//0, 811589153, 2434767459, 3246356612, 1623178306, -1623178306, -2434767459
//
//After 4 rounds of mixing:
//0, 1623178306, -2434767459, 811589153, 2434767459, 3246356612, -1623178306
//
//After 5 rounds of mixing:
//0, 811589153, -1623178306, 1623178306, -2434767459, 3246356612, 2434767459
//
//After 6 rounds of mixing:
//0, 811589153, -1623178306, 3246356612, -2434767459, 1623178306, 2434767459
//
//After 7 rounds of mixing:
//0, -2434767459, 2434767459, 1623178306, -1623178306, 811589153, 3246356612
//
//After 8 rounds of mixing:
//0, 1623178306, 3246356612, 811589153, -2434767459, 2434767459, -1623178306
//
//After 9 rounds of mixing:
//0, 811589153, 1623178306, -2434767459, 3246356612, 2434767459, -1623178306
//
//After 10 rounds of mixing:
//0, -2434767459, 1623178306, 3246356612, -1623178306, 2434767459, 811589153
//
//The grove coordinates can still be found in the same way. Here, the 1000th number after 0 is 811589153, the 2000th is 2434767459, and the 3000th is -1623178306; adding these together produces 1623178306.
//
//Apply the decryption key and mix your encrypted file ten times. What is the sum of the three numbers that form the grove coordinates?


class Day20Part2 extends Day20 {
	public static void main(String[] args) {
		try {
			Day20Part2 day = new Day20Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static final long KEY = 811_589_153L;
	
	public void start() {
		List<Number> numbers = new InputLoader("input-day-20.txt", KEY).getInput();
		
		long start = System.currentTimeMillis();
		Decryptor decryptor = new Decryptor(numbers);
		
		for(int i=0; i<10; i++)
			decryptor.decrypt();
		
		System.out.println("\nSum of coordinates: " + decryptor.getCoordinates());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	class InputLoader extends BasicInputLoader<Number>{
		final long decriptionKey;
		int index = 0;
		
		public InputLoader(String file, long decriptionKey) {
			super(file);
			
			this.decriptionKey = decriptionKey;
		}

		@Override
		protected Number handleLine(String line) {
			Number number = new Number(index, Long.parseLong(line) * decriptionKey);
			index++;
			return number;
		}
	}	
}
