package duskfire.src.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

import duskfire.src.Duskfire;
import duskfire.src.assets.AssetsManager;
import duskfire.src.util.GameInfo;

/**
 * @author Kyle Mandell
 */
public class Button extends MouseOverArea {

	private String text;
	public boolean locked;
	private Image lock;
	
	public Button(String text, boolean locked, GUIContext container, Image image, int x, int y, ComponentListener listener) {
		super(container, image, x, y, listener);
		
		this.text = text;
		
		this.locked = locked;
		this.lock = AssetsManager.getTexture("framework/lock");
		lock.setRotation(45);
	}
	
	@Override
	public void render(GUIContext gc, Graphics g) {
		super.render(gc, g);
		g.setColor(Color.black);
		Font font = GameInfo.font;
		font.drawString(getX() + (getWidth() / 2) - (font.getWidth(text) / 2), getY() + (getHeight() / 2) - (font.getHeight(text) / 2), text);
		
		if(locked) {
			lock.draw(getX() + getWidth() - 45, getY() - 20);
		}
	}
}
