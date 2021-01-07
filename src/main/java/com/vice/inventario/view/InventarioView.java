package com.vice.inventario.view;

import org.springframework.stereotype.Component;

import com.vice.inventario.Validacion;
import com.vice.inventario.model.Color;
import com.vice.inventario.model.Inventario;
import com.vice.inventario.model.Talla;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

@Component
public class InventarioView {
	
	public BorderPane getPane() {
		return bp;
	}
	public void init() {
		bp = new BorderPane();
		bp.setId("border-color");
		lbTitulo = new Label("Almacen");
		lbTitulo.setId("title-top");
		lbTitulo.setAlignment(Pos.CENTER);
		lbTitulo.prefWidthProperty().bind(bp.widthProperty());
		
		//INITIALIZE LIST
		tallaList = FXCollections.observableArrayList();
		colorList = FXCollections.observableArrayList();
		inventarioList = FXCollections.observableArrayList();
		
		//INITIALIZE HYPERLINK
		linkExce = new Hyperlink("Importar Excel");
		linkNuevo = new Hyperlink("Agregar");
		linkTabla = new Hyperlink("Tabla");
		linkValorInv = new Hyperlink("Valor");
		
		//CREATE TABLE
		createTable();
		//CREATE FOMR
		createForm();
		Label labelLeft = new Label();
		Label labelRight = new Label();
		
		labelLeft.setMinWidth(10);
		labelRight.setMinWidth(10);
		
		bp.setTop(
				new VBox(
						lbTitulo,
						new HBox(linkNuevo,linkTabla,linkValorInv,linkExce)));
		bp.setCenter(table);
		bp.setLeft(labelLeft);
		bp.setRight(labelRight);
		
		//EVENT TO CHANGE PANEL BETWEEN TABLE AND FORM
		setEventPanel();
	}
	
	//METHOD TO CHANGE PANELS
	private void setEventPanel() {
		linkNuevo.setOnAction((ActionEvent evt)->{
			bp.setCenter(vbox);
		});
		
		linkTabla.setOnAction((ActionEvent evt) ->{
			bp.setCenter(table);
		});
	}
	
	//CREATE FORM TO INSERT NEW ROW
	private void createForm(){
		//FORM RIGHT FOR NEW INVENTARIO
		vbox = new VBox(10);
		
		cbTalla = new ComboBox<>();
		cbColor = new ComboBox<>();
		rbGroup = new ToggleGroup();
		rbBueno = new RadioButton("Bueno");
		rbBueno.setToggleGroup(rbGroup);
		rbMalo = new RadioButton("Malo");
		rbMalo.setToggleGroup(rbGroup);
		lbError = new Label();
		txtCantidad = new TextField();
		validateCantida();
		btnNew = new Button("AGERGAR");
		
		vbox.getChildren().addAll(
				new Label("Talla"),
				cbTalla,
				new Label("Color"),
				cbColor,
				new Label("Condicion"),
				new HBox(rbBueno,rbMalo),
				new HBox(new Label("Cantidad"),lbError),
				txtCantidad,
				btnNew);
		lbError.setAlignment(Pos.BASELINE_RIGHT);
		cbTalla.prefWidthProperty().bind(vbox.widthProperty());
		cbColor.prefWidthProperty().bind(vbox.widthProperty());
		btnNew.prefWidthProperty().bind(vbox.widthProperty());
		rbBueno.prefWidthProperty().bind(vbox.widthProperty());
		rbMalo.prefWidthProperty().bind(vbox.widthProperty());
		vbox.setMaxWidth(400);
		vbox.setMinWidth(100);
		vbox.alignmentProperty().set(Pos.TOP_CENTER);

	}
	
	//CREATE TABLE INVENTARIO
	private void createTable() {
		//CREATE TABLE INVENTARIO AND YOUR CONFIGURATION
		table = new TableView<>();
		table.setEditable(true);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//CREATE COLUMNS OF THE TABLE INVENTARIO AND YOUR CONFIGURATION
		columnTalla = new TableColumn<>("TALLA");
		columnTalla.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Inventario,Talla>, ObservableValue<Talla>>() {
			
			@Override
			public ObservableValue<Talla> call(CellDataFeatures<Inventario, Talla> param) {
				Inventario inv = param.getValue();  
				return new SimpleObjectProperty<>(inv.getTalla());
			}
		});
		columnTalla.setCellFactory(ComboBoxTableCell.forTableColumn(tallaList));
		
		columnColor = new TableColumn<>("COLOR");
		columnColor.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Inventario,Color>, ObservableValue<Color>>() {
					
					@Override
					public ObservableValue<Color> call(CellDataFeatures<Inventario, Color> param) {
						Inventario inv = param.getValue();
						return new SimpleObjectProperty<>(inv.getColor());
					}
				});
		columnColor.setCellFactory(ComboBoxTableCell.forTableColumn(colorList));
		
		columnCondicion = new TableColumn<>("CONDICION");
		columnCondicion.setCellValueFactory(
				new Callback<CellDataFeatures<Inventario,String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Inventario, String> param) {
						switch (param.getValue().getCondicion()) {
						case 1:							
							return new SimpleObjectProperty<>("BUENAS");
						case 2:							
							return new SimpleObjectProperty<>("MALAS");
						default:
							return new SimpleObjectProperty<>("");
						}
					}
				});
		
		columnTotal = new TableColumn<>("TOTAL");
		columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		table.getColumns().add(columnTalla);
		table.getColumns().add(columnColor);
		table.getColumns().add(columnCondicion);
		table.getColumns().add(columnTotal);
		
		table.setItems(inventarioList);
		
		//MENU CONTEXT OF THE TABLE
		menuContext = new ContextMenu();
		addItem = new MenuItem("AGREGAR");
		removeItem = new MenuItem("QUITAR");
		deleteItem = new MenuItem("ELIMINAR");
		precioPBuenas = new MenuItem("PRECIO PLAYERAS BUENAS");
		precioPMalas= new MenuItem("PRECIO PLAYERAS MALAS");
		menuContext.getItems().addAll(addItem,removeItem,deleteItem,precioPBuenas,precioPMalas);
		table.setContextMenu(menuContext);
	}
	
	private void validateCantida() {
		txtCantidad.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				if(!Validacion.esEntero(txtCantidad.getText())) {
					lbError.setText("*Solo numeros");
					lbError.setStyle("-fx-text-fill:red");
					txtCantidad.setText(null);
				}else
					lbError.setText(null);
			}
		});
	}
	
	private BorderPane bp;
	private Label lbTitulo,lbError;
	public TableView<Inventario> table;
	public TableColumn<Inventario, Talla> columnTalla;
	public TableColumn<Inventario, Color> columnColor;
	public TableColumn<Inventario, String> columnCondicion;
	public TableColumn<Inventario, Integer> columnTotal;
	public ObservableList<Talla> tallaList;
	public ObservableList<Color> colorList;
	public ObservableList<Inventario> inventarioList;
	public ContextMenu menuContext;
	public MenuItem addItem,removeItem,deleteItem,precioPBuenas,precioPMalas;
	private VBox vbox;
	public ComboBox<Talla> cbTalla;
	public ComboBox<Color> cbColor;
	public TextField txtCantidad;
	public RadioButton rbBueno,rbMalo;
	private ToggleGroup rbGroup;
	public Button btnNew;
	public Hyperlink linkTabla,linkNuevo,linkExce,linkValorInv;
}
