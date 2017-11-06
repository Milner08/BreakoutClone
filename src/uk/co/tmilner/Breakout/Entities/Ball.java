package uk.co.tmilner.Breakout.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.vecmath.Vector2d;

import uk.co.tmilner.Breakout.GameLoader;
import uk.co.tmilner.Breakout.Log;
import uk.co.tmilner.Breakout.Screens.Level;
import uk.co.tmilner.Breakout.Screens.MainMenu;

public class Ball extends Entity {
	private static String TAG = "Ball";
	public Ellipse2D.Double bounds;
	
	public Ball(int x, int y){
		super(x,y,20,20);
		bounds = new Ellipse2D.Double(x,y,20,20);
		Double randx = Math.random();
		velocity = new Vector2d(-(randx/2),-0.3);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fill(bounds);
	}

	@Override
	public void update(long dt) {
		velocity.x += acceleration.x * dt;
		velocity.y += acceleration.y * dt;
		
		//Small amount of friction. 
		//velocity.x *= 0.999;
		//velocity.y *= 0.999;
		
		bounds.x += velocity.x * dt;
		bounds.y += velocity.y * dt;		
	}

	@Override
	//Hitting a Wall!
	public void collision(Level level) {
		if(this.bounds.x < 0){
			velocity.x *= -1;
		}else if(this.bounds.x > (level.getBounds().getWidth() - bounds.getWidth())){
			velocity.x *= -1;
		}
		if(this.bounds.y < 0){
			velocity.y *= -1;
		}else if(this.bounds.y > (level.getBounds().getHeight() - bounds.getHeight())){
			GameLoader.changeScreen(MainMenu.class);
		}
		
	}

	@Override
	public void collision(Entity entity) {
		
		if(entity instanceof Block){
			if(!((Block)entity).visible){
				return;
			}
		}
		
		//Line interception for each edge... UGH.
		
		//Each Point on the entity.
		Vector2d top_left = new Vector2d(entity.bounds.getX(), entity.bounds.getY());
		Vector2d bottom_left = new Vector2d(entity.bounds.getX(), (entity.bounds.getY() + entity.bounds.getHeight()) );
		Vector2d top_right = new Vector2d((entity.bounds.getX() + entity.bounds.getWidth()), entity.bounds.getY());
		Vector2d bottom_right = new Vector2d((entity.bounds.getX() + entity.bounds.getWidth()),  (entity.bounds.getY() + entity.bounds.getHeight()));
		
		Vector2d center = new Vector2d(bounds.getCenterX(), bounds.getCenterY());
		
		double radius = bounds.getWidth()/2;
		
		//Each Edge.
		Vector2d top = new Vector2d(0,0);
		top.sub(top_right, top_left);
		
		Vector2d left = new Vector2d(0,0);
		left.sub(bottom_left, top_left);
		
		Vector2d bottom = new Vector2d(0,0);
		bottom.sub(bottom_right, bottom_left);
		
		Vector2d right = new Vector2d(0,0);
		right.sub(bottom_right, top_right);
		
		//Distance to ball...
		Vector2d top_left_2_ball = new Vector2d(0,0);
		Vector2d bottom_left_2_ball = new Vector2d(0,0);
		Vector2d top_right_2_ball = new Vector2d(0,0);
		
		top_left_2_ball.sub(top_left, center);
		bottom_left_2_ball.sub(bottom_left, center);
		top_right_2_ball.sub(top_right, center);
		
		if(lineCircleIntersection(top, top_left_2_ball, radius)){
			Log.d(TAG, "TOP!!!");
			velocity.y *= -1;
			entity.hitBy(this);
		}else if(lineCircleIntersection(left, top_left_2_ball, radius)){
			Log.d(TAG, "LEFT!!!");
			velocity.x *= -1;
			entity.hitBy(this);
		}else if(lineCircleIntersection(bottom, bottom_left_2_ball, radius)){
			Log.d(TAG, "BOTTOM!!!");
			velocity.y *= -1;
			entity.hitBy(this);
		}else if(lineCircleIntersection(right, top_right_2_ball, radius)){
			Log.d(TAG, "RIGHT!!!");
			velocity.x *= -1;
			entity.hitBy(this);
		}		
	}
	
	/*
	 * d is the Line, End - Start;
	 * f is Start - Center of Circle
	 * 
	 * Makes heavy use of -b +- sqrt (b^2 -4ac) / 2a
	 */
	private boolean lineCircleIntersection(Vector2d d, Vector2d f, double r){
		
		double a = d.dot(d) ;
		double b = 2*f.dot(d) ;
		double c = f.dot(f) - r*r ;

		double discriminant = b*b-4*a*c; //Remember A Level Math. 
		if( discriminant < 0 )
		{
		  return false;
		}
		else
		{
		  discriminant = Math.sqrt(discriminant);

		  double t1 = (-b - discriminant)/(2*a);
		  double t2 = (-b + discriminant)/(2*a);

		  if( t1 >= 0 && t1 <= 1 )
		  {
		    return true;
		  }

		  if( t2 >= 0 && t2 <= 1 )
		  {
		    return true ;
		  }

		  return false ;
		}
	}

	@Override
	public void hitBy(Entity entity) {
		
	}

}