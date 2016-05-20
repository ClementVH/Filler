package Gui;

import javax.swing.JOptionPane;

import Main.Filler;
import Main.GameState;
import Main.Main;
import processing.core.PConstants;
import processing.core.PFont;

public class SettingsGui extends IGui{

	public static void update() {
		
		display();
		
	}
	
	static int yOffsetPlayerNames = 40;
	
	private static void display() {
		
		Filler.p.background(Filler.settingsBG);
		
		PFont font = Filler.p.loadFont("KristenITC-Regular-16.vlw");

		Filler.p.textFont(font);
		Filler.p.textSize(18);
		
		Filler.p.stroke(255);
		Filler.p.strokeWeight(2);
		Filler.p.fill(255);
		
		Filler.p.textAlign(PConstants.LEFT);
		
		for(int i = 0; i < Filler.nbHumanPlayer; i++){
			
			Filler.p.text(Filler.playerNames[i], 250, 375 + i * yOffsetPlayerNames);
			
			Filler.p.image(Filler.editButton, humanEditX, baseEditY + i * yOffsetPlayerNames);
			
		}
		
		for(int i = 0; i < Filler.nbAIPlayer; i++){
			
			Filler.p.text(Filler.playerNames[i + Filler.nbHumanPlayer], 560, 375 + i * yOffsetPlayerNames);
			
			Filler.p.image(Filler.editButton, computerEditX, baseEditY + i * yOffsetPlayerNames);
			
		}
		
		
		
	}
	
	static int addHPlayerX = 438;
	static int addHPlayerY = 309;
	static int addHPlayerWidth = 30;
	static int addHPlayerHeight = 28;
	
	static int removeHPlayerX = 475;
	static int removeHPlayerY = 308;
	static int removeHPlayerWidth = 30;
	static int removeHPlayerHeight = 30;
	
	static int addAIPlayerX = 691;
	static int addAIPlayerY = 309;
	static int addAIPlayerWidth = 30;
	static int addAIPlayerHeight = 28;
	
	static int removeAIPlayerX = 728;
	static int removeAIPlayerY = 308;
	static int removeAIPlayerWidth = 30;
	static int removeAIPlayerHeight = 30;
	
	static int returnX = 3;
	static int returnY = 543;
	static int returnWidth = 52;
	static int returnHeight = 52;
	
	static int humanEditX = 438;
	static int computerEditX = 720;
	static int baseEditY = 357;
	static int editWidth = 22;
	static int editHeight = 22;
	
	
	public static void moussePressed(int mouseX, int mouseY) {
		
		if(isInRect(addHPlayerX, addHPlayerY, addHPlayerWidth, addHPlayerHeight, mouseX, mouseY)){
		
			Filler.nbHumanPlayer ++;
			Filler.nbHumanPlayer = Main.clamp(Filler.nbHumanPlayer, 0 , 4 - Filler.nbAIPlayer);
		}
		
		else if (isInRect(removeHPlayerX, removeHPlayerY, removeHPlayerWidth, removeHPlayerHeight, mouseX, mouseY)) {
			Filler.nbHumanPlayer --;
			Filler.nbHumanPlayer = Main.clamp(Filler.nbHumanPlayer, 0 , 4 - Filler.nbAIPlayer);
		}
		
		else if (isInRect(addAIPlayerX, addAIPlayerY, addAIPlayerWidth, addAIPlayerHeight, mouseX, mouseY)) {
			Filler.nbAIPlayer ++;
			Filler.nbAIPlayer = Main.clamp(Filler.nbAIPlayer, 0 , 4 - Filler.nbHumanPlayer);
		}
		
		else if (isInRect(removeAIPlayerX, removeAIPlayerY, removeAIPlayerWidth, removeAIPlayerHeight, mouseX, mouseY)) {
			Filler.nbAIPlayer --;
			Filler.nbAIPlayer = Main.clamp(Filler.nbAIPlayer, 0 , 4 - Filler.nbHumanPlayer);
		}
		
		if (isInRect(returnX, returnY, returnWidth, returnHeight, mouseX, mouseY)) {
			Filler.gameState = GameState.MENU;	
		}
		
		for(int i = 0; i < Filler.nbHumanPlayer; i++){
			
			if(isInRect(humanEditX, baseEditY + i * yOffsetPlayerNames, editWidth, editHeight, mouseX, mouseY)){
				
				Filler.playerNames[i] = JOptionPane.showInputDialog("What's your name?");
				
			}
			
		}
		
		for(int i = 0; i < Filler.nbAIPlayer; i++){
			
			if(isInRect(computerEditX, baseEditY + i * yOffsetPlayerNames, editWidth, editHeight, mouseX, mouseY)){
				
				Filler.playerNames[i + Filler.nbHumanPlayer] = JOptionPane.showInputDialog("What's your name?");
				
			}
			
		}
		

	}
	
	

}
