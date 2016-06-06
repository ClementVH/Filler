package Gui;

import Main.Filler;
import processing.core.PConstants;

public class VictoryGui extends Gui{
	
	public static void update(){
		
		display();
		
	}
	
	static int menuX = 325;
	static int menuY = 250;
	static int menuWidth = 150;
	static int menuHeight = 50;
	
	static int replayX = 325;
	static int replayY = 340;
	static int replayWidth = 150;
	static int replayHeight = 50;
	
	public static void display() {
		
		Filler.p.background(Filler.inGameBG);
		
		Filler.p.fill(255);
		
		Filler.p.textAlign(PConstants.CENTER);
		
		Filler.p.textSize(40);
		
		Filler.p.text("Victory " + Filler.winner.name, 800/2, 150);
		
		
		Filler.p.textSize(30);
		Filler.p.text("Menu", 800/2, 285);
		
		Filler.p.text("Rejouer", 800/2, 375);
		
		Filler.p.noFill();
		Filler.p.stroke(255);
		Filler.p.strokeWeight(2.5f);
		Filler.p.rect(325, 250, 150, 50);
		Filler.p.rect(325, 340, 150, 50);
		
	}
	
	public static void mousePressed(float mouseX, float mouseY){
		
		if (isInRect(menuX, menuY, menuWidth, menuHeight, mouseX, mouseY)) {
			
			Filler.reset();
			
		}
		
		if (isInRect(replayX, replayY, replayWidth, replayHeight, mouseX, mouseY)) {
			
			Filler.replay();
			
		}
		
	}

}
