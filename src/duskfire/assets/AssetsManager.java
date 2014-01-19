package duskfire.assets;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import duskfire.tile.Tile;
import duskfire.tile.TileManager;
import duskfire.util.GameInfo;

/**
 * @author Kyle Mandell
 */
public class AssetsManager {
	
	public static ArrayList<Image> tileTextures;
	
	public static void initAssets() {
		tileTextures = new ArrayList<Image>();
		
		for(Tile tile : TileManager.tileList) {
			tileTextures.add(getTexture(tile.getTextureName()));
		}
	}
	
	private static Object getGenericAsset(String imagePath) {
		try {
			return new Image(GameInfo.userDir + imagePath);
		} catch (SlickException e) {
			System.out.println("Failed To Load Asset: " + imagePath);
		}
		return null;
	}
	
	public static Image getTexture(String imagePath) {
		imagePath = "/assets/" + imagePath + ".png"; 
		Image missing_texture = null;
		try {
			missing_texture = new Image(GameInfo.userDir + "/assets/missing_texture.png");
		} catch (SlickException e) {
		
		}
		File file = new File(GameInfo.userDir + imagePath);
		return (Image) (file.exists() ? getGenericAsset(imagePath) : missing_texture);
	}
}
