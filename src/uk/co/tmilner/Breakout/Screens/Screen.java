package uk.co.tmilner.Breakout.Screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public abstract class Screen extends JPanel{
	
	public Screen(int width, int height){
		setLayout(null);
	  	setSize(width,height);
	  	setPreferredSize(new Dimension(width,height));
	  	setBounds(0, 0, width, height);
    	setFocusable(true);
    	setBackground(Color.BLACK);
    	setForeground(Color.GREEN);
    	setDoubleBuffered(true);
  		repaint();	
	}
		
	public void paint(Graphics G) {
		Graphics2D g = (Graphics2D)G;
		
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		draw(g);
				
		//Clean up rendering objects.
		g.setColor(getForeground());
		g.dispose();
		G.dispose();		
	}
	
	public abstract void draw(Graphics2D g);
	
	public abstract void update(long timePassed);
}
