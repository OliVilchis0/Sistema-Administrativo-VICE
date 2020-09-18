package com.vice.inventario.view;



import com.vice.inventario.model.Color;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;

public class ConfiguracionesView {
	
	public ConfiguracionesView() {
		init();
	}
	
	public BorderPane getPanel() {
		return this.bp;
	}
	
	private void init() {
		bp = new BorderPane();
		
		lbSuperior = new Label("Configuraciones");
		tp = new TilePane();
		tp.setPrefColumns(4);
		tp.setPrefRows(3);
		
		vbox1 = new VBox();
		vbox1.setPadding(new Insets(10,10,10,10));
		vbox2 = new VBox();
		vbox2.setPadding(new Insets(10,10,10,10));
		vbox3 = new VBox();
		vbox3.setPadding(new Insets(10,10,10,10));
		vbox4 = new VBox();
		vbox4.setPadding(new Insets(10,10,10,10));
		
		lbColor = new Label("0 Colores en total");
		lbTalla = new Label("0 Tallas en total");
		lbDiametro = new Label("0 Diametros en total");
		lbTipoPro = new Label("0 tipos de produtos en total");
		
		txtcolor = new TextField();
		txttalla = new TextField();
		txtdiametro = new TextField();
		txttipoPro = new TextField();
		
		bpColor = new BorderPane();
		bpTalla = new BorderPane();
		bpDiametro = new BorderPane();
		bpTipoPro = new BorderPane();
		
		bpColor.setTop(lbColor);
		bpColor.setCenter(new Label("Imagen Color"));
		
		bpTalla.setTop(lbTalla);
		bpTalla.setCenter(new Label("Imagen Talla"));
		
		bpDiametro.setTop(lbDiametro);
		bpDiametro.setCenter(new Label("Imagen Diamtro"));
		
		bpTipoPro.setTop(lbTipoPro);
		bpTipoPro.setCenter(new Label("Imagen tipo producto"));
		
		
		//PROPIEDAES DE LA TABLA COLOR
		tbColor = new TableView<Color>();
		tbColor.setEditable(true);
		
		idColor = new TableColumn<Color, Integer>("ID");
		idColor.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreColor = new TableColumn<Color, String>("NOMBRE");
		nombreColor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		statusColor = new TableColumn<Color, Byte>("ESTATUS");		
		statusColor.setCellValueFactory(new PropertyValueFactory<>("status"));

		datosColor = FXCollections.observableArrayList();
		tbColor.setItems(datosColor);
		
		tbColor.getColumns().add(idColor);
		tbColor.getColumns().add(nombreColor);
		tbColor.getColumns().add(statusColor);
		
		vbox1.getChildren().addAll(
				bpColor,
				txtcolor,
				tbColor
				);
		vbox2.getChildren().addAll(
				bpTalla,
				txttalla
				);
		vbox3.getChildren().addAll(
				bpDiametro,
				txtdiametro
				);
		vbox4.getChildren().addAll(
				bpTipoPro,
				txttipoPro
				);
		
		tp.getChildren().addAll(vbox1,vbox2,vbox3,vbox4);
		
		bp.setTop(lbSuperior);
		bp.setCenter(tp);
		
	}

	public Button botonColor;
	private BorderPane bp,bpColor,bpTalla,bpDiametro,bpTipoPro;
	private TilePane tp;
	private TextField txtcolor,txttalla,txtdiametro,txttipoPro;
	private Label lbColor,lbTalla,lbDiametro,lbTipoPro;
	private VBox vbox1,vbox2,vbox3,vbox4;
	private Label lbSuperior;
	public TableView<Color> tbColor;
	private TableColumn<Color,Integer> idColor;
	private TableColumn<Color,String> nombreColor;
	private TableColumn<Color,Byte> statusColor;
	public ObservableList<Color> datosColor;
}
