package Restaurante;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringJoiner;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
public class CReservas {
String nombre;
String horaHorario;
String fecha;
String restaurante;

public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getHoraHorario() {
	return horaHorario;
}
public void setHoraHorario(String horaHorario) {
	this.horaHorario = horaHorario;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getRestaurante() {
	return restaurante;
}
public void setRestaurante(String restaurante) {
	this.restaurante = restaurante;
}
public void leerRestaurantes(JComboBox comboRestaurantes) {
	
	try {
		/*Lee el archivo txt donde tenemos almacenados todos los restaurantes*/
		BufferedReader br = new BufferedReader(new FileReader("Restaurantes.txt"));
		String linea;
		
		while((linea = br.readLine()) != null) {
			StringTokenizer tonkens = new StringTokenizer(linea,",");
			 
			while(tonkens.hasMoreTokens()) {
				comboRestaurantes.addItem(tonkens.nextToken());
			}
		}
		br.close();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Ocurrio un problema");
	}
}

public void agregarRegistrosHorarios() {
	try {
		/* FileWrite escribe cadenas de caracteres en archivos de texto que se puedan leer*/
		FileWriter fw = new FileWriter("Reservas.txt",true);
		
		/*write es una clase que se usa para hacer escrituras utilizando caracteres.*/
		fw.write(getNombre());
		fw.write(",");
		fw.write(getRestaurante());
		fw.write(",");
		fw.write(getHoraHorario());
		fw.write(",");
		fw.write(getFecha());
		fw.write("\n");
		fw.close();
		
		JOptionPane.showMessageDialog(null,"Se registro correctamente");
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Ocurrio un error al registrar" + e.toString());
	}
}

public void MostrarTotalHorarios(JTable tablaTotalHorarios) {
	
	String nombreArchivo = "Reservas.txt";
	
	File file = new File(nombreArchivo);
	
	try {
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		String primeraLinea = br.readLine().trim();
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Nombre");
		model.addColumn("Restaurante");
		model.addColumn("Hora");
		model.addColumn("Fecha");
		
		
		tablaTotalHorarios.setModel(model);
		
		Object[] tableLines = br.lines().toArray();
		
		for (int i = 0; i < tableLines.length; i++) {
			
			String line = tableLines[i].toString().trim();
			String[] datarow= line.split(",");
			model.addRow(datarow);
			tablaTotalHorarios.setModel(model);
		}
		
		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Ocurrio un error"+ e.toString());
		
	}
}


public void seleccionarHorarios(JTable tablaHorarios) {
	
	try {
		
		int  fila = tablaHorarios.getSelectedRow();
		
		if (fila>=0) {
			
			setNombre(tablaHorarios.getValueAt(fila, 0).toString());
			setRestaurante(tablaHorarios.getValueAt(fila, 1).toString());
			setHoraHorario(tablaHorarios.getValueAt(fila, 2).toString());
			setFecha(tablaHorarios.getValueAt(fila, 3).toString());
		}
		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Ocurrio un error"+ e.toString());
	}
	
}


public void EliminarHorarios (JTable tablaHorarios, JTextField codigoHorario) {
	
	//Eliminacion visual de la tabla
	DefaultTableModel model = (DefaultTableModel)tablaHorarios.getModel();
	
	for (int i = 0; i < model.getRowCount(); i++) {
		
		if(((String)model.getValueAt(i, 0)).equals(codigoHorario.getText())) {	
			model.removeRow(i);
			break;
			
		}
	}
	//Limpieza del archivo .txt
	
	try {
		PrintWriter writer = new PrintWriter("Reservas.txt");
		writer.print("");
		writer.close();
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null,"Ocurri  un problema"+ e.toString());
	}
	
	//Creaci n de los nuevos registros luego de la eliminaci n
	
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Reservas.txt")))) {
		StringJoiner joiner = new StringJoiner(",");
		
		for (int col = 0; col < tablaHorarios.getColumnCount(); col++) {
			joiner.add(tablaHorarios.getColumnName(col));
		}
		
		System.out.println(joiner.toString());
		bw.write(joiner.toString());
		bw.newLine();
		
		for (int row = 0; row < tablaHorarios.getRowCount(); row++) {
			 joiner = new StringJoiner(",");		
			for (int col = 0; col < tablaHorarios.getColumnCount(); col++) {
				
				Object obj = tablaHorarios.getValueAt(row, col);
				String value = obj == null ? "null" :obj.toString();
				joiner.add(value);
				
			}
			
			
			bw.write(joiner.toString());
			bw.newLine();
			JOptionPane.showMessageDialog(null, "Se elimino  correctamente");
		}

		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, "Ocurrio un error");
	}
	
	
	
}

}
