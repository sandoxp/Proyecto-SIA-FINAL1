package Ventanas;
import Avance.Agenda;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class VentanaOpcionesMostrar {
    private JButton visualizarTodosLosEventosButton;
    private JPanel panel1;
    private JButton visualizarEventosPorFechaButton;
    private JButton visualizarEventosPorEtiquetaButton;
    private JButton visualizarEventosEnSemanaButton;
    private JButton visualizarEventosEnMesButton;
    private JButton visualizarEventosPorFechayEtiquetaButton;

    private VentanaMostrarTodo ventanaMostrarTodo;
    private Agenda agenda; // Referencia a la agenda



    public VentanaOpcionesMostrar(Agenda agenda) {
        this.agenda = agenda;
        // Acción para visualizar todos los eventos
        visualizarTodosLosEventosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaMostrarTodo v1 = new VentanaMostrarTodo(agenda);
                v1.mostrarTablaEventos();
            }
        });

        // Acción para visualizar eventos por fecha
        visualizarEventosPorFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaPorFecha ventanaPorFecha = new VentanaPorFecha(agenda);
            }
        });

        // Acción para visualizar eventos por etiqueta
        visualizarEventosPorEtiquetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear e instanciar VentanaPorEtiqueta con la agenda actual
                VentanaPorEtiqueta ventanaPorEtiqueta = new VentanaPorEtiqueta(agenda);
            }
        });

        // Acción para visualizar eventos por fecha y etiqueta
        visualizarEventosPorFechayEtiquetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, "Visualizar eventos por fecha y etiqueta.");
            }
        });

        // Acción para visualizar eventos en la semana
        this.agenda = agenda;

        // Acción para visualizar eventos de la semana
        visualizarEventosEnSemanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar al usuario la fecha inicial para la semana
                String fechaInicial = JOptionPane.showInputDialog(null, "Ingrese la fecha inicial de la semana (Formato YYYY-MM-DD):");

                // Crear e inicializar VentanaSemanal
                VentanaSemanal ventanaSemanal = new VentanaSemanal(agenda);
                ventanaSemanal.mostrarTablaEventosSemana(fechaInicial);
            }
        });

        // Acción para visualizar eventos en el mes
        visualizarEventosEnMesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, "Visualizar eventos en el mes.");
            }
        });
    }


    public JPanel getPanel() {
        return panel1;
    }


}
