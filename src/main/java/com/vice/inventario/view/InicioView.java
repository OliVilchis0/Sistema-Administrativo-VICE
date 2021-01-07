package com.vice.inventario.view;

import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

@Component
public class InicioView {
	
	public BorderPane getPane() {
		return bp;
	}
	
	public void init() {
		bp = new BorderPane();
		gridPane = new GridPane();
		lbTitulo = new Label("Inicio");
		lbValorInv = new Label("Valor Inventario");
		lbConfiguracion = new Label("Configuracion");
		lbRollos = new Label("Rollos");
		lbPlayeras = new Label("Playeras");
		lbCorte = new Label("Corte");
		
		lbPBuenas = new Label();
		lbPMalas = new Label();
		lbBuenasTotal = new Label();
		lbMalasTotal = new Label();
		lbValorInv2 = new Label();
		lbColores = new Label();
		lbTallas = new Label();
		lbDiametros = new Label();
		lbTiposPro = new Label();
		lbRolosAlmacen = new Label();
		lbRollosCorte = new Label();
		lbRollosConf = new Label();
		lbPBuenasDif = new Label();
		lbPMalasDif = new Label();
		lbCorte2 = new Label();
		
		sp1 = new Pane(lbPBuenas);
		sp2 = new Pane(lbPMalas);
		sp3 = new Pane(lbBuenasTotal);
		sp4 = new Pane(lbMalasTotal);
		sp5 = new Pane(lbValorInv2);
		sp6 = new Pane(lbColores);
		sp7 = new Pane(lbTallas);
		sp8 = new Pane(lbDiametros);
		sp9 = new Pane(lbTiposPro);
		sp10 = new Pane(lbRolosAlmacen);
		sp11 = new Pane(lbRollosCorte);
		sp12 = new Pane(lbRollosConf);
		sp13 = new Pane(lbPBuenasDif);
		sp14 = new Pane(lbPMalasDif);
		sp15 = new Pane(lbCorte2);
		
		lbTitulo.setId("title-top");
		lbTitulo.setAlignment(Pos.CENTER);
		lbTitulo.prefWidthProperty().bind(bp.widthProperty());
		
		sp1.setId("valor-inventario");
		sp2.setId("valor-inventario");
		sp3.setId("valor-inventario");
		sp4.setId("valor-inventario");
		sp5.setId("valor-inventario");
		sp6.setId("configuracion");
		sp7.setId("configuracion");
		sp8.setId("configuracion");
		sp9.setId("configuracion");
		sp10.setId("rollos");
		sp11.setId("rollos");
		sp12.setId("rollos");
		sp13.setId("playeras");
		sp14.setId("playeras");
		sp15.setId("corte");
		
		lbValorInv.prefWidthProperty().bind(gridPane.widthProperty());
		lbConfiguracion.prefWidthProperty().bind(gridPane.widthProperty());
		lbRollos.prefWidthProperty().bind(gridPane.widthProperty());
		lbCorte.prefWidthProperty().bind(gridPane.widthProperty());
		lbPlayeras.prefWidthProperty().bind(gridPane.widthProperty());
		
		lbPBuenas.setId("alignment-center");
		lbPMalas.setId("alignment-center");
		lbBuenasTotal.setId("alignment-center");
		lbMalasTotal.setId("alignment-center");
		lbValorInv2.setId("alignment-center");
		lbColores.setId("alignment-center"); 
		lbTallas.setId("alignment-center");
		lbDiametros.setId("alignment-center");
		lbTiposPro.setId("alignment-center");
		lbRolosAlmacen.setId("alignment-center");
		lbRollosCorte.setId("alignment-center");
		lbRollosConf.setId("alignment-center");
		lbPBuenasDif.setId("alignment-center");
		lbPMalasDif.setId("alignment-center");
		lbCorte2.setId("alignment-center");
		
		lbPBuenas.setAlignment(Pos.CENTER_LEFT);
		lbPMalas.setAlignment(Pos.CENTER_LEFT);
		lbBuenasTotal.setAlignment(Pos.CENTER_LEFT);
		lbMalasTotal.setAlignment(Pos.CENTER_LEFT);
		lbValorInv2.setAlignment(Pos.CENTER_LEFT);
		lbColores.setAlignment(Pos.CENTER_LEFT); 
		lbTallas.setAlignment(Pos.CENTER_LEFT);
		lbDiametros.setAlignment(Pos.CENTER_LEFT);
		lbTiposPro.setAlignment(Pos.CENTER_LEFT);
		lbRolosAlmacen.setAlignment(Pos.CENTER_LEFT);
		lbRollosCorte.setAlignment(Pos.CENTER_LEFT);
		lbRollosConf.setAlignment(Pos.CENTER_LEFT);
		lbPBuenasDif.setAlignment(Pos.CENTER_LEFT);
		lbPMalasDif.setAlignment(Pos.CENTER_LEFT);
		lbCorte2.setAlignment(Pos.CENTER_LEFT);
		
		sp1.prefWidthProperty().bind(gridPane.widthProperty());
		lbPBuenas.prefWidthProperty().bind(sp1.widthProperty());
		
		lbValorInv.setAlignment(Pos.CENTER);
		lbConfiguracion.setAlignment(Pos.CENTER);
		lbRollos.setAlignment(Pos.CENTER);
		lbCorte.setAlignment(Pos.CENTER);
		lbPlayeras.setAlignment(Pos.CENTER);
		
		gridPane.add(lbValorInv, 0, 0);
		gridPane.add(sp1, 0, 1);
		gridPane.add(sp2, 0, 2);
		gridPane.add(sp3, 0, 3);
		gridPane.add(sp4, 0, 4);
		gridPane.add(sp5, 0, 5);
		
		gridPane.add(lbConfiguracion, 1, 0);
		gridPane.add(sp6, 1, 1);
		gridPane.add(sp7, 1, 2);
		gridPane.add(sp8, 1, 3);
		gridPane.add(sp9, 1, 4);
		
		gridPane.add(lbRollos, 2, 0);
		gridPane.add(sp10, 2, 1);
		gridPane.add(sp11, 2, 2);
		gridPane.add(sp12, 2, 3);
		
		gridPane.add(lbPlayeras, 3, 0);
		gridPane.add(sp13, 3, 1);
		gridPane.add(sp14, 3, 2);
		
		gridPane.add(lbCorte, 4, 0);
		gridPane.add(sp15, 4, 1);
		
		gridPane.setVgap(5);
		gridPane.setHgap(5);
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.prefWidthProperty().bind(bp.widthProperty());
		
		
		//INITIALIZE LIST OF DATA
		data = FXCollections.observableArrayList();
		//CREATE A BAR CHART
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Playeras");
		xAxis.setTickLabelRotation(-90);
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Cantidad");
		
		chart = new BarChart<>(xAxis, yAxis);
		chart.prefWidthProperty().bind(bp.widthProperty());
		chart.setMinHeight(500);
		chart.setTitle("Total de playeras");
		chart.setData(data);
		chart.setId("border-color");
		
		
		
		//CREATE A SCROLL PANE
		scroll = new ScrollPane();
		scroll.setContent(chart);
		
		bp.setTop(lbTitulo);
		bp.setCenter(gridPane);
		bp.setBottom(scroll);
		bp.setId("border-color");
	}
	
	private BorderPane bp;
	private GridPane gridPane;
	private Pane sp1,sp2,sp3,sp4,sp5,sp6,sp7,sp8,sp9,sp10,sp11,sp12,sp13,sp14,sp15;
	private Label lbTitulo,lbValorInv,lbConfiguracion,lbRollos,lbPlayeras,lbCorte;
	public Label lbPBuenas,lbPMalas,lbBuenasTotal,lbMalasTotal,lbValorInv2,lbColores,lbTallas,lbDiametros,
	lbTiposPro,lbRolosAlmacen,lbRollosCorte,lbRollosConf,lbPBuenasDif,lbPMalasDif,lbCorte2;
	public BarChart<String, Number> chart;
	public ObservableList<XYChart.Series<String, Number>> data;
	private ScrollPane scroll;
}
