package model;
public class Auditorium{

	//ATRIBUTES//
	private String name;
	private String locate;
	private boolean available;
	private Chair[][] chairs;	

	//METHODS//
	public Auditorium(String name, String locate, boolean available){
		this.name = name;
		this.locate = locate;
		this.available = available;
	}
	public String getName(){
		return this.name;
	}
	public String getLocate(){
		return this.locate;
	}
	public boolean getAvailable(){
		return this.available;
	}
	public void setAvailable(boolean available){
		this.available = available;
	}
	public Chair[][] getChairs(){
		return this.chairs;
	}
	public void setChairs(Chair[][] chairs){
		this.chairs = chairs;
	}
}