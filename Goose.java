
public class Goose extends FlyingThing {
	private float gpaDeduction;
	private int pointsScored; 
	
	//Default constructor with additional parameters of GPA and points
	Goose(){
		super();
		gpaDeduction = 0;
		pointsScored = 0;
	}
	
	public void kill(){
		isStillAlive = false;
		pointsScored = 10;
	}
	
	public int getPointsScored (){
		return pointsScored;
	}
	
	public float getGpaDeduction() {
		return gpaDeduction;
	}
	
	protected boolean changeLifetime (int time){
		if (lifetime - time < 0){
			lifetime = 0;
			gpaDeduction = -0.5f;
			return false;
		}
		else{
			lifetime -= time;
		}
		return true;
	}
}
