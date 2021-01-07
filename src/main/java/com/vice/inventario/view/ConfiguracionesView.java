package com.vice.inventario.view;



import org.springframework.stereotype.Component;

import com.vice.inventario.model.Color;
import com.vice.inventario.model.Talla;
import com.vice.inventario.model.TipoProducto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;

@Component
public class ConfiguracionesView {
	
	public BorderPane getPanel() {
		return this.bp;
	}
	
	public void init() {
		bp = new BorderPane();
		bp.setId("border-color");
		
		lbSuperior = new Label("Configuraciones");
		lbSuperior.setId("title-top");
		lbSuperior.setAlignment(Pos.CENTER);
		lbSuperior.prefWidthProperty().bind(bp.widthProperty());
		
		tp = new TilePane();
		tp.prefWidthProperty().bind(bp.widthProperty());
		tp.prefHeightProperty().bind(bp.heightProperty());
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
		
		lbColor = new Label("");
		lbTalla = new Label("");
		lbDiametro = new Label("");
		lbTipoPro = new Label("");
		
		txtcolor = new TextField();
		txttalla = new TextField();
		txtdiametro = new TextField();
		txttipoPro = new TextField();
		
		bpColor = new BorderPane();
		bpTalla = new BorderPane();
		bpDiametro = new BorderPane();
		bpTipoPro = new BorderPane();
		
		bpColor.setTop(lbColor);
		bpColor.setCenter(new ImageView(new Image("/imagenes/colores.png")));
		
		bpTalla.setTop(lbTalla);
		bpTalla.setCenter(new ImageView(new Image("/imagenes/talla.png")));
		
		bpDiametro.setTop(lbDiametro);
		bpDiametro.setCenter(new ImageView(new Image("/imagenes/diametro.png")));
		
		bpTipoPro.setTop(lbTipoPro);
		bpTipoPro.setCenter(new ImageView(new Image("/imagenes/rolloG.png")));
		
		lbColor.prefWidthProperty().bind(bpColor.widthProperty());
		lbColor.setAlignment(Pos.TOP_CENTER);
		lbColor.setId("alignment-center");
		
		lbTalla.prefWidthProperty().bind(bpTalla.widthProperty());
		lbTalla.setAlignment(Pos.TOP_CENTER);
		lbTalla.setId("alignment-center");
		
		lbDiametro.prefWidthProperty().bind(bpDiametro.widthProperty());
		lbDiametro.setAlignment(Pos.TOP_CENTER);
		lbDiametro.setId("alignment-center");
		
		lbTipoPro.prefWidthProperty().bind(bpTipoPro.widthProperty());
		lbTipoPro.setAlignment(Pos.TOP_CENTER);
		lbTipoPro.setId("alignment-center");
		
		bpColor.setMaxHeight(80);
		bpColor.setMinHeight(80);
		bpColor.setId("bg-color");
		
		bpTalla.setMaxHeight(80);
		bpTalla.setMinHeight(80);
		bpTalla.setId("bg-talla");
		
		bpDiametro.setMaxHeight(80);
		bpDiametro.setMinHeight(80);
		bpDiametro.setId("bg-diametro");
		
		bpTipoPro.setMaxHeight(80);
		bpTipoPro.setMinHeight(80);
		bpTipoPro.setId("bg-tipo-rollo");
		
		//PROPIEDAES DE LA TABLA COLOR
		tbColor = new TableView<Color>();
		tbColor.setEditable(true);
		tbColor.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tbColor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		idColor = new TableColumn<Color, Integer>("ID");
		idColor.setCellValueFactory(new PropertyValueFactory<>("id"));
		idColor.setMinWidth(50);
		nombreColor = new TableColumn<Color, String>("NOMBRE");
		nombreColor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombreColor.setCellFactory(TextFieldTableCell.forTableColumn());

		datosColor = FXCollections.observableArrayList();
		tbColor.setItems(datosColor);
		
		tbColor.getColumns().add(idColor);
		tbColor.getColumns().add(nombreColor);
		
		//CONTEXT MENU PARA LA TABLA COLOR
		menuColor = new ContextMenu();
		itemColor = new MenuItem("Eliminar");
		menuColor.getItems().add(itemColor);
		tbColor.setContextMenu(menuColor);
		
		//PROPIEDAES DE LA TABLA TALLA
		tbTalla = new TableView<Talla>();
		tbTalla.setEditable(true);
		tbTalla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tbTalla.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		idTalla = new TableColumn<Talla, Integer>("ID");
		idTalla.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreTalla= new TableColumn<Talla, String>("NOMBRE");
		nombreTalla.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombreTalla.setCellFactory(TextFieldTableCell.forTableColumn());

		datosTalla = FXCollections.observableArrayList();
		tbTalla.setItems(datosTalla);

		tbTalla.getColumns().add(idTalla);
		tbTalla.getColumns().add(nombreTalla);
		
		//CONTEXT MENU PARA LA TABLA TALLA
		menuTalla= new ContextMenu();
		itemTalla= new MenuItem("Eliminar");
		menuTalla.getItems().add(itemTalla);
		tbTalla.setContextMenu(menuTalla);
		
		//PROPIEDAES DE LA TABLA DIAMETRO
		tbDiametro = new TableView<Talla>();
		tbDiametro.setEditable(true);
		tbDiametro.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tbDiametro.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		idDiametro = new TableColumn<Talla, Integer>("ID");
		idDiametro.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreDiametro= new TableColumn<Talla, String>("NOMBRE");
		nombreDiametro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombreDiametro.setCellFactory(TextFieldTableCell.forTableColumn());


		datosDiametro = FXCollections.observableArrayList();
		tbDiametro.setItems(datosDiametro);

		tbDiametro.getColumns().add(idDiametro);
		tbDiametro.getColumns().add(nombreDiametro);
		
		//CONTEXT MENU PARA LA TABLA DIAMETRO
		menuDiametro = new ContextMenu();
		itemDiametro= new MenuItem("Eliminar");
		menuDiametro.getItems().add(itemDiametro);
		tbDiametro.setContextMenu(menuDiametro);
		
		//PROPIEDAES DE LA TABLA TIPO PRODUCTO
		tbTipoPro = new TableView<TipoProducto>();
		tbTipoPro.setEditable(true);
		tbTipoPro.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tbTipoPro.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		idTipoPro = new TableColumn<TipoProducto, Integer>("ID");
		idTipoPro.setCellValueFactory(new PropertyValueFactory<>("id"));
		nombreTipoPro= new TableColumn<TipoProducto, String>("NOMBRE");
		nombreTipoPro.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		nombreTipoPro.setCellFactory(TextFieldTableCell.forTableColumn());

		datosTipoPro = FXCollections.observableArrayList();
		tbTipoPro.setItems(datosTipoPro);
		
		//CONTEXT MENU PARA LA TABLA TIPO PRODUCTO
		menuTipoPro = new ContextMenu();
		itemTipoPro= new MenuItem("Eliminar");
		menuTipoPro.getItems().add(itemTipoPro);
		tbTipoPro.setContextMenu(menuTipoPro);

		tbTipoPro.getColumns().add(idTipoPro);
		tbTipoPro.getColumns().add(nombreTipoPro);

		//AGREGAR COMPONENTES AL vbox
		vbox1.getChildren().addAll(
				bpColor,
				txtcolor,
				tbColor
				);
		vbox2.getChildren().addAll(
				bpTalla,
				txttalla,
				tbTalla
				);
		vbox3.getChildren().addAll(
				bpDiametro,
				txtdiametro,
				tbDiametro
				);
		vbox4.getChildren().addAll(
				bpTipoPro,
				txttipoPro,
				tbTipoPro
				);
		
		tp.getChildren().addAll(vbox1,vbox2,vbox3,vbox4);
		
		bp.setTop(lbSuperior);
		bp.setCenter(tp);
		
	}

