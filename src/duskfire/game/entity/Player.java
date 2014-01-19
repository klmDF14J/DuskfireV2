package duskfire.game.entity;

import java.io.Serializable;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.Log;

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
	
	public Player(int x, int y) {
		this.playerX = x;
		this.playerY = y;
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
	
	private void tryMove(int moveSize, EnumDirection direction) {
		if(direction == EnumDirection.LEFT) {
			if((getX() - moveSize) >= 0 && canMove(EnumDirection.LEFT)) {
				playerX += moveSize;
			}
		}
		if(direction == EnumDirection.RIGHT) {
			if((getX() + moveSize) < WorldInfo.worldX * GameInfo.tileSize && canMove(EnumDirection.RIGHT)) {
				playerX += moveSize;
			}
		}
		if(direction == EnumDirection.UP) {
			if(((getY() + PlayerInfo.playerHeight) - moveSize) >= 0) {
				playerY += moveSize;
			}
		}
		if(direction == EnumDirection.DOWN) {
			if((getY() + PlayerInfo.playerHeight) + moveSize < GameInfo.screenY) {
				playerY += moveSize;
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), PlayerInfo.playerWidth,  PlayerInfo.playerHeight);
	}
	
	private boolean canMove(EnumDirection direction) {
		boolean flag = true;
		for(int x = 0; x < WorldInfo.worldX; x++) {
			for(int y = 0; y < WorldInfo.worldY; y++) {
				Rectangle tileBounds = new Rectangle((x * GameInfo.tileSize), (y * GameInfo.tileSize), GameInfo.tileSize, GameInfo.tileSize);
		
				if(getBounds().intersects(tileBounds)) {
					if(WorldInfo.world.getTile(x, y).isSolid()) {
						int combined = (int) (getBounds().getY() + PlayerInfo.playerHeight);
						Log.info("Combined: " + combined);
						int tileY = (int) tileBounds.getY();
						Log.info("Tile Y: " + tileY);
						if(combined > tileY) {
							Log.info(combined + " > " + tileY);
							break;
						}
						else {
							Log.info("Same");
							break;
						}
					}
				}
			}
		}
		return flag;
	}
	
	public void update() {
		if(canMove(EnumDirection.DOWN)) {
			tryMove(PlayerInfo.fallSpeed, EnumDirection.DOWN);
		}
	}
}
