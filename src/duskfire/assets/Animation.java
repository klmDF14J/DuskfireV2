/**
 * 
 */
package duskfire.assets;

import org.newdawn.slick.Image;

/**
 * @author Kyle Mandell
 */
public class Animation {
	
	private String identifier;
	
	private Image frame;
	
	public Animation(String identifier, Image frame) {
		this.identifier  = identifier;
		this.frame = frame;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setFrame(Image frame) {
		this.frame = frame;
	}
	
	public Image getFrame() {
		return frame;
	}
	
	public boolean canPlay() {
		return false;
	}
}
