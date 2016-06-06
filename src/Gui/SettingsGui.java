package Gui;

import javax.swing.JOptionPane;

import Main.Filler;
import Main.GameState;
import Main.Main;
import Map.EMapType;
import processing.core.PConstants;

public class SettingsGui extends Gui{

	public static void update() {
		
		display();
		
	}
	
	static int yOffsetPlayerNames = 40;
	
	private static void display() {
		
		Filler.p.background(Filler.settingsBG);
		
		Filler.p.textFont(Filler.font);
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
		
		Filler.p.textAlign(PConstants.CENTER);
		
		Filler.p.text(Filler.mapType.name(), 565, 127);
		
		Filler.p.textSize(18);
		
		Filler.p.textAlign(PConstants.RIGHT);
		
		Filler.p.text(Filler.GRID_HEIGHT, 430, 208);
		
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
	
	static int previousMapTypeX = 383;
	static int previousMapTypeY = 104;
	static int previousMapTypeWidth = 36;
	static int previousMapTypeHeight = 33;
	
	static int nextMapTypeX = 709;
	static int nextMapTypeY = 103;
	static int nextMapTypeWidth = 41;
	static int nextMapTypeHeight = 35;
	
	static int editSizeX = 439;
	static int editSizeY = 189;
	static int editSizeWidth = 22;
	static int editSizeHeight = 22;
	
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
				
				String res = JOptionPane.showInputDialog("What's your name?");
				
				try {
					Filler.playerNames[i + Filler.nbHumanPlayer] = (res == null)? Filler.playerNames[i + Filler.nbHumanPlayer] : res;
				} catch (Exception e) {
					
				}
				
			}
			
		}
		
		if (isInRect(previousMapTypeX, previousMapTypeY, previousMapTypeWidth, previousMapTypeHeight, mouseX, mouseY)) {
			
			int i = indexOf(Filler.mapType);
			
			Filler.mapType = (i == 0)? EMapType.values()[ EMapType.values().length-1] : EMapType.values()[i-1];
			
		}
		
		if (isInRect(nextMapTypeX, nextMapTypeY, nextMapTypeWidth, nextMapTypeHeight, mouseX, mouseY)) {
			
			int i = indexOf(Filler.mapType);
			
			Filler.mapType = (i == EMapType.values().length-1)? EMapType.values()[0] : EMapType.values()[i+1];
			
		}
		
		if (isInRect(editSizeX, editSizeY, editSizeWidth, editSizeHeight, mouseX, mouseY)) {
			
			String res = JOptionPane.showInputDialog("Grid size ?");
			
			try {
				Filler.GRID_HEIGHT = Integer.parseInt(res);
				Filler.GRID_WIDTH = Filler.GRID_HEIGHT/2;
				Filler.updateScale();
			} catch (Exception e) {}
			
			
			
			
			
			
		}
		
	}

	private static int indexOf(EMapType mapType) {
		
		for(int i = 0; i < EMapType.values().length; i++){
			
			if(mapType == EMapType.values()[i]) return i;
			
		}
		
		return 0;
	}
	
	

}
