package sda;

import static org.asynchttpclient.Dsl.asyncHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.ListenableFuture;
import org.asynchttpclient.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class DataRequest {
	
	static String ApiKey = "ecdyk5YRfnFIfdgR9Px5wW2lLTK6ztEIJPYt1qDoREZZHLaF2UjhHgWkCshRv3uP";
	static String SecretKey="lqPOxrhGoCGdlH50u70XT5klnx4ENzpUq428DKRTItkievXsHdbBSiuIW0S3Y0Ic";
	public static ArrayList<Candlestick> getCD(String pair,String timeframe){
		ArrayList<Candlestick> response= new ArrayList<Candlestick>();
		try {
			URL url=new URL("https://fapi.binance.com/fapi/v1/klines"+"?"+"symbol="+pair+"&interval="+timeframe+"&limit=1500");
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			
		con.setRequestProperty("X-MBX-APIKEY",ApiKey);
			int status = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			

			
					String inputLine;
					StringBuffer content = new StringBuffer();
					content.append("{ \"candels\":");
					content.append(in.readLine());			
					in.close();
					content.append("}");
					System.out.println(content.toString());
					JSONObject js=new JSONObject(content.toString());
			
			
			
					JSONArray jarr=js.getJSONArray("candels");
				
					if (jarr != null) {   
					   
					  
					    for (int i=0;i<jarr.length()-1;i++){   
					    	
					          JSONArray candle=(JSONArray)jarr.get(i);
					          double open=Double.parseDouble(candle.getString(1));
					          double high=Double.parseDouble(candle.getString(2));
					          double low=Double.parseDouble(candle.getString(3));
					          double close=Double.parseDouble(candle.getString(4));
					         
					         
					          long openTime=(Long) candle.get(0);
					          long closeTime=(Long) candle.get(6);
					    //double open, double high, double low, double close, long openTime, long closeTime
					    Candlestick c= new Candlestick(open,high,low,close,openTime,closeTime);
					    response.add(c);}
		}} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return response;
		
		}
	public static JSONArray GetPrecision() {

		JSONArray precisions=new JSONArray();

		String url="https://fapi.binance.com/fapi/v1/exchangeInfo";
		AsyncHttpClient asyncHttpClient = asyncHttpClient();

		ListenableFuture<Response> whenResponse = asyncHttpClient.prepareGet(url).execute();

		JSONObject response;
		try {
			response = new JSONObject(whenResponse.get().getResponseBody());
			precisions=response.getJSONArray("symbols");
		} catch (JSONException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return precisions;

		}
	


}

