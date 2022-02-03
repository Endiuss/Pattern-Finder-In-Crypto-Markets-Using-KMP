package sda;

public class Interval {

PinPoint startPin;
PinPoint endPin;
double avgPrice;
public PinPoint getStartPin() {
	return startPin;
}
public void setStartPin(PinPoint startPin) {
	this.startPin = startPin;
}
public PinPoint getEndPin() {
	return endPin;
}
public void setEndPin(PinPoint endPin) {
	this.endPin = endPin;
}
public double getAvgPrice() {
	return avgPrice;
}
public void setAvgPrice(double avgPrice) {
	this.avgPrice = avgPrice;
}
public Interval(PinPoint startPin, PinPoint endPin, double avgPrice) {
	super();
	this.startPin = startPin;
	this.endPin = endPin;
	this.avgPrice = avgPrice;
}

}