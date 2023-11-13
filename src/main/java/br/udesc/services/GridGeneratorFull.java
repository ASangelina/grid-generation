package br.udesc.services;

import br.udesc.entities.*;

import java.util.*;
import java.util.stream.Collectors;

public class GridGeneratorFull {

    // Essa classe precisa fazer a geração da grade para todas as restrições.
    // Estrátegia:
    // Separar os professores em duas listas, uma contendo os professores mais restritos, e a outraa os que sobrarem
    // pergunta precisa ter duas listas? acho que não.

    // Quem começa a brincadeira é o professor mais restrito, tanto pela quantidade de dias, como as restrições das suas disciplinas

    // Talvez vai precisar de um ultis para os dias.
    // ou talvez não, poderia ser o for day in daysMandatory();

    // creditos das disciplinas do professor;

    // Estratégia:
    // iniciar pela

    public void buildSchedule(Transaction transaction){
        //result objet:
        Map<Integer, Discipline[][]> grades = new HashMap<>();

        // pegar a lista de professores do objeto transaction
        List<Professor> professorsESO = transaction.getProfessorList();

        // lista de professores com restrições
        List<Professor> professorsRestrict = professorsESO.stream()
                .filter(professor -> professor.getMandatoryDays().size() > 0)
                .collect(Collectors.toList());

        // lista de professores com restrição de aulas juntas
        List<Professor> professorsRestrictClass = new ArrayList<>();
        for(Professor professor:professorsESO){
            for(Discipline discipline:professor.getDisciplines()){
                if((discipline.getClassRestriction() == ClassRestriction.TOGETHER) && !professorsRestrictClass.contains(professor)){
                    professorsRestrictClass.add(professor);
                }
            }
        }

        // antes:
        for(Professor prof: professorsRestrictClass){
            System.out.println("ProfessorAntes: " + (prof.getName()) + ", Dias: " + (prof.getMandatoryDays()));
        }

        // ordena professores pelos que tem mais dias restristos
        Collections.sort(professorsRestrictClass, new Comparator<Professor>() {
            @Override
            public int compare(Professor professor1, Professor professor2) {
                return Integer.compare(professor2.getMandatoryDays().size(), professor1.getMandatoryDays().size());
            }
        });

        //depois
        for(Professor prof: professorsRestrictClass){
            System.out.println("Professor Depois: " + (prof.getName()) + ", Dias: " + (prof.getMandatoryDays()));
        }

        // O professorsRestrictClass vão ser os que darão inicio da brincadeira.




        //ordena usando outra classe
        //professorsRestrict.sort(new ProfessorComparator());

        // preencher a grade:



        //teste saída
        //for(Professor prof: professorsRestrict){
        //    List<Weekdays> teste = prof.getMandatoryDays();
        //    System.out.println("Professor: " + (prof.getName()) + ", Dias: " + (prof.getMandatoryDays()));
        //}

        // ordenar esses professores restritos

    }

}
