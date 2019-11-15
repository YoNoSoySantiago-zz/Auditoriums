package model;
import java.time.*;
public class Event{

	//ATRIBUTES//
	private String name;
	private LocalDate date;
	private int startTime;
	private int endTime;
	private String teacherName;
	private int amountPeople;
	private Auditorium[] auditorium;

	//METHODS//
	public Event(String name, LocalDate date, int startTime, int endTime, String teacherName, int amountPeople, Auditorium[] auditorium){
		this.name = name;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.teacherName = teacherName;	
		this.auditorium = auditorium;	
	}
	public LocalDate getDate(){
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
	public Auditorium[] getAuditorium(){
		return this.auditorium;
	}
	
}	