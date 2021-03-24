package hupman;

public class MazeSpace {
	int x;
	int y;
	int northWall;
	int eastWall;
	int southWall;
	int westWall;
	boolean available;

	public MazeSpace(int newX, int newY, int newNorth, int newEast, int newSouth, int newWest, boolean newAvailable) {
		x = newX;
		y = newY;
		northWall  = newNorth;
		eastWall  = newEast;
		southWall  = newSouth;
		westWall  = newWest;
		available = newAvailable;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getNorth() {
		return northWall;
	}
	
	public int getEast() {
		return eastWall;
	}
	
	public int getSouth() {
		return southWall;
	}
	
	public int getWest() {
		return westWall;
	}
	
	public boolean getAvailable() {
		return available;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public void setNorth(int newWall) {
		this.northWall = newWall;
	}
	
	public void setEast(int newWall) {
		this.eastWall = newWall;
	}
	
	public void setSouth(int newWall) {
		this.southWall = newWall;
	}
	
	public void setWest(int newWall) {
		this.westWall = newWall;
	}
	
	public void setAvailable(boolean newAvailable) {
		this.available = newAvailable;
	}
}
