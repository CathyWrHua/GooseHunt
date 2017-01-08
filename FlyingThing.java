import java.util.concurrent.ThreadLocalRandom;

public class FlyingThing {
	private float posX, posY;
	private float speedX, speedY;
	protected int lifetime;//lifetime in seconds
	protected boolean isFlying;
	protected boolean isStillAlive;
	
	//Creates default flyingThing
	FlyingThing(){
		isFlying = true;
		isStillAlive = true;
		posX = (float) ThreadLocalRandom.current().nextDouble(-1, 1);
		posY = -1; // note that posY very likely will need to be changed
		lifetime = ThreadLocalRandom.current().nextInt(150, 210);
		speedY = (1-posY)/(lifetime);
	}
	
	//updates the object position and whether it was shot at or not
	public void update() {
		changeX();
		changeY();
		
		isFlying = changeLifetime(1);
	}
	
	//change x coordinate according to random x number generator;
	protected void changeX(){
		speedX = (float) ThreadLocalRandom.current().nextDouble(-0.10, 0.10);
		if (posX + speedX < -1){
			posX = -1;
		}
		else if (posX + speedX > 1){
			posX = 1;
		}
		else {
			posX += speedX;
		}
	}
	
	//changes the Y coordinate according to random number generation
	protected void changeY(){
		posY += speedY;
	}
	
	//getter function for isFlying
	public boolean getIsFlying(){
		return isFlying;
	}
	
	public void flyAway(){
		isFlying = false;
	}
	
	public int getLifetime(){
		return lifetime;
	}
	
	//getter function for isStillAlive
	public boolean getisStillAlive(){
		return isStillAlive;
	}
	
	//setter function for if object is shot
	public void kill (){
		isStillAlive = false;
	}
	
	//getter function for posX
	public float getPosX() {
		return posX;
	}
	
	//getter function for posY
	public float getPosY() {
		return posY;
	}
	
	//gameloop timer changes lifetime
	protected boolean changeLifetime (int time){
		if (lifetime - time < 0){
			lifetime = 0;
			return false;
		}
		else{
			lifetime -= time;
		}
		return true;
	}
}
