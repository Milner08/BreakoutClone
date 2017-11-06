package uk.co.tmilner.Breakout.Screens;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Level extends Screen {

	public Level(int width, int height) {
		super(width, height);
	}
	
	public abstract void left();
	
	public abstract void right();
	
	public abstract void none();
}
