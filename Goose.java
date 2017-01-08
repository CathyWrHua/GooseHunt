//Author: Cathy Hua
//Date: January 8, 2017
//Description: Goose class

public class Goose extends FlyingThing {
	private float gpaDeduction;
	private int pointsScored; 
	
	//Default constructor with additional parameters of GPA and points
	Goose(){
		super();
		gpaDeduction = 0;
		pointsScored = 0;
	}
	
	//kill function that can be called for kill action of the goose
	public void kill(){
		isStillAlive = false;
		pointsScored = 1;
	}
	
	//returns pointsScored for killing (or not killing) goose
	public int getPointsScored (){
		return pointsScored;
	}
	
	//returns deduction of gpaDeduction
	public float getGpaDeduction() {
		return gpaDeduction;
	}
	
	//changes lifetime of the goose before it flies away
	protected boolean changeLifetime (int time){
		if (lifetime - time < 0){
			lifetime = 0;
			gpaDeduction = 0.5f;
			return false;
		}
		else{
			lifetime -= time;
		}
		return true;
	}
}
