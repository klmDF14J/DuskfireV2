package duskfire.game.entity;

import java.io.Serializable;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.Log;

import duskfire.game.Camera;
import duskfire.tile.Tile;
import duskfire.util.EnumDirection;
import duskfire.util.GameInfo;
import duskfire.util.PlayerInfo;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class Player implements Serializable {

	private static final long serialVersionUID = 3L;
	
	private int playerX, playerY;
	
	private Camera camera;
	
	private boolean falling = false;
	
	public Player(int x, int y) {
		this.playerX = x;
		this.playerY = y;
		
		this.camera = new Camera(GameInfo.screenX / 2, 0, GameInfo.screenX, GameInfo.screenY);
	}
	
	public void setX(int x) {
		this.playerX = x; 
	}
	
	public void moveX(int moveSize) {
		tryMove(moveSize, moveSize < 0 ? EnumDirection.LEFT : EnumDirection.RIGHT);
	}

	public int getX() {
		return playerX;
	}
	
	public void setY(int y) {
		this.playerY = y;
	}
	
	public void moveY(int moveSize) {
		tryMove(moveSize, moveSize < 0 ? EnumDirection.UP : EnumDirection.DOWN);
	}
	
	public int getY() {
		return playerY;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	private boolean tryMove(int moveSize, EnumDirection direction) {
		if(direction == EnumDirection.LEFT) {
			if((getX() - moveSize) >= 0 && canMove(EnumDirection.LEFT)) {
				playerX += moveSize;
				return true;
			}
		}
		if(direction == EnumDirection.RIGHT) {
			if((getX() + moveSize) < WorldInfo.worldX * GameInfo.tileSize && canMove(EnumDirection.RIGHT)) {
				playerX += moveSize;
				return true;
			}
		}
		if(direction == EnumDirection.UP) {
			if(((getY() + PlayerInfo.playerHeight) - moveSize) >= 0) {
				playerY += moveSize;
				return true;
			}
		}
		if(direction == EnumDirection.DOWN) {
			if((getY() + PlayerInfo.playerHeight) + moveSize < GameInfo.screenY) {
				playerY += moveSize;
				return true;
			}
		}
		return false;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), PlayerInfo.playerWidth,  PlayerInfo.playerHeight);
	}
	
	private boolean canMove(EnumDirection direction) {
		boolean flag = true;
		for(int x = 0; x < WorldInfo.worldX; x++) {
			for(int y = 0; y < WorldInfo.worldY; y++) {
				Rectangle tileBounds = new Rectangle((x * GameInfo.tileSize), (y * GameInfo.tileSize), GameInfo.tileSize, GameInfo.tileSize);
				Rectangle bounds = getBounds();
				
				if(direction == EnumDirection.LEFT) {
					bounds.setX(getX() - PlayerInfo.moveSpeed);
					bounds.setWidth(PlayerInfo.playerWidth + PlayerInfo.moveSpeed);
				}
				if(direction == EnumDirection.RIGHT) {
					bounds.setWidth(PlayerInfo.playerWidth + PlayerInfo.moveSpeed);
				}
				if(direction == EnumDirection.UP) {
					bounds.setY(getY() - PlayerInfo.jumpSpeed);
					bounds.setHeight(PlayerInfo.playerHeight + PlayerInfo.fallSpeed);
				}
				if(direction == EnumDirection.DOWN) {
					bounds.setHeight(PlayerInfo.playerHeight + PlayerInfo.fallSpeed);
				}
				
				if(bounds.intersects(tileBounds)) {
					if(WorldInfo.world.getTile(x, y).isSolid()) {
						if(bounds.getMaxY() > tileBounds.getMinY()) {
							flag = false;
						}
					}
				}
			}
		}
		return flag;
	}
	
	public void update() {
		if(canMove(EnumDirection.DOWN)) {
			boolean move = tryMove(PlayerInfo.fallSpeed, EnumDirection.DOWN);
			if(move) {
				setFalling(true);
				getCamera().setY((getY() + PlayerInfo.fallSpeed) - (GameInfo.screenY / 2));
			}
		}
		else {
			setFalling(false);
		}
	}
}
