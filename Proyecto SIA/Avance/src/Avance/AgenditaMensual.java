package Avance;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class AgenditaMensual extends Agenda {

    public AgenditaMensual(String nombreAgenda) {
        super(nombreAgenda);
    }

    @Override
    public ArrayList<Object[]> mostrarEventos(String fecha) {
        LocalDate dia = LocalDate.parse(fecha);
        ArrayList<Object[]> listongo = new ArrayList<>();
        // Obtener el primer y último día del mes
        LocalDate primerDiaMes = dia.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate ultimoDiaMes = dia.with(TemporalAdjusters.lastDayOfMonth());

        // Mostrar eventos desde el primer día hasta el último día del mes
        System.out.println("Mostrando eventos del mes desde el " + primerDiaMes + " hasta el " + ultimoDiaMes + ":");

        for (LocalDate currentDay = primerDiaMes; !currentDay.isAfter(ultimoDiaMes); currentDay = currentDay.plusDays(1)) {
            System.out.println("\nEventos para el día: " + currentDay);

            // Comprobar si hay eventos en el día actual
            if (eventos.containsKey(currentDay)) {
                ArrayList<Evento> eventosDelDia = eventos.get(currentDay);
                for (Evento evento : eventosDelDia) {
                    System.out.println(evento);  // Imprimir los eventos del día
                }
            } else {
                System.out.println("No hay eventos para este día.");
            }
        }
        return listongo;
    }
}