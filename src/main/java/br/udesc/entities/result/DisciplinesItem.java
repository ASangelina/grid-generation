package br.udesc.entities.result;

public class DisciplinesItem{
	private String fase;
	private String disciplineCode;
	private String weekDay;
	private String time;

	public void setFase(String fase){
		this.fase = fase;
	}

	public String getFase(){
		return fase;
	}

	public void setDisciplineCode(String disciplineCode){
		this.disciplineCode = disciplineCode;
	}

	public String getDisciplineCode(){
		return disciplineCode;
	}

	public void setWeekDay(String weekDay){
		this.weekDay = weekDay;
	}

	public String getWeekDay(){
		return weekDay;
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
			"DisciplinesItem{" + 
			"fase = '" + fase + '\'' + 
			",discipline_code = '" + disciplineCode + '\'' + 
			",week_day = '" + weekDay + '\'' + 
			",time = '" + time + '\'' + 
			"}";
		}
}
