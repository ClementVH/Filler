package Main;

import java.awt.Color;
import java.util.Random;

import Gui.InGameGui;
import Gui.SettingsGui;
import Gui.StartMenuGui;
import Gui.VictoryGui;
import Map.EMapType;
import Player.Player;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.data.JSONObject;

public class Filler{
	
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
	
	public static EAI AI;
	
	static JSONObject settingsJSON;
	
	public static Color[] colors;
	
	public static PFont font;

	public static Player winner;
	
	public Filler(PApplet parent){
		
		p = parent;
		
		p.size(800, 600);
		
		font = Filler.p.loadFont("KristenITC-Regular-16.vlw");
		
		loadFiles();
	
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
			
			case VICTORY:	VictoryGui.update();  
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
			case VICTORY:	VictoryGui.mousePressed(mouseX, mouseY); 
							break;
		}
	
	}
		
	public void keyPressed(){
		
		
		switch(gameState){
		
			case MENU : 	break;
							
			case INGAME : 	InGameGui.keyPressed();
							break;
			case SETTINGS:	break;
			
			case VICTORY:
							break;
		default:
			break;
		
		}
		
		
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
	
	public static void initPositions() {
		
		playerPositions = new int[4][2];
		
		switch (mapType) {
		
		case SQUARE:	playerPositions[0] = new int[]{0,0};
						playerPositions[1] = new int[]{GRID_WIDTH - 1,0};
						playerPositions[2] = new int[]{0,GRID_HEIGHT - 1};
						playerPositions[3] = new int[]{GRID_WIDTH - 1,GRID_HEIGHT - 1};
						break;
		
		case TRIANGLE:	
			
						if(GRID_WIDTH%2 == 0){
							
							playerPositions[0] = new int[]{0,GRID_HEIGHT/2};
							playerPositions[1] = new int[]{GRID_WIDTH - 1,GRID_HEIGHT/2};
							playerPositions[2] = new int[]{GRID_WIDTH/2, 0};
							playerPositions[3] = new int[]{GRID_WIDTH/2,GRID_HEIGHT - 1};
							
						}
						else if(GRID_WIDTH%2 != 0){
							
							playerPositions[0] = new int[]{0,GRID_HEIGHT/2};
							playerPositions[1] = new int[]{GRID_WIDTH - 1,GRID_HEIGHT/2};
							playerPositions[2] = new int[]{GRID_WIDTH/2 + 1, 0};
							playerPositions[3] = new int[]{GRID_WIDTH/2 + 1,GRID_HEIGHT - 1};
							
						}
						break;
		
		default:
						break;
		
		
		}
		
		
		
		
		
		
		
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
		
		case "TRIANGLE":
			mapType = EMapType.TRIANGLE;
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
		
		switch (settingsJSON.getString("AI")) {
		
		case "RANDOM":
			AI = EAI.RANDOM;
			break;
		
		case "EASY":
			AI = EAI.EASY;
			break;
		default:
			AI = EAI.EASY;
			break;
		}
		
		nbHumanPlayer = settingsJSON.getInt("nbHumanPlayer");
		nbAIPlayer = settingsJSON.getInt("nbAIPlayer");
		
		
		
		initMapType();
		
		if(settingsJSON.getInt("gridSize")%2 != 0)
			settingsJSON.setInt("gridSize", settingsJSON.getInt("gridSize") + 1);
		
		switch (mapType) {
		
		case SQUARE:	GRID_WIDTH 	= settingsJSON.getInt("gridSize");
						GRID_HEIGHT = settingsJSON.getInt("gridSize");
						break;
		
		case TRIANGLE:	GRID_WIDTH 	= settingsJSON.getInt("gridSize") / 2;
						GRID_HEIGHT = settingsJSON.getInt("gridSize");
						break;
		
		default:
						break;
		
		
		}
		
		
		
		updateScale();
		initColors(); 
		initPositions();
		initNames();
		
	}
	
	private void loadFiles() {
		
		startMenuBG = p.loadImage("startMenuBG.png"); // Chargement des images utilisées;
		inGameBG = p.loadImage("inGameBG.png");
		settingsBG = p.loadImage("settingsBG.png");
		editButton = p.loadImage("editButton.png");
		
		settingsJSON = p.loadJSONObject("settings.json");
		
	}

	public static void replay() {
		
		gameState = GameState.INGAME;
		
		Game.begin();
		
	}
	
	public static void updateScale(){
		
		switch (mapType) {
		
		case SQUARE:	scale = 600f/GRID_WIDTH;
						break;
		
		case TRIANGLE:	scale = 600f/GRID_WIDTH * 0.85f;
						break;
		
		default:
						break;
		
		
		}
		
	}

}
