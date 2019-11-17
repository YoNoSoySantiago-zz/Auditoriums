package ui;
import java.util.Scanner;
import model.*;
import java.time.*;
public class Main{
	private Scanner s = new Scanner(System.in);
	private Scanner n = new Scanner(System.in);
	private  University university = new University();
	public static void main(String[]args){
		Main main = new Main();
		boolean continue1 = true;
		main.init();
		do{
			System.out.println("///////////////////////////////////////\n");
			main.showMenu();
			continue1 = main.start();
		}while(continue1 == true);
		

	}
	public void showMenu(){
		System.out.println("Por favor escoja la obcion a realizar\n"+
			"1. Agregar un nuevo audirio a la Universidad\n"+	//ya
			"2. Crear un Evento\n"+								//ya		
			"3. Reportar silla defectuosa\n"+					//ya
			"4. Mostrar sillas de un Auditorio\n"+				
			"5. Mostrar sillas del Evento\n"+
			"6. Añadir sillas\n"+
			"7. Mostrar porcentaje de sillas defectuosas\n"+
			"8. Mostrar registro de Eventos\n"+
			"0. Salir");

	}
	public boolean start(){
		int selection,year,month,day,startTime,endTime,amountPeople,audi,number;
		LocalDate today;
		int[] chair;
		String name,locate,teacherName,position,row,description;
		Chair[][] chairs;
		selection = n.nextInt();
		switch (selection){
			case 1:
			//String name, String locate, boolean available, Chair[][] chair   
			System.out.println("indique el nombre del auditorio que desea agregar: ");
			name = s.nextLine();
			System.out.println("indique la ubicacion del auditorio:");
			locate = s.nextLine();
			do{
				System.out.println("indique la cantidad de filas(silleteria) que contiene el auditorio");
				chair = new int[n.nextInt()];
				for(int i= 0; i<chair.length;i++){
					System.out.println("indique la cantidad de sillas en la fila: "+(char)('A'+i));
					chair[i] = n.nextInt();
				}
			}while(chair.length == 0);
			chairs = university.addChairsAuditorium(chair);
			university.addAuditorium(name,locate,true,chairs);
			break;

			case 2:
			//addEvent(String name, int date, int startTime, int endTime, String teacherName, int amountPeople, Auditorium auditorium)
			System.out.println("indique el nombre del evento: ");
			name = s.nextLine();
			System.out.println("indique el año del evento: ");
			year= n.nextInt();
			System.out.println("indique el mes(numerico) del evento: ");
			month = n.nextInt();
			System.out.println("indique el dia del evento: ");
			day = n.nextInt();
			System.out.println("indique la hora de inicio del evento: ");
			startTime = n.nextInt();
			System.out.println("indique la duracion del evento: ");
			endTime = n.nextInt();
			System.out.println("indique el nombre del profesor encargado");
			teacherName = s.nextLine();
			System.out.println("indique el numero de personas para el evento");
			amountPeople = n.nextInt();
			System.out.println("indique cuantos auditorios desea agregar");
			int[] cantAudi = new int[n.nextInt()];
			for(int i=0;i<cantAudi.length;i++){
				System.out.println("indique el auditorio numero "+(i+1));
				university.showAuditorium();
				cantAudi[i] = n.nextInt();
			}
		//String name, int day, int month, int year, int startTime, int endTime, String teacherName, int amountPeople, int[] audis
			String mensaje = university.addEvent(name,day,month,year,startTime,endTime,teacherName,amountPeople,cantAudi);
			System.out.println(mensaje);
			break;

			case 3:
			System.out.println("indique el auditorio donde se encuentra la silla");
			university.showAuditorium();
			audi = n.nextInt();
			System.out.println("indique la fila donde se encuentra en formato alfabetico: ");
			row = Character.toString(s.next().charAt(0));
			System.out.println("indique el numero de la silla");
			number = n.nextInt();
			System.out.println("indique una description del daño de la silla");
			description = s.nextLine();
			position = row+number;	
			university.reportDefectChair(audi,position,description);
			break;

			case 4:
			System.out.println("indique el auditorio: ");
			university.showAuditorium();
			university.showAuditoriumChairs(n.nextInt());
			break;

			case 5:
			System.out.println("indique el evento: ");
			
			if(university.getEvents().size() > 0){
				university.showEventsChairs(n.nextInt());
				university.showEvents();
			}else{
				System.out.println("No hay eventos disponibles");
			}
			break;

			case 6:
			if(university.getEvents().size() > 0){
				System.out.println("indique el evento: ");
				university.showEvents();
				int a = n.nextInt();
				if(university.getEvents().get(a-1) != null){
					if(university.getEvents().get(a-1).getAuditorium().length > 0){
						System.out.println("indique el auditorio: ");
						for(int i = 0;i<university.getEvents().get(a-1).getAuditorium().length;i++){
							System.out.println((i+1)+". "+university.getEvents().get(a-1).getAuditorium()[i].getName());
						}
						int b = n.nextInt();
						university.addChairEvent(a,b);
					}
				}else{
					System.out.println("Este evento no Existe");
				}
			}else{
				System.out.println("No hay eventos disponibles");
			}
			
			break;

			case 7:
			double defect = university.calcularPorcentDefect();
			System.out.println("EL porcentaje de sillas defectuosas es: "+ defect*100+"%");
			break;

			case 8:
			university.showEventRegister();
			break;

			case 0:
			System.out.println("GRACIAS! ");
			break;


			default:
			System.out.println("ingresa una obcion correcta");
 

		}
		if(selection == 0){
			return false;
		}else{
			return true;
		}
	}
	public void init(){
		int[] chair = new int []{10,10,10,9,9,9,8,8,8,7};
		university.addAuditorium("Manuelita","Auditorios",true,university.addChairsAuditorium(chair));
		chair = new int[]{8,8,8,8,8,8,8,8};
		university.addAuditorium("Sidoc","Auditorios",true,university.addChairsAuditorium(chair));

		
	}
}