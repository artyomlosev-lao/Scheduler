package server.scheduler;

import java.io.*;
import java.util.Random;

// ген представляет перестановку слотов в виде расписания для одной группы студентов (выглядит как {5,22,14,1, ...})
//gene represents permutation of slots as timetable for a single student group(looks like {5,22,14,1,...} )
public class Gene implements Serializable{

	public int slotno[];
	int days=inputdata.daysperweek;
	int hours=inputdata.hoursperday;
	
	Random r=new Random();
	
	Gene(int i){
		
		boolean[] flag=new boolean[days*hours];


		/* генерируем массив слота no, соответствующий индексу гена, например, предположим индекс
				* равно 2, тогда слот будет варьироваться от 2 * часов * дней до 3 * часов * дней
				* /


		/*  generating an array of slot no corresponding to index of gene eg suppose index
		 *	is 2 then slotno will vary from 2*hours*days to 3*hours*days
		 */
		
		slotno=new int[days*hours];
		
		for(int j=0;j<days*hours;j++){
			
			int rnd;
			while(flag[rnd=r.nextInt(days*hours)]==true){}
			flag[rnd]=true;
			slotno[j]=i*days*hours + rnd;
			
			/*	Slot[] slot=TimeTable.returnSlots();
			 *	if(slot[slotno[j]]!=null)System.out.print(slot[slotno[j]].subject+" ");
			 *	else System.out.print("break ");
			 */
		
		}
		
	}
	
	public Gene deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Gene) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}