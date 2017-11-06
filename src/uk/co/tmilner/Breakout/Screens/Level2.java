package uk.co.tmilner.Breakout.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import uk.co.tmilner.Breakout.Log;
import uk.co.tmilner.Breakout.Entities.Ball;
import uk.co.tmilner.Breakout.Entities.Block;
import uk.co.tmilner.Breakout.Entities.Paddle;

public class Level2 extends Level {
	private static String TAG = "Level 2";
	
	Paddle paddle;
	Ball ball;
	
	public int rows = 8;
	public int blocksPerRow = 16;
	Block[][] blocks = new Block[rows][blocksPerRow];

	public Level2(int width, int height) {
		super(width, height);
		Log.d(TAG, "Level2 Starting Up!");
		
		paddle = new Paddle((getWidth()/2-20),(getHeight()-40));
		ball = new Ball((getWidth()/2-20),(getHeight()-100));
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < blocksPerRow; j++){
				blocks[i][j] = new Block(j*25,i*25);
			}
		}
		repaint();
	}

	@Override
	public void update(long dt) {		
		paddle.update(dt);
		ball.update(dt);
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < blocksPerRow; j++){
				ball.collision(blocks[i][j]);		
			}
		}
		
		paddle.collision(this);
		ball.collision(this);
		
		ball.collision(paddle);
	}

	@Override
	public synchronized void draw(Graphics2D g){
		paddle.draw(g);
		ball.draw(g);
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < blocksPerRow; j++){
				if(blocks[i][j].visible){
					blocks[i][j].draw(g);
				}
			}
		}
	}

	@Override
	public void left() {
		paddle.left();
	}

	@Override
	public void right() {
		paddle.right();
	}
	
	public void none() {
		paddle.none();
	}

}
