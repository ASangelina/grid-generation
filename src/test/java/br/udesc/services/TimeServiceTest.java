package br.udesc.services;

import br.udesc.entities.Professor;
import br.udesc.entities.Weekdays;
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
        disciplinesL.add("555RED");
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
        TimeService timeService = new TimeService(profe);



         // Chama o metodo para achar o professor mais restrito de dias
        //TODO: add verifica, que tem mais disciplinas na brincadeira
        Professor resultadoObtido = timeService.mostRestrictedProfessor();

        assertEquals(professorLeandro.getName(), resultadoObtido.getName());
    }
}
