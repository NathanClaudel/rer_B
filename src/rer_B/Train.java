package rer_B;

import java.util.Comparator;

public class Train 
{
	public static class ArrivalComparator implements Comparator<Train>
	{
		@Override
		public int compare(Train t0, Train t1) 
		{
			return t0.arrivalCode() - t1.arrivalCode();
		}
	}
	
	//These attributes need to be public in order to allow jackson parser
	public String vehicleName;
	public String lineDirection;
	public String sens;
	public String code;
	public String time;
	public String schedule;
	
	public String toString()
	{
		return "Train " + vehicleName + " à direction de " + lineDirection + " : Prévu " + schedule + ", Annoncé " + time + "\n";
	}
	
	/*
	 * Conventional int allowing an easier way to compare arrival times.
	 * Uses schedule when time is null, the prioritized value is "time".
	 */
	public int arrivalCode()
	{
		if(time == null)
		{
			return stringToArrivalCode(schedule);
		}
		else
		{
			return stringToArrivalCode(time);
		}
	}
	
	private int stringToArrivalCode(String s)
	{
		if(schedule.equals("A quai"))
		{
			return -1;
		}
		if(schedule.equals("A l'approche"))
		{
			return 0;
		}
		int i = -2;
		try
		{
			i = Integer.parseInt(s);
		}
		catch(Exception e) {}
		
		return i;
	}
}
