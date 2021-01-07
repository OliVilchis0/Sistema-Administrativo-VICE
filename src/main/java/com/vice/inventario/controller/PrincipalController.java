package com.vice.inventario.controller;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;

import com.vice.inventario.InventarioApplication;
import com.vice.inventario.view.ConfiguracionesView;
import com.vice.inventario.view.CorteView;
import com.vice.inventario.view.InicioView;
import com.vice.inventario.view.InventarioView;
import com.vice.inventario.view.PrincipalView;
import com.vice.inventario.view.ProductoView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

@Controller
public class PrincipalController extends Application{
	
	@Autowired private PrincipalView view;
	@Autowired private ConfiguracionesView viewConfig;
	@Autowired private ConfiguracionController ctrlConfig;
	@Autowired private ProductoController ctrlProduct;
	@Autowired private ProductoView viewProduct;
	@Autowired private CorteController ctrlCorte;
	@Autowired private CorteView viewCorte;
	@Autowired private InventarioView viewInventario;
	@Autowired private InventarioController ctrlInventario;
	@Autowired private InicioController ctrlInicio;
	@Autowired private InicioView viewInicio;
	
	
	public void initComponent() {		
		
		//INITIALIZE PANEL INICIO
		panelInicio(null);
		//ESTABLECER EVENTOS A BOTONES
		//INICIO
		view.btnInicio.setOnAction(this::panelInicio);
		view.menuInventario.getItems().get(0).setOnAction(this::panelInicio);
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
	
	//LAUNCH INICIO PANEL
	public void panelInicio(ActionEvent evt) {
		viewInicio.init();
		view.bp.setCenter(viewInicio.getPane());
		ctrlInicio.setPanel(viewInicio);
		ctrlInicio.init();
		changeColor(view.btnInicio);
	}
	//LANZAR PANEL DE ROLLOS
	public void panelRollo(ActionEvent evt) {
		viewProduct.init();
		view.bp.setCenter(viewProduct.getPanel());
		ctrlProduct.setView(viewProduct);
		ctrlProduct.init();
		changeColor(view.btnRollos);
	}
	//LANZAR PANEL DE CONFIGURACIONES
	public void newConfig(ActionEvent evt) {
		viewConfig.init();
		view.bp.setCenter(viewConfig.getPanel());
		ctrlConfig.setView(viewConfig);
		ctrlConfig.init();
		changeColor(view.btnConfig);
	}
	//LANZAR PANEL DE CORTE
	public void panelCorte(ActionEvent evt) {
		viewCorte.init();
		view.bp.setCenter(viewCorte.getPane());
		ctrlCorte.setView(viewCorte);
		ctrlCorte.init();
		changeColor(view.btnCorte);
	}
	//LAUNCH INVENTARIO PANEL
	public void panelInventario(ActionEvent evt) {
		viewInventario.init();
		view.bp.setCenter(viewInventario.getPane());
		ctrlInventario.init();
		ctrlInventario.setView(viewInventario);
		changeColor(view.btnAlmacen);
	}
	
	//IF BUTTON IS SELECTED, CHANGE IT COLOR TO WHITE
	public void changeColor(Button button) {
		view.btnAlmacen.setId("bg-darck");
		view.btnConfig.setId("bg-darck");
		view.btnCorte.setId("bg-darck");
		view.btnInicio.setId("bg-darck");
		view.btnRollos.setId("bg-darck");
		
		button.setId("bg-white");
	}
	
	@Override
	public void init() {
		SpringApplication.run(InventarioApplication.class).getAutowireCapableBeanFactory().autowireBean(this);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//LANZAR VENTANA PRINCIPAL
		view.init(stage);
		//LAUNCH COMPONENTS
		initComponent();
	}
	
	@Override
	public void stop() {
		Platform.exit();
	}
}

