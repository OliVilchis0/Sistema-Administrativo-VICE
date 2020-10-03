package com.vice.inventario.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vice.inventario.Validacion;
import com.vice.inventario.model.Corte;
import com.vice.inventario.model.Producto;
import com.vice.inventario.model.Talla;
import com.vice.inventario.service.CorteServiceImpl;
import com.vice.inventario.service.ProductoServiceImpl;
import com.vice.inventario.service.TallaServiceImpl;
import com.vice.inventario.view.CorteView;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;

@Controller
public class CorteController {
	
	@Autowired private CorteView view;
	@Autowired private CorteServiceImpl serviceCorte;
	@Autowired private ProductoServiceImpl serviceProducto;
	@Autowired private TallaServiceImpl serviceTalla;
	private Producto producto;
	private Talla talla;
	private Corte corte;
	
	public void setView(CorteView view) {
		this.view = view;
	}
	
	public void addCorte(ActionEvent evt) {
		producto = view.cbProducto.getSelectionModel().getSelectedItem();
		talla = view.cbTalla.getSelectionModel().getSelectedItem();
		String numPlayeras = view.txtNumPlayeras.getText();
		
		if(producto == null || talla == null || numPlayeras == null)
			Validacion.getAlert(AlertType.INFORMATION,"Informacion", "", "Deber ingresar todos los campos");
		else {
			int totalPlayeras = Integer.parseInt(numPlayeras);
			corte = Corte.builder()
					.fecha(new Date())
					.numPlayeras(totalPlayeras)
					.relacion(totalPlayeras/producto.getPeso())
					.producto(producto)
					.talla(talla)
					.build();
			serviceCorte.create(corte);
			view.corteList.add(corte);
			view.cbProducto.getItems().remove(producto);
		}
	}
	
	public void deleteCorte(ActionEvent evt){
		List<Corte> list = view.table.getSelectionModel().getSelectedItems();
		List<Corte> newList = serviceCorte.deleteAll(list);
		if(newList != null) 
			view.corteList.removeAll(newList);
	}
	
	public void updateRow() {
		//FULL COMBOBOX TALLA IN THE TABLE
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==1)view.tallaList.add(x);});
		//UPDATE COLUMN NUMPLAYERAS
		view.columnPlayeras.setOnEditCommit((CellEditEvent<Corte,Integer> evt)->{
			TablePosition<Corte,Integer> pos = evt.getTablePosition();
			corte = evt.getTableView().getItems().get(pos.getRow());
			corte.setNumPlayeras(evt.getNewValue());
			corte.setRelacion(corte.getNumPlayeras()/corte.getProducto().getPeso());
			serviceCorte.Update(corte);
			view.table.refresh();
		});
		
		//UPDATE COLUMN TALLA
		view.columnTalla.setOnEditCommit((CellEditEvent<Corte,Talla> evt)->{
			TablePosition<Corte,Talla> pos = evt.getTablePosition();
			corte = evt.getTableView().getItems().get(pos.getRow());
			corte.setTalla(evt.getNewValue());
			serviceCorte.Update(corte);
		});
	}

	public void init() {
		//EVENT FOR ADD NEW ROW
		view.btnAdd.setOnAction(this::addCorte);
		//DELETE ONE OR MORE ROWS IN THE TABLE
		view.eliminarItem.setOnAction(this::deleteCorte);
		//UPDATE A ROW OF THE TABLE
		updateRow();
		//FULL THE COMBOBOX TALLA AND PRODUCTO
		serviceProducto.getAll().stream().forEach(x->{if(x.getStatus()==1)view.cbProducto.getItems().add(x);});
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==1)view.cbTalla.getItems().add(x);});
		//FULL EACH ROW IN THE TABLE
		serviceCorte.getAll().stream().forEach(x->{if(x.getStatus() == 1)view.corteList.add(x);});
	}
}
