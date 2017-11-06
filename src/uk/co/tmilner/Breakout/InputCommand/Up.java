package uk.co.tmilner.Breakout.InputCommand;

import uk.co.tmilner.Breakout.Screens.MainMenu;
import uk.co.tmilner.Breakout.Screens.Screen;

public class Up extends InputCommand {

	@Override
	public void pressed(Screen screen) {
		// TODO Auto-generated method stub

	}

	@Override
	public void released(Screen screen) {
		if(screen instanceof MainMenu){
			((MainMenu)screen).up();
		}
		return;
	}

}