	public Button botonColor;
	private BorderPane bp,bpColor,bpTalla,bpDiametro,bpTipoPro;
	private TilePane tp;
	public TextField txtcolor,txttalla,txtdiametro,txttipoPro;
	public Label lbColor,lbTalla,lbDiametro,lbTipoPro;
	private VBox vbox1,vbox2,vbox3,vbox4;
	private Label lbSuperior;
	public TableView<Color> tbColor;
	private TableColumn<Color,Integer> idColor;
	public TableColumn<Color,String> nombreColor;
	public ObservableList<Color> datosColor;
	public TableView<Talla> tbTalla;
	private TableColumn<Talla,Integer> idTalla;
	public TableColumn<Talla,String> nombreTalla;
	public ObservableList<Talla> datosTalla;
	public TableView<Talla> tbDiametro;
	private TableColumn<Talla,Integer> idDiametro;
	public TableColumn<Talla,String> nombreDiametro;
	public ObservableList<Talla> datosDiametro;
	public TableView<TipoProducto> tbTipoPro;
	private TableColumn<TipoProducto,Integer> idTipoPro;
	public TableColumn<TipoProducto,String> nombreTipoPro;
	public ObservableList<TipoProducto> datosTipoPro;
	private ContextMenu menuColor,menuTalla,menuDiametro,menuTipoPro;
	public MenuItem itemColor,itemTalla,itemDiametro,itemTipoPro;
}
