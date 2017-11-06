package uk.co.tmilner.Breakout;

public class Log {
	public static boolean debug = false;
		
	public static void d(String tag, String message){
		if(Log.debug){
			System.out.println("Debug - " + tag + ": " + message);
		}
	}
	
	public static void e(String tag, String message){
		System.out.println("Error - " + tag + ": " + message);
	}
}
