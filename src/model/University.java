package model;
public class University{
	private ArrayList<Event> events = new ArrayList<Event>();
	private ArrayList<Auditorium> auditoriums = new ArrayList<Auditorium>();

	public String reportDefectChair(String position, String description){
		String result = "Esta silla no existe";
		for(int i = 0; i<auditoriums.size();i++){
			if(auditoriums.get(i)/*LaMatrixEsa*/ == position){
				auditoriums.get(i)/*LaMatrixEs*/.getFuntional() = false;
				auditoriums.get(i)/*LaMatrixEsa*/.setDescription() = description;
				result = "Su report ha sido realizado con exito";
				break;
			}
		}
	}
}
