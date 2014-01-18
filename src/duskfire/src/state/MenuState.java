package duskfire.src.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import duskfire.src.Duskfire;
import duskfire.src.assets.AssetsManager;
import duskfire.src.gui.Button;
import duskfire.src.util.GameInfo;
import duskfire.src.util.ScreenUtils;

/**
 * @author Kyle Mandell
 */
public class MenuState extends GenericGameState {
	
	private static Image background;
	private static Image backgroundGrass, backgroundDirt;
	private static Image title;
	private static Button playButton, coOpButton, optionsButton;
	
	@Override
	public void init(GameContainer gc, final StateBasedGame sbg) throws SlickException {
		background = AssetsManager.getTexture("/framework/background");
		
		backgroundGrass = AssetsManager.getTexture("/terrain/grass");
		backgroundDirt = AssetsManager.getTexture("/terrain/dirt");
		
		title = AssetsManager.getTexture("/framework/title");
		
		Image button = AssetsManager.getTexture("/framework/button");
		
		playButton = new Button("Play", false, gc, button, ScreenUtils.getCenteredX(button.getWidth()), 175, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent paramAbstractComponent) {
				sbg.enterState(1);
			}
		});
		
		coOpButton = new Button("Co-Op", true, gc, button, ScreenUtils.getCenteredX(button.getWidth()), 275, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent paramAbstractComponent) {
				
			}
		});
		
		optionsButton = new Button("Options", true, gc, button, ScreenUtils.getCenteredX(button.getWidth()), 375, new ComponentListener() {
			@Override
			public void componentActivated(AbstractComponent paramAbstractComponent) {
				
			}
		});
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		renderBackground(gc, g);
		
		title.draw(ScreenUtils.getCenteredX(title.getWidth()), 50);
		
		playButton.render(gc, g);
		coOpButton.render(gc, g);
		optionsButton.render(gc, g);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}
	
	public static void renderBackground(GameContainer gc, Graphics g) {
		background.draw();
		
		for(int x = 0; x < 40; x++) {
			for(int y = 17; y < 23; y++) {
				if(y == 17) {
					backgroundGrass.draw((x * GameInfo.tileSize), (y * GameInfo.tileSize));
				}
				else {
					backgroundDirt.draw((x * GameInfo.tileSize), (y * GameInfo.tileSize));
				}
			}
		}
	}
	
	@Override
	public int getID() {
		return 0;
	}
}
