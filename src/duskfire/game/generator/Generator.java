/**
 * 
 */
package duskfire.game.generator;

import java.util.Random;

/**
 * @author Kyle Mandell
 */
public class Generator {
	
	public void generate(Random rand, int numStepsAfterLastHeightChange, int lastTerrainHeight) {
		
	}
	
	protected int adjustTerrainHeightRandomly(Random rand, int lastTerrainHeight) {
		int terrainHeightChance = rand.nextInt(2);
		if(terrainHeightChance == 0) {
			lastTerrainHeight += rand.nextInt(3);
		}
		else {
			lastTerrainHeight -= rand.nextInt(3);
		}
		return lastTerrainHeight;
	}
}
