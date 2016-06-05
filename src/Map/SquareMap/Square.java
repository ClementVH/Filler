package Map.SquareMap;

import Main.Filler;
import Map.MapType;

public class Square extends MapType{
	
	public Square(int x, int y){
		super(x, y);
		
		this.neighbors = new int[4][2];
		
		neighbors[0] = new int[]{x-1, y};
		neighbors[1] = new int[]{x+1, y};
		neighbors[2] = new int[]{x	, y-1};
		neighbors[3] = new int[]{x	, y+1};
		
	}

	@Override
	public void display() {
		
		Filler.p.rect(x, y, 1, 1);
		
	}

	

	

}
