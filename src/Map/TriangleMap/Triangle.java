package Map.TriangleMap;

import Main.Filler;
import Map.MapType;
import processing.core.PConstants;

public class Triangle extends MapType{

	public Triangle(int x, int y) {
		super(x, y);
		
		this.neighbors = new int[3][2];
		
		if(y%2 ==0){
			
			neighbors[0] = new int[]{x, y-1};
			neighbors[1] = new int[]{x, y+1};
			neighbors[2] = new int[]{x+1, y+1};
			
		}
		
		else{
			
			neighbors[0] = new int[]{x, y-1};
			neighbors[1] = new int[]{x, y+1};
			neighbors[2] = new int[]{x-1, y-1};
			
		}
		
		
		
	}

	@Override
	public void display() {
		
		
		
		if(y%4 == 0){
			
			
			Filler.p.beginShape();
			
			Filler.p.vertex(x * 2, y);
			Filler.p.vertex(x * 2 + 2, y);
			Filler.p.vertex(x * 2 + 1, y+2);
			
			Filler.p.endShape(PConstants.CLOSE);
		}
		
		else if(y%4 == 1){
			
			Filler.p.beginShape();
			
			Filler.p.vertex(x * 2 + 1, y + 1);
			Filler.p.vertex(x * 2 + 3, y + 1);
			Filler.p.vertex(x * 2 + 2, y - 1);
			
			Filler.p.endShape(PConstants.CLOSE);
		}
		
		else if(y%4 == 2){
			
			Filler.p.beginShape();
			
			Filler.p.vertex(x * 2 + 1, y);
			Filler.p.vertex(x * 2 + 3, y);
			Filler.p.vertex(x * 2 + 2, y+2);
			
			Filler.p.endShape(PConstants.CLOSE);
		}
		
		else if(y%4 == 3){
			
			Filler.p.beginShape();
			
			Filler.p.vertex(x * 2, y + 1);
			Filler.p.vertex(x * 2 + 2, y + 1);
			Filler.p.vertex(x * 2 + 1, y-1);
			
			Filler.p.endShape(PConstants.CLOSE);
		}
		
	}

}
