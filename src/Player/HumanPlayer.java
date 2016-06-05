package Player;

public class HumanPlayer extends Player{
	
	public HumanPlayer(String name, int nb, int[] position){
		super(name, nb, position);
	}

	@Override
	public void update() {
		
		if(choosedColor != null){
			
			color = choosedColor;
			
			fill();
			
			choosedColor = null;
			
			pass();
			
		}
		
		calculateScore();
		
		
		
		
	}

	

}
