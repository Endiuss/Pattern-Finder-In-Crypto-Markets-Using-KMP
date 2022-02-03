package sda;

public class PinPoint {
public int getPozInPriceData() {
		return PozInPriceData;
	}
	public void setPozInPriceData(int pozInPriceData) {
		PozInPriceData = pozInPriceData;
	}
public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
public Candlestick getCandlestick() {
		return c;
	}
	public void getCandlestick(Candlestick c) {
		this.c = c;
	}

private String type;
private Candlestick c;
private int PozInPriceData;
public PinPoint(String type, Candlestick c,int poz) {
	super();
	this.type = type;
	this.c = c;
	this.PozInPriceData=poz;
}

}
