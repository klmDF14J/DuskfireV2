package duskfire.state;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import duskfire.assets.AnimationManager;
import duskfire.assets.AssetsManager;
import duskfire.game.entity.Player;
import duskfire.util.GameInfo;
import duskfire.util.KeyInfo;
import duskfire.util.PlayerInfo;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class PlayState extends GenericGameState {
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		AnimationManager.initAnimations();
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		MenuState.background.draw();
		renderMap();
		Player player = WorldInfo.world.playerList.get(0);
		Image playerImage = AnimationManager.getPlayerAnimation().getFrame();
		playerImage.draw((player.getX()) - WorldInfo.world.playerList.get(0).getCamera().getX(), (player.getY()) - WorldInfo.world.playerList.get(0).getCamera().getY() + 2);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(Keyboard.isKeyDown(KeyInfo.escape)) {
			sbg.enterState(1);
		}
		if(Keyboard.isKeyDown(KeyInfo.left)) {
			WorldInfo.world.playerList.get(0).moveX(KeyInfo.left, -PlayerInfo.moveSpeed);
		}
		if(Keyboard.isKeyDown(KeyInfo.right)) {
			WorldInfo.world.playerList.get(0).moveX(KeyInfo.right, PlayerInfo.moveSpeed);
		}
		if(Keyboard.isKeyDown(KeyInfo.jump)) {
			if(!WorldInfo.world.playerList.get(0).isFalling()) {
				WorldInfo.world.playerList.get(0).moveY(KeyInfo.jump, -PlayerInfo.jumpSpeed);
			}
		}
		
		AnimationManager.timeSinceLastWalkChange++;
		
		WorldInfo.world.playerList.get(0).update();
	}
	
	private void renderMap() {
		for(int x = 0; x < WorldInfo.worldX; x++) {
			for(int y = 0; y < WorldInfo.worldY; y++) {
				Rectangle viewportBounds = WorldInfo.world.playerList.get(0).getCamera().getViewport();
				Rectangle tileBounds = new Rectangle((x * GameInfo.tileSize), (y * GameInfo.tileSize), GameInfo.tileSize, GameInfo.tileSize);
				if(viewportBounds.intersects(tileBounds)) {
					int tileID = WorldInfo.world.getTileID(x, y);
					AssetsManager.tileTextures.get(tileID).draw((x * GameInfo.tileSize) - WorldInfo.world.playerList.get(0).getCamera().getX(), (y * GameInfo.tileSize) - WorldInfo.world.playerList.get(0).getCamera().getY());
				}
			}
		}
	}
	
	@Override
	public int getID() {
		return 2;
	}
}
