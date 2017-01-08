//Author: Cathy Hua
//Date: January 8, 2017
//Description: Player stats for the game

public class GameStatistics {
	private float gpa;
	private int ammo;
	private int score;
	private boolean hasLost;
	
	//Default constructor
	GameStatistics() {
		gpa = 4.0f;
		ammo = 5;
		hasLost = true;
		score = 0;
		hasLost = false;
	}
	
	//getter function for current gpa
	public float getGpa () {
		return gpa;
	}
	
	//for decrementing the gpa
	public void decreaseGpa (float gpa){
		if (this.gpa - gpa > 0){
			this.gpa -= gpa;
		}
		else {
			this.gpa = 0;
			hasLost = true;
		}
	}
	
	//returns victory state
	public boolean getLost () {
		return hasLost;
	}
	
	//getter function for amount of ammo left
	public int getAmmo () {
		return ammo;
	}
	
	//changes amount of ammo left
	public boolean changeAmmo (int change) {
		if (ammo == 0){
			return false;
		}
		else if (ammo+change > 5){
			ammo = 5;
		}
		else if (ammo + change <=0){
			ammo = 0;
		}
		else{
			ammo += change;
		}
		return true;
	}
	
	//returns current score
	public int getScore () {
		return score;
	}
	
	//adds score
	public void addScore (int score){
		this.score += score;
	}
}

