package duskfire.game;

import org.newdawn.slick.SlickException;

import duskfire.assets.AssetsManager;
import duskfire.gui.GUIManager;
import duskfire.tile.TileManager;

/**
 * @author Kyle Mandell
 */
public class GameManager {
	
	public static void initGame() throws SlickException {
		TileManager.initTiles();
		AssetsManager.initAssets();
		GUIManager.initGUI();
	}
}
