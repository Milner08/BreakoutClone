package uk.co.tmilner.Breakout.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import uk.co.tmilner.Breakout.GameLoader;

public class MainMenu extends Screen{
	private static int BUTTON_HEIGHT, BUTTON_WIDTH;
	private static final int START = 0, OPTIONS = 1;
	private int selected = START;

	public MainMenu(int width, int height) {
		super(width, height);
		System.out.println("MainMenu Created");
	}

	@Override
	public void update(long timePassed) {
		
	}

	@Override
	public synchronized void draw(Graphics2D g){
		BUTTON_HEIGHT = 50;
		BUTTON_WIDTH = getWidth()-20;
		int total_button_height = BUTTON_HEIGHT+10;
		
		g.setColor(Color.GRAY);
		g.fillRect(10, 3*total_button_height, BUTTON_WIDTH, BUTTON_HEIGHT);
		g.fillRect(10, 4*total_button_height, BUTTON_WIDTH, BUTTON_HEIGHT);
		
		
		g.setColor(getForeground());
		
		g.setFont(new Font("Arial", Font.BOLD, 60));
		g.drawString("BREAKOUT!", 19 , 70);
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("By Thomas Milner", 110 , 120);
		
		g.setFont(new Font("Arial", Font.BOLD, 45));
		g.drawString("Start", 20 , 3*total_button_height+40);
		g.drawString("Options - N/A", 20 , 4*total_button_height+40);

		int[] xPoints1 = {3,20,3};
		int[] xPoints2 = {getWidth() - 3, getWidth() - 20, getWidth() - 3};
		switch(selected){
		case START:
			int[] startYPoints = {10 + 3*total_button_height, 25 + 3*total_button_height, 40 + 3*total_button_height};
			g.fillPolygon(xPoints1, startYPoints, 3);
			g.fillPolygon(xPoints2, startYPoints, 3);
			g.drawRect(10, 3*total_button_height, BUTTON_WIDTH, BUTTON_HEIGHT);
			break;
		case OPTIONS:
			int[] optionsYPoints = {10 + 4*total_button_height, 25 + 4*total_button_height, 40 + 4*total_button_height};
			g.fillPolygon(xPoints1, optionsYPoints, 3);
			g.fillPolygon(xPoints2, optionsYPoints, 3);
			g.drawRect(10, 4*total_button_height, BUTTON_WIDTH, BUTTON_HEIGHT);
			break;
		}
		//Clean up rendering objects.
		g.setColor(getForeground());
		
	}

	public void enter() {
		switch(selected){
		case 0: 
			GameLoader.changeScreen(Level1.class);
			break;
		}
		
	}

	public void down() {
		if(selected < 1){
			selected++;
		}
	}
	
	public void up() {
		if(selected > 0){
			selected--;
		}
	}
}
