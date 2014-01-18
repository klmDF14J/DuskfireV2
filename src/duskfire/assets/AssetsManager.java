package duskfire.assets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import duskfire.util.GameInfo;

/**
 * @author Kyle Mandell
 */
public class AssetsManager {
	
	public static void initAssets() {
		
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
		return (Image) (getGenericAsset(imagePath) != null ? getGenericAsset(imagePath) : missing_texture);
	}
}
