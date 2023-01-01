package nl.jrwer.challenge.advent;

import java.util.HashMap;
import java.util.Map;

import nl.jrwer.challenge.advent.input.SingleLineInputLoader;

//--- Day 17: Pyroclastic Flow ---
//
//Your handheld device has located an alternative exit from the cave for you and the elephants. The ground is rumbling almost continuously now, but the strange valves bought you some time. It's definitely getting warmer in here, though.
//
//The tunnels eventually open into a very tall, narrow chamber. Large, oddly-shaped rocks are falling into the chamber from above, presumably due to all the rumbling. If you can't work out where the rocks will fall next, you might be crushed!
//
//The five types of rocks have the following peculiar shapes, where # is rock and . is empty space:
//
//####
//
//.#.
//###
//.#.
//
//..#
//..#
//###
//
//#
//#
//#
//#
//
//##
//##
//
//The rocks fall in the order shown above: first the - shape, then the + shape, and so on. Once the end of the list is reached, the same order repeats: the - shape falls first, sixth, 11th, 16th, etc.
//
//The rocks don't spin, but they do get pushed around by jets of hot gas coming out of the walls themselves. A quick scan reveals the effect the jets of hot gas will have on the rocks as they fall (your puzzle input).
//
//For example, suppose this was the jet pattern in your cave:
//
//>>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
//
//In jet patterns, < means a push to the left, while > means a push to the right. The pattern above means that the jets will push a falling rock right, then right, then right, then left, then left, then right, and so on. If the end of the list is reached, it repeats.
//
//The tall, vertical chamber is exactly seven units wide. Each rock appears so that its left edge is two units away from the left wall and its bottom edge is three units above the highest rock in the room (or the floor, if there isn't one).
//
//After a rock appears, it alternates between being pushed by a jet of hot gas one unit (in the direction indicated by the next symbol in the jet pattern) and then falling one unit down. If any movement would cause any part of the rock to move into the walls, floor, or a stopped rock, the movement instead does not occur. If a downward movement would have caused a falling rock to move into the floor or an already-fallen rock, the falling rock stops where it is (having landed on something) and a new rock immediately begins falling.
//
//Drawing falling rocks with @ and stopped rocks with #, the jet pattern in the example above manifests as follows:
//
//The first rock begins falling:
//|..@@@@.|
//|.......|
//|.......|
//|.......|
//+-------+
//
//Jet of gas pushes rock right:
//|...@@@@|
//|.......|
//|.......|
//|.......|
//+-------+
//
//Rock falls 1 unit:
//|...@@@@|
//|.......|
//|.......|
//+-------+
//
//Jet of gas pushes rock right, but nothing happens:
//|...@@@@|
//|.......|
//|.......|
//+-------+
//
//Rock falls 1 unit:
//|...@@@@|
//|.......|
//+-------+
//
//Jet of gas pushes rock right, but nothing happens:
//|...@@@@|
//|.......|
//+-------+
//
//Rock falls 1 unit:
//|...@@@@|
//+-------+
//
//Jet of gas pushes rock left:
//|..@@@@.|
//+-------+
//
//Rock falls 1 unit, causing it to come to rest:
//|..####.|
//+-------+
//
//A new rock begins falling:
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Jet of gas pushes rock left:
//|..@....|
//|.@@@...|
//|..@....|
//|.......|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Rock falls 1 unit:
//|..@....|
//|.@@@...|
//|..@....|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Jet of gas pushes rock right:
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|.......|
//|..####.|
//+-------+
//
//Rock falls 1 unit:
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|..####.|
//+-------+
//
//Jet of gas pushes rock left:
//|..@....|
//|.@@@...|
//|..@....|
//|.......|
//|..####.|
//+-------+
//
//Rock falls 1 unit:
//|..@....|
//|.@@@...|
//|..@....|
//|..####.|
//+-------+
//
//Jet of gas pushes rock right:
//|...@...|
//|..@@@..|
//|...@...|
//|..####.|
//+-------+
//
//Rock falls 1 unit, causing it to come to rest:
//|...#...|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//A new rock begins falling:
//|....@..|
//|....@..|
//|..@@@..|
//|.......|
//|.......|
//|.......|
//|...#...|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//The moment each of the next few rocks begins falling, you would see this:
//
//|..@....|
//|..@....|
//|..@....|
//|..@....|
//|.......|
//|.......|
//|.......|
//|..#....|
//|..#....|
//|####...|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@...|
//|..@@...|
//|.......|
//|.......|
//|.......|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@@@.|
//|.......|
//|.......|
//|.......|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|...@...|
//|..@@@..|
//|...@...|
//|.......|
//|.......|
//|.......|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|....@..|
//|....@..|
//|..@@@..|
//|.......|
//|.......|
//|.......|
//|..#....|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@....|
//|..@....|
//|..@....|
//|..@....|
//|.......|
//|.......|
//|.......|
//|.....#.|
//|.....#.|
//|..####.|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@...|
//|..@@...|
//|.......|
//|.......|
//|.......|
//|....#..|
//|....#..|
//|....##.|
//|....##.|
//|..####.|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//|..@@@@.|
//|.......|
//|.......|
//|.......|
//|....#..|
//|....#..|
//|....##.|
//|##..##.|
//|######.|
//|.###...|
//|..#....|
//|.####..|
//|....##.|
//|....##.|
//|....#..|
//|..#.#..|
//|..#.#..|
//|#####..|
//|..###..|
//|...#...|
//|..####.|
//+-------+
//
//To prove to the elephants your simulation is accurate, they want to know how tall the tower will get after 2022 rocks have stopped (but before the 2023rd rock begins falling). In this example, the tower of rocks will be 3068 units tall.
//
//How many units tall will the tower of rocks be after 2022 rocks have stopped falling?


