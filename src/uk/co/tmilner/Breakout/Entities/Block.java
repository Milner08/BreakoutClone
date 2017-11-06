package uk.co.tmilner.Breakout.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Vector2d;

import uk.co.tmilner.Breakout.Log;
import uk.co.tmilner.Breakout.Screens.Level;

public class Block extends Entity {
	private static String TAG = "Block";
	public Rectangle2D.Double bounds;
	
	public boolean visible = true;
	
	public Block(int x, int y){
		super(x+2,y+2,21,21);
		bounds = new Rectangle2D.Double(x+2,y+2,21,21);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fill(bounds);
	}

	@Override
	public void update(long timePassed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void collision(Entity entity) {
		//This is static, No collisions possible.
		
	}

	@Override
	public void collision(Level level) {
		//This is static, No collisions possible. 
		
	}

	@Override
	public void hitBy(Entity entity) {
		Log.d(TAG, "Hit!!");
		visible = false;
		//entity.acceleration = new Vector2d(0.1,0.1);
		
	}

}
