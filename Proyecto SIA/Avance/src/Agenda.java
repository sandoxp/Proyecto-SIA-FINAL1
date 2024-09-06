//Bibliotecas
import java.time.LocalDate;
import java.util.*;

public class Agenda {
    private String nombreAgenda;
    private TreeMap<LocalDate, ArrayList<Evento>> eventos;

    //Constructores
    public Agenda(String nombreAgenda) {
        this.nombreAgenda = nombreAgenda;
        this.eventos = new TreeMap<>();
    }

    public TreeMap<LocalDate, ArrayList<Evento>> getEventos() {
        return eventos;
    }

    public String getNombreAgenda() { return nombreAgenda; }
    public void setNombreAgenda(String nombreAgenda) { this.nombreAgenda = nombreAgenda; }

    public void setEventos(TreeMap<LocalDate, ArrayList<Evento>> eventos) {
        this.eventos = eventos;
    }

    //Método para agregar evento a un arrayList dentro de un HashMap.
    public void agregarEvento(String fecha, Evento evento) {
        LocalDate dia = LocalDate.parse(fecha);  //'Casting'
        //
        if (!eventos.containsKey(dia)) {
            eventos.put(dia, new ArrayList<>());
        }
        eventos.get(dia).add(evento);
    }

    public void mostrarEventos(String fecha) {
        LocalDate dia = LocalDate.parse(fecha);
        ArrayList<Evento> eventosEnDia = eventos.getOrDefault(dia, new ArrayList<>());

        if (eventosEnDia.isEmpty()) {
            System.out.println("No hay eventos en esta fecha.");
        } else {
            // Ordenar eventos por hora (String)
            eventosEnDia.sort((e1, e2) -> e1.getHoraEvento().compareTo(e2.getHoraEvento())); // Comparar como String


            // Imprimir encabezado
            System.out.println("╔═════════════╦═════════════════════════════╦════════════════════════════════╦═════════════╗");
            System.out.println("║    HORA     ║        NOMBRE               ║         DESCRIPCIÓN            ║   ETIQUETA  ║");
            System.out.println("╠═════════════╬═════════════════════════════╬════════════════════════════════╬═════════════╣");

            // Imprimir eventos con línea separadora entre ellos
            for (Evento evento : eventosEnDia) {
                String descripcionCorta = evento.getDescripcionEvento();
                if (descripcionCorta.length() > 30) {
                    descripcionCorta = descripcionCorta.substring(0, 27) + "...";  // Limitar a 30 caracteres
                }

                System.out.printf("║ %11s ║ %-27s ║ %-28s ║ %-11s ║%n",
                        evento.getHoraEvento(),
                        evento.getNombreEvento(),
                        descripcionCorta,
                        evento.getEtiqueta());

                // Añadir línea separadora entre eventos
                System.out.println("╠═════════════╬═════════════════════════════╬════════════════════════════════╬═════════════╣");
            }

            System.out.println("╚═════════════╩═════════════════════════════╩════════════════════════════════╩═════════════╝");
        }
    }

    //Mostrar evento por fecha y etiqueta (Sobrecarga de metodo)
    public void mostrarEventos(String fecha, String etiqueta) {
        LocalDate dia = LocalDate.parse(fecha);
        ArrayList<Evento> eventosEnDia = eventos.getOrDefault(dia, new ArrayList<>());
        eventosEnDia.sort((e1, e2) -> e1.getHoraEvento().compareTo(e2.getHoraEvento()));

        if (eventosEnDia.isEmpty()) {
            System.out.println("No hay eventos en esta fecha.");
        } else {
            // Filtrar eventos por etiqueta
            List<Evento> eventosFiltrados = new ArrayList<>();
            for (Evento evento : eventosEnDia) {
                if (evento.getEtiqueta().equalsIgnoreCase(etiqueta)) {
                    eventosFiltrados.add(evento);
                }
            }

            if (eventosFiltrados.isEmpty()) {
                System.out.println("No hay eventos con la etiqueta '" + etiqueta + "' en la fecha " + fecha + ".");
            } else {
                // Imprimir encabezado
                System.out.println("═══════════════════════════════════════════════════════════════════════════════");
                System.out.println("                  EVENTOS DEL " + fecha + " - ETIQUETA: " + etiqueta.toUpperCase() + "                  ");
                System.out.println("═══════════════════════════════════════════════════════════════════════════════");
                System.out.println("╔═════════════╦═════════════════════════════╦════════════════════════════════╗");
                System.out.println("║    HORA     ║        NOMBRE               ║         DESCRIPCIÓN            ║");
                System.out.println("╠═════════════╬═════════════════════════════╬════════════════════════════════╣");

                // Imprimir los eventos filtrados
                for (Evento evento : eventosFiltrados) {
                    String descripcionCorta = evento.getDescripcionEvento();
                    if (descripcionCorta.length() > 30) {
                        descripcionCorta = descripcionCorta.substring(0, 27) + "...";  // Limitar a 30 caracteres
                    }

                    System.out.printf("║ %11s ║ %-27s ║ %-28s ║%n",
                            evento.getHoraEvento(),
                            evento.getNombreEvento(),
                            descripcionCorta);

                    // Añadir línea separadora entre eventos
                    System.out.println("╠═════════════╬═════════════════════════════╬════════════════════════════════╣");
                }

                System.out.println("╚═════════════╩═════════════════════════════╩════════════════════════════════╝");
            }
        }
    }


