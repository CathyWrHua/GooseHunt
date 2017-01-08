
public class Goose extends FlyingThing {
	private float gpaDeduction;
	private int pointsScored; 
	
	//Default constructor with additional parameters of GPA and points
	Goose(){
		super();
		gpaDeduction = 0;
		pointsScored = 0;
	}
	
	public void update(){
		changeX();
		changeY();
	
		if (lifetime == 0){
			isFlying = false;
			gpaDeduction = -0.5f;
		}
	}
	
	public void setIsStillAlive(boolean isShot){
		if (isShot){
			isStillAlive = false;
			pointsScored = 10;
		}
	}
	
	public int getPointsScored (){
		return pointsScored;
	}
	
	public float getGpaDeduction() {
		return gpaDeduction;
	}
}
