package duskfire.tile;

import java.util.ArrayList;

import org.newdawn.slick.util.Log;

/**
 * @author Kyle Mandell
 */
public class TileManager {
	
	public static ArrayList<Tile> tileList;
	
	public static Tile air, grass, grass_left, grass_right, dirt, dirt_left, dirt_right, rock, flower;
	
	public static void initTiles() {
		tileList = new ArrayList<Tile>();
		
		air = new Tile(0).setName("Air").setTextureName("terrain/air").setSolid(false);
		grass = new Tile(1).setName("Grass").setTextureName("terrain/grass");
		grass_left = new Tile(2).setName("Grass Left").setTextureName("terrain/grass_left");
		grass_right = new Tile(3).setName("Grass Right").setTextureName("terrain/grass_right");
		dirt = new Tile(4).setName("Dirt").setTextureName("terrain/dirt");
		dirt_left = new Tile(5).setName("Dirt Left").setTextureName("terrain/dirt_left");
		dirt_right = new Tile(6).setName("Dirt Right").setTextureName("terrain/dirt_right");
		rock = new Tile(7).setName("Rock").setTextureName("terrain/rock");
		flower = new Tile(8).setName("Flower").setTextureName("terrain/flower").setSolid(false);
		
		for(Tile tile : tileList) {
			Log.info(" Registered Tile: " + tile.getName());
		}
	}
}