    // Este es mostrar ParaTreeMap y ya viene ordenado
    public void mostrarTodosLosEventos() {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos en la agenda.");
            return;
        }
        for (Map.Entry<LocalDate, ArrayList<Evento>> entrada : eventos.entrySet()) {
            LocalDate dia = entrada.getKey();
            ArrayList<Evento> eventosEnDia = entrada.getValue();

            eventosEnDia.sort((e1, e2) -> e1.getHoraEvento().compareTo(e2.getHoraEvento()));


            System.out.println("Eventos en " + dia + ":");
            for (Evento evento : eventosEnDia) {
                System.out.println(evento);
            }
        }
    }

    // Este es el mostrareventos etiquetas que funciona
    public void mostrarTodosLosEventos(String etiqueta) {
        boolean encontrado = false;
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos en la agenda.");
            return;
        }
        for (Map.Entry<LocalDate, ArrayList<Evento>> entrada : eventos.entrySet()) {
            LocalDate dia = entrada.getKey();
            ArrayList<Evento> eventosEnDia = entrada.getValue();

            eventosEnDia.sort((e1, e2) -> e1.getHoraEvento().compareTo(e2.getHoraEvento()));

            boolean hayEventosEnEsteDia = false;
            for (Evento evento : eventosEnDia) {
                if (evento.getEtiqueta().equals(etiqueta)) {
                    if (!hayEventosEnEsteDia) {
                        System.out.println("Eventos en " + dia + ":");
                        hayEventosEnEsteDia = true;
                    }
                    System.out.println(evento);
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            System.out.println("No hay eventos con la etiqueta '" + etiqueta + "' en la agenda.");
        }
    }

////Este es el eliminarEventos
//    public void eliminarEvento(String fecha, int id) {
//
//        LocalDate dia = LocalDate.parse(fecha);
//        if (!eventos.containsKey(dia)) {
//            System.out.println("No hay eventos en esta fecha: " + fecha);
//        } else{
//            this.eventos.get(dia).remove(id-1);
//            System.out.println("Evento con id " + id + " eliminado.");
//
//        }
//    }
//}

    public void eliminarEvento(String fecha, int id) {
        LocalDate dia = LocalDate.parse(fecha);  // Convertir la fecha a LocalDate
        if (!eventos.containsKey(dia)) {
            System.out.println("No hay eventos en esta fecha: " + fecha);
        } else {
            ArrayList<Evento> eventosEnDia = this.eventos.get(dia);

            // Buscar el evento con el ID proporcionado
            Evento eventoAEliminar = null;
            for (Evento evento : eventosEnDia) {
                if (evento.getIdEvento() == id) {
                    eventoAEliminar = evento;
                    break;
                }
            }

            if (eventoAEliminar != null) {
                eventosEnDia.remove(eventoAEliminar);
                System.out.println("Evento con ID " + id + " eliminado.");
            } else {
                System.out.println("No se encontró un evento con ID " + id + " en la fecha " + fecha + ".");
            }
        }
    }


//    public void eliminarEvento(String fecha, int id) {
//        LocalDate dia = LocalDate.parse(fecha);  // Convertir la fecha a LocalDate
//        if (!eventos.containsKey(dia)) {
//            System.out.println("No hay eventos en esta fecha: " + fecha);
//        } else {
//            ArrayList<Evento> eventosEnDia = this.eventos.get(dia);
//
//            // Intentar eliminar el evento con el ID proporcionado usando removeIf
//            boolean eliminado = eventosEnDia.removeIf(evento -> evento.getIdEvento() == id);
//
//            if (eliminado) {
//                System.out.println("Evento con ID " + id + " eliminado.");
//            } else {
//                System.out.println("No se encontró un evento con ID " + id + " en la fecha " + fecha + ".");
//            }
//        }
//    }

}