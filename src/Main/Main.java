package Main;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.net.*;

public class Main extends PApplet{
	
	public static void main(String args[]) {
	    PApplet.main(new String[] {Main.class.getName()});
	    	    
	}
	
	Filler filler;
	
	public void settings(){
		
		filler = new Filler(this);
		
	}
	
	
	public void draw(){
		
		filler.update();
		
	}
	
	@Override
	public void mousePressed(){
		
		filler.mousePressed(mouseX, mouseY);
		
	}

	
	@Override
	public void keyPressed(KeyEvent e){
		
		filler.keyPressed();
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void handleKeyEvent(KeyEvent event) {
        if (!this.keyRepeatEnabled && event.isAutoRepeat()) {
            return;
        }
        this.keyEvent = event;
        this.key = event.getKey();
        this.keyCode = event.getKeyCode();
        switch (event.getAction()) {
            case 1: {
                this.keyPressed = true;
                this.keyPressed(this.keyEvent);
                break;
            }
            case 2: {
                this.keyPressed = false;
                this.keyReleased(this.keyEvent);
                break;
            }
            case 3: {
                this.keyTyped(this.keyEvent);
            }
        }
        this.handleMethods("keyEvent", new Object[]{event});
        
    }
	
	public static int clamp(int nb, int min, int max) {
		
		if(nb < min) nb = min;
		if(nb > max) nb = max;
		
		return nb;
		
	}

}
