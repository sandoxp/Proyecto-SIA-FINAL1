package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaOpcionesMostrar {
    private JButton visualizarTodosLosEventosButton;
    private JPanel panel1;
    private JButton visualizarEventosPorFechaButton;
    private JButton visualizarEventosPorEtiquetaButton;
    private JButton visualizarEventosEnSemanaButton;
    private JButton visualizarEventosEnMesButton;
    private JButton visualizarEventosPorFechayEtiquetaButton;

    public VentanaOpcionesMostrar() {
        // Acción para visualizar todos los eventos
        visualizarTodosLosEventosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, "Visualizar todos los eventos.");
            }
        });

        // Acción para visualizar eventos por fecha
        visualizarEventosPorFechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, "Visualizar eventos por fecha.");
            }
        });

        // Acción para visualizar eventos por etiqueta
        visualizarEventosPorEtiquetaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, "Visualizar eventos por etiqueta.");
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
        visualizarEventosEnSemanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel1, "Visualizar eventos en la semana.");
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
