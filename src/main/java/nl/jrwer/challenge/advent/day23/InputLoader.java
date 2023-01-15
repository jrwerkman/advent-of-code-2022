package nl.jrwer.challenge.advent.day23;

import java.util.ArrayList;
import java.util.List;

import nl.jrwer.challenge.advent.input.SingleObjectsInputLoader;

class InputLoader extends SingleObjectsInputLoader<Elves>{

	int id = 0;
	List<Elf> elves = new ArrayList<>();
	int y = 0;
	
	public InputLoader(String file) {
		super(file);
	}

	@Override
	protected void handleLine(String line) {
		char[] chars = line.toCharArray();
		
		for(int x=0; x<chars.length; x++)
			if(chars[x] == '#')
				elves.add(new Elf(id, x, y));
		
		id++;
		y++;
	}

	@Override
	protected Elves createObject() {
		return new Elves(elves);
	}
}