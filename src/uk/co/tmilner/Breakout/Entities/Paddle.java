package uk.co.tmilner.Breakout.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import uk.co.tmilner.Breakout.GameLoader;
import uk.co.tmilner.Breakout.Screens.Level;
import uk.co.tmilner.Breakout.Screens.MainMenu;

public class Paddle extends Entity {
	private static String TAG = "Paddle";
	public enum Direction {NONE, LEFT, RIGHT};
	public Direction movement = Direction.NONE;

	public Paddle(int x, int y){
		super(x, y, 40, 15);
	}
	
	@Override
	public synchronized void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fill(bounds);
	}

	@Override
	public void update(long dt) {
		switch(movement){
		case LEFT:
			velocity.x = -0.5;	
			break;
		case RIGHT:
			velocity.x = 0.5;
			break;
		case NONE:
			velocity.x = 0;
			break;
		default:
			break;
		}
		

		bounds.x += velocity.x * dt;
		bounds.y += velocity.y * dt;
	}
	
	public void left(){
		movement = Direction.LEFT;
	}
	
	public void right(){
		movement = Direction.RIGHT;
	}
	
	public void none(){
		movement = Direction.NONE;
	}
	
	@Override
	public void collision(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collision(Level level) {
		if(this.bounds.x < 0){
			movement = Direction.NONE;
		}else if(this.bounds.x > (level.getBounds().getWidth() - bounds.getWidth())){
			movement = Direction.NONE;
		}
	}

	@Override
	public void hitBy(Entity entity) {
		//Dont do anything...
	}

}
