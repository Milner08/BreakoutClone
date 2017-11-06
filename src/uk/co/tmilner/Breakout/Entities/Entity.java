package uk.co.tmilner.Breakout.Entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Vector2d;

import uk.co.tmilner.Breakout.Screens.Level;

public abstract class Entity {
	public Rectangle bounds;	
	public Vector2d velocity;
	public Vector2d acceleration;
	public int mass;
	
	
	public Entity(int x, int y, int width, int height){
		bounds = new Rectangle(x,y,width,height);
		velocity = new Vector2d(0,0);
		acceleration = new Vector2d(0,0);
	}
	
	public abstract void draw(Graphics2D g);
	
	public abstract void update(long timePassed);
	
	//Detect a collision with an Entity
	public abstract void collision(Entity entity);
	
	//Detect a collision with a Level... EG out of bounds.
	public abstract void collision(Level level);
	
	//What to do when Hit. 
	public abstract void hitBy(Entity entity);

}
