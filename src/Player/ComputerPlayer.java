package Player;

import java.awt.Color;
import java.util.ArrayList;

import Main.Filler;
import Main.Game;
import Map.MapType;

public class ComputerPlayer extends Player{

	public ComputerPlayer(String name, int nb, int[] position) {
		super(name, nb, position);
	}

	@Override
	public void update() {
		
		//randomAI();
		
		easyAI();
		
		color = choosedColor == null ? color : choosedColor;
		
		
		fill();
		
		choosedColor = null;
		
		pass();
		
		calculateScore();
		
	}

	
	private void randomAI() {
		
		choosedColor = Filler.randomColor();
		
		checkColor(choosedColor);
		
	}
	
	@SuppressWarnings("unused")
	private void easyAI() {
		
		int[] colorsOcc = new int[6];
				
		for(ArrayList<MapType> column : Game.map){
			for(MapType cell : column){
				
				if(cell.domination == nb){
					
					for(int[] nCoords : cell.neighbors){
						
						int x = nCoords[0];
						int y = nCoords[1];
						
						if(x >= 0 && y >= 0 && x < Filler.GRID_WIDTH && y < Filler.GRID_HEIGHT){
							
							MapType neighbor = Game.map.get(x).get(y);
							
							if(!neighbor.visited){
								colorsOcc = updateColors(colorsOcc, neighbor.color);
								neighbor.visited = true;
							}
							
						}
						
					}
					
				}
				
				
								
			}
		}	
		
		resetVisited();
		
		Player bestEnnemy = chooseBestEnnemy();
		
		int maxOcc = 0;
		
		ArrayList<Integer> indexesOfMaxOcc = new ArrayList<Integer>();
		
		for(int i = 0; i < Filler.colors.length; i++){
			
			if(chkColor(Filler.colors[i]) && colorsOcc[i] >= maxOcc){
				maxOcc = colorsOcc[i];
				choosedColor = Filler.colors[i];
			}
			
		}
		
		
		
		checkColor(choosedColor);
		
	}

	private int[] updateColors(int[] colorsOcc, Color color) {
		
		for(int i = 0; i < Filler.colors.length; i++){
			
			if(color == Filler.colors[i])
				colorsOcc[i] ++;
			
		}
		
		return colorsOcc;
	}

	

}