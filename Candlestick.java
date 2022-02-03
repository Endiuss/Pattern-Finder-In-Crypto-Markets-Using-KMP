package sda;


public class Candlestick {
private double Open;
@Override
public String toString() {
	return "Candlestick [Open=" + Open + ", High=" + High + ", Low=" + Low + ", Close=" + Close +
			", OpenTime=" + OpenTime + ", CloseTime=" + CloseTime ;
}

private double High;
private double Low;
private double Close;

private long OpenTime;
private long CloseTime;

public double getOpen() {
	return Open;
}

public double getLow() {
	return Low;
}
public double getHigh() {
	return High;
}
public double getClose() {
	return Close;
}



public long getOpenTime() {
	return OpenTime;
}
public long getCloseTime() {
	return CloseTime;
}





public Candlestick(double open, double high, double low, double close, long openTime, long closeTime) {
	super();
	Open = open;
	High = high;
	Low = low;
	Close = close;
	OpenTime = openTime;
	CloseTime = closeTime;
}

}
