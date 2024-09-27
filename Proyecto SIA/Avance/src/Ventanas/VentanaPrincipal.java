package Ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal {
    private JButton registrarEventoButton;
    private JPanel panel1;
    private JButton visualizarEventoSButton;
    private JButton modificarEventoButton;
    private JButton eliminarEventoButton;
    private JButton verTiempoRestanteButton;
    private JPanel panelContenedor; // Panel que contiene todas las tarjetas
    private CardLayout cardLayout; // CardLayout para cambiar entre tarjetas

    public VentanaPrincipal() {
        // Inicializar el CardLayout y el panel contenedor
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Crear instancia de VentanaOpcionesMostrar
        VentanaOpcionesMostrar opcionesMostrar = new VentanaOpcionesMostrar();

        // Crear placeholders para cada funcionalidad
        JPanel panelRegistrarEvento = new JPanel();
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

        // Acción para cambiar a la vista de opciones para visualizar eventos
        visualizarEventoSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "PanelOpciones"); // Cambiar a la tarjeta de opciones
            }
        });

        // Acción para registrar un evento
        registrarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "PanelRegistrarEvento"); // Cambiar a la tarjeta de registrar evento
            }
        });

        // Acción para modificar un evento
        modificarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "PanelModificarEvento"); // Cambiar a la tarjeta de modificar evento
            }
        });

        // Acción para eliminar un evento
        eliminarEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "PanelEliminarEvento"); // Cambiar a la tarjeta de eliminar evento
            }
        });

        // Acción para ver tiempo restante de un evento
        verTiempoRestanteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "PanelVerTiempoRestante"); // Cambiar a la tarjeta de tiempo restante
            }
        });
    }

    public JPanel getPanelContenedor() {
        return panelContenedor;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana Principal");

        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        frame.setContentPane(ventanaPrincipal.getPanelContenedor());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }
}
