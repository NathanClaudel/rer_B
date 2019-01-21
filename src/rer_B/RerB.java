package rer_B;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class RerB 
{
	private static final RerWindow window = new RerWindow();
	private static final Timer timer = new Timer();
	private static final UpdateTask updateTask = new UpdateTask();
	
	private static class UpdateTask extends TimerTask
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			TrainList trains = ViaNavigoClient.updateTrains();
			System.out.println(trains);
			window.updateText(trains);
		}
	}
	
	public static void main(String[] args)
	{
		// Update every 10 sec
		timer.scheduleAtFixedRate(updateTask, 0, 10000);
	}
}
