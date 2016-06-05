package Map;

import java.awt.Color;

import Main.Filler;

public abstract class MapType {
	
	public int x, y, z;
	public Color color;
	public int domination;
	public boolean visited;
	
	
	public int[][] neighbors;
	
	public MapType(int x, int y){
		
		this.x = x;
		this.y = y;
		
		this.color = Filler.randomColor();
		
		visited = false;
		
	}
	
	public MapType(int x, int y, int z){
		
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.color = Filler.randomColor();
		
		visited = false;
		
	}

	public abstract void display();
	
	

}
