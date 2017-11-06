package uk.co.tmilner.Breakout.InputCommand;

import uk.co.tmilner.Breakout.Screens.MainMenu;
import uk.co.tmilner.Breakout.Screens.Screen;

public class Enter extends InputCommand {

	@Override
	public void pressed(Screen screen) {
		
	}

	@Override
	public void released(Screen screen) {
		System.out.println("Enter");
		if(screen instanceof MainMenu){
			((MainMenu)screen).enter();
		}
		return;
	}

}
