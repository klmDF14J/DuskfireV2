package duskfire.assets;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import duskfire.tile.Tile;
import duskfire.tile.TileManager;
import duskfire.util.GameInfo;
import duskfire.util.PlayerInfo;

/**
 * @author Kyle Mandell
 */
public class AssetsManager {
	
	public static ArrayList<Image> tileTextures;
	
	private static SpriteSheet playerTextureSheet;
	
	public static ArrayList<Image> playerTextures;
	
	public static void initAssets() throws SlickException {
		tileTextures = new ArrayList<Image>();
		
		for(Tile tile : TileManager.tileList) {
			tileTextures.add(getTexture(tile.getTextureName()));
		}
		
		playerTextureSheet = new SpriteSheet(GameInfo.userDir + "/assets/entity/player.png", 20, 48);
		
		playerTextures = new ArrayList<Image>();
		
		for(int x = 0; x < PlayerInfo.numberAnimations; x++) {
			for(int y = 0; y < PlayerInfo.numberTypes; y++) {
				playerTextures.add(playerTextureSheet.getSprite(x, y));
			}
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
