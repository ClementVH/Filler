package Map;

import java.awt.Color;

import Main.Filler;

public abstract class MapType {
	
	public int x, y;
	public Color color;
	public int domination;
	public boolean visited;
	
	
	public int[][] neighbors = new int[4][2];
	
	public MapType(int x, int y){
		
		this.x = x;
		this.y = y;
		
		this.color = Filler.randomColor();
		
		visited = false;
		
	}

	public abstract void display();
	
	

}
