package com.vice.inventario.view;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.vice.inventario.Validacion;
import com.vice.inventario.model.Corte;
import com.vice.inventario.model.Producto;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

@Component
public class CorteView {
	
	public BorderPane getPane() {
		return bp;
	}

	public void init() {
		bp = new BorderPane();
		bp.setId("border-color");
		lbTitulo = new Label("Corte");
		lbTitulo.setId("title-top");
		lbTitulo.setAlignment(Pos.CENTER);
		lbTitulo.prefWidthProperty().bind(bp.widthProperty());
		
		linkNewCorte = new Hyperlink("Nuevo",new ImageView(new Image("/imagenes/nuevo.png")));
		linkTabla = new Hyperlink("Tabla",new ImageView(new Image("/imagenes/tabla.png")));
		linkExcel = new Hyperlink("Generar Excel");
		
		//LISTA DE TALLAS
		tallaList = FXCollections.observableArrayList();
		corteList = FXCollections.observableArrayList();
		
		//INITIALIZE TABLE AND FORM
		getform();
		getTable();
		
		Label labelLeft = new Label();
		Label labelRight = new Label();
		
		labelLeft.setMinWidth(10);
		labelRight.setMinWidth(10);
		
		//CREATE NEW BORDER PANE FOR THE COMPONENT TABLEBIEW AND FORM 
		bp.setTop(
				new VBox(
						lbTitulo,
						new HBox(linkNewCorte,linkTabla,linkExcel))
				);
		//bp.setTop(lbTitulo);
		bp.setCenter(table);
		bp.setLeft(labelLeft);
		bp.setRight(labelRight);
		
		//EVENT FOR CHANGE PANEL FORM OR TABLE
		setEventPanel();
	}
	
	//METHOD FOR CHANGE DIFERENT PANELS(FORM OR TABLE)
	public void setEventPanel() {
		//IF PRESSED HYPERLINK FOR NEW CORTE
		this.linkNewCorte.setOnAction((ActionEvent evt) ->{
			bp.setCenter(vbox1);
		});
		
		//IF PRESSED HYPERLINK FOR TABLE
		this.linkTabla.setOnAction((ActionEvent evt) -> {
			bp.setCenter(table);
		});
		
	}
	//COMPONENTS OF THE FORM
	public void getform() {
		//CONTENIDO DEL FORMULARIO
		vbox1 = new VBox(10);
		
		cbProducto = new ComboBox<>();
		cbTalla = new ComboBox<>();
		lbnuPlayeras = new Label("Numero de playeras");
		lbErrorNp = new Label();
		txtNumPlayeras = new TextField();
		validarTxt();
		btnAdd = new Button("Agregar");
		
		vbox1.getChildren().addAll(
				new Label("Partida de Rollo"),
				cbProducto,
				new Label("Talla"),
				cbTalla,
				new HBox(
						lbnuPlayeras,
						lbErrorNp),
				txtNumPlayeras,
				btnAdd);
		
		cbProducto.prefWidthProperty().bind(vbox1.widthProperty());
		cbTalla.prefWidthProperty().bind(vbox1.widthProperty());
		btnAdd.prefWidthProperty().bind(vbox1.widthProperty());
		vbox1.setMaxWidth(400);
		vbox1.setMinWidth(100);
		vbox1.alignmentProperty().set(Pos.TOP_CENTER);
		
	}
	
	//COMPONENTS OF THE TABLE
	public void getTable() {
		//PROPIEDADES DE LA TABLA
		table = new TableView<>();
		table.setEditable(true);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		//COLUMNAS DE LA TABLA
		columnPartida = new TableColumn<>("PARTIDA");
		columnPartida.setCellValueFactory(new PropertyValueFactory<>("producto"));
		
		columnFecha = new TableColumn<Corte, Date>("FECHA");
		columnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		
		columnPlayeras = new TableColumn<Corte, Integer>("# DE PLAYERAS");
		columnPlayeras.setCellValueFactory(new PropertyValueFactory<>("numPlayeras"));
		columnPlayeras.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			
		columnRelacion = new TableColumn<Corte, Float>("RELACION");
		columnRelacion.setCellValueFactory(new PropertyValueFactory<>("relacion"));
		
		columnTalla = new TableColumn<Corte, Talla>("TALLA");
		columnTalla.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Corte,Talla>, ObservableValue<Talla>>() {
			
			@Override
			public ObservableValue<Talla> call(CellDataFeatures<Corte, Talla> param) {
				Corte corte = param.getValue();
				return new SimpleObjectProperty<>(corte.getTalla());
			}
		});
		columnTalla.setCellFactory(ComboBoxTableCell.forTableColumn(tallaList));
		
		
		//CREAR Y AGREGAR EL CONTEXT MENU A LA TABLA
		context = new ContextMenu();
		eliminarItem = new MenuItem("Elimiar corte");
		generatedReport = new MenuItem("Generar reporte");
		context.getItems().addAll(eliminarItem,generatedReport);
		table.setContextMenu(context);
		
		table.getColumns().add(columnPartida);
		table.getColumns().add(columnFecha);
		table.getColumns().add(columnPlayeras);
		table.getColumns().add(columnRelacion);
		table.getColumns().add(columnTalla);
		
		table.setItems(corteList);
		
	}
	
	private void validarTxt() {
		txtNumPlayeras.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				if(!Validacion.esEntero(txtNumPlayeras.getText())) {
					lbErrorNp.setText("*Solo numeros");
					lbErrorNp.setStyle("-fx-text-fill:red");
					txtNumPlayeras.setText(null);
				}else {
					lbErrorNp.setText(null);
				}
			}
		});
	}
	
	public BorderPane bp;
	private Label lbTitulo,lbnuPlayeras,lbErrorNp;
	public TableView<Corte> table;
	public TableColumn<Corte,String> columnPartida;
	public TableColumn<Corte, Date> columnFecha;
	public TableColumn<Corte, Integer> columnPlayeras;
	public TableColumn<Corte, Float> columnRelacion;
	public TableColumn<Corte, Talla> columnTalla;
	public ObservableList<Talla> tallaList;
	public ObservableList<Corte> corteList;
	public ContextMenu context;
	public MenuItem eliminarItem,generatedReport;
	public VBox vbox0,vbox1,vbox2;
	public ComboBox<Producto> cbProducto;
	public ComboBox<Talla> cbTalla;
	public TextField txtNumPlayeras;
	public Button btnAdd; 
	public Hyperlink linkNewCorte,linkTabla,linkExcel;
}
