package com.example.demo; 

import java.io.BufferedReader; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import android.content.Context; 
import android.content.Intent;
import android.net.ConnectivityManager; 
import android.net.NetworkInfo; 
import android.os.AsyncTask; 
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText; 
import android.widget.TextView; 
import android.widget.Toast;

public class Webdata extends  AsyncTask<String, String, String>{ 
	
	//Initialization
		 public static Bundle bundle1 = new Bundle();
		 public static Bundle bundle2 = new Bundle();
		 public static Bundle bundle3 = new Bundle();
		 
		 
	private TextView dataField; 
	private Context context; 
	
	public String temperature;
	public static String date;
	public String condition;
	public String humidity;
	public String wind;
	public String link;
	public String place="Weather Report";
	public String url1;
	public String url2;
	public String placename;
	public String visibility;
	public String pressure;
	public String rising;
	public String sunrise;
	public String sunset;
	public String code;
	public String full;
	public String chill;
	
    public String d11,d13,d14,d15,d16,d21,d23,d24,d25,d26,d31,d33,d34,d35,d36,
	d41,d43,d44,d45,d46,d51,d53,d54,d55,d56;
	
	String woeid=null;
	
	public Webdata(Context context,TextView dataField) { 
		this.context = context; 
		this.dataField = dataField; 
	} 
	
	//check Internet conenction. 
	private void checkInternetConenction(){ 
		ConnectivityManager check = (ConnectivityManager) 
				this.context. getSystemService(Context.CONNECTIVITY_SERVICE); 
		if (check != null) { 
			NetworkInfo[] info = check.getAllNetworkInfo(); 
			if (info != null) 
				for (int i = 0; i <info.length; i++) 
					if (info[i].getState() == NetworkInfo.State.CONNECTED) { 
						Toast.makeText(context, "Internet is connected", Toast.LENGTH_SHORT).show();
					} 
		} 
		else{
				Toast.makeText(context, "not conencted to internet", 
				Toast.LENGTH_SHORT).show(); 
			} 
	} 
	protected void onPreExecute(){ 
		checkInternetConenction(); 
	}

