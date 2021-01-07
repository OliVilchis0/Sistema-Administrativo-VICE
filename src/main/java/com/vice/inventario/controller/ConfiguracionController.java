package com.vice.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vice.inventario.model.Color;
import com.vice.inventario.model.Talla;
import com.vice.inventario.model.TipoProducto;
import com.vice.inventario.service.ColorServiceImpl;
import com.vice.inventario.service.TallaServiceImpl;
import com.vice.inventario.service.TipoProductoServiceImpl;
import com.vice.inventario.view.ConfiguracionesView;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Setter;

@Controller
@Setter 
public class ConfiguracionController {
	
	@Autowired private ColorServiceImpl serviceColor;
	@Autowired private TallaServiceImpl serviceTalla;
	@Autowired private TipoProductoServiceImpl serviceTipoPro;
	@Autowired private ConfiguracionesView view; 
	
	//ELIMINAR UNO O MAS REGISTRO DE LA TABLA COLOR
	public void ContextMenuColor(ActionEvent evt) {
		List<Color> colores = view.tbColor.getSelectionModel().getSelectedItems();
		List<Color> coloresNuevos = serviceColor.deleteAll(colores); 
		if(coloresNuevos != null) {
			view.datosColor.removeAll(colores);
			view.lbColor.setText(
					serviceColor.getAll().stream().filter(x->x.getStatus()==1).count()+" COLORES EN TOTAL");
		}
			
	}
	
	//ELIMINAR UNO O MAS REGISTRO DE LA TABLA TALLA
	public void ContextMenuTalla(ActionEvent evt) {
		List<Talla> tallas = view.tbTalla.getSelectionModel().getSelectedItems();
		List<Talla> tallasNuevos = serviceTalla.deleteAll(tallas); 
		if(tallasNuevos != null) {
			view.datosTalla.removeAll(tallas);
			view.lbTalla.setText(serviceTalla.getAll()
					.stream().filter(x->x.getStatus()==1).count()+" TALLAS EN TOTAL");
		}
	}
	
	//ELIMINAR UNO O MAS REGISTRO DE LA TABLA DIAMETRO
	public void ContextMenuDiametro(ActionEvent evt) {
		List<Talla> tallas = view.tbDiametro.getSelectionModel().getSelectedItems();
		List<Talla> tallasNuevos = serviceTalla.deleteAll(tallas); 
		if(tallasNuevos != null) {
			view.datosDiametro.removeAll(tallas);
			view.lbDiametro.setText(serviceTalla.getAll()
					.stream().filter(x->x.getStatus()==2).count()+" DIAMETROS EN TOTAL");
		}
	}
	
	//ELIMINAR UNO O MAS REGISTRO DE LA TABLA TIPO PRODUCTO
	public void ContextMenuTipoPro(ActionEvent evt) {
		List<TipoProducto> tiposPro = view.tbTipoPro.getSelectionModel().getSelectedItems();
		List<TipoProducto> tiposProNuevos = serviceTipoPro.deleteAll(tiposPro); 
		if(tiposProNuevos != null) {
			view.datosTipoPro.removeAll(tiposPro);
			view.lbTipoPro.setText(serviceTipoPro.getAll()
					.stream().filter(x->x.getStatus()==1).count()+" TIPOS DE PRODUCTOS EN TOTAL");
		}
	}
	
