package Restaurante;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import Restaurante.CReservas;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class GestionReservas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txthorasHorario;
	private JTextField txtFecha;
	private JTable tbListaHorarios;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GestionReservas dialog = new GestionReservas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GestionReservas() {
		/*Tamaño pantalla*/
		setBounds(100, 100, 828, 700);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 255, 255), new Color(160, 160, 160)), "Datos Registro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(10, 45, 239, 239);
			contentPanel.add(panel);
			{
				txtNombre = new JTextField();
				txtNombre.setColumns(10);
				txtNombre.setBounds(132, 24, 86, 20);
				panel.add(txtNombre);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Nombre");
				lblNewLabel_3.setBounds(10, 27, 46, 14);
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Restaurantes");
				lblNewLabel_4.setBounds(10, 68, 112, 14);
				panel.add(lblNewLabel_4);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("Hora");
				lblNewLabel_5.setBounds(10, 106, 112, 14);
				panel.add(lblNewLabel_5);
			}
			{
				txthorasHorario = new JTextField();
				txthorasHorario.setColumns(10);
				txthorasHorario.setBounds(132, 103, 86, 20);
				panel.add(txthorasHorario);
			}
			{
				JLabel lblDiaSemana = new JLabel("Fecha");
				lblDiaSemana.setBounds(10, 146, 86, 14);
				panel.add(lblDiaSemana);
			}
			{
				// Instanciar Componente
				JDateChooser calendar = new JDateChooser();
				// Ubicar y agregar al panel
				calendar.setBounds(132, 140, 86, 20);
				
				
			
				panel.add(calendar);
				
			}
			{
				txtFecha = new JTextField();
				txtFecha.setColumns(10);
				txtFecha.setBounds(10, 180, 86, 20);
				panel.add(txtFecha);
			}
			
			JComboBox cbRestaurantes = new JComboBox();
			cbRestaurantes.setBounds(132, 64, 86, 22);
			panel.add(cbRestaurantes);
			Restaurante.CReservas objetoHorarios = new Restaurante.CReservas();
			objetoHorarios.leerRestaurantes(cbRestaurantes);
			{
				JButton btnGuardar = new JButton("Guardar");
				btnGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Restaurante.CReservas objetoHorarios = new Restaurante.CReservas();
						objetoHorarios.setNombre(txtNombre.getText());
						objetoHorarios.setRestaurante(cbRestaurantes.getSelectedItem().toString());
						objetoHorarios.setHoraHorario(txthorasHorario.getText());
						objetoHorarios.setFecha(txtFecha.getText());
					
						objetoHorarios.agregarRegistrosHorarios();
						
					}
				});
				btnGuardar.setBounds(10, 200, 99, 23);
				panel.add(btnGuardar);
			}
			{
				JButton btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Restaurante.CReservas objetoHorarios = new Restaurante.CReservas();
						objetoHorarios.EliminarHorarios(tbListaHorarios, txthorasHorario);
					}
				});
				btnEliminar.setBounds(100, 200, 99, 23);
				panel.add(btnEliminar);
			}
			/*{
				JButton btnRegistrarFecha = new JButton("Registrar");
				btnRegistrarFecha.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						
					}
				});
				btnRegistrarFecha.setBounds(100, 200, 99, 23);
				panel.add(btnRegistrarFecha);
			}*/
			
			
			
			
		
			
		}
		{
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Lista de Registros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(248, 45, 354, 239);
			contentPanel.add(panel_1);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 21, 334, 207);
				panel_1.add(scrollPane);
				{
					tbListaHorarios = new JTable();
					scrollPane.setViewportView(tbListaHorarios);
				}
			}
		}
		
		{
			JButton btnMostrarHorarios = new JButton("Mostrar Horarios");
			btnMostrarHorarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Restaurante.CReservas objetoHorarios = new Restaurante.CReservas();
					objetoHorarios.MostrarTotalHorarios(tbListaHorarios);
				}
			});
			btnMostrarHorarios.setBounds(259, 11, 113, 23);
			contentPanel.add(btnMostrarHorarios);
		}
		{
			JButton btnSeleccionarHorarios = new JButton("Seleccionar");
			btnSeleccionarHorarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Restaurante.CReservas objetoHorarios = new Restaurante.CReservas();
					objetoHorarios.seleccionarHorarios(tbListaHorarios);
					txtNombre.setText(objetoHorarios.getNombre());
					txthorasHorario.setText(objetoHorarios.getHoraHorario());
					txtFecha.setText(objetoHorarios.getFecha());
				}
			});
			btnSeleccionarHorarios.setBounds(513, 11, 89, 23);
			contentPanel.add(btnSeleccionarHorarios);
		}
	}

}
