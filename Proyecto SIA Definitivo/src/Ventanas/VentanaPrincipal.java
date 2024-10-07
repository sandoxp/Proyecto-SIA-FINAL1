package Ventanas;

import Clases.Agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JButton registrarEventoButton;
    private JPanel panel1;
    private JButton visualizarEventosButton;
    private JButton modificarEventoButton;
    private JButton eliminarEventoButton;
    private JButton verTiempoRestanteButton;
    private JPanel panelContenedor; // Panel que contiene todas las tarjetas
    private CardLayout cardLayout; // CardLayout para cambiar entre tarjetas

    private String archivoCSV = "src/Resources/DatosGenerados.csv"; // Ruta del archivo CSV
    private Agenda agenda;

    public VentanaPrincipal(Agenda agenda) {
        // Inicializar el CardLayout y el panel contenedor
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);



        // Crear instancia de VentanaOpcionesMostrar
        VentanaOpcionesMostrar opcionesMostrar = new VentanaOpcionesMostrar(agenda);

        // Crear placeholders para cada funcionalidad
        JPanel panelRegistrarEvento = new JPanel(); // Verifica que no sea null
        panelRegistrarEvento.add(new JLabel("Formulario para registrar un evento."));

        JPanel panelModificarEvento = new JPanel();
        panelModificarEvento.add(new JLabel("Vista para modificar un evento."));

        JPanel panelEliminarEvento = new JPanel();
        panelEliminarEvento.add(new JLabel("Vista para eliminar un evento."));

        JPanel panelVerTiempoRestante = new JPanel();
        panelVerTiempoRestante.add(new JLabel("Vista para consultar el tiempo restante de un evento."));

        // Añadir los paneles al contenedor con identificadores
        panelContenedor.add(panel1, "PanelPrincipal");
        panelContenedor.add(opcionesMostrar.getPanel(), "PanelOpciones");
        panelContenedor.add(panelRegistrarEvento, "PanelRegistrarEvento");
        panelContenedor.add(panelModificarEvento, "PanelModificarEvento");
        panelContenedor.add(panelEliminarEvento, "PanelEliminarEvento");
        panelContenedor.add(panelVerTiempoRestante, "PanelVerTiempoRestante");

        // Acción para abrir la ventana de registro de eventos
        registrarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear e instanciar VentanaRegistrarEvento con la agenda y el archivo CSV actual
                new VentanaRegistrarEvento(agenda, archivoCSV);
            }
        });

        // Acción para cambiar a la vista de opciones para visualizar eventos
        visualizarEventosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva ventana para las opciones de visualización
                JFrame frame = new JFrame("Opciones de Visualización");

                // Asegurarse de que solo cierre esta ventana y no la aplicación completa
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Crear una nueva instancia de VentanaOpcionesMostrar y agregarla al JFrame
                VentanaOpcionesMostrar opcionesMostrar = new VentanaOpcionesMostrar(agenda);

                frame.setContentPane(opcionesMostrar.getPanel());

                // Configurar y mostrar la ventana
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });


        // Acción para modificar un evento
        modificarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instanciar la ventana para modificar eventos
                new VentanaModificarEvento(agenda, archivoCSV);
            }
        });

        // Acción para abrir la ventana de eliminación de eventos
        eliminarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear e instanciar VentanaEliminarEvento con la agenda y el archivo CSV actual
                new VentanaEliminarEvento(agenda, archivoCSV);
            }
        });

        // Acción para ver tiempo restante de un evento
        verTiempoRestanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instanciar la ventana para visualizar el tiempo restante
                new VentanaVisualizarTiempoRestante(agenda);
            }
        });
    }

    public JPanel getPanelContenedor() {
        return panelContenedor;
    }

    public static void main(String[] args) {
        // Instancia de la agenda
        Agenda miAgenda = new Agenda("Mi Agenda");
        String archivoCSV = "Avance/DatosGenerados.csv";
        miAgenda.cargarEventosCSV(archivoCSV);

        // Crear la ventana principal y pasarle la agenda
        JFrame frame = new JFrame("VentanaPrincipal");
        frame.setContentPane(new VentanaPrincipal(miAgenda).getPanelContenedor());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


}