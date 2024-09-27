package Avance;
import Avance.Agenda;
import Avance.Evento;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.ArrayList;

public class AgenditaSemanal extends Agenda {

    public AgenditaSemanal(String nombreAgenda) {
        super(nombreAgenda);
    }

    @Override
    public void mostrarEventos(String fecha) {
        LocalDate dia = LocalDate.parse(fecha);

        // Obtener el lunes de la semana de la fecha proporcionada
        LocalDate lunes = dia.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // Mostrar eventos desde el lunes hasta el domingo
        System.out.println("Mostrando eventos de la semana del " + lunes + " al " + lunes.plusDays(6) + ":");

        for (int i = 0; i < 7; i++) {
            LocalDate diaSemana = lunes.plusDays(i);
            System.out.println("\nEventos para el día: " + diaSemana);

            if (eventos.containsKey(diaSemana)) {
                ArrayList<Evento> eventosEnDia = eventos.get(diaSemana);
                for (Evento evento : eventosEnDia) {
                    System.out.println(evento);  // Imprime los eventos del día
                }
            } else {
                System.out.println("No hay eventos para este día.");
            }
        }
    }
}
