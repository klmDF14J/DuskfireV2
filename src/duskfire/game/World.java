package duskfire.game;

import java.io.Serializable;
import java.util.ArrayList;

import duskfire.game.entity.Player;
import duskfire.tile.Tile;
import duskfire.tile.TileManager;
import duskfire.util.FileManager;
import duskfire.util.GameInfo;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class World implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int[][] grid = new int[WorldInfo.worldX][WorldInfo.worldY];
	
	private int worldID;
	
	private Camera camera;
	
	public ArrayList<Player> playerList;
	
	public World(int worldID) {
		this.worldID = worldID;
		this.camera = new Camera(0, 0, GameInfo.screenX, GameInfo.screenY);
		
		playerList = new ArrayList<Player>();
		
		playerList.add(new Player(0, findPlayerSpawn()));
	}
	
	private int findPlayerSpawn() {
		return 0;
	}

	public void setTileID(int x, int y, int id) {
		grid[x][y] = id;
	}
	
	public int getTileID(int x, int y) {
		return grid[x][y];
	}
	
	public void setTile(int x, int y, Tile tile) {
		grid[x][y] = tile.tileID;
	}
	
	public Tile getTile(int x, int y) {
		return TileManager.tileList.get(grid[x][y]);
	}
	
	public void saveWorld() {
		FileManager.save("world_" + worldID, this);
	}

	public Camera getCamera() {
		return camera;
	}
}
