package duskfire.src.game;

import duskfire.src.tile.Tile;
import duskfire.src.tile.TileManager;
import duskfire.src.util.FileManager;
import duskfire.src.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class World {
	
	private int[][] grid = new int[WorldInfo.worldX][WorldInfo.worldY];
	
	private int worldID;
	
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
}
