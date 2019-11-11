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
						auditoriums.get(i).setChairs(chair);
						result = "Su report ha sido realizado con exito";
						break;
					}
				}
			}
		}	
		
		return result;
	}
	public Chair[][] addChairsAuditorium(int[] chairsByLine){
		int aux=0;
		for(int i =0;i<chairsByLine.length;i++){
			if(chairsByLine[i]> aux){
				aux = chairsByLine[i];
			}
		}
		Chair[][] chairAuditorium = new Chair[chairsByLine.length][aux];
		for(int i = 0; i<chairsByLine.length;i++){
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
	//String name, String date, int startTime, int endTime, String teacherName, int amountPeople
	public String addEvent(String name, int day, int month, int year, int startTime, int endTime, String teacherName, int amountPeople, Auditorium auditorium){
		boolean conditional = false;
		String result = "";
		for(int i =0;i<events.size();i++){
			if(events.get(i).getAuditorium().getName().equals(auditorium.getName())){
				if(events.get(i).getStartTime() > startTime || events.get(i).getEndTime() < startTime){
					Chair[][] chair = events.get(i).getAuditorium().getChairs();
					if(chair.length*chair[0].length>= amountPeople){
						result = "Evento agregado exitosamente";
						break;
					}else{
						result="Auditorio no tiene la capacidad suficiente para este evento";
					}
					
				}else{
					result="Auditorio no disponible para este evento";
				}
			}
		}

		if(conditional == true){
			Event event = new Event(name,date,startTime,endTime,teacherName,amountPeople,auditorium);
		}
		return result;
	}
	public void removeEnvent(){
		//quitar el evento cuando la hora y fecha acutal sean mayor al del evento
		for(int i = 0; i<events.size();i++){
			if(events.get(i).getDate()< LocalDate.now()){
				events.remove(i);
			}else if(events.get(i).getDate() == LocalDate.now()){
				if(events.get(i).getEndTime() < LocalTime.now()){
					events.remove(i);
				}
			}
		}
	}
	public void showAuditorium(){

	}
	 
}
 