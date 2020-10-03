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
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

@Component
public class CorteView {
	
	public BorderPane getPane() {
		return bp;
	}

	public void init() {
		bp = new BorderPane();
		lbTitulo = new Label("Corte");
		
		//LISTA DE TALLAS
		tallaList = FXCollections.observableArrayList();
		corteList = FXCollections.observableArrayList();
		
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
		context.getItems().add(eliminarItem);
		table.setContextMenu(context);
		
		//CONTENIDO DEL FORMULARIO
		vbox1 = new VBox();
		
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
		
		table.getColumns().add(columnPartida);
		table.getColumns().add(columnFecha);
		table.getColumns().add(columnPlayeras);
		table.getColumns().add(columnRelacion);
		table.getColumns().add(columnTalla);
		
		table.setItems(corteList);
		
		//Contenido de informacion
		vbox2 = new VBox();
		
		lbTipoRollo = new Label();
		lbTalla = new Label();
		lbDiametro = new Label();
		lbPeso = new Label();
		lbColor = new Label();
		
		vbox2.getChildren().addAll(
				new HBox(new Label("Tipo de rollo: "),lbTipoRollo),
				new HBox(new Label("Talla: "),lbTalla),
				new HBox(new Label("Diamtetro: "),lbDiametro),
				new HBox(new Label("Peso: "),lbPeso),
				new HBox(new Label("Color: "),lbColor));
		
		//AGREGAR LOS VBOX AL PRICIPAL
		vbox0 = new VBox();
		vbox0.getChildren().addAll(vbox1,vbox2);
		
		bp.setTop(lbTitulo);
		bp.setCenter(table);
		bp.setRight(vbox0);
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
	
	private BorderPane bp;
	private Label lbTitulo,lbnuPlayeras,lbErrorNp,lbTipoRollo,lbTalla,lbDiametro,lbPeso,lbColor;
	public TableView<Corte> table;
	public TableColumn<Corte,String> columnPartida;
	public TableColumn<Corte, Date> columnFecha;
	public TableColumn<Corte, Integer> columnPlayeras;
	public TableColumn<Corte, Float> columnRelacion;
	public TableColumn<Corte, Talla> columnTalla;
	public ObservableList<Talla> tallaList;
	public ObservableList<Corte> corteList;
	public ContextMenu context;
	public MenuItem eliminarItem;
	private VBox vbox0,vbox1,vbox2;
	public ComboBox<Producto> cbProducto;
	public ComboBox<Talla> cbTalla;
	public TextField txtNumPlayeras;
	public Button btnAdd; 
}
