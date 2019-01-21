package rer_B;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RerWindow extends JFrame
{
	private TrainText text = new TrainText();
	
	private class TrainText extends JTextArea
	{
		public TrainText()
		{
			super();
			setEditable(false);
			setText("Loading...");
		}
		
		public void setText(TrainList trains)
		{
			String text = "RER B prévus en gare de Cité Universitaire : \n";
			for(Train train : trains)
			{
				text += "Le train " + train.vehicleName + " à direction de " + train.lineDirection;
				
				if(train.arrivalCode() == -2)
				{
					text += ": " + train.schedule + "\n";
				}
				else if(train.arrivalCode() == -1)
				{
					text += " est à quai.\n";
				}
				else if(train.arrivalCode() == -0)
				{
					text += " est à l'approche.\n";
				}
				else
				{
					text += " arrive à quai dans " + train.arrivalCode() + " minutes.\n";
				}
			}
			setText(text);
		}
	}
	
	public RerWindow()
	{
		setTitle("Horaires des RER B");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800,600));
		add(text);
		
		pack();
		setVisible(true);
	}
	
	public void updateText(TrainList trains)
	{
		text.setText(trains);
	}
}
