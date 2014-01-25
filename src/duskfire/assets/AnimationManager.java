/**
 * 
 */
package duskfire.assets;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import duskfire.util.KeyInfo;
import duskfire.util.WorldInfo;

/**
 * @author Kyle Mandell
 */
public class AnimationManager {

	private static ArrayList<Animation> playerAnimations;
	
	private static int lastWalk_left, lastWalk_right;
	public static int timeSinceLastWalkChange;
	
	public static void initAnimations() {
		playerAnimations = new ArrayList<Animation>();
		
		playerAnimations.add(new Animation("idle", AssetsManager.playerTextures.get(0)) {
			@Override
			public boolean canPlay() {
				if(!WorldInfo.world.playerList.get(0).isFalling()) {
					return true;
				}
				else {
					return false;
				}
			}
		});
		
		playerAnimations.add(new Animation("look_left", AssetsManager.playerTextures.get(1)) {
			@Override
			public boolean canPlay() {
				if(WorldInfo.world.playerList.get(0).lastKey == KeyInfo.left && !Keyboard.isKeyDown(KeyInfo.left)) {
					return true;
				}
				else {
					return false;
				}
			}
		});
		
		playerAnimations.add(new Animation("look_right", AssetsManager.playerTextures.get(2)) {
			@Override
			public boolean canPlay() {
				if(WorldInfo.world.playerList.get(0).lastKey == KeyInfo.right && !Keyboard.isKeyDown(KeyInfo.right)) {
					return true;
				}
				else {
					return false;
				}
			}
		});
		
		playerAnimations.add(new Animation("walk_left", AssetsManager.playerTextures.get(3)) {
			@Override
			public boolean canPlay() {
				if(Keyboard.isKeyDown(KeyInfo.left)) {
					if(timeSinceLastWalkChange >= 18) {
						timeSinceLastWalkChange = 0;
						lastWalk_left += lastWalk_left == 3 ? -3 : 1;
						setFrame(AssetsManager.playerTextures.get(3 + lastWalk_left));
					}
					return true;
				}
				else {
					return false;
				}
			}
		});
		
		playerAnimations.add(new Animation("walk_right", AssetsManager.playerTextures.get(7)) {
			@Override
			public boolean canPlay() {
				if(Keyboard.isKeyDown(KeyInfo.right)) {
					if(timeSinceLastWalkChange >= 18) {
						timeSinceLastWalkChange = 0;
						lastWalk_right += lastWalk_right == 3 ? -3 : 1;
						setFrame(AssetsManager.playerTextures.get(7 + lastWalk_right));
					}
					return true;
				}
				else {
					return false;
				}
			}
		});
	}
	
	public static Animation getPlayerAnimation() {
		Animation finalAnimation = playerAnimations.get(0);
		for(Animation animation : playerAnimations) {
			if(animation.canPlay()) {
				finalAnimation = animation;
			}
		}
		return finalAnimation;
	}
	
}
