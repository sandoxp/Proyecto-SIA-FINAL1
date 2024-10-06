package Ventanas;

import Clases.Agenda;
import Clases.Evento;
import Clases.FechaInvalidaException;
import Clases.HoraInvalidaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class VentanaRegistrarEvento {
    private Agenda agenda;
    private String archivoCSV; // Ruta del archivo CSV

    public VentanaRegistrarEvento(Agenda agenda, String archivoCSV) {
        this.agenda = agenda;
        this.archivoCSV = archivoCSV;
        crearVentana();
    }

    private void crearVentana() {
        // Crear el frame de la ventana
        JFrame frame = new JFrame("Registrar Nuevo Evento");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel principal para ingresar datos del evento
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(6, 2, 10, 10));

        // Componentes para ingresar los detalles del evento
        JLabel labelNombre = new JLabel("Nombre del Evento:");
        JTextField campoNombre = new JTextField();

        JLabel labelDescripcion = new JLabel("Descripción del Evento:");
        JTextField campoDescripcion = new JTextField();

        JLabel labelEtiqueta = new JLabel("Etiqueta:");
        JComboBox<String> comboBoxEtiqueta = new JComboBox<>(new String[]{"Reunión", "Actividad"});

        JLabel labelHora = new JLabel("Hora (HH:mm):");
        JTextField campoHora = new JTextField();

        JLabel labelFecha = new JLabel("Fecha (YYYY-MM-DD):");
        JTextField campoFecha = new JTextField();

        JButton botonRegistrar = new JButton("Registrar");

        // Añadir componentes al panel principal
        panelPrincipal.add(labelNombre);
        panelPrincipal.add(campoNombre);
        panelPrincipal.add(labelDescripcion);
        panelPrincipal.add(campoDescripcion);
        panelPrincipal.add(labelEtiqueta);
        panelPrincipal.add(comboBoxEtiqueta);
        panelPrincipal.add(labelHora);
        panelPrincipal.add(campoHora);
        panelPrincipal.add(labelFecha);
        panelPrincipal.add(campoFecha);
        panelPrincipal.add(new JLabel()); // Espacio vacío
        panelPrincipal.add(botonRegistrar);

        // Añadir panel al frame
        frame.add(panelPrincipal, BorderLayout.CENTER);

        // Acción del botón "Registrar"
        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos ingresados
                String nombre = campoNombre.getText().trim();
                String descripcion = campoDescripcion.getText().trim();
                String etiqueta = (String) comboBoxEtiqueta.getSelectedItem();
                String hora = campoHora.getText().trim();
                String fecha = campoFecha.getText().trim();

                try {
                    // Verificar campos vacíos
                    if (nombre.isEmpty() || descripcion.isEmpty() || hora.isEmpty() || fecha.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Todos los campos deben ser llenados.", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Detener el proceso si hay campos vacíos
                    }

                    // Validar la hora
                    validarHora(hora);

                    // Validar la fecha
                    validarFecha(fecha);

                    // Crear y agregar evento a la agenda
                    Evento nuevoEvento = new Evento(nombre, descripcion, etiqueta, hora, fecha);
                    agenda.agregarEvento(fecha, nuevoEvento);

                    // Guardar los cambios en el archivo CSV
                    agenda.guardarEventosCSV(archivoCSV);

                    // Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(frame, "Evento registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar campos después de registrar
                    campoNombre.setText("");
                    campoDescripcion.setText("");
                    campoHora.setText("");
                    campoFecha.setText("");
                } catch (HoraInvalidaException ex) {
                    // Manejar errores de hora inválida
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Hora", JOptionPane.ERROR_MESSAGE);
                } catch (FechaInvalidaException ex) {
                    // Manejar errores de fecha inválida
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error de Fecha", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    // Manejar otros errores generales
                    JOptionPane.showMessageDialog(frame, "Error al registrar el evento. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Configuración final de la ventana
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Métodos de validación de hora y fecha
    private void validarHora(String hora) throws HoraInvalidaException {
        try {
            // Intentar parsear la hora
            LocalTime.parse(hora);
        } catch (DateTimeParseException e) {
            // Si el formato de la hora es incorrecto, lanzar una excepción personalizada.
            throw new HoraInvalidaException("Formato de hora incorrecto. Use el formato HH:mm (ej. 14:30).");
        }
    }

    private void validarFecha(String fecha) throws FechaInvalidaException {
        try {
            LocalDate parsedFecha = LocalDate.parse(fecha);
            if (parsedFecha.isBefore(LocalDate.now())) {
                throw new FechaInvalidaException("La fecha no puede ser anterior a hoy.");
            }
        } catch (DateTimeParseException e) {
            // Si el formato de la fecha es incorrecto, lanzar una excepción personalizada.
            throw new FechaInvalidaException("Formato de fecha incorrecto. Use el formato YYYY-MM-DD.");
        }
    }
}
