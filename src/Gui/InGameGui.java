package Gui;

import java.awt.Color;

import javax.swing.JOptionPane;

import Main.Filler;
import Main.Game;
import Player.Player;
import processing.core.PConstants;
import processing.core.PFont;

public class InGameGui extends IGui{
	
	static int colorY = 300;
	static int redX = 610;
	static int offset = 32;
	static int orangeX = redX + 1*offset;
	static int yellowX = redX + 2*offset;
	static int greenX = redX + 3*offset;
	static int blueX = redX + 4*offset;
	static int purpleX = redX + 5*offset;
	
	static int colorWidth = 20;
	
	public static void update(){
		
		display();
		
	}
	
	public static void display(){
		
		Filler.p.background(Filler.inGameBG);
		
		PFont font = Filler.p.loadFont("KristenITC-Regular-16.vlw");

		Filler.p.textFont(font);
		Filler.p.textSize(18);
		
		Filler.p.stroke(255);
		Filler.p.strokeWeight(2);
		Filler.p.fill(255);
		
		Filler.p.textAlign(PConstants.CENTER);
		
		Filler.p.text("C'est le tour de :", 700, 100);
		
		Filler.p.text(Filler.currentPlayer.name, 700, 150);
		
		Filler.p.strokeWeight(1);
		
		
		
		colorRect(Filler.colors[0], redX, colorY, 20, 20);
		
		colorRect(Filler.colors[1], orangeX, colorY, 20, 20);
		
		colorRect(Filler.colors[2], yellowX, colorY, 20, 20);
		
		colorRect(Filler.colors[3], greenX, colorY, 20, 20);
		
		colorRect(Filler.colors[4], blueX, colorY, 20, 20);
		
		colorRect(Filler.colors[5], purpleX, colorY, 20, 20);
		
		
		
		Filler.p.fill(255);
		Filler.p.textAlign(PConstants.LEFT);
		
		for(int i = 0; i < Filler.nbHumanPlayer; i++){
			Filler.p.text(Game.players.get(i).name + " : " + Game.players.get(i).score, 610, 475 + i * 35);
		}
		for(int i = 0; i < Filler.nbAIPlayer; i++){
			Filler.p.text(Game.players.get(i + Filler.nbHumanPlayer).name + " : " + Game.players.get(i + Filler.nbHumanPlayer).score, 610, 475 + (i+Filler.nbHumanPlayer) * 35);
		}
		
	}
	
	public static void mousePressed(float mouseX, float mouseY){
		
		if(isInRect(redX, colorY, colorWidth, colorWidth, mouseX, mouseY) && Player.checkColor(Filler.colors[0])){			
			
			Filler.currentPlayer.choosedColor = Filler.colors[0];
			
		}
		
		if(isInRect(orangeX, colorY, colorWidth, colorWidth, mouseX, mouseY) && Player.checkColor(Filler.colors[1])){
			
			Filler.currentPlayer.choosedColor = Filler.colors[1];
			
		}

		if(isInRect(yellowX, colorY, colorWidth, colorWidth, mouseX, mouseY) && Player.checkColor(Filler.colors[2])){
	
			Filler.currentPlayer.choosedColor = Filler.colors[2];	
		}

		if(isInRect(greenX, colorY, colorWidth, colorWidth, mouseX, mouseY) && Player.checkColor(Filler.colors[3])){
	
			Filler.currentPlayer.choosedColor = Filler.colors[3];	
		}

		if(isInRect(blueX, colorY, colorWidth, colorWidth, mouseX, mouseY) && Player.checkColor(Filler.colors[4])){
	
			Filler.currentPlayer.choosedColor = Filler.colors[4];	
		}
		
		if(isInRect(purpleX, colorY, colorWidth, colorWidth, mouseX, mouseY) && Player.checkColor(Filler.colors[5])){
			
			Filler.currentPlayer.choosedColor = Filler.colors[5];	
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
	
	public static boolean alreadyChoosed(Color color){
		
		for(Player p : Game.players){
			
			Color c = p.color;
			
			if(color == c) return true;
			
		}
		
		return false;
	}
	
	public static void colorRect(Color color, float x, float y, float w, float h){
		
		Filler.p.fill(color.getRGB());
		Filler.p.rect(x , y, w, h);
		
		if(alreadyChoosed(color)){
			Filler.p.fill(Color.LIGHT_GRAY.getRGB(), 175);
			Filler.p.rect(x     , y, w, h);
		}
		
	}
	
	public static void keyPressed(){
		
		if(Filler.p.keyCode == 27){
			
			if(JOptionPane.showConfirmDialog(null, "Back to main menu ?", "Quitter",JOptionPane.YES_NO_OPTION) == 0){
				Filler.reset();
				
				
				
			}
			
		}
		
	}
	
}
