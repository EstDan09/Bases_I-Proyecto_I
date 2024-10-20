package application.Model;

public class SportRecord {
	private String sport;
	private String competitor;
	private String record;
	private String country;
	
	
	public SportRecord(String sport, String competitor, String record, String country) {
		this.sport = sport;
		this.competitor = competitor;
		this.record = record;
		this.country = country;
	}
	
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	public String getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor = competitor;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
