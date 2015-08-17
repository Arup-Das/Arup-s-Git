package com.example.demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Element;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;

public class WebActivity extends FragmentActivity {
	
	//Initialization
	 public static Bundle bundle1 = new Bundle();
	 public static Bundle bundle2 = new Bundle();
	 public static Bundle bundle3 = new Bundle();
	 
	 TextView title;
	
	String temperature, date, condition, humidity, wind, link, 
	place="Weather Report", url1, url2,placename,visibility,pressure,rising,sunrise,sunset,code,full,chill;
	
    String d11,d13,d14,d15,d16,d21,d23,d24,d25,d26,d31,d33,d34,d35,d36,
	d41,d43,d44,d45,d46,d51,d53,d54,d55,d56;
	
	String woeid=null;
	String msg2="Loading..";
	ArrayList<String> weather = new ArrayList<String>();
	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		
		title = (TextView) findViewById(R.id.d1);
    	title.setText("Weather Portal");
		placename=MainActivity.placename;
		
		url1="http://where.yahooapis.com/v1/places.q('"+ placename +"')?select=short&appid=[z2JP.KDV34HnmD7Sb.hlOCKlkpZr4m5Ky0H9llL_BIcJL4tXfkrBsCZc1voTKldI6KgHDd4-]";
		new retrieve_weatherTask().execute();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.full, menu);
		return true;
	}
    
    
    //Main Work
    /*The Class Defined below does the main work
     * It get the woeid of the given city
     * It get the whole information of weather and parse them
     */
    protected class retrieve_weatherTask extends AsyncTask<Void, String, String> {

    	protected void onPreExecute(){
    	dialog = new ProgressDialog(WebActivity.this);
    	dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	dialog.setMessage(msg2);
    	dialog.setCancelable(false);
    	dialog.show();
    	//android.os.SystemClock.sleep(10000);
    	
    	}

    	@Override
    	protected String doInBackground(Void... arg0) {
    		
    		String qResult = null;
    		
    		
    		//Getting WOEID
    		String xml = null;
    	
    		//Getting the RSS feed
    		
    		
    	    try {
    	        // defaultHttpClient
    	        DefaultHttpClient httpClient = new DefaultHttpClient();
    	        HttpGet httpGet= new HttpGet(url1);

    	        HttpResponse httpResponse1 = httpClient.execute(httpGet);
    	        HttpEntity httpEntity = httpResponse1.getEntity();
    	        xml = EntityUtils.toString(httpEntity);

    	    } catch (Exception e) {
    	    	showAlertDialog(WebActivity.this, "No Internet Connection",
                "You don't have internet connection. Please check your Internet connction and try again.", false);
    	    } 
    	    
    	    
    	    //Coping the RSS feed into Document
    	    
    	    Document doc = null;
    	    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

    	    try {
    	    DocumentBuilder docb = dbf.newDocumentBuilder();
    	    InputSource in = new InputSource();
    	    in.setCharacterStream(new StringReader(xml));
    	    doc = docb.parse(in); 
    	    } catch (Exception e) {
    	    	showAlertDialog(WebActivity.this, "Data connection timeout",
    	        "We can not find information about the place you want.\nPleace enter place name correctly.", false);
    	    } 
    	    
    	    //Parsing
    	    XPath xPath =  XPathFactory.newInstance().newXPath();
    	    String expression = "/places/place/woeid";
    	    
    	    //read a string value
    	    try {
    		     woeid = xPath.compile(expression).evaluate(doc);
    	    } catch (Exception e1) {
    		// TODO Auto-generated catch block
    		e1.printStackTrace();
    	}
    	   
    	//Getting the value of woeid is finidshed//
    	    char unit = 'c';
    	    
    	    
    	    
    	    
    	    
    	//Getting the value of woeid is finidshed//

    	//Getting Weather Info from Yahoo//
    	    
    	    
    	    //Url never change

    	url2="http://weather.yahooapis.com/forecastrss?w=" + woeid + "&u=" + unit;
    	
    	
    	
    	
		qResult = getWeatherString( url2);
		
    	
    	Document dest = convertStringToDocument(qResult);
    	
    	
    	parseDataFromDocument(dest);
    	


	    
		return qResult;
    	
		//Getting value From Yahoo Weather is finished
    	
    	}

    	protected void onPostExecute(String result) {
    	System.out.println("POST EXECUTE");
    	String msg3 = "Finished";
    	dialog.setMessage(msg3);
    	dialog.setCancelable(false);
    	dialog.show();
    	
    	android.os.SystemClock.sleep(1000);
    	
    	if(dialog.isShowing())
    	dialog.dismiss();
    	
    	Intent intent = new Intent(WebActivity.this , FragActivity.class); 
	    startActivity(intent);
	    finish();
    	}

    	}
    
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * @param status - success/failure (used to set icon)
     * */
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        alertDialog.setIcon(R.drawable.s);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialog, final int which) {
            	final Intent intent = new Intent(WebActivity.this , MainActivity.class); 
        	    startActivity(intent);
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }
	
	
	
	private String getWeatherString(String queryUrl)  {
		

		String qResult = "";
		
		/** default timeout is 20 seconds */
	    int mConnectTimeout = 20 * 1000;
	    int mSocketTimeout = 20 * 1000; 
	    
	    HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, mConnectTimeout);
		HttpConnectionParams.setSoTimeout(params, mSocketTimeout);
		HttpClient httpClient = new DefaultHttpClient(params);
		
		HttpGet httpGet = new HttpGet(queryUrl);
		
		HttpEntity httpEntity = null;
		try {
			httpEntity = httpClient.execute(httpGet).getEntity();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (httpEntity != null) {
			InputStream inputStream = null;
			try {
				inputStream = httpEntity.getContent();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Reader in = new InputStreamReader(inputStream);
			BufferedReader bufferedreader = new BufferedReader(in);
			StringBuilder stringBuilder = new StringBuilder();

			String readLine = null;

			try {
				while ((readLine = bufferedreader.readLine()) != null) {
					stringBuilder.append(readLine + "\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			qResult = stringBuilder.toString();
		}
		
		return qResult;
		
	}
	
	private Document convertStringToDocument(String src) {
		
		
    	
		Document dest = null;

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder parser;

		try {
			parser = dbFactory.newDocumentBuilder();
			dest = parser.parse(new ByteArrayInputStream(src.getBytes()));
		} catch (ParserConfigurationException e) {
		} catch (SAXException e) {
		} catch (IOException e) {
		}

		return dest;
	}
	
	private void parseDataFromDocument(Document dest){
		
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
		
	}

}
