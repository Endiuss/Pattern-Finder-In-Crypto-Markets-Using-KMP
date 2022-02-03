package sda;

import java.util.ArrayList;

import org.json.JSONObject;

public class CryptoPair {
private double LastPrice=0;
private String Name;
private static ArrayList<TimeFrame> tf=new ArrayList<TimeFrame>();
private static IntervalMap<String,ArrayList<Interval>> PriceLevels=new IntervalMap<String,ArrayList<Interval>>();
private int PricePrecision=8;



public int getPricePrecision() {
	return PricePrecision;
}

public double getLastPrice() {
	return LastPrice;
}
public void setLastPrice(double lastPrice) {
	LastPrice = lastPrice;
}
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public IntervalMap<String, ArrayList<Interval>> getPriceLevels() {
	return PriceLevels;
}

public CryptoPair(String name) {
	super();
	Name = name;
	for(int i=0; i<main.precisions.length();i++) {
		JSONObject symbol=main.precisions.getJSONObject(i);
		if(symbol.getString("symbol").equals(name)) {
			
			PricePrecision=symbol.getInt("pricePrecision");
			
		}
		
	}
	
	
	
	
	for(int i=0;i<main.TimeFrameList.size();i++) {
		tf.add(new TimeFrame(main.TimeFrameList.get(i),this.Name));
}
	 for(int i=0;i<tf.size();i++) {
	    	for(int j=0;j<main.patterns.size();j++) {
	    	 String key=tf.get(i).getTimeFrame()+main.patterns.get(j);
	    	 
	    	 PriceLevels.add(key,KMP.KMPSearch(main.patterns.get(j), tf.get(i)));}
	    	 
	     }
	 LastPrice=tf.get(0).getPriceData().get(tf.get(0).getPriceData().size()-1).getClose();
	 System.out.println(LastPrice);
	 System.out.println(this.Name);
	 for(int i=0;i<tf.size();i++) {
		 System.out.println(tf.get(i).getTimeFrame());
	    	for(int j=0;j<main.patterns.size();j++) {
	    		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+main.patterns.get(j));
	    		String key=tf.get(i).getTimeFrame()+main.patterns.get(j);
	    		ArrayList<Interval> aux=PriceLevels.get(key);
	    		
	    		for(int k=0;k<aux.size();k++) {
	    			System.out.println(aux.get(k).getAvgPrice());
	    			
	    		}
	    		}
	    	}
	 }
public ArrayList<Interval> GetPriceListByKey(String key){
	System.out.println(key);
	return PriceLevels.get(key);
}
public static void addPattern(String pattern) {
	for(int i=0;i<tf.size();i++) {

    	 String key=tf.get(i).getTimeFrame()+pattern;
    	 
    	 PriceLevels.add(key,KMP.KMPSearch(pattern, tf.get(i)));}
    	 
     }
public void deletePattern(String pattern) {
for(int i=0;i<tf.size();i++) {
	for(int j=0;j<main.patterns.size();j++) {
   	 String key=tf.get(i).getTimeFrame()+main.patterns.get(j);
   	PriceLevels.remove(key);
   	}
   	 
    }
	
}
	}
	



