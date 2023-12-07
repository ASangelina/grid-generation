package br.udesc.entities.result;

import java.util.List;

public class Response{
	private List<DisciplinesItem> disciplines;
	private List<ProfessorsItem> professors;

	public void setDisciplines(List<DisciplinesItem> disciplines){
		this.disciplines = disciplines;
	}

	public List<DisciplinesItem> getDisciplines(){
		return disciplines;
	}

	public void setProfessors(List<ProfessorsItem> professors){
		this.professors = professors;
	}

	public List<ProfessorsItem> getProfessors(){
		return professors;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"disciplines = '" + disciplines + '\'' + 
			",professors = '" + professors + '\'' + 
			"}";
		}
}
