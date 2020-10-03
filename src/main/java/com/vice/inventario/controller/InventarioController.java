package com.vice.inventario.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vice.inventario.Validacion;
import com.vice.inventario.model.Color;
import com.vice.inventario.model.Inventario;
import com.vice.inventario.model.Talla;
import com.vice.inventario.service.ColorServiceImpl;
import com.vice.inventario.service.InventarioServiceImpl;
import com.vice.inventario.service.TallaServiceImpl;
import com.vice.inventario.view.InventarioView;

import javafx.event.ActionEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

@Controller
public class InventarioController {
	
	@Autowired private InventarioView view;
	@Autowired private InventarioServiceImpl serviceInv;
	@Autowired private ColorServiceImpl serviceColor;
	@Autowired private TallaServiceImpl serviceTalla;
	private Inventario inventario;
	private Talla talla;
	private Color color;
	
	//ADD NEW ROW IN THE TABLE INVENTARIO
	public void addNewInventario(ActionEvent evt) {
		//GET THE OBJECT TALLA 
		talla = view.cbTalla.getSelectionModel().getSelectedItem();
		//GET THE OBJECT COLOR
		color = view.cbColor.getSelectionModel().getSelectedItem();
		//GET THE VALUE OF TEXT FIELD CANTIDAD
		String cantidad = view.txtCantidad.getText();
		boolean radioButton = true; 
		
		if(view.rbBueno.isSelected() == false && view.rbMalo.isSelected() == false)
			radioButton = false;
		
		if(color == null || talla == null || cantidad == null || radioButton == false)
		Validacion.getAlert(AlertType.INFORMATION, "Informacion", "", "Debes ingresar todos los campos");
		else {
			inventario = Inventario.builder()
					.talla(talla)
					.color(color)
					.condicion(view.rbBueno.isSelected() ? (byte)1 : (byte)2)
					.total(Integer.parseInt(cantidad))
					.build();
			
			Inventario inv = serviceInv.existe(inventario);
			
			if(inv == null) {
				serviceInv.create(inventario);
				view.inventarioList.add(inventario);
			}else {
				inv.setTotal(inv.getTotal()+inventario.getTotal());
				serviceInv.Update(inv);
				view.inventarioList.stream().forEach(x->{if(x.getId()==inv.getId())x.setTotal(inv.getTotal());});
				view.table.refresh();
			}
		}
	}
	
	public void deleteRow(ActionEvent evt) {
		inventario = view.table.getSelectionModel().getSelectedItem();
		if(inventario != null) {
			serviceInv.delete(inventario.getId());
			view.inventarioList.remove(inventario);
		}
	}
	
	public void sumTShirt(ActionEvent evt) {
		inventario = view.table.getSelectionModel().getSelectedItem();
		if(inventario != null) {
			int numPlayeras = getNumberShirt("Cantidad de playeras para agregar");
			if(numPlayeras != 0) {
				inventario.setTotal(inventario.getTotal()+numPlayeras);
				serviceInv.Update(inventario);
				view.table.refresh();
			}
		}
	}
	
	public void removeTShirt(ActionEvent evt) {
		inventario = view.table.getSelectionModel().getSelectedItem();
		if(inventario != null) {
			int numPlayeras = getNumberShirt("Cantidad de playeras para retirar");
			if(numPlayeras != 0) {
				if(numPlayeras > inventario.getTotal())
					Validacion.getAlert(
							AlertType.INFORMATION,
							"Informacion", "", 
							"La cantida a retirar es mayor que la existente");
				else {
					inventario.setTotal(inventario.getTotal() - numPlayeras);
					serviceInv.Update(inventario);
					view.table.refresh();
				}
					
			}
		}
	}
	
	//LAUNCH WINDOW WITH TEXT FIELD FOR GET THE NUMBER OF NEW T-SHIRT
	public int getNumberShirt(String text) {
		int value = 0;
		String head = "";
		while(true) {
			TextInputDialog input = new TextInputDialog();
			input.setTitle("Numero de playeras");
			input.setHeaderText(head);
			input.setContentText(text);
			input.initStyle(StageStyle.DECORATED);
			Optional<String> respuesta = input.showAndWait();
			if(respuesta.isEmpty())
				break;
			else if(respuesta.get().equals("")) 
				head = "Ingresa un valor real!";
			else {
				try {
					value = Integer.parseInt(respuesta.get());
					if(value < 0) 
						head = "Ingresa numeros positivos";
					else
						break;
				} catch (NumberFormatException e) {
					head = "Ingresa valores numericos";
				}
			}
		}
		return value;
	}
	
	public void setView(InventarioView view) {
		this.view = view;
	}
	//INITIALIZE VALUES
	public void init() {
		//LIST ALL ROWS IN THE TABLE
		serviceInv.getAll().stream().forEach(x->view.inventarioList.add(x));
		//SUM NEW T-SHIT
		view.addItem.setOnAction(this::sumTShirt);
		//REMOVE T-SHIT
		view.removeItem.setOnAction(this::removeTShirt);
		//DELETE ROW 
		view.deleteItem.setOnAction(this::deleteRow);
		//CREATE A NEW EVENT FOR ADD MORE REGISTRIES
		view.btnNew.setOnAction(this::addNewInventario);
		//ADD TO COMBOBOX TALLA ALL OF DATA 
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==1)view.cbTalla.getItems().add(x);});
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==1)view.tallaList.add(x);});
		//ADD TO COMBOBOX COLOR ALL OF DATA
		serviceColor.getAll().stream().forEach(x->{if(x.getStatus()==1)view.cbColor.getItems().add(x);});
		serviceColor.getAll().stream().forEach(x->{if(x.getStatus()==1)view.colorList.add(x);});
	}

}
