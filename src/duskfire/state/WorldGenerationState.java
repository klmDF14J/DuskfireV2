package duskfire.state;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import duskfire.game.generator.Generator;
import duskfire.game.generator.GrassGenerator;
import duskfire.game.generator.RockGenerator;
import duskfire.util.GameInfo;
import duskfire.util.ScreenUtils;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class WorldGenerationState extends GenericGameState {
	
	private static ArrayList<Generator> generators;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		generators = new ArrayList<Generator>();
		
		generators.add(new GrassGenerator());
		generators.add(new RockGenerator());
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
	public static int numStepsAfterLastHeightChange = 0;
	
	private void generateWorld() {
		Random rand = new Random();
		
		generateRockLayers(rand);
		
		for(Generator generator : generators) {
			generator.generate(rand, numStepsAfterLastHeightChange, lastTerrainHeight);
		}
		
		WorldInfo.world.saveWorld();
	}
	
	private void generateRockLayers(Random rand) {
		
	}

	@Override
	public int getID() {
		return 3;
	}
}
