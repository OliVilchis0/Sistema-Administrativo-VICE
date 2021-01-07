package com.vice.inventario;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
public class Validacion {
	
	public static boolean esEntero(String str) {
		if(str == null)
			return false;
		else {
			try {
				int value = Integer.parseInt(str);
				return value < 0 ? false : true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	
	public static boolean esDouble(String str) {
		if(str == null) 
			return false;
		else {
			try {
				double value = Double.parseDouble(str);
				return value < 0 ? false : true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	
	public static boolean esFloat(String str) {
		if(str == null || str.isBlank())
			return false;
		else {
			try {
				double value = Float.parseFloat(str);
				return value < 0 ? false : true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
	}
	
	//LANZAR UNA VENTANA EMERGENTE
	public static void getAlert(AlertType type,String title, String header,String text) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		if(!header.equals(""))
			alert.setHeaderText(header);
		else
			alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
		
	//LAUCH WINDOW WITH TEXT FIELD FOR GET TEXT AND RETURN THIS
	public static String getInputText(String title,String header,String text) {
		String result = null;
		String localHeader = "";
		while(true) {
			TextInputDialog input = new TextInputDialog();
			input.setTitle(title);
			input.setHeaderText(localHeader);
			input.setContentText(text);
			input.initStyle(StageStyle.DECORATED);
			Optional<String> respuesta = input.showAndWait();
			
			if(respuesta.isEmpty())
				break;
			else if(respuesta.get().equals(""))
				localHeader = header;
			else {
				result = respuesta.get();
				break;
			}
		}
		
		return result;
	}
}
