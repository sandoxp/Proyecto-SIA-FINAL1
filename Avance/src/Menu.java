import java.util.Scanner;
import java.time.LocalDate;

public class Menu {

    private Scanner scanner;
    private Agenda agenda;

    public Menu(Agenda agenda) {
        this.agenda = agenda;
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu(){
        int opcion = 0;
        do{
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║                    MENÚ PRINCIPAL                    ║");
            System.out.println("╠══════════════════════════════════════════════════════╣");
            System.out.println("║ 1. Agregar Evento                                    ║");
            System.out.println("║ 2. Mostrar todos los Eventos                         ║");
            System.out.println("║ 3. Mostrar Eventos por fecha                         ║");
            System.out.println("║ 4. Mostrar todos los eventos por etiqueta            ║");
            System.out.println("║ 5. Mostrar eventos del dia fecha y por etiqueta      ║");
            System.out.println("║ 6. Eliminar Evento por ID                            ║");
            System.out.println("║ 7. Salir                                             ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion){
                case 1:
                    agregarEvento();
                    break;
                case 2:
                    mostrarTodosLosEventos();
                    break;
                case 3:
                    mostrarEventos();
                    break;
                case 4:
                    mostrarTodosLosEventosEtiqueta();
                    break;
                case 5:
                    mostrarEventosEtiqueta();
                    break;
                case 6:
                    eliminarEvento();
                    break;
                case 7:
                    System.out.print("Saliendo del programa.....");
                    break;
                default:
                    System.out.print("Ingrese una opción válida");
            }
        }while(opcion != 7);

    }

    public void agregarEvento(){
        System.out.println("Ingrese el nombre del evento: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el descripcion del evento: ");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese tipo de evento : Reunion ó Actividad: ");
        String etiqueta = scanner.nextLine();

        System.out.println("Ingrese la hora del evento: ");
        String horaEvento = scanner.nextLine();

        Evento evento = new Evento(nombre, descripcion, etiqueta, horaEvento);

        System.out.println("Ingrese fecha del evento: 'Formato YYYY-MM-DD': ");
        String clave = scanner.nextLine();
        agenda.agregarEvento(clave, evento);
        System.out.println("Evento agregado a Agenda!");
    }

    public void mostrarEventos() //por fecha
    {
        System.out.print("Ingrese fecha  Formato YYYY-MM-DD para consultar eventos: ");
        String fecha = scanner.nextLine();
        this.agenda.mostrarEventos(fecha);
    }

    public void mostrarEventosEtiqueta() //por fecha
    {
        System.out.print("Ingrese fecha  Formato YYYY-MM-DD para consultar eventos: ");
        String fecha = scanner.nextLine();
        System.out.println("Aplique filtro: 'Actividad/Reunion': ");
        String etiketa = scanner.nextLine();
        this.agenda.mostrarEventos(fecha, etiketa);
    }

    public void eliminarEvento(){
        System.out.println("Ingrese fecha para buscar evento a eliminar: ");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese id de evento a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        this.agenda.eliminarEvento(fecha, id);
    }

    public void mostrarTodosLosEventos(){
        agenda.mostrarTodosLosEventos();
    }

    public void mostrarTodosLosEventosEtiqueta(){
        System.out.println("Ingrese etiqueta para buscar eventos así: ");
        String etiqueta = scanner.nextLine();
        agenda.mostrarTodosLosEventosEtiqueta(etiqueta);
    }

//    Funciones del menu que hay que hacer
//    1.Agregar Evento
//    2.Mostrar Evento
//    3.Modificar Evento
//    4.Eliminar Evento
//    5.
//    6.

}
