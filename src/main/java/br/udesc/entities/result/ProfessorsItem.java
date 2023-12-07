package br.udesc.entities.result;

public class ProfessorsItem{
	private String disciplineName;
	private String weekDay;
	private String professorName;
	private String time;

	public void setDisciplineName(String disciplineName){
		this.disciplineName = disciplineName;
	}

	public String getDisciplineName(){
		return disciplineName;
	}

	public void setWeekDay(String weekDay){
		this.weekDay = weekDay;
	}

	public String getWeekDay(){
		return weekDay;
	}

	public void setProfessorName(String professorName){
		this.professorName = professorName;
	}

	public String getProfessorName(){
		return professorName;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"ProfessorsItem{" + 
			"discipline_name = '" + disciplineName + '\'' + 
			",week_day = '" + weekDay + '\'' + 
			",professor_name = '" + professorName + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}
