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
	
	//Creates default flyingThing
	FlyingThing(){
		isFlying = true;
		isStillAlive = true;
		disappeared = false;
		posX = (float) ThreadLocalRandom.current().nextDouble(-1, 1);
		posY = -1; // note that posY very likely will need to be changed
		lifetime = ThreadLocalRandom.current().nextInt(150, 210);
		speedY = (1-posY)/(lifetime);
	}
	
	//updates the object position and whether it was shot at or not
	public void update() {
		float accY = 1.001;
		if (isFlying && isStillAlive){
			changeX();
			changeY();
			
			isFlying = changeLifetime(1);
		}
		else if (isStillAlive == false) {
			if (posY > -1) {
				posY -= (accY * speedY);
			}
			else {
				disappeared = true;
			}
		}
		else {
			if (posY < 1) {
				posY += (accY * speedY);
			}
			else {
				disappeared = true;
			}
		}
	}
	
	//change x coordinate according to random x number generator;
	protected void changeX(){
		speedX = (float) ThreadLocalRandom.current().nextDouble(-0.001, 0.001);
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
		speedY = (float) ThreadLocalRandom.current().nextDouble(-0.0008, 0.0012);
		if (posY + speedY < -1 /*Change based on boundaries of gameplay screen*/ ){
			posY = -1;
		}
		else if (posY + speedY > 1 /*Change based on boundaries of gameplay screen*/ ){
			posY = 1;
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
}

