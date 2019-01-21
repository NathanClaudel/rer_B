package rer_B;

import java.util.ArrayList;
import java.util.Collections;

// Ued to rename ArrayList<Train> for jackson usage purposes.
public class TrainList extends ArrayList<Train>
{
	public void sortByArrivalTime()
	{
		Collections.sort(this, new Train.ArrivalComparator());
	}
	
	@Override
	public String toString()
	{
		String str = "";
		
		for(Train train : this)
		{
			str += train.toString();
		}
		
		return str;
	}
}