	@Override
	protected String doInBackground(String... arg0) { 
				try{
					
					String link = (String)arg0[0]; 
					URL url = new URL(link); 
				HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
				conn.setReadTimeout(10000); 
				conn.setConnectTimeout(15000); 
				conn.setRequestMethod("GET"); 
				conn.setDoInput(true); 
				conn.connect();
				
				Document doc = null;
	    	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	    DocumentBuilder docb = dbf.newDocumentBuilder();
	    	 
				InputStream is = conn.getInputStream();
				
				 doc = docb.parse(is);
				 
				//Parsing
		    	    XPath xPath =  XPathFactory.newInstance().newXPath();
		    	    String expression = "/places/place/woeid";
		    	   
		    		woeid = xPath.compile(expression).evaluate(doc);
		    	   
		    	  //Getting the value of woeid is finidshed//

		        	//Getting Weather Info from Yahoo//
		    	    
		        	//link="http://weather.yahooapis.com/forecastrss?w=" + woeid + "&u=c";
		    		link="http://weather.yahooapis.com/forecastrss?w=1915035 &u=c";
		        	//Getting RSS feed
		    	    
		    	    
		    	    URL url1 = new URL(link); 
					HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection(); 
					conn1.setReadTimeout(10000); 
					conn1.setConnectTimeout(15000); 
					conn1.setRequestMethod("GET"); 
					conn1.setDoInput(true); 
					conn1.connect();
					
					Document dest = null;
		    	    DocumentBuilderFactory dbf1 = DocumentBuilderFactory.newInstance();
		    	    DocumentBuilder docb1 = dbf1.newDocumentBuilder();
		    	 
					InputStream is1 = conn1.getInputStream();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader (is1, "UTF-8") ); 
					String data = null; 
					String webPage = ""; 
					while ((data = reader.readLine()) != null){ 
						webPage += data + "\n"; }
						
						
					
					dest = docb1.parse(is1);
					 
					 //return webPage;
					 
					//Parsing

				    	//Parsing using node

				    	Node cityNode = dest.getElementsByTagName("yweather:location").item(0);
				    	place = cityNode.getAttributes().getNamedItem("city").getNodeValue().toString();
				    	Node countryNode = dest.getElementsByTagName("yweather:location").item(0);
				    	place = place + "," +countryNode.getAttributes().getNamedItem("country").getNodeValue().toString();


				    	Node conditionNode = dest.getElementsByTagName("yweather:condition").item(0);
				    	temperature = conditionNode.getAttributes().getNamedItem("temp").getNodeValue().toString();
				    	condition = conditionNode.getAttributes().getNamedItem("text").getNodeValue().toString();
				    	date = conditionNode.getAttributes().getNamedItem("date").getNodeValue().toString();
				    	code = conditionNode.getAttributes().getNamedItem("code").getNodeValue().toString();
				    	
				    	Node UnitNode = dest.getElementsByTagName("yweather:units").item(0);
				 
				    	Node atmosphereNode = dest.getElementsByTagName("yweather:atmosphere").item(0);
				    	humidity = atmosphereNode.getAttributes().getNamedItem("humidity").getNodeValue().toString();
				    	humidity = humidity + "%";
				    	visibility = atmosphereNode.getAttributes().getNamedItem("visibility").getNodeValue().toString();
				    	visibility = visibility + " " + UnitNode.getAttributes().getNamedItem("distance").getNodeValue().toString();
				    	pressure = atmosphereNode.getAttributes().getNamedItem("pressure").getNodeValue().toString();
				    	pressure = pressure + " " + UnitNode.getAttributes().getNamedItem("pressure").getNodeValue().toString();
				    	rising = atmosphereNode.getAttributes().getNamedItem("rising").getNodeValue().toString();
				    	
				    	Node astronomyNode = dest.getElementsByTagName("yweather:astronomy").item(0);
				    	sunrise = astronomyNode.getAttributes().getNamedItem("sunrise").getNodeValue().toString();
				    	sunset = astronomyNode.getAttributes().getNamedItem("sunset").getNodeValue().toString();
				    	
				    	Node windNode = dest.getElementsByTagName("yweather:wind").item(0);
				    	wind = windNode.getAttributes().getNamedItem("speed").getNodeValue().toString();
				    	Node windUnitNode = dest.getElementsByTagName("yweather:units").item(0);
				    	wind = wind + " "+windUnitNode.getAttributes().getNamedItem("speed").getNodeValue().toString();
				    	chill = windNode.getAttributes().getNamedItem("chill").getNodeValue().toString();
				        
				    	
				    	//DATA from forecast node
				 //DAY 1   	
				    	Node forecastNode = dest.getElementsByTagName("yweather:forecast").item(0);
				    	d11 = forecastNode.getAttributes().getNamedItem("day").getNodeValue().toString();
				    	d13 = forecastNode.getAttributes().getNamedItem("low").getNodeValue().toString();
				    	d14 = forecastNode.getAttributes().getNamedItem("high").getNodeValue().toString();
				    	d15 = forecastNode.getAttributes().getNamedItem("text").getNodeValue().toString();
				    	d16 = forecastNode.getAttributes().getNamedItem("code").getNodeValue().toString();
				//DAY 2
				    	Node forecastNode1 = dest.getElementsByTagName("yweather:forecast").item(1);
				    	d21 = forecastNode1.getAttributes().getNamedItem("day").getNodeValue().toString();
				    	d23 = forecastNode1.getAttributes().getNamedItem("low").getNodeValue().toString();
				    	d24 = forecastNode1.getAttributes().getNamedItem("high").getNodeValue().toString();
				    	d25 = forecastNode1.getAttributes().getNamedItem("text").getNodeValue().toString();
				    	d26 = forecastNode1.getAttributes().getNamedItem("code").getNodeValue().toString();
				//DAY 3
				    	Node forecastNode2 = dest.getElementsByTagName("yweather:forecast").item(2);
				    	d31 = forecastNode2.getAttributes().getNamedItem("day").getNodeValue().toString();
				    	d33 = forecastNode2.getAttributes().getNamedItem("low").getNodeValue().toString();
				    	d34 = forecastNode2.getAttributes().getNamedItem("high").getNodeValue().toString();
				    	d35 = forecastNode2.getAttributes().getNamedItem("text").getNodeValue().toString();
				    	d36 = forecastNode2.getAttributes().getNamedItem("code").getNodeValue().toString();
				//DAY 4
				    	Node forecastNode3 = dest.getElementsByTagName("yweather:forecast").item(3);
				    	d41 = forecastNode3.getAttributes().getNamedItem("day").getNodeValue().toString();
				    	d43 = forecastNode3.getAttributes().getNamedItem("low").getNodeValue().toString();
				    	d44 = forecastNode3.getAttributes().getNamedItem("high").getNodeValue().toString();
				    	d45 = forecastNode3.getAttributes().getNamedItem("text").getNodeValue().toString();
				    	d46 = forecastNode3.getAttributes().getNamedItem("code").getNodeValue().toString();
				//DAY 5
				    	Node forecastNode4 = dest.getElementsByTagName("yweather:forecast").item(4);
				    	d51 = forecastNode4.getAttributes().getNamedItem("day").getNodeValue().toString();
				    	d53 = forecastNode4.getAttributes().getNamedItem("low").getNodeValue().toString();
				    	d54 = forecastNode4.getAttributes().getNamedItem("high").getNodeValue().toString();
				    	d55 = forecastNode4.getAttributes().getNamedItem("text").getNodeValue().toString();
				    	d56 = forecastNode4.getAttributes().getNamedItem("code").getNodeValue().toString();
				    	
				    	//Putting Data
				    	//Bundle 1
				    	bundle1.putString("TEMPARATURE1",temperature);
				    	bundle1.putString("PLACE1",place);
				    	bundle1.putString("CONDITION1",condition);
				    	bundle1.putString("HIGH1",d14);
				    	bundle1.putString("LOW1",d13);
				    	bundle1.putString("CODE1",code);
				    	
				    	//Bundle 2
				    	//DAY1
				    	bundle2.putString("D1",d11);
				    	bundle2.putString("D1C",d16);
				    	bundle2.putString("D1H",d14);
				    	bundle2.putString("D1L",d13);
				    	
				    	//Day2
				    	bundle2.putString("D2",d21);
				    	bundle2.putString("D2C",d26);
				    	bundle2.putString("D2H",d24);
				    	bundle2.putString("D2L",d23);
				    	
				    	//Day3
				    	bundle2.putString("D3",d31);
				    	bundle2.putString("D3C",d36);
				    	bundle2.putString("D3H",d34);
				    	bundle2.putString("D3L",d33);
				    	
				    	//Day4
				    	bundle2.putString("D4",d41);
				    	bundle2.putString("D4C",d46);
				    	bundle2.putString("D4H",d44);
				    	bundle2.putString("D4L",d43);
				    	
				    	//Day5
				    	bundle2.putString("D5",d51);
				    	bundle2.putString("D5C",d56);
				    	bundle2.putString("D5H",d54);
				    	bundle2.putString("D5L",d53);
				    	
				    	//Bundle3
				    	bundle3.putString("SUNRISE",sunrise);
				    	bundle3.putString("SUNSET",sunset);
				    	bundle3.putString("VISIBILITY",visibility);
				    	bundle3.putString("HUMIDITY",humidity);
				    	bundle3.putString("PRESSURE",pressure);
				    	bundle3.putString("WIND",wind);
				    	bundle3.putString("CHILL",chill);
				  
				    	return webPage;
					    
		    	   
					}catch(Exception e){
						return new String("Exception: " + e.getMessage()); 
						}
			} 
	protected void onPostExecute(String result){ 
				this.dataField.setText(result); 
			}
	}