class Day17 {
	public static void main(String[] args) {
		try {
			Day17 day = new Day17();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		Sequence sequence = new InputLoader("input-day-17.txt").getInput();
		
		long start = System.currentTimeMillis();
		Chamber c = new Chamber(sequence);
		System.out.println(c.calculate());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	class Chamber {
		final int fallingRocks = 2022;
		final int width = 7;
		int highest = 0;
		final Rocks rocks = new Rocks();

		final Sequence sequence;
		Map<Integer, Map<Integer, Character>> chamber = new HashMap<>();
		
		public Chamber(Sequence sequence) {
			this.sequence = sequence;
			
			for(int i=0; i<width; i++)
				chamber.put(i, new HashMap<>());
		}
		
		public int calculate() {
			for(int i=0; i<fallingRocks; i++)
				fall(rocks.next());
			
//			printHeight();
			
			return highest;
		}
		
		private void fall(Rock rock) {
			int fallingHeight = getHighest() + 4;
			boolean falling = true;
			
			rock.setY(fallingHeight);

			while(falling) {
				Direction dir = sequence.next();
				if(canMove(rock, dir))
					rock.move(dir);
				
				if(canFall(rock)) {
					rock.move(Direction.DOWN);
				} else {
					setNewHighest(rock);
					addRock(rock);
					falling = false;
				}
			}
		}

		public boolean canMove(Rock rock, Direction dir) {
			Coord l = rock.mostLeft();
			Coord r = rock.mostRight();
			
			if((l.x == 0 && dir == Direction.LEFT) 
					|| (r.x == width - 1 && dir == Direction.RIGHT))
				return false;
			
			if(dir == Direction.LEFT)
				for(Coord c : rock.coords)
					if(c.x > 0 && get(c.x - 1, c.y) == '#' && dir == Direction.LEFT)
						return false;
			
			if(dir == Direction.RIGHT)
				for(Coord c : rock.coords)
					if(c.x < width - 1 && get(c.x + 1, c.y) == '#' && dir == Direction.RIGHT)
						return false;
			
			return true;
		}
		
		public boolean canFall(Rock rock) {
			for(Coord c : rock.coords)
				if(c.y <= 1 || get(c.x, c.y - 1) == '#')
					return false;
			
			return true;
		}
		
		public void setNewHighest(Rock rock) {
			int highestPointNewRock = rock.getHighest().y;
			
			if(highestPointNewRock > this.highest)
				this.highest = rock.getHighest().y;
		}
		
		public void addRock(Rock rock) {
			for(Coord c : rock.coords)
				set(c.x, c.y);
		}
		
		public void set(int x, int y) {
			chamber.get(x).put(y, '#');
		}
		
		public Character get(int x, int y) {
			return chamber.get(x).getOrDefault(y, '.');
		}
		
		public int getHighest() {
			return this.highest;
		}
		
		public void printHeight() {
			StringBuilder sb = new StringBuilder();
			
			for(int y=highest; y>0; y--) {
				for(int x=0; x<width; x++)
					sb.append(get(x, y));
				
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}
	}
	
	class Rocks {
		final Stones[] stones;
		private int index = -1;
		
		public Rocks() {
			this.stones = new Stones[] {
					Stones.MINUS,
					Stones.PLUS,
					Stones.HOOK,
					Stones.PIPE,
					Stones.SQUARE};
		}
		
		public Rock next() {
			index++;
			
			if(index == stones.length)
				index = 0;
			
			return getRock(stones[index]);
		}
		
		private Rock getRock(Stones stone) {
			switch (stone) {
			case HOOK:
				return new Hook();
			case MINUS:
				return new Minus();
			case PIPE:
				return new Pipe();
			case PLUS:
				return new Plus();
			case SQUARE:
				return new Square();
			default:
				return null;
			}
		}
	}
	
	class Minus extends Rock {
		public Minus() {
			super(0, 3, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(2,0), new Coord(3,0), new Coord(4,0), new Coord(5,0)};
		}
	}
	
	class Plus extends Rock {
		public Plus() {
			super(1, 3, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(3,2), 
					new Coord(2,1), new Coord(3,1), new Coord(4,1), 
					new Coord(3,0)};
		}
	}
	
	class Hook extends Rock {
		public Hook() {
			super(2, 4, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(4,2), 
					new Coord(4,1), 
					new Coord(2,0), new Coord(3,0), new Coord(4,0)};
		}
	}
	
	class Pipe extends Rock {
		public Pipe() {
			super(3, 3, 0);
		}
		
		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(2,3), new Coord(2,2), new Coord(2,1), new Coord(2,0)};
		}
	}
	
	class Square extends Rock {
		public Square() {
			super(2, 3, 0);
		}

		@Override
		protected Coord[] getCoords() {
			return new Coord[] {new Coord(2,1), new Coord(3,1), 
					new Coord(2,0), new Coord(3,0)};
		}
	}

	abstract class Rock {
		Coord[] coords;
		final int mostLeft, mostRight, highest;
		
		public Rock(int mostLeft, int mostRight, int highest) {
			this.coords = getCoords();
			
			this.mostLeft = mostLeft;
			this.mostRight = mostRight;
			this.highest = highest;
		}
		
		abstract Coord[] getCoords();
		
		public void move(Direction direction) {
			for(Coord c: coords)
				if(direction == Direction.LEFT) 
					c.moveLeft();
				else if(direction == Direction.RIGHT) 
					c.moveRight();
				else
					c.moveDown();
		}
		
		public Coord mostLeft() {
			return coords[mostLeft];
		}
		
		public Coord mostRight() {
			return coords[mostRight];
		}
		
		public void setY(int height) {
			for(Coord c : coords)
				c.y += height;
		}
		
//		public boolean directBelow(int x, int y) {
//			for(Coord c : coords)
//				if(c.x == x && c.y <= y + 1)
//					return true;
//
//			return false;
//		}
		
		public Coord getHighest() {
			return coords[highest];
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for(Coord c : coords)
				sb.append(c).append(';');
			
			return sb.toString();
		}
	}
	
	enum Stones {
		MINUS,
		PLUS,
		HOOK,
		PIPE,
		SQUARE;
	}
	
	class Coord {
		int x, y;
		
		public Coord(int x, int y) {
			this.x = x;
			this.y = y; 
		}
		
		public void moveLeft() {
			x--;
		}
		
		public void moveRight() {
			x++;
		}	
		
		public void moveDown() {
			y--;
		}
		
		@Override
		public String toString() {
			return String.format("%d,%d", x,y);
		}
	}
	
	class Sequence {
		private final Direction[] sequence;
		private int index = -1;
		
		public Sequence(String input) {
			this.sequence = new Direction[input.length()];
			
			for(int i=0; i<input.length(); i++)
				this.sequence[i] = input.charAt(i) == '<' ? 
						Direction.LEFT : Direction.RIGHT;
		}
		
		public Direction next() {
			index++;
			
			if(index == sequence.length)
				index = 0;
			
			return sequence[index];
		}
	}
	
	enum Direction {
		LEFT,RIGHT,DOWN;
	}
	
	class InputLoader extends SingleLineInputLoader<Sequence>{
				
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected Sequence handleLine(String line) {
			return new Sequence(line);
		}
	}
}
