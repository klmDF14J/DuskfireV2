package duskfire.src;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import duskfire.src.game.GameManager;
import duskfire.src.state.MapSelectionState;
import duskfire.src.state.MenuState;
import duskfire.src.util.GameInfo;

/**
 * @author Kyle Mandell
 */
public class Duskfire extends StateBasedGame {
	
	public Duskfire() {
		super("Duskfire");
	}

	public static AppGameContainer app;
	
	public static int lastStateID;
	
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
	}
}
