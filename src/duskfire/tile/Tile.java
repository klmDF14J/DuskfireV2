package duskfire.tile;

/**
 * @author Kyle Mandell
 */
public class Tile {
	
	public int tileID;
	private String name = "Unknown", textureName = "missing_texture";
	
	public Tile(int tileID) {
		this.tileID = tileID;
		
		TileManager.tileList.add(this);
	}
	
	public Tile setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Tile setTextureName(String textureName) {
		this.textureName = textureName;
		return this;
	}
	
	public String getTextureName() {
		return textureName;
	}
}
