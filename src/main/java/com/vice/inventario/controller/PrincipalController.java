package com.vice.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

<<<<<<< HEAD
=======
import com.vice.inventario.view.ConfiguracionesView;
import com.vice.inventario.view.CorteView;
import com.vice.inventario.view.InventarioView;
>>>>>>> 7bc2cee... Se termino las funciones basicas de la vista almacen
import com.vice.inventario.view.PrincipalView;

@Controller
public class PrincipalController {
	
<<<<<<< HEAD
	@Autowired
	private PrincipalView view;
	
	public void init() {
		view.init();
=======
	@Autowired private PrincipalView view;
	@Autowired private ConfiguracionesView viewConfig;
	@Autowired private ConfiguracionController ctrlConfig;
	@Autowired private ProductoController ctrlProduct;
	@Autowired private ProductoView viewProduct;
	@Autowired private CorteController ctrlCorte;
	@Autowired private CorteView viewCorte;
	@Autowired private InventarioView viewInventario;
	@Autowired private InventarioController ctrlInventario;
	
	
	public void init() {
		//LANZAR VENTANA PRINCIPAL
		view.init();		
		
		//ESTABLECER EVENTOS A BOTONES
		//INICIO
		//CONFIGURACIONES
		view.btnConfig.setOnAction(this::newConfig);
		view.menuInventario.getItems().get(1).setOnAction(this::newConfig);
		//ROLLOS
		view.btnRollos.setOnAction(this::panelRollo);
		view.menuInventario.getItems().get(2).setOnAction(this::panelRollo);
		//CORTE
		view.btnCorte.setOnAction(this::panelCorte);
		view.menuInventario.getItems().get(3).setOnAction(this::panelCorte);
		//ALMACEN
		view.btnAlmacen.setOnAction(this::panelInventario);
		view.menuInventario.getItems().get(4).setOnAction(this::panelInventario);
		
	}
	//LANZAR PANEL DE ROLLOS
	public void panelRollo(ActionEvent evt) {
		viewProduct.init();
		view.bp.setCenter(viewProduct.getPanel());
		ctrlProduct.setView(viewProduct);
		ctrlProduct.init();
>>>>>>> 7bc2cee... Se termino las funciones basicas de la vista almacen
	}
	
	/*public void newPanel(ActionEvent evt) {
		ConfiguracionesView viewConfig = new ConfiguracionesView();
		ConfiguracionController ctrConfing = new ConfiguracionController(viewConfig); 
		view.bp.setCenter(viewConfig.getPanel());
<<<<<<< HEAD
	}*/
=======
		ctrlConfig.setView(viewConfig);
		ctrlConfig.init();
	}
	//LANZAR PANEL DE CORTE
	public void panelCorte(ActionEvent evt) {
		viewCorte.init();
		view.bp.setCenter(viewCorte.getPane());
		ctrlCorte.setView(viewCorte);
		ctrlCorte.init();
	}
	//LAUNCH INVENTARIO PANEL
	public void panelInventario(ActionEvent evt) {
		viewInventario.init();
		view.bp.setCenter(viewInventario.getPane());
		ctrlInventario.init();
		ctrlInventario.setView(viewInventario);
	}
>>>>>>> 7bc2cee... Se termino las funciones basicas de la vista almacen
}

