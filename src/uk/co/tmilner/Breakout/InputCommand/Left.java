package uk.co.tmilner.Breakout.InputCommand;

import uk.co.tmilner.Breakout.Screens.Level;
import uk.co.tmilner.Breakout.Screens.Screen;

public class Left extends InputCommand {

	@Override
	public void pressed(Screen screen) {
		if(screen instanceof Level){
			((Level)screen).left();
		}
		return;	
	}

	@Override
	public void released(Screen screen) {
		if(screen instanceof Level){
			((Level)screen).none();
		}
		return;	
	}

}
