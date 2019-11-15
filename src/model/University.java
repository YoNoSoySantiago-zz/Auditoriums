package model;
import java.util.ArrayList;
import java.time.*;
public class University{
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<Auditorium> auditoriums = new ArrayList<Auditorium>();


	//String name, String locate, boolean available, Chair[][] chairs
	public void addAuditorium(String name, String locate, boolean available, Chair[][] chairs){
		Auditorium auditorium = new Auditorium(name,locate,available,chairs);
		auditoriums.add(auditorium);
	}
	public String reportDefectChair(int auditorium, String position, String description){
		String result = "Esta silla no existe";
		if(auditoriums.get(auditorium-1) != null){
			Chair[][] chair = auditoriums.get(auditorium-1).getChairs();
			for(int j =0; j<chair.length;j++){
				for(int k=0;k<chair[0].length;k++){
					if(chair[j][k].getPosition().equalsIgnoreCase(position)){
						chair[j][k].setFuntional(false);
						chair[j][k].setDescription(description);
						auditoriums.get(auditorium-1).setChairs(chair);
						result = "Su report ha sido realizado con exito";
						break;
					}
				}
			}
		}	
		
		return result;
	}
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
			Chair[][] chair = auditoriums.get(i).getChairs();
			for(int j=0;j<chair.length;j++){
				for(int k =0;k<chair[0].length;k++){
					if(!chair[j][k].equals(null)){
						total++;
						if(chair[j][k].getFuntional()==false){   
							defect++;
						}
					}
				}
			}
		}
		result = defect/total;
		return result;
	}

	public String addEvent(String name, int day, int month, int year, int startTime, int endTime, String teacherName, int amountPeople, int[] audis){
		boolean conditional = true;
		String result = "";
		Auditorium[] auditoriumList = new Auditorium[audis.length -1];
		LocalDate date = LocalDate.of(year,month,day);
		for(int i = 0; i<audis.length && conditional == true;i++){
			for(int j = 0;j<events.size() && conditional == true;j++){
				Auditorium[] auditorium = events.get(j).getAuditorium();
				for(int k =0;k<auditorium.length && conditional == true;k++){
					if(auditoriums.get(audis[i]-1).getName().equals(auditorium[k].getName())){
						if(events.get(j).getDate().isAfter(date) || events.get(j).getDate().isBefore(date)){
							Chair[][] chair = auditoriums.get(audis[i]-1).getChairs();
							if(chair.length*chair[0].length>= amountPeople){
								result = "Evento agregado exitosamente";
								conditional = true;
							
							}else{
								result="Auditorio no tiene la capacidad suficiente para este evento";
								conditional = false;

							}
							
						}else{
							if(events.get(i).getStartTime() > startTime || events.get(i).getEndTime() < startTime){
								Chair[][] chair = auditoriums.get(audis[i]-1).getChairs();
								if(chair.length*chair[0].length>= amountPeople){
									result = "Evento agregado exitosamente";
									conditional = true;
								
								}else{
									result="Auditorio no tiene la capacidad suficiente para este evento";
									conditional = false;
								}	
							}else{
								result="Auditorio no disponible para este evento";
								conditional = false;
							}
							
						}
					}
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
	 
}

 