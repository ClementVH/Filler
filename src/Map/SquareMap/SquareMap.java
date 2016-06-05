package Map.SquareMap;

import java.util.ArrayList;
import Main.Filler;
import Map.Map;
import Map.MapType;

public class SquareMap extends Map<MapType>{

	private static final long serialVersionUID = 1L;

	public SquareMap(){
		
		super();
		
		for(int i = 0; i < Filler.GRID_WIDTH; i++){
			
			ArrayList<MapType> column = new ArrayList<MapType>();
			
			for(int j = 0; j < Filler.GRID_HEIGHT; j++){
				
				Square s = new Square(i, j);
				
				column.add(s);
				
			}
			

			this.add(column);
		}
		
		
	}

	@Override
	public void display() {
		
		Filler.p.scale(Filler.scale);
		
		Filler.p.stroke(48, 32, 19);
		Filler.p.strokeWeight(0.2f);
		
		for(int i = 0; i < size(); i++){
			
			ArrayList<MapType> column = get(i);
			
			for(int j = 0; j < column.size(); j++){
				
				Square square = (Square) column.get(j);
				
				Filler.p.fill(square.color.getRGB());
				square.display();
				
			}
		}
	}

}
