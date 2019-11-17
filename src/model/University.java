package model;
import java.util.ArrayList;
import java.time.*;
import java.util.Random;
public class University{
	//ATRIBUTES
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<Auditorium> auditoriums = new ArrayList<Auditorium>();
	private Random random = new Random();
	private LocalDate date;
	private LocalTime time;


	//METHODS
	/**
	*<p> desc: <p> This method allows you to create an auditorium and add it to the auditorium list
	*<p> pre: <p> The auditorium list must already be created
	*@param name,locate,available,chairs these parameters represent the attributes of the auditorium class, in order to create them
	*/
	public void addAuditorium(String name, String locate, boolean available, Chair[][] chairs){
		Auditorium auditorium = new Auditorium(name,locate,available,chairs);
		auditoriums.add(auditorium);
	}
	public String reportDefectChair(int auditorium, String position, String description){
		String result = "Esta silla no existe";
		if(auditoriums.get(auditorium-1) != null){
			Chair[][] chair = auditoriums.get(auditorium-1).getChairs();
			for(int j =0; j<auditoriums.get(auditorium-1).getChairs().length;j++){
				for(int k=0;k<auditoriums.get(auditorium-1).getChairs()[j].length;k++){
					if(auditoriums.get(auditorium-1).getChairs()[j][k].getPosition().equalsIgnoreCase(position)){
						auditoriums.get(auditorium-1).getChairs()[j][k].setFuntional(false);
						auditoriums.get(auditorium-1).getChairs()[j][k].setAvailable(false);
						auditoriums.get(auditorium-1).getChairs()[j][k].setDescription(description);
						result = "Su report ha sido realizado con exito";
						break;
					}
				}
			}
		}	
		
		return result;
	}
	/**
	*<p> desc: <p> This method allows you to generate an array of chairs from a list of positive integers that indicate the length of the columns for each row,
	and indicate the position parameter of each chair depending on a letter that represents its row and a number that represents its column
	*@param chairsByLine this paraetro is a list of positive integers, which indicate the amount of
	Colonas of a row depending on its location in the arrangement which has a maximum length of 26 spaces,
	which each represent a letter of the alphabet starting from A to Z
	*@return this method returns an array of chairs, in which each one has in its position attribute the position created
	and the available and funtional attributes as true and the empty description
	*/
	public Chair[][] addChairsAuditorium(int[] chairsByLine){
		
		Chair[][] chairAuditorium = new Chair[chairsByLine.length][];
		for(int i = 0; i<chairsByLine.length;i++){
			chairAuditorium[i] = new Chair[chairsByLine[i]];
			for(int j = 0;j<chairsByLine[i];j++){
				Chair chair = new Chair(Character.toString((char)('A'+i))+(j+1),true,true,"");
				chairAuditorium[i][j] = chair;
			}
		}
		return chairAuditorium;
	}
	/**
	*<p> desc <p> This method calculates the percentage of defective chairs of all chairs in the auditoriums
	*@return this method returns a positive decimal number which represents the percentage (0-1) of defective chairs with respect to the total of chairs
	*/
	public double calcularPorcentDefect(){
		double total =0,defect=0,result=0;
		for(int i=0;i<auditoriums.size();i++){
			for(int j=0;j<auditoriums.get(i).getChairs().length;j++){
				for(int k =0;k<auditoriums.get(i).getChairs()[j].length;k++){
					if(auditoriums.get(i).getChairs()[j][k] != null){
						total++;
						if(auditoriums.get(i).getChairs()[j][k].getFuntional()==false){   
							defect++;
						}
					}
				}
			}
		}
		if(total!= 0){
			result = defect/total;
		}
		
		return result;
	}
	/**
	*<p> desc: <p> This method receives the parameters of the event class, allows you to create and add an event to the list of events of the university class
	having it not be together with another event in the same auditorium, that the duration of the event is within 2-12h,
	that the event can only be done between 7-20
	*<p> pre: <p> the LocalDate and LocalTime classes must be installed
	*@param name,startTime,endTime,teacherName,faculty,amountPeople these parameters are attributes of the Event class
	*@param day,mouth,year these parameters are positive integers with which the date attribute of type LocalDate of the Event class will be created
	*@param audis this parameter is a list of positive integers which represent the location + 1 of the event auditoriums in the auditorium list
	*@return This method returns a string of characters which contains a message indicating whether the event was added correctly or not	
	*/
	public String addEvent(String name, int day, int month, int year, int startTime, int endTime, String teacherName,String faculty, int amountPeople, int[] audis){
		boolean conditional = true;
		int max =0;
		String result = "";
		Auditorium[] auditoriumList = new Auditorium[audis.length];
		LocalDate date = LocalDate.of(year,month,day);

		if(this.date.now().isAfter(date)){
			conditional = false;
			result = "Fecha no disponible";
		}else if(this.date.now().equals(date)){
			if(this.time.now().getHour() > startTime){
				conditional = false;
				result = "Fecha no disponible";
			}
		}		
		for(int i = 0; i<audis.length && conditional == true;i++){
			for(int j = 0;j<events.size() && conditional == true;j++){
				for(int k =0;k<events.get(j).getAuditorium().length && conditional == true;k++){
					if(events.get(j).getAuditorium()[k].getName().equals(auditoriums.get(audis[i]-1))){
						if(!events.get(j).getDate().isAfter(date) || !events.get(j).getDate().isBefore(date)){
							if(events.get(j).getStartTime() > startTime && events.get(j).getEndTime() > startTime){
								result="Auditorio no disponible para este evento";
								conditional = false;															
							}						
						}	
					}
								
				}						
			}
			if(conditional == true){
				for(int l =0;l<auditoriums.get(audis[i]-1).getChairs().length;l++){
					max += auditoriums.get(audis[i]-1).getChairs()[l].length;
				}
				if(audis.length - i == 1){
					if(max >= amountPeople){
						result = "Evento agregado exitosamente";
						conditional = true;
							
					}else{
						result="Auditorio no tiene la capacidad suficiente para este evento";
						conditional = false;

					}
				}
				
			}
		}
		if(startTime < 7 || startTime > 20 || endTime < 7 || endTime > 20 || endTime-startTime < 2 || endTime-startTime > 12){
			conditional = false;
			result = "Hora no disponible";
		}	
		
		if(conditional == true){
			for(int i = 0;i<audis.length;i++){
				
				auditoriumList[i] = auditoriums.get(audis[i]-1);
			}
			Event event = new Event(name,date,startTime,endTime,teacherName,amountPeople,faculty,auditoriumList);
			events.add(event);

		}
		return result;
	}
	
