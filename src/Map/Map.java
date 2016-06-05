package Map;

import java.util.ArrayList;

public abstract class Map<T> extends ArrayList<ArrayList<T>>{
	
	private static final long serialVersionUID = 1L;

	public Map(){
		super();
	}
	
	public abstract void display();
	
	

}
