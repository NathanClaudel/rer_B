package rer_B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ViaNavigoClient 
{
	private static final String lineId = "810:B";
	private static final String stopPointId = "StopPoint:8775864:810:B";
	private static final String apiURL = "https://api-lab-trone-stif.opendata.stif.info/service/tr-vianavigo/departures";
	private static final String apiKey = "64f3b8a5c97a3aa1587bcda670f3e092a9fbfdc4d29d4ac0adcac9b2";
	private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	public static TrainList updateTrains()
	{
		try {
			return mapper.readValue(updateRequest(), TrainList.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
			
	public static String updateRequest()
	{
		try {
			System.out.println("Updating...");
			
			// create the HttpURLConnection
			String strURL = apiURL + "?apikey=" + URLEncoder.encode(apiKey, "UTF-8")
								   + "&line_id=" + URLEncoder.encode(lineId, "UTF-8")
								   + "&stop_point_id=" + URLEncoder.encode(stopPointId, "UTF-8");
			
			URL url = new URL(strURL);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			// Necessary because Vianavigo refuses the connection otherwise
			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
			connection.setRequestProperty("Accept", "application/json");
				
		    //dump all the content
		    return getContent(connection);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
		
	private static String getContent(HttpsURLConnection connection)
	{
		String input = "";
		
		if(connection!=null)
		{
			try {

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder stringBuilder = new StringBuilder();

				String line = null;
				while ((line = reader.readLine()) != null)
				{
					stringBuilder.append(line + "\n");
				}
				
				input = stringBuilder.toString();
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return input;
	}
}
