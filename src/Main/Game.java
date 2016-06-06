package Main;

import java.util.ArrayList;

import Map.Map;
import Map.MapType;
import Map.SquareMap.SquareMap;
import Map.TriangleMap.TriangleMap;
import Player.ComputerPlayer;
import Player.HumanPlayer;
import Player.Player;

public class Game {
	
	public static Map<MapType> map;	
	
	public static ArrayList<Player> players;
	
	public Game(){
		
		players = new ArrayList<Player>();
		
		switch(Filler.mapType){
		
		case SQUARE: map = new SquareMap();
					Filler.initPositions();
					break;
					
		case TRIANGLE: map = new TriangleMap();
					Filler.initPositions();
			break;
		
		default:
			break;
			
		}
		
		for(int i = 0; i < Filler.nbHumanPlayer; i++){
			
			players.add(new HumanPlayer("" + Filler.playerNames[i], i + 1, Filler.playerPositions[i]));
			
		}
		
		for(int i = 0; i < Filler.nbAIPlayer; i++){
			
			players.add(new ComputerPlayer("" + Filler.playerNames[i + Filler.nbHumanPlayer], i + Filler.nbHumanPlayer + 1, Filler.playerPositions[i + Filler.nbHumanPlayer]));
			
		}
		
		Filler.currentPlayer = players.get(0);
		
	}
	
	

	public static void begin(){
				
		Filler.game = new Game();
		
		Filler.p.frameRate(60);
		
	}

	public void update() {
		
		
		
		Filler.currentPlayer.update();
		
		display();
		
		
	}
	
	public void display(){
		
		map.display();
				
		
	}



	public static boolean isFinished() {
		
		int somme = 0; 
		Player best = players.get(0);
		for(Player p : players){
			if(p.score > best.score) best = p;
			somme += p.score;
			
		}
		
		if(somme == Filler.GRID_WIDTH * Filler.GRID_HEIGHT){
			
			Filler.winner = best;
			
			return true;
		}
		
		return false;
	}

}
