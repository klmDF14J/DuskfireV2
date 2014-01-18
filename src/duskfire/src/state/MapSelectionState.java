package duskfire.src.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.state.StateBasedGame;

import duskfire.src.assets.AssetsManager;
import duskfire.src.game.WorldManager;
import duskfire.src.gui.Button;
import duskfire.src.util.ScreenUtils;

/**
 * @author Kyle Mandell
 */
public class MapSelectionState extends GenericGameState {
	
	private static Button[] saveButtons = new Button[5];
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		Image button = AssetsManager.getTexture("framework/button");
		for(int i = 0; i < saveButtons.length; i++) {
			addButton(i, gc, button, sbg);
		}
	}
	
	private static void addButton(final int index, GameContainer gc, Image button, final StateBasedGame sbg) {
		saveButtons[index] = new Button("Save " + (index + 1), false, gc, button, ScreenUtils.getCenteredX(button.getWidth()), 50 + (index * 100), new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent arg0) {
				WorldManager.loadWorld(index);
				
				sbg.enterState(2);
			}
		});
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		MenuState.renderBackground(gc, g);
		
		for(int i = 0; i < saveButtons.length; i++) {
			saveButtons[i].render(gc, g);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
}
