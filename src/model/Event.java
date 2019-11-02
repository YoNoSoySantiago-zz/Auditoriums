package model;
public class Event{

	//ATRIBUTES//
	private String name;
	private String date;
	private int startTime;
	private int endTime;
	private String teacherName;
	private int amountPeople;

	//METHODS//
	public Event(String name, String date, int startTime, int endTime, String teacherName, int amountPeople){
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.teacherName = teacherName;		
	}
	public String getDate(){
		return this.date;
	}
	public int getStartTime(){
		return this.startTime;
	}
	public int getEndTime(){
		return this.endTime;
	}
	private int getAmountPeople(){
		return this.amountPeople;
	}
	
}	