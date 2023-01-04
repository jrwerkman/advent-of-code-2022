package nl.jrwer.challenge.advent;

import java.util.List;

import nl.jrwer.challenge.advent.input.BasicInputLoader;

//--- Day 18: Boiling Boulders ---
//
//You and the elephants finally reach fresh air. You've emerged near the base of a large volcano that seems to be actively erupting! Fortunately, the lava seems to be flowing away from you and toward the ocean.
//
//Bits of lava are still being ejected toward you, so you're sheltering in the cavern exit a little longer. Outside the cave, you can see the lava landing in a pond and hear it loudly hissing as it solidifies.
//
//Depending on the specific compounds in the lava and speed at which it cools, it might be forming obsidian! The cooling rate should be based on the surface area of the lava droplets, so you take a quick scan of a droplet as it flies past you (your puzzle input).
//
//Because of how quickly the lava is moving, the scan isn't very good; its resolution is quite low and, as a result, it approximates the shape of the lava droplet with 1x1x1 cubes on a 3D grid, each given as its x,y,z position.
//
//To approximate the surface area, count the number of sides of each cube that are not immediately connected to another cube. So, if your scan were only two adjacent cubes like 1,1,1 and 2,1,1, each cube would have a single side covered and five sides exposed, a total surface area of 10 sides.
//
//Here's a larger example:
//
//2,2,2
//1,2,2
//3,2,2
//2,1,2
//2,3,2
//2,2,1
//2,2,3
//2,2,4
//2,2,6
//1,2,5
//3,2,5
//2,1,5
//2,3,5
//
//In the above example, after counting up all the sides that aren't connected to another cube, the total surface area is 64.
//
//What is the surface area of your scanned lava droplet?


class Day18 {
	public static void main(String[] args) {
		try {
			Day18 day = new Day18();
			day.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void start() {
		List<Cube> cubes = new InputLoader("input-day-18.txt").getInput();
		
		long start = System.currentTimeMillis();
		Grid3D grid = new Grid3D(cubes);
		System.out.println("Exposed surface area: " + grid.exposedSurfaceArea());
		long end = System.currentTimeMillis();
		
		System.out.println("Process took: " + (end - start) + " ms\n");
	}
	
	class Grid3D {
		final List<Cube> cubes;
		final int[] dim;
		final boolean[][][] grid;
		
		public Grid3D(List<Cube> cubes) {
			this.cubes = cubes;
			this.dim = getDimensionsGrid();
			this.grid = new boolean[this.dim[0]][this.dim[1]][this.dim[2]];
			this.populateGrid();
		}
		
		private int[] getDimensionsGrid() {
			int x = 0, y = 0, z = 0;
			
			for(Cube cube : cubes) {
				x = x > cube.x ? x : cube.x;
				y = y > cube.y ? y : cube.y;
				z = z > cube.z ? z : cube.z;
			}
			
//			System.out.println(String.format("x: %d, y: %d, z: %d", x, y, z));
			
			return new int[] {x+1, y+1, z+1};
		}
		
		private void populateGrid() {
			for(Cube cube : cubes)
				set(cube);
		}
		
		public int exposedSurfaceArea() {
			int exposed = 0;
			
			for(Cube cube : cubes)
				exposed += exposedSurfaceArea(cube);
			
			return exposed;
		}
		
		private void set(Cube cube) {
			set(cube.x, cube.y, cube.z);
		}
		
		private void set(int x, int y, int z) {
//			System.out.println(String.format("x: %d, y: %d, z: %d", x, y, z));

			grid[x][y][z] = true;
		}
		
		protected boolean get(int x, int y, int z) {
			// coordinates are outside grid, so there cannot be a cube
			if(isBorder(x, y, z))
				return false;
			
//			System.out.println(String.format("x: %d, y: %d, z: %d", x, y, z));
			
			return grid[x][y][z];
		}

		protected boolean isBorder(Cube cube) {
			return isBorder(cube.x, cube.y, cube.z);
		}
		
		protected boolean isBorder(int x, int y, int z) {
			return x < 0 || x >= dim[0] || y < 0 || y >= dim[1] || z < 0 || z >= dim[2];
		}	
		
		protected int exposedSurfaceArea(Cube cube) {
			return exposedSurfaceArea(cube.x, cube.y, cube.z);
		}
		
		protected int exposedSurfaceArea(int x, int y, int z) {
			int exposed = 0;
			
			if(!get(x + 1, y, z)) 
				exposed++;
			if(!get(x - 1, y, z)) 
				exposed++;
			if(!get(x, y + 1, z)) 
				exposed++;
			if(!get(x, y - 1, z)) 
				exposed++;
			if(!get(x, y, z + 1)) 
				exposed++;
			if(!get(x, y, z - 1)) 
				exposed++;
			
			return exposed;
		}
	}
	
	class Cube {
		final int x,y,z;
		
		public Cube(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Cube) {
				Cube c = (Cube) obj;
				
				return c.x == x && c.y == y && c.z == z;
			}
			
			return false;
		}
		
		@Override
		public String toString() {
			return x +","+y +"," + z;
		}
		
		@Override
		public int hashCode() {
			return toString().hashCode();
		}
	}
	
	class InputLoader extends BasicInputLoader<Cube>{
				
		public InputLoader(String file) {
			super(file);
		}

		@Override
		protected Cube handleLine(String line) {
			String[] split = line.split(",");
			
			return new Cube(
					Integer.parseInt(split[0]),
					Integer.parseInt(split[1]),
					Integer.parseInt(split[2]));
		}
	}
}