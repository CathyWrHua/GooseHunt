
public class Drone extends FlyingThing{
	private float gpaDeduction;
	
	Drone(){
		super();
		gpaDeduction = 0;
	}
	
	public void update(){
		changeX();
		changeY();
	
		if (lifetime == 0){
			isFlying = false;
		}
	}
	
	public void setIsStillAlive(boolean isShot){
		if (isShot){
			isStillAlive = false;
			gpaDeduction = 1.0f;
		}
	}
	
	public float getGpaDeduction() {
		return gpaDeduction;
	}
}
