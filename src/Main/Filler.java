package Main;

import java.awt.Color;
import java.util.Random;

import Gui.InGameGui;
import Gui.SettingsGui;
import Gui.StartMenuGui;
import Map.EMapType;
import Player.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;

public class Filler {
	
	public static PApplet p;
	
	public static float scale;
	
	public static int 			GRID_WIDTH;
	public static int 			GRID_HEIGHT;
	
	public static EMapType 		mapType;
	public static GameState 	gameState;
	public static Game 			game;
	public static PImage 		startMenuBG;
	public static PImage 		inGameBG;
	public static PImage 		settingsBG;
	public static PImage		editButton;
	
	public static int nbHumanPlayer;
	public static int nbAIPlayer;
	
	public static Player currentPlayer;
	
	public static String[] playerNames;
	
	public static int[][] playerPositions;
	
	static JSONObject settingsJSON;
	
	// Un tableau des couleurs utilisées dans jeu
	public static Color[] colors;
	
	public Filler(PApplet parent){
		
		p = parent;
		
		p.size(800, 600);
		
		startMenuBG = parent.loadImage("startMenuBG.png"); // Chargement des images utilisées;
		inGameBG = parent.loadImage("inGameBG.png");
		settingsBG = parent.loadImage("settingsBG.png");
		editButton = parent.loadImage("editButton.png");
		
		settingsJSON = p.loadJSONObject("settings.json");
		
	
		setup();
		
	}

	public void update(){
		
		switch(gameState){
			
			case MENU : 	StartMenuGui.update();
							break;
			case INGAME : 	InGameGui.update();
							game.update();
							break;
			case SETTINGS:	SettingsGui.update();  
							break;
		
		}
		
	}
	
	public void mousePressed(int mouseX, int mouseY){
		
		switch(gameState){
		
			case MENU : 	StartMenuGui.mousePressed(mouseX, mouseY);
							break;
			case INGAME : 	InGameGui.mousePressed(mouseX, mouseY);
							break;
			case SETTINGS:	SettingsGui.moussePressed(mouseX, mouseY);
							break;
		}
	
	}
		
	public void keyPressed(){
		
		
		switch(gameState){
		
			case MENU : 	break;
							
			case INGAME : 	InGameGui.keyPressed();
							break;
			case SETTINGS:	break;
		
		}
		
		
	}
		
	
	
	public static void play(){
		
		gameState = GameState.INGAME;
		Game.begin();
		
	}
	
	public static void quit(){
		
		p.exit();
		
	}
	
	public static void settings(){
		
		gameState = GameState.SETTINGS;
		
	}
	
	private static void initColors(){
		colors = new Color[6];
		
		colors[0] = Color.decode(settingsJSON.getString("color1"));
		colors[1] = Color.decode(settingsJSON.getString("color2"));
		colors[2] = Color.decode(settingsJSON.getString("color3"));
		colors[3] = Color.decode(settingsJSON.getString("color4"));
		colors[4] = Color.decode(settingsJSON.getString("color5"));
		colors[5] = Color.decode(settingsJSON.getString("color6"));
	}
	
	public static Color randomColor(){
		
		Random r = new Random();
		
		return Filler.colors[r.nextInt(colors.length)];
		
	}
	
	private static void initPositions() {

		playerPositions = new int[4][2];
		
		playerPositions[0] = new int[]{0,0};
		playerPositions[1] = new int[]{GRID_WIDTH - 1,0};
		playerPositions[2] = new int[]{0,GRID_HEIGHT - 1};
		playerPositions[3] = new int[]{GRID_WIDTH - 1,GRID_HEIGHT - 1};
		
		
	}
	
	private static void initNames(){
		
		playerNames = 
				new String[]{
								settingsJSON.getString("name1"),
								settingsJSON.getString("name2"),
								settingsJSON.getString("name3"),
								settingsJSON.getString("name4")
							};
		
	}
	
	private static void initMapType(){
		switch (settingsJSON.getString("mapType")) {
		case "SQUARE":
			mapType = EMapType.SQUARE;
			break;

		default:
			mapType = EMapType.SQUARE;
			break;
		}
	}

	public static void reset() {
		
		setup();
		
		
	}

	private static void setup() {
		
		gameState = GameState.MENU;
		
		nbHumanPlayer = settingsJSON.getInt("nbHumanPlayer");
		nbAIPlayer = settingsJSON.getInt("nbAIPlayer");
		
		
		GRID_WIDTH 	= settingsJSON.getInt("gridSize");
		GRID_HEIGHT = settingsJSON.getInt("gridSize");
		
		scale = 600f/GRID_WIDTH;
		
		initMapType();
		initColors(); 
		initPositions();
		initNames();
		
	}

}
