package ui;
import java.util.Scanner;
public class Mian{
	private Scanner s = new Scanner(System.in);
	private Scanner n = new Scanner(System.in);
	private  University university = new University();
	public static void main(String[]args){
		boolean continue1 = true;
		do{
			showMenu();
			continue1 = start();
		}while(continue1 == true);
		

	}
	public void showMenu(){
		System.out.println("Por favor escoja la obcion a realizar\n"+
			"1. Agregar un nuevo audirio a la Universidad\n"+
			"2. Reservar un audirio\n"+
			"3. Reportar silla defectuosa\n"+
			"0. Salir");

	}
	public boolean start(){
		int selection,year,month,day,startTime,endTime,amountPeople,audi,number;
		Calendar today = Calendar.getInstance();
		String name,locate,teacherName,position,row;
		Chair[][] chairs;
		selection = n.nextInt();
		switch (selection){
			case 1:
			//String name, String locate, boolean available, Chair[][] chairs
			System.out.println("indique el nombre del auditorio que desea agregar: ");
			name = s.nextLine();
			System.out.println("indique la ubicacion del auditorio:");
			locate = s.nextLine();
			do{
				System.out.println("indique la cantidad de filas(silleteria) que contiene el auditorio");
				int[] chair = new Int[n.nextInt()];
				for(int = 0; i<chair.length;i++){
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
			break;

			case 3:
			System.out.println("indique el auditorio donde se encuentra la silla");
			university.showAuditorium();
			audi = n.nextInt();
			System.out.println("indique la fila donde se encuentra en formato alfabetico: ");
			row = s.next().char(0);
			System.out.println("indique el numero de la silla");
			number = n.nextInt();
			System.out.println("indique una description del daño de la silla");
			description = s.nextLine();
			position = row+number;	
			university.reportDefectChair(audi,position,description);
			break;


		}
		if(selection == 0){
			return false;
		}else{
			return true;
		}
	}
}