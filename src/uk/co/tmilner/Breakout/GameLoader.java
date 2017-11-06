package uk.co.tmilner.Breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import uk.co.tmilner.Breakout.Screens.Level1;
import uk.co.tmilner.Breakout.Screens.Level2;
import uk.co.tmilner.Breakout.Screens.MainMenu;
import uk.co.tmilner.Breakout.Screens.Notification;
import uk.co.tmilner.Breakout.Screens.Screen;


/**
 * @author Thomas Milner
 *
 */
@SuppressWarnings("serial")
public class GameLoader extends JFrame {
	private final static String TAG = "Game Loader";
	private final static int WINDOW_WIDTH = 400, WINDOW_HEIGHT = 700;
	private final static int FRAME_WAIT = 40;
	
	private static boolean NEW_SCREEN = true;
	
	public static int FRAME_WIDTH, FRAME_HEIGHT;
	
	private static Screen screen;
	private InputHandler input;
	private static PriorityQueue<Notification> notificationQueue = new PriorityQueue<Notification>();
		
	private long startTime;
	private long totalTime;
	
	public static void main(String[] args) {
		Log.debug = true;
		new GameLoader();
	}
	
	public GameLoader(){
		init();
		gameLoop();
	}
	
	private void gameLoop() {
		startTime = System.currentTimeMillis();
		totalTime = startTime;
		long fpsTimer = startTime;
		int fps = 0;
		while (true) {
			long timePassed = System.currentTimeMillis() - totalTime;
			totalTime += timePassed;
	
			if(NEW_SCREEN){
				add(screen);
				NEW_SCREEN = false;
			}
			
			fps++;
			
			//Every second read off FPS
			if((System.currentTimeMillis() - fpsTimer) >= 1000){
				//Log.d(TAG, "FPS :"+fps);
				fpsTimer = System.currentTimeMillis();
				fps = 0;
			}
			
			
			screen.update(timePassed); //Update
			
			screen.repaint(); //Render
		
			//Keep up framerate
			try {
				//How much time is left to kill for this frame?
				int sleep = FRAME_WAIT - (int)(System.currentTimeMillis() - totalTime);
				if (sleep > 0)
				{
					Thread.sleep(sleep);					
				}				
			} catch (Exception ex) {}
			
		}
	}

	private void init(){
		input = new InputHandler(InputHandler.Mode.WASD);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //How the game should close
		setLocationRelativeTo(null); //Not sure? Maybe you can set it to locate next to things etc
		setResizable(false); //Stop it from being resized 
		setFocusable(true); //Stop it from ...? :S
		setLayout(null); // Dont use any layout files etc
		setBackground(Color.BLUE); //Set Background colour
		setForeground(Color.WHITE); //Set foreground colour...
		setFont(new Font("Arial",Font.PLAIN,24)); //set the font to use
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT); //set the size of the window
		setTitle("BREAKOUT"); //set the title
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2) - 200, (Toolkit.getDefaultToolkit().getScreenSize().height/2) - 350); //set where it should be placed initially 
		//setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "blank cursor")); //Set what the cursor should be
		setVisible(true); //Make it visible

		this.addKeyListener(input);
		
		FRAME_WIDTH = getWidth();
		FRAME_HEIGHT = getHeight()-22;//Minus Menubar... OSX is odd...
		
		changeScreen(MainMenu.class);
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
		
		g.setColor(getForeground());
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString("http://tmilner.co.uk", 200 , 10);
				
		//Clean up rendering objects.
		g.setColor(getForeground());
		g.dispose();
		G.dispose();		
	}

	
	public static Screen currentScreen(){
		return screen;
	}
	
	public static void changeScreen(Class<? extends Screen> screenClass){
		Log.d(TAG,"Changing Screen");
		NEW_SCREEN = true;
		if(screenClass == MainMenu.class){
			screen = new MainMenu(FRAME_WIDTH, FRAME_HEIGHT);
		}else if(screenClass == Level1.class){
			screen = new Level1(FRAME_WIDTH, FRAME_HEIGHT);
		}else if(screenClass == Level2.class){
			screen = new Level2(FRAME_WIDTH, FRAME_HEIGHT);
		}else{
			NEW_SCREEN = false;
		}
	}
	
	public static void addNotification(Notification notification){
		Log.d(TAG, "Adding Notification");
		notificationQueue.add(notification);
	}
	
	public static Notification peekNotification(){
		Log.d(TAG, "Adding Notification");
		return notificationQueue.peek();
	}
	
	public static Notification pollNotification(){
		Log.d(TAG, "Adding Notification");
		return notificationQueue.poll();
	}

}
