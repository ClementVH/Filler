package Main;

import java.util.ArrayList;

import Map.Map;
import Map.MapType;
import Map.SquareMap.SquareMap;
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

}
