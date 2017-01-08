
public class Drone extends FlyingThing{
	private float gpaDeduction;
	
	Drone(){
		super();
		gpaDeduction = 0;
	}
	
	public void kill(){
		isStillAlive = false;
		gpaDeduction = 1.0f;
	}
	
	public float getGpaDeduction() {
		return gpaDeduction;
	}
	
}