	//AGREGAR UN REGISTRO DE LAS DIFERENTES CONFIGURACIONES
	public void clickCajas(KeyEvent evt) {
		//AL PRESIONAR ENTER EN CUALQUIERA DE LAS CAJAS DE TEXTO
		if(evt.getCode() == KeyCode.ENTER) {
			//SABER CUAL CAJA DE TEXTO SE PRESIONO ENTER Y VERIFICAR QUE HAIGA TEXTO 
			if(evt.getSource() == view.txtcolor) {
				if(view.txtcolor.getText().equals(""))
					this.getAlert(AlertType.INFORMATION, "Informacion","","Debes ingresar un color!");
				else {
					Color color = serviceColor.getColor(view.txtcolor.getText().toUpperCase());
					if(color == null) {
						Color c = Color.builder().nombre(view.txtcolor.getText().toUpperCase()).build();
						serviceColor.create(c);
						view.datosColor.add(serviceColor.getColor(c.getNombre()));
					}else if(color.getStatus() == 0) {
						color.setStatus((byte)1);
						serviceColor.Update(color);
						view.datosColor.add(color);
					}else 
						this.getAlert(AlertType.INFORMATION, "Informacion","","Este color ya existe!");
					view.txtcolor.setText(null);
					view.txtcolor.setFocusTraversable(true);
					view.lbColor.setText(serviceColor.getAll()
							.stream().filter(x->x.getStatus()==1).count()+" COLORES EN TOTAL");
				}
			}else if(evt.getSource() == view.txttalla){
				if(view.txttalla.getText().equals(""))
					this.getAlert(AlertType.INFORMATION, "Informacion","","Debes ingresar una talla!");
				else {
					Talla talladb = serviceTalla.getTalla(view.txttalla.getText().toUpperCase());
					if(talladb == null) {
						Talla talla = Talla.builder().nombre(view.txttalla.getText().toUpperCase()).status((byte)1).build();
						serviceTalla.create(talla);
						
						view.datosTalla.add(serviceTalla.getTalla(talla.getNombre()));
					}else if(talladb.getStatus() == 0){
						talladb.setStatus((byte)1);
						serviceTalla.Update(talladb);
						view.datosTalla.add(talladb);
					}else
						this.getAlert(AlertType.INFORMATION, "Informacion","","Esta talla ya existe!");
					view.txttalla.setText(null);
					view.txttalla.setFocusTraversable(true);
					view.lbTalla.setText(serviceTalla.getAll()
							.stream().filter(x->x.getStatus()==1).count()+" TALLAS EN TOTAL");
				}
					
			}else if(evt.getSource() == view.txtdiametro) {
				if(view.txtdiametro.getText().equals(""))
					this.getAlert(AlertType.INFORMATION, "Informacion","","Debes ingresar un diametro!");
				else {
					Talla talladb = serviceTalla.getTalla(view.txtdiametro.getText().toUpperCase());
					if(talladb == null) {
						Talla talla = Talla.builder().nombre(view.txtdiametro.getText().toUpperCase()).status((byte)2).build();
						serviceTalla.create(talla);
						view.datosDiametro.add(serviceTalla.getTalla(talla.getNombre()));
					}else if(talladb.getStatus() == 0) {
						talladb.setStatus((byte)1);
						serviceTalla.Update(talladb);
						view.datosDiametro.add(talladb);
					}else
						this.getAlert(AlertType.INFORMATION, "Informacion","","Este diametro ya existe!");
					view.txtdiametro.setText(null);
					view.txtdiametro.setFocusTraversable(true);
					view.lbDiametro.setText(serviceTalla.getAll()
							.stream().filter(x->x.getStatus()==2).count()+" DIAMETROS EN TOTAL");
				}
			}else {
				if(view.txttipoPro.getText().equals(""))
					this.getAlert(AlertType.INFORMATION, "Informacion","","Debes ingresar un tipo de producto!");
				else{
					TipoProducto tpdb = serviceTipoPro.getTipoProducto(view.txttipoPro.getText().toUpperCase());
					if(tpdb == null) {
						TipoProducto tp = TipoProducto.builder().nombre(view.txttipoPro.getText().toUpperCase()).build();
						serviceTipoPro.create(tp);
						view.datosTipoPro.add(serviceTipoPro.getTipoProducto(tp.getNombre()));
					}else if(tpdb.getStatus() == 0){
						tpdb.setStatus((byte)1);
						serviceTipoPro.Update(tpdb);
						view.datosTipoPro.add(tpdb);
					}else 
						this.getAlert(AlertType.INFORMATION, "Informacion","","Este tipo de producto ya existe!");
					view.txttipoPro.setText(null);
					view.txttipoPro.setFocusTraversable(true);
					view.lbTipoPro.setText(serviceTipoPro.getAll()
							.stream().filter(x->x.getStatus()==1).count()+" TIPOS DE PRODUCTOS EN TOTAL");
				}
					
			}
		}
			
	}
	
