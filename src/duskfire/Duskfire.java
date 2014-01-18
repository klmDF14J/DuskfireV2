package duskfire;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import duskfire.game.GameManager;
import duskfire.state.MapSelectionState;
import duskfire.state.MenuState;
import duskfire.state.PlayState;
import duskfire.state.WorldGenerationState;
import duskfire.util.GameInfo;

/**
 * @author Kyle Mandell
 */
public class Duskfire extends StateBasedGame {
	
	public Duskfire() {
		super("Duskfire");
	}

	public static AppGameContainer app;
	
	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Duskfire(), GameInfo.screenX, GameInfo.screenY, false);
		app.setTargetFrameRate(60);
		app.start();
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		GameManager.initGame();
		
		addState(new MenuState());
		addState(new MapSelectionState());
		addState(new WorldGenerationState());
		addState(new PlayState());
		
		enterState(0);
	}
}