	/**
	*<p> des: <p> 	This method allows to show the list of auditoriums and their availability depending on the events,
					which will be available if the event has not started and busy if the event has already started
	*/
	public void showAuditorium(){
		for(int i =0;i<events.size();i++){
			if(events.get(i).getDate().equals(date.now())){
				if(events.get(i).getStartTime()-1<= time.now().getHour() && events.get(i).getEndTime() > time.now().getHour()){
					for(int j =0;j<events.get(i).getAuditorium().length;j++){
						for(int k =0;k<auditoriums.size();k++){
							if(events.get(i).getAuditorium()[j].getName().equals(auditoriums.get(k))){
								auditoriums.get(k).setAvailable(false);
							}
						}
					}
					
				}else{
					for(int j =0;j<events.get(i).getAuditorium().length;j++){
						for(int k =0;k<auditoriums.size();k++){
							if(events.get(i).getAuditorium()[j].getName().equals(auditoriums.get(k))){
								auditoriums.get(k).setAvailable(true);
							}
						}
					}
				}
			}else{
				for(int j =0;j<events.get(i).getAuditorium().length;j++){
					for(int k =0;k<auditoriums.size();k++){
						if(events.get(i).getAuditorium()[j].getName().equals(auditoriums.get(k))){
							auditoriums.get(k).setAvailable(true);
						}
					}
				}
			}
			
		}
		for(int i = 0;i<auditoriums.size();i++){
			System.out.print((i+1)+". "+auditoriums.get(i).getName()+": ");
			if(auditoriums.get(i).getAvailable()==true){
				System.out.println("DISPONIBLE");
			}else{
				System.out.println("OCUPADO");
			}
		}
	}
	/**
	*<p> des: <p> This method allows to display a numbered list of the name of the events in the event list
	*/
	public void showEvents(){
		for(int i = 0;i<events.size();i++){
			System.out.println((i+1)+". "+events.get(i).getName());
		}
	}
	/**
	*<p> desc: <p> receives a positive integer, this method allows to show the status of the chairs of an auditorium
	depending on the funtional attribute of the chairs, which indicates whether the chair is operational or not showing it on the screen
	*@param auditorium this parameter is a positive integer which represents the position + 1 of the auditorium to be searched
	*/
	public void showAuditoriumChairs(int auditorium){
		if(auditoriums.get(auditorium-1)!= null){
			for(int i = 0;i<auditoriums.get(auditorium-1).getChairs().length;i++){
				for(int j = 0; j<auditoriums.get(auditorium-1).getChairs()[i].length;j++){
					System.out.print(auditoriums.get(auditorium-1).getChairs()[i][j].getPosition()+": ");
					if(auditoriums.get(auditorium-1).getChairs()[i][j].getFuntional() == true){
						System.out.println("OPERATIVA");

					}else{
						System.out.print("DEFECTUOSA - ");
						System.out.println(auditoriums.get(auditorium-1).getChairs()[i][j].getDescription());

					}

					
				}
			}
		}else{
			System.out.println("Este Auditorio no Existe");
		}
	/**
	*<p> desc: <p> receives a positive integer, this method allows to show the status of the chairs of the auditoriums of an event
	depending on the available attribute of the chairs, which indicates whether the chair is available or not during the event
	*@param event this parameter is a positive integer which represents the position + 1 of the event to look for
	*/
	}public void showEventsChairs(int event){
		if(events.get(event-1) != null){
			for(int i = 0; i<events.get(event-1).getAuditorium().length;i++){
				System.out.println(events.get(event-1).getAuditorium()[i].getName().toUpperCase());
				for(int j = 0;j<events.get(event-1).getAuditorium()[i].getChairs().length;j++){
					for(int k = 0;k<events.get(event-1).getAuditorium()[i].getChairs()[j].length;k++){
						System.out.print(events.get(event-1).getAuditorium()[i].getChairs()[j][k].getPosition()+": ");
						if(events.get(event-1).getAuditorium()[i].getChairs()[j][k].getAvailable() == true){
						System.out.println("DISPONIBLE");

					}else{
						System.out.println("OCUPADA");

					}

					}
				}
				
			}
		}else{
			System.out.println("Este Evento no Existe");
		}
	}
	/**
	<p> desc: <p> 	This method allows you to display one of all events, showing a list of all your attributes and
	a comparison between the number of people who attended the event and those of the amountPeople attribute
	*/
	
