package uk.co.tmilner.Breakout.InputCommand;

import uk.co.tmilner.Breakout.Screens.Screen;

public abstract class InputCommand {
	
	public abstract void pressed(Screen screen);
	
	public abstract void released(Screen screen);
	
}
