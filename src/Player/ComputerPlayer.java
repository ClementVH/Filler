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
		
		switch(Filler.AI){
			
		case RANDOM:
			randomAI();
			break;
		
		case EASY:
			easyAI();
			break;
		
		default:
			break;
		
		}
		
		color = choosedColor == null ? color : choosedColor;
		
		
		fill();
		
		choosedColor = null;
		
		pass();
		
		calculateScore();
		
	}

	
	private void randomAI() {
		
		Color color = Filler.randomColor();
		
		boolean ok = checkColor(color);
		
		choosedColor = ok ? color : null;
		
		
	}
	
	private void easyAI() {
		
		int[] colorsOcc = new int[6];
				
		for(ArrayList<MapType> column : Game.map){
			for(MapType cell : column){
				
				if(cell.domination == nb){
					
					for(int[] nCoords : cell.neighbors){
						
						int x = nCoords[0];
						int y = nCoords[1];
						
						if(x >= 0 && y >= 0 && x < Filler.GRID_WIDTH && y < Filler.GRID_HEIGHT){
							
							MapType neighbor = Game.map.getCell(x, y);
							
							if(!neighbor.visited){
								colorsOcc = updateColorsOcc(colorsOcc, neighbor.color);
								neighbor.visited = true;
							}
							
						}
						
					}
					
				}								
			}
		}	
		
		resetVisited();
		
		//Player bestEnnemy = chooseBestEnnemy();
		
		int maxOcc = 0;
		
		for(int i = 0; i < Filler.colors.length; i++){
			
			if(checkColor(Filler.colors[i]) && colorsOcc[i] >= maxOcc){
				maxOcc = colorsOcc[i];
				choosedColor = Filler.colors[i];
			}
			
		}
		
	}

	private int[] updateColorsOcc(int[] colorsOcc, Color color) {
		
		for(int i = 0; i < Filler.colors.length; i++){
			
			if(color == Filler.colors[i])
				colorsOcc[i] ++;
			
		}
		
		return colorsOcc;
	}

	

}
