package Ventanas;

import Avance.Agenda;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaPorEtiqueta {
    private Agenda agenda;
    private JComboBox<String> etiquetaComboBox;
    private JButton filtrarButton;

    // Constructor que recibe la agenda para acceder a los eventos
    public VentanaPorEtiqueta(Agenda agenda) {
        this.agenda = agenda;

        // Crear ventana
        JFrame frame = new JFrame("Filtrar Eventos por Etiqueta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        // Panel principal
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Crear un JComboBox para seleccionar la etiqueta
        etiquetaComboBox = new JComboBox<>(new String[]{"Reunion", "Actividad"});
        JLabel etiquetaLabel = new JLabel("Seleccione la etiqueta para filtrar:");

        // Botón para filtrar
        filtrarButton = new JButton("Filtrar");

        // Agregar componentes al panel
        panel.add(etiquetaLabel);
        panel.add(etiquetaComboBox);
        panel.add(filtrarButton);

        // Acción del botón de filtrar
        filtrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la etiqueta seleccionada
                String etiqueta = (String) etiquetaComboBox.getSelectedItem();

                // Obtener la lista de eventos filtrados por la etiqueta
                List<Object[]> eventosFiltrados = agenda.mostrarTodosLosEventos(etiqueta);

                // Mostrar la tabla con los eventos filtrados
                mostrarTablaEventos(eventosFiltrados);
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Método para mostrar la tabla de eventos filtrados
    private void mostrarTablaEventos(List<Object[]> eventosData) {
        // Encabezados de la tabla
        String[] columnNames = {"ID", "Nombre", "Descripción", "Etiqueta", "Hora", "Fecha"};

        // Crear el modelo de la tabla
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Llenar el modelo con los datos de los eventos
        for (Object[] eventoData : eventosData) {
            tableModel.addRow(eventoData);
        }

        // Crear la tabla con el modelo
        JTable tablaEventos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tablaEventos);

        // Crear un nuevo panel para mostrar la tabla
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BoxLayout(panelTabla, BoxLayout.Y_AXIS));
        panelTabla.add(scrollPane);

        // Mostrar la tabla en un cuadro de diálogo (o agregarla a la ventana actual)
        JFrame frame = new JFrame("Eventos por Etiqueta");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(panelTabla);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
