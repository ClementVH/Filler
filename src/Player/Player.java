package Player;

import java.awt.Color;
import java.util.ArrayList;

import Main.Filler;
import Main.Game;
import Map.MapType;

public abstract class Player {
	
	public Color color;
	
	public String name;

	public Color choosedColor;
	
	public int nb;
	
	public int score = 1;
	
	public Player(String name, int nb, int[] position){
		
		this.name = name;
		
		this.nb = nb;
		
		color = Game.map.get(position[0]).get(position[1]).color;
		
		Game.map.get(position[0]).get(position[1]).domination = nb;
		
	}
	
	public abstract void update();
	
	public void fill(){
		
		ArrayList<MapType> group = new ArrayList<MapType>();
		
		for(int i = 0; i < Game.map.size(); i++){
			ArrayList<MapType> column = (ArrayList<MapType>) Game.map.get(i);
			for(int j =0; j < column.size(); j ++){
				
				MapType cell = column.get(j);
				
				if(cell.domination == nb){
					
					cell.color = color;
					
					for(int[] neighborPos : cell.neighbors){
						
						int x, y;
						x = neighborPos[0];
						y = neighborPos[1];
						
						if(x >= 0 && y >= 0 && x < Filler.GRID_WIDTH && y < Filler.GRID_HEIGHT){
							if(Game.map.getCell(x, y).color == color)
								group.add(Game.map.getCell(x, y));
						}
					}
				}
			}
			
		}
		
		fill(group);
		
	}
	
	public void fill(ArrayList<MapType> group){
		
		for(int i = 0; i < group.size(); i++){
			group.get(i).color = color;
			group.get(i).domination = nb;
		}
		
		ArrayList<MapType> newGroup = new ArrayList<MapType>();
		
		for(int i = 0; i < group.size(); i++){
			
			MapType cell = group.get(i);
			
			for(int[] neighborPos : cell.neighbors){
				
				int x, y;
				x = neighborPos[0];
				y = neighborPos[1];
				
				if(x >= 0 && y >= 0 && x < Filler.GRID_WIDTH && y < Filler.GRID_HEIGHT){
					if(Game.map.getCell(x, y).color == color && Game.map.getCell(x, y).domination == 0)
						newGroup.add(Game.map.getCell(x, y));
				}
			}
			
		}
		
		
		
		if(newGroup.size() == 0){ choosedColor = null; return;}
		else fill(newGroup);
		
	}

	public void pass(){
		
		if(nb >= Filler.nbHumanPlayer + Filler.nbAIPlayer){
			Filler.currentPlayer = Game.players.get(0);
			
		}
		else{
			Filler.currentPlayer = Game.players.get(nb);
		}
		
	}
	
	public static boolean checkColor(Color color){
		
		boolean ok = true;
		
		for(int i = 0; i < Game.players.size(); i++){
			
			if(color == Game.players.get(i).color)
				ok = false;			
		}
		
		return ok;
		
	}
	
	protected void calculateScore() {
		
		score = 0;
		
		for(int i = 0; i < Filler.GRID_WIDTH; i++){
			
			ArrayList<MapType> column = Game.map.get(i);
			
			for(int j = 0; j < Filler.GRID_HEIGHT; j++){
				
				MapType cell = column.get(j);
				
				if(cell.domination == nb){
					score += 1;
				}
				
			}
			
		}
		
	}
	
	public void resetVisited(){
		for(ArrayList<MapType> column : Game.map){
			for(MapType cell : column){
				cell.visited = false;
			}
		}
	}
	
	public Player chooseBestEnnemy(){
		
		Player bestPlayer = null;
		
		int maxScore = 0;
		
		for(Player p : Game.players){
			
			if(p.score >= maxScore && p.nb != nb){
				maxScore = p.score;
				bestPlayer = p;
			}
			
		}
		
		return bestPlayer;
		
	}

}
