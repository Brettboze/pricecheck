package models;

public class Flight {
	public String fromAirport;
	public String toAirport;
	public String fromDateTime;
	public String toDateTime;
	public String price;
	
	public Flight(String fromAirport, String toAirport, String fromDateTime, String toDateTime, String price) {
		this.fromAirport = fromAirport;
		this.toAirport = toAirport;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.price = price;
	}
	
	public String getFilename() {
		return fromAirport + "_" + toAirport + "_" + fromDateTime + "_" + toDateTime + ".txt";
	}
	
}
