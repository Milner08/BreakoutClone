package uk.co.tmilner.Breakout.InputCommand;

import uk.co.tmilner.Breakout.Screens.Level;
import uk.co.tmilner.Breakout.Screens.MainMenu;
import uk.co.tmilner.Breakout.Screens.Screen;

public class Right extends InputCommand {

	@Override
	public void pressed(Screen screen) {
		if(screen instanceof Level){
			((Level)screen).right();
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
