package Gui;

import Main.Filler;

public class StartMenuGui extends IGui {
	
	static int PlayX = 492;
	static int PlayY = 212;
	static int PlayWidth = 294;
	static int PlayHeight = 49;
	
	static int SettingsX = 492;
	static int SettingsY = 322;
	static int SettingsWidth = 294;
	static int SettingsHeight = 49;
	
	static int QuitX = 492;
	static int QuitY = 432;
	static int QuitWidth = 294;
	static int QuitHeight = 49;
	
	public static void update(){
		
		display();
		
	}
	
	public static void display() {
		
		Filler.p.background(Filler.startMenuBG);
		
	}
	
	public static void mousePressed(float mouseX, float mouseY){
		
		if(isInRect(PlayX, PlayY, PlayWidth, PlayHeight, mouseX, mouseY)){
			
			Filler.play();
			
		}
		
		if(isInRect(SettingsX, SettingsY, SettingsWidth, SettingsHeight, mouseX, mouseY)){
			
			Filler.settings();
			
		}
		
		if(isInRect(QuitX, QuitY, QuitWidth, QuitHeight, mouseX, mouseY)){
			
			Filler.quit();
			
		}
		
	}

}
