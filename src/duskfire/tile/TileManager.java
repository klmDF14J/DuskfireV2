package duskfire.tile;

import java.util.ArrayList;

import org.newdawn.slick.util.Log;

/**
 * @author Kyle Mandell
 */
public class TileManager {

	public static Tile air, grass, dirt;
	
	public static ArrayList<Tile> tileList;
	
	public static void initTiles() {
		tileList = new ArrayList<Tile>();
		
		air = new Tile(0).setName("Air").setTextureName("terrain/air").setSolid(false);
		grass = new Tile(1).setName("Grass").setTextureName("terrain/grass");
		dirt = new Tile(2).setName("Dirt").setTextureName("terrain/dirt");
		
		for(Tile tile : tileList) {
			Log.info(" Registered Tile: " + tile.getName());
		}
	}
}
