package duskfire.gui;

import java.awt.Color;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import duskfire.util.GameInfo;

/**
 * @author Kyle Mandell
 */
public class GUIManager {
	
	@SuppressWarnings("unchecked")
	public static void initGUI() {
		try {
			GameInfo.font = new UnicodeFont(GameInfo.userDir + "/assets/framework/moonhouse.ttf", 36, false, false);
			GameInfo.font.addAsciiGlyphs();
			GameInfo.font.addGlyphs(400, 600);
			GameInfo.font.getEffects().add(new ColorEffect(Color.black));
			GameInfo.font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
