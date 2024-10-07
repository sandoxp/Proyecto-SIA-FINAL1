package Clases;//Bibliotecas
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.time.LocalDate;

import java.util.*;

public class Agenda {
    private String nombreAgenda;
    protected TreeMap<LocalDate, ArrayList<Evento>> eventos;
    private Scanner scanner;

    public Agenda(String nombreAgenda) {
        this.nombreAgenda = nombreAgenda;
        this.eventos = new TreeMap<>();
        this.scanner = new Scanner(System.in);
    }

    public TreeMap<LocalDate, ArrayList<Evento>> getEventos() {
        return eventos;
    }

    public String getNombreAgenda() { return nombreAgenda; }
    public void setNombreAgenda(String nombreAgenda) { this.nombreAgenda = nombreAgenda; }

    public void setEventos(TreeMap<LocalDate, ArrayList<Evento>> eventos) {
        this.eventos = eventos;
    }

    public void agregarEvento(String fecha, Evento evento) {
        LocalDate dia = LocalDate.parse(fecha);  //'Casting'
        //
        if (!eventos.containsKey(dia)) {
            eventos.put(dia, new ArrayList<>());
        }
        eventos.get(dia).add(evento);
    }

    public List<Object[]> mostrarEventos(String fecha) {
        LocalDate dia = LocalDate.parse(fecha);
        ArrayList<Evento> eventosEnDia = eventos.getOrDefault(dia, new ArrayList<>());

        if (eventosEnDia.isEmpty()) {
            System.out.println("No hay eventos en esta fecha.");
        } else {
            // Ordenar eventos por hora (String)
            eventosEnDia.sort((e1, e2) -> e1.getHoraEvento().compareTo(e2.getHoraEvento())); // Comparar como String

            System.out.println("╔═════════════╦═════════════════════════════╦════════════════════════════════╦═════════════╗");
            System.out.println("║    HORA     ║        NOMBRE               ║         DESCRIPCIÓN            ║   ETIQUETA  ║");
            System.out.println("╠═════════════╬═════════════════════════════╬════════════════════════════════╬═════════════╣");

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

                System.out.println("╠═════════════╬═════════════════════════════╬════════════════════════════════╬═════════════╣");
            }

            System.out.println("╚═════════════╩═════════════════════════════╩════════════════════════════════╩═════════════╝");
        }
        return null;
    }

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


    public List<Object[]> mostrarTodosLosEventos() {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos en la agenda.");
            return new ArrayList<>(); // Retorna una lista vacía en lugar de null
        }

        List<Object[]> listaEventos = new ArrayList<>();

        for (Map.Entry<LocalDate, ArrayList<Evento>> entrada : eventos.entrySet()) {
            LocalDate dia = entrada.getKey();
            ArrayList<Evento> eventosEnDia = entrada.getValue();

            // Ordenar eventos por hora
            eventosEnDia.sort((e1, e2) -> e1.getHoraEvento().compareTo(e2.getHoraEvento()));

            for (Evento evento : eventosEnDia) {
                // Agregar los datos del evento a la lista en formato Object[]
                listaEventos.add(new Object[]{
                        evento.getIdEvento(),
                        evento.getNombreEvento(),
                        evento.getDescripcionEvento(),
                        evento.getEtiqueta(),
                        evento.getHoraEvento(),
                        dia.toString()
                });
            }
        }

        return listaEventos;
    }


    public List<Object[]> mostrarTodosLosEventos(String etiqueta) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos en la agenda.");
            return new ArrayList<>(); // Retorna una lista vacía en lugar de null
        }

        List<Object[]> listaEventos = new ArrayList<>();

        for (Map.Entry<LocalDate, ArrayList<Evento>> entrada : eventos.entrySet()) {
            LocalDate dia = entrada.getKey();
            ArrayList<Evento> eventosEnDia = entrada.getValue();

            // Ordenar eventos por hora
            eventosEnDia.sort((e1, e2) -> e1.getHoraEvento().compareTo(e2.getHoraEvento()));

            for (Evento evento : eventosEnDia) {
                // Filtrar eventos por la etiqueta proporcionada
                if (evento.getEtiqueta().equalsIgnoreCase(etiqueta)) {
                    listaEventos.add(new Object[]{
                            evento.getIdEvento(),
                            evento.getNombreEvento(),
                            evento.getDescripcionEvento(),
                            evento.getEtiqueta(),
                            evento.getHoraEvento(),
                            dia.toString()
                    });
                }
            }
        }

        return listaEventos; // Retornar la lista de eventos filtrados por la etiqueta
    }



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
                System.out.println("Clases.Evento con ID " + id + " eliminado.");
            } else {
                System.out.println("No se encontró un evento con ID " + id + " en la fecha " + fecha + ".");
            }
        }
    }

    public void modificarEvento(String fecha, int id) {
        LocalDate dia = LocalDate.parse(fecha);

        // Verifica si hay eventos en la fecha proporcionada
        if (!eventos.containsKey(dia)) {
            System.out.println("No hay eventos en esta fecha: " + fecha);
        } else {
            ArrayList<Evento> eventosEnDia = this.eventos.get(dia);

            // Busca el evento con el ID proporcionado
            int indice = -1;
            for (int i = 0; i < eventosEnDia.size(); i++) {
                if (eventosEnDia.get(i).getIdEvento() == id) {
                    indice = i;
                    break;
                }
            }

            // Si se encuentra el evento, permite modificar sus atributos
            if (indice != -1) {
                Evento evento = eventosEnDia.get(indice);
                int opcion = 0;

                // Ciclo de menú para seleccionar qué atributo del evento modificar
                do {
                    System.out.println("¿Qué desea modificar en el evento?");
                    System.out.println("1. Modificar nombre.");
                    System.out.println("2. Modificar descripción.");
                    System.out.println("3. Modificar etiqueta.");
                    System.out.println("4. Modificar hora del evento.");
                    System.out.println("5. Modificar fecha del evento.");
                    System.out.println("6. Volver al menú principal."); // Salir del ciclo de modificación
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada

                    switch (opcion) {
                        case 1:
                            // Modificar el nombre del evento
                            System.out.println("Ingrese nuevo nombre para el evento " + id + ":");
                            String nuevoNombre = scanner.nextLine();
                            evento.setNombreEvento(nuevoNombre);
                            System.out.println("Nombre del evento actualizado.");
                            break;

                        case 2:
                            // Modificar la descripción del evento
                            System.out.println("Ingrese nueva descripción para el evento " + id + ":");
                            String nuevaDescripcion = scanner.nextLine();
                            evento.setDescripcionEvento(nuevaDescripcion);
                            System.out.println("Descripción del evento actualizada.");
                            break;

                        case 3:
                            // Modificar la etiqueta del evento
                            System.out.println("Ingrese nueva etiqueta para el evento " + id + ":");
                            String nuevaEtiqueta = scanner.nextLine();
                            evento.setEtiqueta(nuevaEtiqueta);
                            System.out.println("Etiqueta del evento actualizada.");
                            break;

                        case 4:
                            // Modificar la hora del evento
                            System.out.println("Ingrese nueva hora (HH:mm) para el evento " + id + ":");
                            String nuevaHora = scanner.nextLine();
                            evento.setHoraEvento(nuevaHora);
                            System.out.println("Hora del evento actualizada.");
                            break;

                        case 5:
                            // Modificar la fecha del evento
                            System.out.println("Ingrese nueva fecha (YYYY-MM-DD) para el evento " + id + ":");
                            String nuevaFecha = scanner.nextLine();
                            LocalDate nuevaFechaEvento = LocalDate.parse(nuevaFecha);
                            eventosEnDia.remove(evento); // Remueve el evento de la fecha original
                            agregarEvento(nuevaFecha, evento); // Agrega el evento en la nueva fecha
                            System.out.println("Fecha del evento actualizada.");
                            break;

                        case 6:
                            // Opción para volver al menú principal
                            System.out.println("Volviendo al menú principal...");
                            break;

                        default:
                            // Manejo de opción no válida
                            System.out.println("Opción no válida.");
                    }

                } while (opcion != 6); // Repite hasta que el usuario elija volver al menú principal

            } else {
                // Informa si no se encontró el evento con el ID dado en la fecha específica
                System.out.println("No se encontró un evento con ID " + id + " en la fecha " + fecha + ".");
            }
        }
    }


    public void guardarEventosCSV(String archivoCSV) {
        // Uso de try-with-resources para asegurar que el recurso (FileWriter y CSVWriter)
        // se cierre automáticamente al finalizar el bloque, evitando posibles fugas de recursos.
        try (CSVWriter writer = new CSVWriter(new FileWriter(archivoCSV))) {

            // Escribir la primera línea del archivo CSV, que contiene los encabezados de las columnas.
            String[] encabezados = {"ID", "Nombre", "Descripción", "Etiqueta", "Hora", "Fecha"};
            writer.writeNext(encabezados);

            // Iterar sobre cada entrada del TreeMap 'eventos'.
            // Cada entrada consiste en una fecha (LocalDate) y una lista de eventos asociados a esa fecha.
            for (Map.Entry<LocalDate, ArrayList<Evento>> entrada : eventos.entrySet()) {

                // Iterar sobre cada evento en la lista de eventos correspondiente a la fecha actual.
                for (Evento evento : entrada.getValue()) {

                    // Extraer los datos del evento en un arreglo de strings.
                    // Los datos incluyen ID del evento, nombre, descripción, etiqueta, hora y fecha.
                    String[] datosEvento = {
                            String.valueOf(evento.getIdEvento()), // ID del evento convertido a string.
                            evento.getNombreEvento(),             // Nombre del evento.
                            evento.getDescripcionEvento(),        // Descripción del evento.
                            evento.getEtiqueta(),                 // Etiqueta del evento (por ejemplo, "Reunión", "Actividad").
                            evento.getHoraEvento(),               // Hora del evento en formato "HH:mm".
                            evento.getFechaEvento()               // Fecha del evento.
                    };

                    // Escribir los datos del evento como una nueva línea en el archivo CSV.
                    writer.writeNext(datosEvento);
                }
            }
        } catch (IOException e) {
            // En caso de ocurrir un error de E/S (por ejemplo, problemas al abrir o escribir el archivo),
            // se imprime la traza del error para facilitar la identificación y corrección del problema.
            e.printStackTrace();
        }
    }

    public void cargarEventosCSV(String archivoCSV) {
        try (CSVReader reader = new CSVReader(new FileReader(archivoCSV))) {
            // Abre el archivo CSV con un `CSVReader` para facilitar la lectura línea por línea

            String[] siguienteLinea;
            // Variable que almacenará cada línea leída del archivo como un array de Strings

            reader.readNext(); // Saltar encabezados
            // Lee y descarta la primera línea del archivo (asumida como encabezado)

            int maxId = 0;
            // Variable para llevar el ID más alto encontrado en el archivo

            while ((siguienteLinea = reader.readNext()) != null) {
                // Bucle para leer cada línea del archivo CSV mientras no sea nula (fin del archivo)

                if (siguienteLinea.length < 6) continue; // Validar línea
                // Verifica que la línea contenga al menos 6 columnas; si no, omite esta línea

                int id = Integer.parseInt(siguienteLinea[0]);
                // Parsea la primera columna como un entero, que será el ID del evento

                String nombre = siguienteLinea[1];
                String descripcion = siguienteLinea[2];
                String etiqueta = siguienteLinea[3];
                String hora = siguienteLinea[4];
                String fecha = siguienteLinea[5];
                // Extrae los siguientes valores de la línea como Strings:
                // `nombre`, `descripcion`, `etiqueta`, `hora` y `fecha` del evento

                Evento evento = new Evento(nombre, descripcion, etiqueta, hora, fecha);
                // Crea un nuevo objeto `Evento` con los datos extraídos de la línea

                evento.setIdEvento(id);
                // Asigna el ID al evento usando el método `setIdEvento`

                agregarEvento(fecha, evento);
                // Llama al método `agregarEvento` para agregar el evento a la colección de eventos

                if (id > maxId) {
                    maxId = id;
                }
                // Si el ID actual es mayor que el `maxId`, actualiza `maxId`
                // Esto garantiza que siempre se tenga el ID más alto del archivo
            }

            Evento.setContador(maxId);
            // Actualiza el contador estático de IDs en la clase `Evento` con el ID más alto encontrado
            // Esto asegura que los futuros eventos tengan IDs únicos y secuenciales

        } catch (IOException | CsvValidationException e) {
            // Captura excepciones relacionadas con la lectura del archivo o validación del CSV

            System.out.println("Archivo CSV no encontrado. Se creará uno nuevo al guardar.");
            // Imprime un mensaje si no se encuentra el archivo o hay un problema con el CSV

        } catch (NumberFormatException e) {
            // Captura excepciones al intentar convertir el ID de String a entero

            System.out.println("Error al parsear el ID del evento.");
            // Imprime un mensaje si hay un problema al convertir el ID a entero
        }
        // Finaliza el bloque `try-catch`
    }
    
}