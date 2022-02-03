package sda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

public class SendOrders {
	
		public static void BuyOrderGenerator(double price, String pair) {
			String ApiKey = "ecdyk5YRfnFIfdgR9Px5wW2lLTK6ztEIJPYt1qDoREZZHLaF2UjhHgWkCshRv3uP";
			String SecretKey="lqPOxrhGoCGdlH50u70XT5klnx4ENzpUq428DKRTItkievXsHdbBSiuIW0S3Y0Ic";
		
			byte[] encodeKey = SecretKey.getBytes();
	String time=String.valueOf(System.currentTimeMillis());	
	String param="symbol="+pair+"&side=BUY"+"&type=LIMIT"+"&timeInForce=GTC"+"&quantity="+0.002+"&price="+price+"&timestamp="+time;

			
			 
			 
			try {
			
				
				String querry_string = URLEncoder.encode(param,"UTF-8");
				
				 System.out.println(querry_string.toString());
				
				 byte[] encodeParam=param.getBytes("UTF-8");
				 System.out.println(encodeParam.length);
				 String sig=Hex.encodeHexString(HMAC.calcHmacSha256(encodeKey,encodeParam));

				 System.out.println(param);
				 System.out.println(sig);
		







				 URL url=new URL("https://fapi.binance.com/fapi/v1/order?"+param+"&signature="+sig);
					
					
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					System.out.println(con.getRequestMethod());
				con.setRequestProperty("X-MBX-APIKEY", ApiKey);
				
				if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						  new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer content = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
						    content.append(inputLine);
						}
						in.close();
						System.out.println(content);
				}
				else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				    String strCurrentLine;
				        while ((strCurrentLine = br.readLine()) != null) {
				               System.out.println(strCurrentLine);
				        }}
	

	} catch (IOException e) {
				
				e.printStackTrace();
			
			
			
			}

			
		}
		
		
		public static void SellOrderGenerator(double price, String pair) {
			String ApiKey = "ecdyk5YRfnFIfdgR9Px5wW2lLTK6ztEIJPYt1qDoREZZHLaF2UjhHgWkCshRv3uP";
			String SecretKey="lqPOxrhGoCGdlH50u70XT5klnx4ENzpUq428DKRTItkievXsHdbBSiuIW0S3Y0Ic";
		
			byte[] encodeKey = SecretKey.getBytes();
	String time=String.valueOf(System.currentTimeMillis());	
	String param="symbol="+pair+"&side=SELL"+"&type=LIMIT"+"&timeInForce=GTC"+"&quantity="+0.002+"&price="+price+"&timestamp="+time;

			
			 
			 
			try {
			
				
				String querry_string = URLEncoder.encode(param,"UTF-8");
				
				 System.out.println(querry_string.toString());
				
				 byte[] encodeParam=param.getBytes("UTF-8");
				 System.out.println(encodeParam.length);
				 String sig=Hex.encodeHexString(HMAC.calcHmacSha256(encodeKey,encodeParam));

				 System.out.println(param);
				 System.out.println(sig);
		







				 URL url=new URL("https://fapi.binance.com/fapi/v1/order?"+param+"&signature="+sig);
					
					
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					System.out.println(con.getRequestMethod());
				con.setRequestProperty("X-MBX-APIKEY", ApiKey);
				
				if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(
						  new InputStreamReader(con.getInputStream()));
						String inputLine;
						StringBuffer content = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
						    content.append(inputLine);
						}
						in.close();
						System.out.println(content);
				}
				else {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				    String strCurrentLine;
				        while ((strCurrentLine = br.readLine()) != null) {
				               System.out.println(strCurrentLine);
				        }}
	

	} catch (IOException e) {
				
				e.printStackTrace();
			
			
			
			}

			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
