package uk.co.tmilner.Breakout.InputCommand;

import uk.co.tmilner.Breakout.Screens.Level1;
import uk.co.tmilner.Breakout.Screens.MainMenu;
import uk.co.tmilner.Breakout.Screens.Screen;

public class Down extends InputCommand {

	@Override
	public void pressed(Screen screen) {
		return;
	}

	@Override
	public void released(Screen screen) {
		if(screen instanceof MainMenu){
			((MainMenu)screen).down();
		}else if(screen instanceof Level1){
			((Level1)screen).down();
		}
		return;
	}

}
