package nl.jrwer.challenge.advent.day23;

class Part2 {
	public static void main(String[] args) {
		try {
			Part2 day = new Part2();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		Elves elves = new InputLoader("input-day-23.txt").getInput();

		long start = System.currentTimeMillis();
		System.out.println("number of the first round where no Elf moves: " + elves.executeRounds());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
}
