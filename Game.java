// Author: Tiger Dong
// Date: January 8th, 2017
// Description: Game class containing the update() logic for Goose Hunt.

import java.util.*;

public class Game {
	// Constants
	private final int GEESE_CAP = 10;
	private final int DRONES_CAP = 1;
	private final float GOOSE_HIT_RANGE = 0.05f;
	private final float DRONE_HIT_RANGE = 0.04f;
	private final float RELOADR = 0.04f;
	private final float RELOADX = -0.5f;
	private final float RELOADY = -0.5f;
	
	// Objects
	private Stack<GameState> states;
	private ArrayList<Goose> geese;
	private ArrayList<Drone> drones;
	private GameStatistics stats;
	private Background background;
	private Random random;
	
	// Variables
	private int maxGeese = 1;
	private int maxDrones = 0;
	private float gooseSpawnRate;
	private float droneSpawnRate;
	private float currentSpawn;
	private boolean previouslyClicked;
	
	public enum GameState {MENU, PLAY, ABOUT}
	
	// Constructor
	public Game() {
		states = new Stack<GameState>();
		geese = new ArrayList<Goose>(maxGeese);
		drones = new ArrayList<Drone>(maxDrones);
		stats = new GameStatistics();
		background = new Background();
		random = new Random();
		
		gooseSpawnRate = 0.05f;
		droneSpawnRate = gooseSpawnRate/10;
		previouslyClicked = false;
		
		states.push(GameState.MENU);
	}
	
	// Function that checks if a mouse click is within a square of length 2*h at (x, y)
	private boolean collided(float mouseX, float mouseY, float x, float y, float h) {
		if (mouseX > x-h && mouseX < x+h && mouseY > y-h && mouseY < y+h)
			return true;
		return false;
	}
	
	// Function that checks if a mouse click is within a circle of radius r at (x, y)
	private boolean circularCollided(float mouseX, float mouseY, float x, float y, float r) {
		float len = (float)Math.sqrt(Math.pow(mouseX-x, 2)+Math.pow(mouseY-y, 2));
		if (len < r)
			return true;
		return false;
	}

	// Accessor method for geese
	public ArrayList<Goose> getGeese() {
		return geese;
	}
	
	// Accessor method for drones
	public ArrayList<Drone> getDrones() {
		return drones;
	}
	
	// Accessor method for game statistics
	public GameStatistics getGameStatistics() {
		return stats;
	}
	
	// Function that updates all of the in-game objects
	public void update(float mouseX, float mouseY, boolean clicked) {
		switch(states.peek()) {
		case MENU:
			background.update();
			if (clicked && collided(mouseX, mouseY, 0, -0.2f, 0.2f)) {
				states.push(GameState.PLAY);
			} else if (clicked && collided(mouseX, mouseY, 0, -0.65f, 0.2f)) {
				states.push(GameState.ABOUT);
			}
			break;
		case ABOUT:
			background.update();
			if (clicked && collided(mouseX, mouseY, 0, -0.5f, 0.2f)) {
				states.pop();
			}
			break;
		case PLAY:
			currentSpawn = random.nextFloat();
			// Spawn new drones and geese
			if (droneSpawnRate > currentSpawn) {
				if (drones.size() < maxDrones)
					drones.add(new Drone());
				if (geese.size() < maxGeese)
					geese.add(new Goose());
			} else if (gooseSpawnRate > currentSpawn) {
				if (geese.size() < maxGeese)
					geese.add(new Goose());
			}
			// Update the objects
			for (int i = 0; i < geese.size(); i++) {
				geese.get(i).update(stats.getScore()/10);
				if (geese.get(i).getLifetime() == 0) {
					geese.get(i).flyAway();
					stats.decreaseGpa(geese.get(i).getGpaDeduction());
				}
				if (geese.get(i).getDisappeared()) {
					geese.remove(i);
				}
			}
			for (int j = 0; j < drones.size(); j++) {
				drones.get(j).update(stats.getScore()/10);
				if (drones.get(j).getLifetime() == 0) {
					drones.get(j).flyAway();
				}
				if (drones.get(j).getDisappeared()) {
					drones.remove(j);
				}
			}
			// Update the condition of the objects if they were clicked
			if (clicked && previouslyClicked == false && stats.getAmmo() > 0) {
				for (int i = 0; i < geese.size(); i++) {
					if (geese.get(i).getisStillAlive() == true && collided(mouseX, mouseY, geese.get(i).getPosX(), geese.get(i).getPosY(), GOOSE_HIT_RANGE)) {
						geese.get(i).kill();
						stats.addScore(geese.get(i).getPointsScored());
					}
				}
				for (int j = 0; j < drones.size(); j++) {
					if (drones.get(j).getisStillAlive() == true && collided(mouseX, mouseY, drones.get(j).getPosX(), drones.get(j).getPosY(), DRONE_HIT_RANGE)) {
						drones.get(j).kill();
						stats.decreaseGpa(drones.get(j).getGpaDeduction());
					}
				}
				stats.changeAmmo(circularCollided(mouseX, mouseY, RELOADX, RELOADY, RELOADR) ? 1 : -1);
				previouslyClicked = true;
			} else {
				previouslyClicked = false;
			}
			maxGeese = (maxGeese < GEESE_CAP) ? 1+stats.getScore()/10 : GEESE_CAP;
			maxDrones = (maxDrones < DRONES_CAP) ? stats.getScore()/10: DRONES_CAP;
			
			break;
		}
	}
}
