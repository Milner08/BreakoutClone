package uk.co.tmilner.Breakout;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import uk.co.tmilner.Breakout.InputCommand.Down;
import uk.co.tmilner.Breakout.InputCommand.Empty;
import uk.co.tmilner.Breakout.InputCommand.Enter;
import uk.co.tmilner.Breakout.InputCommand.InputCommand;
import uk.co.tmilner.Breakout.InputCommand.Left;
import uk.co.tmilner.Breakout.InputCommand.Right;
import uk.co.tmilner.Breakout.InputCommand.Up;
import uk.co.tmilner.Breakout.Screens.Screen;

public class InputHandler extends KeyAdapter{
	public enum Mode{WASD,ARROWS}
	private InputCommand mButtonW, mButtonA, mButtonS, mButtonD, mButtonRight, mButtonLeft, mButtonDown, mButtonUp, mButtonEnter;
	
	public InputHandler(Mode mode){
		switch(mode){
		case WASD:
			mButtonA = new Left();
			mButtonD = new Right();
			mButtonW = new Up();
			mButtonS = new Down();
			mButtonEnter = new Enter();
			mButtonLeft = new Left();
			mButtonRight = new Right();
			mButtonDown = new Down();
			mButtonUp = new Up();
			break;
		case ARROWS:
			mButtonLeft = new Left();
			mButtonRight = new Right();
			mButtonDown = new Down();
			mButtonUp = new Up();
			mButtonEnter = new Enter();
			mButtonA = new Empty();
			mButtonD = new Empty();
			mButtonW = new Empty();
			mButtonS = new Empty();
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Screen screen = GameLoader.currentScreen();
		
		getCommand(e).pressed(screen);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Screen screen = GameLoader.currentScreen();

		getCommand(e).released(screen);	
	}

	
	public InputCommand getCommand(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_A:
				return mButtonA;
			case KeyEvent.VK_D:
				return mButtonD;
			case KeyEvent.VK_W:
				return mButtonW;
			case KeyEvent.VK_S:
				return mButtonS;
			case KeyEvent.VK_RIGHT:
				return mButtonRight;
			case KeyEvent.VK_DOWN:
				return mButtonDown;
			case KeyEvent.VK_UP:
				return mButtonUp;
			case KeyEvent.VK_LEFT:
				return mButtonLeft;
			case KeyEvent.VK_ENTER:
				return mButtonEnter;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
			default:
				return null;
		}
	}

}
