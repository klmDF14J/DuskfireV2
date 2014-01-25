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
public class RockGenerator extends Generator {
	
	@Override
	public void generate(Random rand, int numStepsAfterLastHeightChange, int lastTerrainHeight) {
		lastTerrainHeight = 30;
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
			WorldInfo.world.setTileID(x, lastTerrainHeight, TileManager.rock.tileID);
			for(int y = WorldInfo.worldY - 1; y > lastTerrainHeight; y--) {
				WorldInfo.world.setTileID(x, y, TileManager.rock.tileID);
			}
		}
		lastTerrainHeight = 16; 
	}
}
