package duskfire.game;

import duskfire.util.FileManager;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class WorldManager {
	
	public static void loadWorld(int index) {
		Object world = FileManager.load("world_" + index);
		if(world instanceof World) {
			WorldInfo.world = (World) world;
		}
	}

	public static void createWorld(int index) {
		WorldInfo.world = new World(index);
	}
}
