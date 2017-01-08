//Author: Cathy Hua
//Date: January 8, 2017
//Description: x coordinates for scrolling background


public class Background {
	private float x;
	private float maxX;
	
	//default constructor with double the size of the screen
	Background (){
		x = 0.0f;
		maxX = 4.0f;
	}
	
	public void update() {
		x = (x + 0.001 > maxX)? 0: x+0.001f;
	}
	
	public float getX() {
		return x;
	}
}
