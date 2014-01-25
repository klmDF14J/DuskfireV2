/**
 * 
 */
package duskfire.game.generator;

import java.util.Random;

import duskfire.tile.TileManager;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class GrassGenerator extends Generator {
	
	@Override
	public void generate(Random rand, int numStepsAfterLastHeightChange, int lastTerrainHeight) {
		lastTerrainHeight = adjustTerrainHeightRandomly(rand, lastTerrainHeight);
		for(int x = 0; x < WorldInfo.worldX; x++) {
			int chance = rand.nextInt(3);
			//Terrain height stay the same
			if(chance == 0) {
				numStepsAfterLastHeightChange++;
			}
			//Terrain height goes up
			else if(chance == 1) {
				if(lastTerrainHeight + 1 < WorldInfo.worldY && numStepsAfterLastHeightChange >= 3) {
					lastTerrainHeight++;
					numStepsAfterLastHeightChange = 0;
				}
			}
			//Terrain height goes down
			else if(chance == 2) {
				if(lastTerrainHeight - 1 >= 0 && numStepsAfterLastHeightChange >= 3) {
					lastTerrainHeight--;
					numStepsAfterLastHeightChange = 0;
				}
			}
			WorldInfo.world.setTileID(x, lastTerrainHeight, TileManager.grass.tileID);
			for(int y = WorldInfo.worldY - 1; y > lastTerrainHeight; y--) {
				WorldInfo.world.setTileID(x, y, TileManager.dirt.tileID);
			}
			
			int flowerChance = rand.nextInt(10);
			if(flowerChance == 0) {
				generateFlowers(x, (lastTerrainHeight - 1));
			}
		}
		updateGrassState();
	}
	
	private void generateFlowers(int x, int y) {
		WorldInfo.world.setTileID(x, y, TileManager.flower.tileID);
	}
	
	private void updateGrassState() {
		for(int x = 0; x < WorldInfo.worldX; x++) {
			for(int y = 0; y < WorldInfo.worldY; y++) {
				if(WorldInfo.world.getTileID(x, y) == TileManager.grass.tileID) {
					if(x > 0 && !WorldInfo.world.getTile(x - 1, y).isSolid()) {
						WorldInfo.world.setTileID(x, y, TileManager.grass_left.tileID);
						WorldInfo.world.setTileID(x, y + 1, TileManager.dirt_left.tileID);
					}
					if(x + 1 < WorldInfo.worldX && !WorldInfo.world.getTile(x + 1, y).isSolid()) {
						WorldInfo.world.setTileID(x, y, TileManager.grass_right.tileID);
						WorldInfo.world.setTileID(x, y + 1, TileManager.dirt_right.tileID);
					}
				}
			}
		}
	}
}
