package br.udesc.services;

import br.udesc.entities.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TimeServiceTest extends TestCase {
    @Test
    public void testMostRestrictedProfessor() {

        //leandro
        List<String> disciplinesL = new ArrayList<>();
        disciplinesL.add("15ARC");
        disciplinesL.add("25SIS");
        disciplinesL.add("55RED");
        List<Weekdays> diasDaSemanaRestrito = new ArrayList<>();
        List<Weekdays> diasDaSemanaPreferencial = new ArrayList<>();
        diasDaSemanaRestrito.add(Weekdays.MONDAY);
        diasDaSemanaRestrito.add(Weekdays.FRIDAY);

        //sheila
        List<String> disciplinesS = new ArrayList<>();
        disciplinesS.add("15CEX");
        disciplinesS.add("35MPC");
        List<Weekdays> diasDaSemanaRestritoS = new ArrayList<>();
        List<Weekdays> diasDaSemanaPreferencialS = new ArrayList<>();
        diasDaSemanaRestritoS.add(Weekdays.FRIDAY);

        //cruz
        List<String> disciplinesC = new ArrayList<>();
        disciplinesC.add("15FAD");
        disciplinesC.add("45EMP");
        disciplinesC.add("55EQU");
        disciplinesC.add("85MKT");
        List<Weekdays> diasDaSemanaRestritoC = new ArrayList<>();
        List<Weekdays> diasDaSemanaPreferencialC = new ArrayList<>();
        diasDaSemanaRestritoC.add(Weekdays.WEDNESDAY);
        diasDaSemanaRestritoC.add(Weekdays.FRIDAY);
        diasDaSemanaRestritoC.add(Weekdays.SATURDAY);


        Professor professorLeandro = new Professor("1033292446", "LEANDRO LOFF",disciplinesL, diasDaSemanaPreferencial,diasDaSemanaRestrito);
        Professor professorSheila = new Professor("1033195192", "SCHEILA PATRICIA DE BORBA CURRY",disciplinesS, diasDaSemanaPreferencialS,diasDaSemanaRestritoS);
        Professor professorCarlos = new Professor("1033132265", "CARLOS FERNANDO CRUZ",disciplinesC, diasDaSemanaPreferencialC,diasDaSemanaRestritoC);

        List<Professor> profe = new ArrayList<>();
        profe.add(professorLeandro);
        profe.add(professorSheila);
        profe.add(professorCarlos);

        Discipline disciplineL = new Discipline("15ARC", "Arquitetura de Computadores", "1033292446", 72, 4, ClassRestriction.NONE,TimeRestriction.NONE);
        Discipline disciplineL2 = new Discipline("25SIS", "Sistemas Operacionais", "1033292446", 36, 2, ClassRestriction.NONE,TimeRestriction.END);
        Discipline disciplineL3 = new Discipline("55RED", "Redes de Computadores", "1033292446", 36, 2, ClassRestriction.NONE,TimeRestriction.START);

        Discipline disciplineS1 = new Discipline("15CEX", "Comunicação e Expressão", "1033195192", 36, 2, ClassRestriction.NONE, TimeRestriction.END);
        Discipline disciplineS2 = new Discipline("35MPC", "Metodologia da Pesquisa Científica", "1033195192", 36, 2, ClassRestriction.NONE, TimeRestriction.START);

        Discipline disciplineC1 = new Discipline("15FAD", "Fundamentos de Administração", "1033132265", 36, 2, ClassRestriction.NONE, TimeRestriction.END);
        Discipline disciplineC2 = new Discipline("45EMP", "Empreendedorismo", "1033132265", 72, 4, ClassRestriction.TOGETHER, TimeRestriction.NONE);
        Discipline disciplineC3 = new Discipline("55EQU", "Engenharia da Qualidade", "1033132265", 36, 2, ClassRestriction.NONE, TimeRestriction.END);
        Discipline disciplineC4 = new Discipline("85MKT", "Marketing", "1033132265", 36, 2, ClassRestriction.NONE, TimeRestriction.NONE);

        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(disciplineL);
        disciplines.add(disciplineL2);
        disciplines.add(disciplineL3);
        disciplines.add(disciplineS1);
        disciplines.add(disciplineS2);
        disciplines.add(disciplineC1);
        disciplines.add(disciplineC2);
        disciplines.add(disciplineC3);
        disciplines.add(disciplineC4);

        TimeService timeService = new TimeService(profe,disciplines);



         // Chama o metodo para achar o professor mais restrito de dias
        Professor resultadoObtido = timeService.mostRestrictedProfessor();

        assertEquals(professorLeandro.getName(), resultadoObtido.getName());
    }
}
