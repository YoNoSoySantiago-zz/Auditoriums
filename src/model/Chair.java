package model;
public class Chair{
	// ATRIBUTES //

	private String position;
	private boolean funtional;
	private boolean available;
	private String description;

	//METHODS//
	public Chair(String position, boolean funtional, boolean available, String description){
		this.position = position;
		this.funtional = funtional;
		this.available = available;
		this.description = description;
	}
	public String getPosition(){
		return this.position;
	}
	public boolean getFuntional(){
		return this.funtional;
	}
	public boolean getAvailable(){
		return this.available;
	}
	public void setFuntional(boolean funtional){
		this.funtional = funtional;
	}
	public void setAvailable(boolean available){
		this.available = available;
	}
	public void setDescription(String description){
		this.description = description;
	}

}