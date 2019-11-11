package model;
public class Event{

	//ATRIBUTES//
	private String name;
	private Calendar date;
	private int startTime;
	private int endTime;
	private String teacherName;
	private int amountPeople;
	private Auditorium auditorium;

	//METHODS//
	public Event(String name, Calendar date, int startTime, int endTime, String teacherName, int amountPeople, Auditorium auditorium){
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.teacherName = teacherName;	
		this.auditorium = auditorium;	
	}
	public Calendar getDate(){
		return this.date;
	}
	public int getStartTime(){
		return this.startTime;
	}
	public int getEndTime(){
		return this.endTime;
	}
	public int getAmountPeople(){
		return this.amountPeople;
	}
	public Auditorium getAuditorium(){
		return this.auditorium;
	}
	
}	