	public void showEventRegister(){
		for(int i = 0;i<events.size();i++){
			int amountPeople=0;
			for(int j = 0;j<events.get(i).getAuditorium().length;j++){
				for(int k =0;k<events.get(i).getAuditorium()[j].getChairs().length;k++){
					for(int l=0;l<events.get(i).getAuditorium()[j].getChairs()[k].length;l++){
						if(events.get(i).getAuditorium()[j].getChairs()[k][l].getAvailable()==false && events.get(i).getAuditorium()[j].getChairs()[k][l].getFuntional() == true){
							amountPeople++;
						}
					}
				}
			}
			
			System.out.print(events.get(i).getName()+"\n  Fecha: "+
				events.get(i).getDate()+"\n  Hora: "+
				events.get(i).getStartTime()+" - "+events.get(i).getEndTime()+"\n  Profesor Encargado: "+
				events.get(i).getTeacherName()+"\n  Facultad: "+
				events.get(i).getFaculty()+"\n  Personas que asistieron al Evento: "+
				amountPeople+ " / "+events.get(i).getAmountPeople()+"\n  Auditorio/s: ");
				for(int j =0;j<events.get(i).getAuditorium().length;j++){
					System.out.print(events.get(i).getAuditorium()[j].getName()+" - ");
				}
				System.out.println("");
			
		}
	}
	/**
	*<p> desc: <p> 	This method allows a random chair of an event to be registered as busy (its attribute available as false). The event must be at least one hour from the start.
	to be able to register the chair and can not be reissued once the event is over
	*@param event this parameter is a positive integer which represents the position + 1 of the event to be searched in the event list
	*@param auditorium 	this parameter is a positive integer which represents the position + 1 of the auditorium to be searched in the auditorium list			
	*/
	public void addChairEvent(int event,int auditorium){
		Boolean ready=false;
		int a,b;
		if(events.get(event-1) != null){
			if(events.get(event-1).getDate().equals(date.now())){
				if((events.get(event-1).getStartTime()-1)<= time.now().getHour() && events.get(event-1).getEndTime() > time.now().getHour()){
					do{
						a = events.get(event-1).getAuditorium()[auditorium-1].getChairs().length;
						a = random.nextInt(a);
						b = events.get(event-1).getAuditorium()[auditorium-1].getChairs()[a].length;
						b = random.nextInt(b);
						if(events.get(event-1).getAuditorium()[auditorium-1].getChairs()[a][b].getAvailable()==true){
							events.get(event-1).getAuditorium()[auditorium-1].getChairs()[a][b].setAvailable(false);
							System.out.println("la silla: "+events.get(event-1).getAuditorium()[auditorium-1].getChairs()[a][b].getPosition()+" fue Ocupada");
							ready = true;
						}
				
					}while(ready == false);
				}
			}
			
			
		}
	}
	public ArrayList<Event> getEvents(){
		return this.events;
	}
	 
}

 