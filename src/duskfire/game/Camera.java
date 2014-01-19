package duskfire.game;

import java.io.Serializable;

import org.newdawn.slick.geom.Rectangle;

/**
 * @author Kyle Mandell
 */
public class Camera implements Serializable {

	private static final long serialVersionUID = 2L;
	
	private int x, y, cameraViewportWidth, cameraViewportHeight;
	
	public Camera(int x, int y, int cameraViewportWidth, int cameraViewportHeight) {
		this.x = x;
		this.y = y;
		this.cameraViewportWidth = cameraViewportWidth;
		this.cameraViewportHeight = cameraViewportHeight;
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
	
	public Rectangle getViewport() {
		return new Rectangle(x, y, cameraViewportWidth, cameraViewportHeight);
	}
}
