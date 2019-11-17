package model;
import java.util.ArrayList;
import java.time.*;
import java.util.Random;
public class University{
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<Auditorium> auditoriums = new ArrayList<Auditorium>();
	private Random random = new Random();
	private LocalDate date;
	private LocalTime time;


	//String name, String locate, boolean available, Chair[][] chairs
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
	//Cambiar para que no tenga Chair
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

	public String addEvent(String name, int day, int month, int year, int startTime, int endTime, String teacherName, int amountPeople, int[] audis){
		boolean conditional = true;
		int max =0;
		String result = "";
		Auditorium[] auditoriumList = new Auditorium[audis.length -1];
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
					if(!events.get(j).getDate().isAfter(date) || !events.get(j).getDate().isBefore(date)){
						if(events.get(i).getStartTime() > startTime && events.get(i).getEndTime() > startTime){
							result="Auditorio no disponible para este evento";
							conditional = false;															
						}						
					}				
				}						
			}
			if(conditional == true){
				max =0;
				for(int l =0;l<auditoriums.get(audis[i]-1).getChairs().length;l++){
					max += auditoriums.get(audis[i]-1).getChairs()[l].length;
				}
				if(max >= amountPeople){
					result = "Evento agregado exitosamente";
					conditional = true;
							
				}else{
					result="Auditorio no tiene la capacidad suficiente para este evento";
					conditional = false;

				}
			}
		}
		if(startTime < 7 || startTime > 20 || endTime < 7 || endTime > 20 || endTime-startTime < 2 || endTime-startTime > 12){
			conditional = false;
		}	
		//String name, LocalDate date, int startTime, int endTime, String teacherName, int amountPeople, Auditorium auditorium
		if(conditional == true){
			for(int i = 0;i<auditoriumList.length;i++){
				
				auditoriumList[i] = auditoriums.get(audis[i]-1);
			}
			Event event = new Event(name,date,startTime,endTime,teacherName,amountPeople,auditoriumList);
		}
		return result;
	}
	/*
	public void removeEnvent(){
		//quitar el evento cuando la hora y fecha acutal sean mayor al del evento
		for(int i = 0; i<events.size();i++){
			if(events.get(i).getDate().isBefore(LocalDate.now())){
				events.remove(i);
			}else if(events.get(i).getDate().isBefore(LocalDate.now())){
				if(events.get(i).getEndTime() < LocalTime.now().getHour()){
					events.remove(i);
				}
			}
		}
	}
	*/
	public void showAuditorium(){
		for(int i = 0;i<auditoriums.size();i++){
			System.out.println((i+1)+". "+auditoriums.get(i).getName());
		}
	}
	public void showEvents(){
		for(int i = 0;i<events.size();i++){
			System.out.println((i+1)+". "+events.get(i).getName());
		}
	}
	public void showAuditoriumChairs(int auditorium){
		if(auditoriums.get(auditorium-1)!= null){
			for(int i = 0;i<auditoriums.get(auditorium-1).getChairs().length;i++){
				for(int j = 0; j<auditoriums.get(auditorium-1).getChairs()[i].length;j++){
					System.out.print(auditoriums.get(auditorium-1).getChairs()[i][j].getPosition()+": ");
					if(auditoriums.get(auditorium-1).getChairs()[i][j].getFuntional() == true){
						System.out.print("OPERATIVA");

					}else{
						System.out.print("DEFECTUOSA");

					}

					
				}
			}
		}else{
			System.out.println("Este Auditorio no Existe");
		}
	}public void showEventsChairs(int event){
		if(events.get(event-1) != null){
			for(int i = 0; i<events.get(event-1).getAuditorium().length;i++){
				for(int j = 0;j<events.get(event-1).getAuditorium()[i].getChairs().length;j++){
					for(int k = 0;k<events.get(event-1).getAuditorium()[i].getChairs()[j].length;k++){
						System.out.println(events.get(event-1).getAuditorium()[i].getName().toUpperCase());
						System.out.print(events.get(event-1).getAuditorium()[i].getChairs()[j][k]+": ");
						if(events.get(event-1).getAuditorium()[i].getChairs()[j][k].getAvailable() == true){
						System.out.print("DISPONIBLE");

					}else{
						System.out.print("OCUPADA");

					}

					}
				}
				
			}
		}else{
			System.out.println("Este Evento no Existe");
		}
	}
	public void showEventRegister(){
		for(int i = 0;i<events.size();i++){
			if(events.get(i).getDate().isBefore(date.now())){
				System.out.println(events.get(i).getName()+"\n  ");
			}else if(events.get(i).getDate()==date.now()){
				if(events.get(i).getEndTime()<time.now().getHour()){

				}
			}
		}
	}
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

 