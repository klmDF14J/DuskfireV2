package duskfire.src.tile;

import java.util.ArrayList;

/**
 * @author Kyle Mandell
 */
public class TileManager {

	public static Tile grass, dirt;
	
	public static ArrayList<Tile> tileList;
	
	public static void initTiles() {
		tileList = new ArrayList<Tile>();
		
		grass = new Tile(0).setName("Grass").setTextureName("grass");
		dirt = new Tile(1).setName("Dirt").setTextureName("dirt");
		
		for(Tile tile : tileList) {
			System.out.println("Found Tile: " + tile.getName());
		}
	}
}
