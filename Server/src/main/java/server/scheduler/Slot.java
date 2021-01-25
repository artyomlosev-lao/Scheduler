package server.scheduler;

// слот - это отдельный блок расписания
//slot is single block of timetable

public class Slot{
	public StudentGroup studentgroup;
	public String teacherid;
	public String subject;


	// непараметрический конструктор для разрешения бесплатных периодов
	//non parametrized constructor for allowing free periods
	Slot(){};
	
	Slot(StudentGroup studentgroup,String teacherid,String subject){
		
		this.studentgroup=studentgroup;
		this.subject=subject;
		this.teacherid=teacherid;
	
	}
}
