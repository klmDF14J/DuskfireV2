package duskfire.src.game;

import duskfire.src.assets.AssetsManager;
import duskfire.src.gui.GUIManager;
import duskfire.src.tile.TileManager;

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
