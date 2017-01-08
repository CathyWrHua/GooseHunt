//Author: Cathy Hua
//Date: January 8, 2017
//Description: parent class for goose and drone

import java.util.concurrent.ThreadLocalRandom;

public class FlyingThing {
	private float posX, posY;
	private float speedX, speedY;
	protected int lifetime;//lifetime in seconds
	protected boolean isFlying;
	protected boolean isStillAlive;
	protected boolean disappeared;
	private int level;
	private final float BASESPEED = 0.002f;
	
	//Creates default flyingThing
	FlyingThing(){
		isFlying = true;
		isStillAlive = true;
		disappeared = false;
		posX = (float) ThreadLocalRandom.current().nextDouble(-1, 1);
		posY = -1; // note that posY very likely will need to be changed
		lifetime = ThreadLocalRandom.current().nextInt(150, 210);
		level = 1;
	}
	
	//updates the object position and whether it was shot at or not
	public void update(int level) {
		setLevel(level);
		float accY = 1.001f;
		if (isFlying && isStillAlive){
			changeX();
			changeY();
			
			isFlying = changeLifetime(1);
		}
		else if (isStillAlive == false) {
			if (posY > -1) {
				posY -= (accY * BASESPEED*level);
			}
			else {
				disappeared = true;
			}
		}
		else {
			if (posY < 1) {
				posY += (accY * BASESPEED*level);
			}
			else {
				disappeared = true;
			}
		}
	}
	
	//change x coordinate according to random x number generator;
	protected void changeX(){
		if (lifetime% 15 == 0)
			speedX = (float) ThreadLocalRandom.current().nextDouble(-1*BASESPEED*level*0.5, BASESPEED*level*0.5);
		if (posX + speedX < -1){
			posX -= speedX;
		}
		else if (posX + speedX > 1){
			posX -= speedX;
		}
		else {
			posX += speedX;
		}
	}
	
	//changes the Y coordinate according to random number generation
	protected void changeY(){
		if (lifetime% 15 == 0)
			speedY = (float) ThreadLocalRandom.current().nextDouble(-1*BASESPEED*level*0.1, BASESPEED*level*0.5);
		if (posY + speedY < -1 /*Change based on boundaries of gameplay screen*/ ){
			posY -= speedY;
		}
		else if (posY + speedY > 1 /*Change based on boundaries of gameplay screen*/ ){
			posY -= speedY;
		}
		else {
			posY += speedY;
		}
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
	public void kill(){
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
	
	public boolean getDisappeared() {
		return disappeared;
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
	
	public void setLevel (int level) {
		if (level <= 10){
			this.level = 10;
		}
		else if (level >= 1){
			this.level = 1;
		}
		else
			this.level = level;
		
	}
}
