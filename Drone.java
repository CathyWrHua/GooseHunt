//Author: Cathy Hua
//Date: January 8, 2017
//Description: Drone class 

public class Drone extends FlyingThing{
	private float gpaDeduction;
	
	//default constructor with additional gpaDeduction value
	Drone(){
		super();
		gpaDeduction = 1.0f;
	}
	
	//returns GpaDeduction value for drone
	public float getGpaDeduction() {
		return gpaDeduction;
	}
	
}
