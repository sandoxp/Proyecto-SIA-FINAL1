package Clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Notificacion {

    // Consulta el tiempo restante (días, horas y minutos) para un evento específico.
    public void consultarTiempoRestante(Evento evento) {
        LocalDate fechaEvento = LocalDate.parse(evento.getFechaEvento());
        LocalTime horaEvento = LocalTime.parse(evento.getHoraEvento());
        LocalDateTime fechaHoraEvento = LocalDateTime.of(fechaEvento, horaEvento);

        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Verifica si el evento es futuro; si ya pasó, se notifica.
        if (fechaHoraActual.isBefore(fechaHoraEvento)) {
            long diasRestantes = ChronoUnit.DAYS.between(fechaHoraActual, fechaHoraEvento);
            long horasRestantes = ChronoUnit.HOURS.between(fechaHoraActual, fechaHoraEvento) % 24;
            long minutosRestantes = ChronoUnit.MINUTES.between(fechaHoraActual, fechaHoraEvento) % 60;

            // Muestra el tiempo restante hasta el evento.
            System.out.println("Faltan " + diasRestantes + " días, " + horasRestantes + " horas y " + minutosRestantes + " minutos para el evento '" + evento.getNombreEvento() + "'.");
        } else {
            System.out.println("El evento '" + evento.getNombreEvento() + "' ya ha ocurrido.");
        }
    }

    // Sobrecarga para consultar solo los días restantes hasta un evento.
    public void consultarTiempoRestante(Evento evento, boolean soloDias) {
        LocalDate fechaEvento = LocalDate.parse(evento.getFechaEvento());
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        LocalDateTime fechaHoraEvento = LocalDateTime.of(fechaEvento, LocalTime.parse(evento.getHoraEvento()));

        // Similar a la otra versión, pero solo muestra días restantes.
        if (fechaHoraActual.isBefore(fechaHoraEvento)) {
            long diasRestantes = ChronoUnit.DAYS.between(fechaHoraActual.toLocalDate(), fechaEvento);
            System.out.println("Faltan " + diasRestantes + " días para el evento '" + evento.getNombreEvento() + "'.");
        } else {
            System.out.println("El evento '" + evento.getNombreEvento() + "' ya ha ocurrido.");
        }
    }
}
