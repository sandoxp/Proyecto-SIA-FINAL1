package Clases;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class AgenditaSemanal extends Agenda {

    public AgenditaSemanal(String nombreAgenda) {
        super(nombreAgenda);
    }

    @Override
    public List<Object[]> mostrarEventos(String fecha) {
        LocalDate dia = LocalDate.parse(fecha);

        // Obtener el lunes de la semana de la fecha proporcionada
        LocalDate lunes = dia.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        List<Object[]> eventosSemana = new ArrayList<>(); // Lista que contendrá los eventos de la semana

        // Iterar sobre cada día de la semana (lunes a domingo)
        for (int i = 0; i < 7; i++) {
            LocalDate diaSemana = lunes.plusDays(i);

            // Si hay eventos en el día actual, agregarlos a la lista de eventosSemana
            if (eventos.containsKey(diaSemana)) {
                ArrayList<Evento> eventosEnDia = eventos.get(diaSemana);
                for (Evento evento : eventosEnDia) {
                    // Agregar los atributos relevantes del evento a la lista
                    eventosSemana.add(new Object[]{evento.getIdEvento(), evento.getNombreEvento(), evento.getDescripcionEvento(), evento.getEtiqueta(), evento.getHoraEvento(), diaSemana.toString()});
                }
            }
        }

        // Retornar la lista de eventos de la semana
        return eventosSemana;
    }

}