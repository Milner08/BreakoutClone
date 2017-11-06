package uk.co.tmilner.Breakout.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import uk.co.tmilner.Breakout.GameLoader;

public class Notification extends Rectangle2D.Double{
	String message;

	public Notification(String message) {
		width = GameLoader.FRAME_WIDTH;
		height = 100;
		x = 0;
		y = GameLoader.FRAME_HEIGHT - height;
		message = message;
		
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.draw(this);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(message, (int)(y+10) , 70);
		
	}

}
