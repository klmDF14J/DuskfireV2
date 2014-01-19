package duskfire.state;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import duskfire.tile.TileManager;
import duskfire.util.GameInfo;
import duskfire.util.ScreenUtils;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class WorldGenerationState extends GenericGameState {
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		generateWorld();
		sbg.enterState(2);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		MenuState.renderBackground(gc, g);
		
		String text = "Generating World";
		GameInfo.font.drawString(ScreenUtils.getCenteredX(GameInfo.font.getWidth(text)), 300, text);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	int normalTerrainHeight = 16;
	int lastTerrainHeight = normalTerrainHeight;
	int numStepsAfterLastHeightChange = 0;
	
	private void generateWorld() {
		Random rand = new Random();
	
		generateGrassAndDirtLayers(rand);
		updateGrassState();
		generateRockLayers(rand);
		
		WorldInfo.world.saveWorld();
	}

	private void adjustTerrainHeightRandomly(Random rand) {
		int terrainHeightChance = rand.nextInt(2);
		if(terrainHeightChance == 0) {
			lastTerrainHeight += rand.nextInt(3);
		}
		else {
			lastTerrainHeight -= rand.nextInt(3);
		}
	}

	private void generateGrassAndDirtLayers(Random rand) {
		adjustTerrainHeightRandomly(rand);
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
	
	private void generateRockLayers(Random rand) {
		lastTerrainHeight = 30;
		adjustTerrainHeightRandomly(rand);
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
		lastTerrainHeight = normalTerrainHeight; 
	}

	@Override
	public int getID() {
		return 3;
	}
}
