package sda;

import java.util.ArrayList;

public class TimeFrame {
String TimeFrame;
ArrayList<Candlestick> PriceData;
ArrayList<PinPoint> PivotData;


public String getTimeFrame() {
	return TimeFrame;
}
public void setTimeFrame(String timeFrame) {
	TimeFrame = timeFrame;
}
public ArrayList<Candlestick> getPriceData() {
	return PriceData;
}
public void setPriceData(ArrayList<Candlestick> priceData) {
	PriceData = priceData;
}
public ArrayList<PinPoint> getPivotData() {
	return PivotData;
}
public void setPivotData(ArrayList<PinPoint> pivotData) {
	PivotData = pivotData;
}
public double getAvgPriceOfInterval(int i,int j) {
	double min,max;
	min=PriceData.get(i).getLow();
	max=PriceData.get(i).getHigh();
	for(int k=i;k<=j;k++) {if(min>PriceData.get(k).getLow()) {min=PriceData.get(k).getLow();}
							if(max<PriceData.get(k).getLow()) {max=PriceData.get(k).getHigh();}	}
	return (min+max)/2;
}
private ArrayList<PinPoint> GetPivotData(){
	
	PinPoint lastHigh=null;
	PinPoint lastLow=null;
	ArrayList<PinPoint> marketStructure =new ArrayList<PinPoint>();
	for(int i=1;i<PriceData.size()-1;i++) {
		if(PriceData.get(i).getHigh()>PriceData.get(i-1).getHigh()&&PriceData.get(i).getHigh()>PriceData.get(i+1).getHigh()) {
			if(lastHigh==null) {
				PinPoint p=new PinPoint("A",PriceData.get(i),i);//HH
				marketStructure.add(p);
				lastHigh=p;
				}
			else {
			if(PriceData.get(i).getHigh()>lastHigh.getCandlestick().getHigh()) {
				PinPoint p=new PinPoint("A",PriceData.get(i),i);//HH
				marketStructure.add(p);
				lastHigh=p;
			
				}
			else{if(PriceData.get(i).getHigh() < lastHigh.getCandlestick().getHigh()){
				PinPoint p=new PinPoint("B",PriceData.get(i),i);//LH
				marketStructure.add(p);
				lastHigh=p;
			}}}
			
		
		}
		
	
		else{if(PriceData.get(i).getLow()<PriceData.get(i-1).getLow()&&PriceData.get(i).getLow()<PriceData.get(i+1).getLow()){
			if(lastLow==null) {
				PinPoint p=new PinPoint("C",PriceData.get(i),i);//LL
				marketStructure.add(p);
				lastLow=p;
				}
			else {
			if(PriceData.get(i).getLow()<lastLow.getCandlestick().getLow()) {
				PinPoint p=new PinPoint("C",PriceData.get(i),i);//LL
				marketStructure.add(p);
				lastLow=p;
			
				}
			else{if(PriceData.get(i).getLow() > lastLow.getCandlestick().getLow()){
				PinPoint p=new PinPoint("D",PriceData.get(i),i);//Hl
				marketStructure.add(p);
				lastLow=p;
			}}}	
		}
		}
		
	}
	
	return marketStructure;
	
	
}
public TimeFrame(String timeFrame,String pair) {
	super();
	TimeFrame = timeFrame;
	PriceData=DataRequest.getCD(pair, this.TimeFrame);
	PivotData=GetPivotData();
}

}

