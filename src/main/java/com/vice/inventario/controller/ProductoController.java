package com.vice.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vice.inventario.Validacion;
import com.vice.inventario.model.Color;
import com.vice.inventario.model.Producto;
import com.vice.inventario.model.Talla;
import com.vice.inventario.model.TipoProducto;
import com.vice.inventario.service.ColorServiceImpl;
import com.vice.inventario.service.ProductoServiceImpl;
import com.vice.inventario.service.TallaServiceImpl;
import com.vice.inventario.service.TipoProductoServiceImpl;
import com.vice.inventario.view.ProductoView;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import lombok.Setter;

@Controller
@Setter
public class ProductoController {
	@Autowired private ProductoView view;
	@Autowired private ProductoServiceImpl serviceProduct;
	@Autowired private ColorServiceImpl serviceColor;
	@Autowired private TallaServiceImpl serviceTalla;
	@Autowired private TipoProductoServiceImpl serviceTipoPro;
	private Producto producto;
	
	public void datosTablaCombobox() {
		//DATOS DEL COMBOBOX TIPO PRODUCTO
		serviceTipoPro.getAll().stream().forEach(x->{if(x.getStatus()==1)view.tipoProList.add(x);});
		serviceTipoPro.getAll().stream().forEach(x->{if(x.getStatus()==1)view.cbProducto.getItems().add(x);});
		//DATOS DEL COMBOBOX TALLA
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==1)view.tallaList.add(x);});
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==1)view.cbTalla.getItems().add(x);});
		//DATOS DEL COMBOBOX DIAMETRO
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==2)view.diametroList.add(x);});
		serviceTalla.getAll().stream().forEach(x->{if(x.getStatus()==2)view.cbDiametro.getItems().add(x);});
		//DATOS DEL COMBOBOX COLOR
		serviceColor.getAll().stream().forEach(x->{if(x.getStatus()==1)view.colorList.add(x);});
		serviceColor.getAll().stream().forEach(x->{if(x.getStatus()==1)view.cbColor.getItems().add(x);});
		
		//EDITAR LA COLUMNA PARTIDA
		view.columnPartida.setOnEditCommit((CellEditEvent<Producto,String> evt) -> {
			TablePosition<Producto, String> pos = evt.getTablePosition();
			producto = evt.getTableView().getItems().get(pos.getRow());
			producto.setPartida(evt.getNewValue());
			serviceProduct.Update(producto);
		});
		//EDITAR LA COLUMNA TIPO PRODUCTO
		view.columnTipoPro.setOnEditCommit((CellEditEvent<Producto,TipoProducto> evt) -> {
			TablePosition<Producto, TipoProducto> pos = evt.getTablePosition();
			producto = evt.getTableView().getItems().get(pos.getRow());
			producto.setTipoProducto(evt.getNewValue());
			serviceProduct.Update(producto);
		});
		//EDITAR LA COLUMNA TALLA
		view.columnTalla.setOnEditCommit((CellEditEvent<Producto,Talla> evt) -> {
			TablePosition<Producto, Talla> pos = evt.getTablePosition();
			producto = evt.getTableView().getItems().get(pos.getRow());
			producto.setTalla(evt.getNewValue());
			serviceProduct.Update(producto);
		});
		//EDITAR LA COLUMNA DIAMETRO
		view.columnDiametro.setOnEditCommit((CellEditEvent<Producto,Talla> evt)->{
			TablePosition<Producto,Talla> pos = evt.getTablePosition();
			producto = pos.getTableView().getItems().get(pos.getRow());
			producto.setDiametro(evt.getNewValue());
			serviceProduct.Update(producto);
		});
		//EDITAR LA COLUMNA COLOR
		view.columnColor.setOnEditCommit((CellEditEvent<Producto,Color> evt)->{
			TablePosition<Producto,Color> pos = evt.getTablePosition();
			producto = evt.getTableView().getItems().get(pos.getRow());
			producto.setColor(evt.getNewValue());
			serviceProduct.Update(producto);
 		});
		//EDITAR LA COLUMNA PESO
		view.columnPeso.setOnEditCommit((CellEditEvent<Producto,Float> evt) -> {
			TablePosition<Producto,Float> pos = evt.getTablePosition();
			producto = evt.getTableView().getItems().get(pos.getRow());
			producto.setPeso(evt.getNewValue());
			serviceProduct.Update(producto);
		});
		//EDITAR LA COLUMNA DOCENA
		view.columnDocena.setOnEditCommit((CellEditEvent<Producto,Double> evt) -> {
			TablePosition<Producto,Double> pos = evt.getTablePosition();
			producto = evt.getTableView().getItems().get(pos.getRow());
			producto.setDocena(evt.getNewValue());
			serviceProduct.Update(producto);
		});
	}
	
	//ELIMINAR UNO O MAS REGISTOS DE LA TABLA
	public void deleteRow(ActionEvent evt) {
		List<Producto> list = view.table.getSelectionModel().getSelectedItems();
		List<Producto> newList = serviceProduct.deleteAll(list);
		if(newList != null) 
			view.productoList.removeAll(newList);
	}
	
	//AGREGAR UN PRODUCTO
	public void addProducto(ActionEvent evt) {
		TipoProducto tp = view.cbProducto.getSelectionModel().getSelectedItem();
		Talla talla = view.cbTalla.getSelectionModel().getSelectedItem();
		Talla diametro = view.cbDiametro.getSelectionModel().getSelectedItem();
		Color color = view.cbColor.getSelectionModel().getSelectedItem();
		String peso = view.txtPeso.getText();
		String docena = view.txtDocena.getText();
		String cantidad = view.txtCantidad.getText();
		
		if(tp==null || talla==null || diametro==null || color==null || 
				peso==null || docena == null || cantidad == null) {
			Validacion.getAlert(AlertType.WARNING, "Error", "", "Debes ingresar todos los valores!");
		}else {
			String partida;
			for (int i = 0; i < Integer.parseInt(cantidad); i++) {
				partida = newPartida();
				if(partida != null) {
					Producto p = Producto.builder()
							.partida(partida)
							.tipoProducto(tp)
							.talla(talla)
							.diametro(diametro)
							.color(color)
							.peso(Float.parseFloat(peso))
							.docena(Double.parseDouble(docena))
							.build();
					
					serviceProduct.create(p);
					view.productoList.add(p);
				}
				else
					break;
			}	
		}
				
	}
	
	public String newPartida() {
		java.util.Optional<String> respuesta;
		java.util.Optional<ButtonType> result;
		boolean value = true;
		String partida = null;
		
		while (value) {
			TextInputDialog input = new TextInputDialog();
			input.setTitle("Nuevo Rollo");
			input.setHeaderText("Ingresa el codigo de partida");
			input.initStyle(StageStyle.UTILITY);
			respuesta = input.showAndWait();
			if(respuesta.isEmpty()) {
				break;
			}else if(respuesta.get().equals("")) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Informacion");
				alert.setHeaderText(null);
				alert.setContentText("Debes ingresar un codigo de partida!");
				result = alert.showAndWait();
				if(result.get() != ButtonType.OK)
					break;
			}else {
				partida = respuesta.get();
				value = false;
			}
		}
		return partida;
	}
	
	public void init(){
		//ESTABLECER LOS PARAMETRO EN LOS COMBOBOX DE LAS TABLAS Y EDITAR LAS COLUMNAS
		datosTablaCombobox();
		//ELIMINAR UNO O MAS REGIRTROS DE LA TABLA
		view.itemBorrar.setOnAction(this::deleteRow);
		//EVENTO PARA AGREGAR ROLLO
		view.btnAdd.setOnAction(this::addProducto);
		//LISTAR DATOS DE LA TABLA PRODUCTOS
		serviceProduct.getAll().stream().forEach(x->{if(x.getStatus()!=0)view.productoList.add(x);});
	}
}

