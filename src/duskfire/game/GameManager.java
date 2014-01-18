package duskfire.game;

import duskfire.assets.AssetsManager;
import duskfire.gui.GUIManager;
import duskfire.tile.TileManager;

/**
 * @author Kyle Mandell
 */
public class GameManager {
	
	public static void initGame() {
		TileManager.initTiles();
		AssetsManager.initAssets();
		GUIManager.initGUI();
	}
}