	//LANZAR UNA VENTANA EMERGENTE
	public void getAlert(AlertType type,String title, String header,String text) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		if(!header.equals(""))
			alert.setHeaderText(header);
		else
			alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	//EDITAR EL NOMBRE DE LAS DIFERENTES TABLAS
	public void eventTables() {
		//EDITAR LA COLUMNA NOMBRE DE LA TABLA COLOR
		view.nombreColor.setOnEditCommit((CellEditEvent<Color,String> evt) ->{
			TablePosition<Color,String> pos = evt.getTablePosition();
			Color c = evt.getTableView().getItems().get(pos.getRow());
			c.setNombre(evt.getNewValue());
			serviceColor.Update(c);
		}); 
		//EDITAR LA COLUMNA NOMBRE DE LA TABLA TALLA
		view.nombreTalla.setOnEditCommit((CellEditEvent<Talla,String> evt) -> {
			TablePosition<Talla,String> pos = evt.getTablePosition();
			Talla talla = evt.getTableView().getItems().get(pos.getRow());
			talla.setNombre(evt.getNewValue());
			serviceTalla.Update(talla);
		});
		//EDITAR LA COLUMNA NOMBRE DE LA TABLA DIAMETRO
		view.nombreDiametro.setOnEditCommit((CellEditEvent<Talla,String> evt) -> {
			TablePosition<Talla,String> pos = evt.getTablePosition();
			Talla talla = evt.getTableView().getItems().get(pos.getRow());
			talla.setNombre(evt.getNewValue());
			serviceTalla.Update(talla);
		});
		//EDITAR LA COLUMNA NOMBRE DE LA TABLA TALLA
		view.nombreTipoPro.setOnEditCommit((CellEditEvent<TipoProducto,String> evt) -> {
			TablePosition<TipoProducto,String> pos = evt.getTablePosition();
			TipoProducto tp = evt.getTableView().getItems().get(pos.getRow());
			tp.setNombre(evt.getNewValue());
			serviceTipoPro.Update(tp);
		});
	}
	
	public void init() {
		
		//ESTABLECER LOS EVENTOS A LAS CAJAS DE TEXTOS
		view.txtcolor.setOnKeyReleased(this::clickCajas);
		view.txttalla.setOnKeyReleased(this::clickCajas);
		view.txtdiametro.setOnKeyReleased(this::clickCajas);
		view.txttipoPro.setOnKeyReleased(this::clickCajas);
		
		//ESTABLECER ACTIONEVENT A LOS CONTEXT MENU
		view.itemColor.setOnAction(this::ContextMenuColor);
		view.itemTalla.setOnAction(this::ContextMenuTalla);
		view.itemDiametro.setOnAction(this::ContextMenuDiametro);
		view.itemTipoPro.setOnAction(this::ContextMenuTipoPro);
		
		//ESTABLECER EL EVENTO PARA EDITAR LAS TABLAS
		this.eventTables();
		//view.nombreColor.setOnEditCommit(this::editTableColor);
		
		//ESTABLESER EL TOTAL DE CADA PRODUCTO EN SUS RESPECTIVAS ETIQUETAS
		view.lbColor.setText(serviceColor.getAll()
				.stream().filter(x->x.getStatus()==1).count()+" COLORES EN TOTAL");
		view.lbTalla.setText(serviceTalla.getAll()
				.stream().filter(x->x.getStatus()==1).count()+" TALLAS EN TOTAL");
		view.lbDiametro.setText(serviceTalla.getAll()
				.stream().filter(x->x.getStatus()==2).count()+" DIAMETROS EN TOTAL");
		view.lbTipoPro.setText(serviceTipoPro.getAll()
				.stream().filter(x->x.getStatus()==1).count()+" TIPOS DE PRODUCTOS EN TOTAL");
		
		//LLENAR LAS TABLAS DE COLOR,TALLA,DIAMETRO,TIPO PRODUCTO
		serviceColor.getAll().stream().forEach(x->{if(x.getStatus()==1)view.datosColor.add(x);});
		serviceTalla.getAll().stream().forEach(x -> {if(x.getStatus() == (byte)1)view.datosTalla.add(x);});
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus() == 2)view.datosDiametro.add(x);});
		serviceTipoPro.getAll().stream().forEach(x -> view.datosTipoPro.add(x));
	}
	
}
