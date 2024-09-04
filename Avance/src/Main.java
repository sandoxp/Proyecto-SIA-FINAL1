public class Main {
    public static void main(String[] args) {

        //Creación de agenda.
        Agenda nuevaAgenda = new Agenda("Agenda Empresa");

        //Creacion de eventos
        Evento evento1 = new Evento("Capacitación en Seguridad", "Sesión de capacitación en normas de seguridad laboral", "Actividad", "11:30");
        Evento evento2 = new Evento("Reunión de Estrategia", "Discusión sobre las estrategias de ventas para el próximo trimestre", "Reunión", "09:00");
        Evento evento3 = new Evento("Revisión de Desempeño", "Evaluación del desempeño del equipo de desarrollo", "Reunión", "14:00");
        Evento evento4 = new Evento("Presentación de Resultados", "Presentación de resultados financieros a la gerencia", "Reunión", "16:00");
        Evento evento5 = new Evento("Evento Corporativo", "Celebración anual de la empresa con todos los empleados", "Actividad", "19:00");



        //Agregar eventos a nuevaAgenda utilizando método agregarEvento();
        nuevaAgenda.agregarEvento("2024-01-01", evento1);
        nuevaAgenda.agregarEvento("2024-01-01", evento2);
        nuevaAgenda.agregarEvento("2024-03-11", evento3);
        nuevaAgenda.agregarEvento("2024-08-29", evento4);
        nuevaAgenda.agregarEvento("2024-09-21", evento5);

        System.out.println("Todos los eventos agendados");

        //Menú
        Menu menu = new Menu(nuevaAgenda);
        menu.iniciarMenu();
    }
}
