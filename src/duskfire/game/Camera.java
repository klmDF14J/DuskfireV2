package duskfire.game;

public class Camera {
	
	private int x, y;
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void moveX(int moveSize) {
		this.x += moveSize;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void moveY(int moveSize) {
		this.y += moveSize;
	}
	
	public int getY() {
		return y;
	}
}
