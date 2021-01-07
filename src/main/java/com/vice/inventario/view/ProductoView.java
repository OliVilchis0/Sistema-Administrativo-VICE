package com.vice.inventario.view;

import org.springframework.stereotype.Component;

import com.vice.inventario.Validacion;
import com.vice.inventario.model.Color;
import com.vice.inventario.model.Producto;
import com.vice.inventario.model.Talla;
import com.vice.inventario.model.TipoProducto;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;

@Component
public class ProductoView {
	
	public BorderPane getPanel() {
		return bp;
	}
	public void init() {
		bp = new BorderPane();
		bp.setId("border-color");
		titulo = new Label("Producto");
		titulo.setId("title-top");
		titulo.setAlignment(Pos.CENTER);
		titulo.prefWidthProperty().bind(bp.widthProperty());
		
		//INICIALIZAR LAS LISTAS PARA LOS COMBOBOX
		tipoProList = FXCollections.observableArrayList();
		colorList = FXCollections.observableArrayList();
		diametroList = FXCollections.observableArrayList();
		tallaList = FXCollections.observableArrayList();
		productoList = FXCollections.observableArrayList();
		
		//LINK TO CHANGE PANELS
		linkNuevo = new Hyperlink("Nuevo",new ImageView(new Image("/imagenes/nuevo.png")));
		linkTabla = new Hyperlink("Tabla",new ImageView(new Image("/imagenes/tabla.png")));

		//CREATE TABLE AND FORM
		table();
		form();
		
		Label labelLeft = new Label();
		Label labelRight = new Label();
		
		labelLeft.setMinWidth(10);
		labelRight.setMinWidth(10);
		
		bp.setTop(
				new VBox(
						titulo,
						new HBox(
								linkNuevo,
								linkTabla
								)
						)
				);
		bp.setCenter(table);
		bp.setLeft(labelLeft);
		bp.setRight(labelRight);
		
		changePanel();
	}
	
	//EVENT TO CHANGE PANEL BETWEEN FORM AND TABLA
	public void changePanel() {
		linkNuevo.setOnAction((ActionEvent evt)->{
			bp.setCenter(vbox);
		});
		
		linkTabla.setOnAction((ActionEvent evt)->{
			bp.setCenter(table);
		});
	}
	
	//CREATE FORM TO CREATE NEW PRODUCT OBJECT
	public void form() {
		//PANEL DE FORMULARIO DERECHO
		vbox = new VBox(10);
		hbox = new HBox();
		hbox1 = new HBox();
		hbox2 = new HBox();
		
		lbNuevoRollo = new Label("Nuevo Rollo");
		lbNuevoRollo.setId("titulo-form");
		lbTipoPro = new Label("Tipo de producto");
		cbProducto = new ComboBox<>();
		lbTalla = new Label("Talla");
		cbTalla = new ComboBox<>();
		lbDiametro = new Label("Diametro");
		cbDiametro= new ComboBox<>();
		lbColor = new Label("Color");
		cbColor = new ComboBox<>();
		lbPeso = new Label("Peso");
		lbErrorPeso = new Label();
		txtPeso = new TextField();
		hbox.getChildren().addAll(lbPeso,lbErrorPeso);
		lbErrordocena = new Label();
		lbDocena = new Label("Docena");
		hbox1.getChildren().addAll(lbDocena,lbErrordocena);
		txtDocena = new TextField();
		lbCantidad = new Label("Cantidad");
		lbErrorCantidad = new Label();
		hbox2.getChildren().addAll(lbCantidad,lbErrorCantidad);
		txtCantidad = new TextField();
		btnAdd = new Button("Agregar producto");
		this.validarTxt();
		
		
		vbox.getChildren().addAll(
				lbNuevoRollo,
				lbTipoPro,
				cbProducto,
				lbTalla,
				cbTalla,
				lbDiametro,
				cbDiametro,
				lbColor,
				cbColor,
				hbox,
				txtPeso,
				hbox1,
				txtDocena,
				hbox2,
				txtCantidad,
				btnAdd);
		
		lbNuevoRollo.setAlignment(Pos.CENTER);
		lbTipoPro.setAlignment(Pos.BASELINE_LEFT);
		lbCantidad.setAlignment(Pos.BASELINE_LEFT);
		lbColor.setAlignment(Pos.BASELINE_LEFT);
		lbDiametro.setAlignment(Pos.BASELINE_LEFT);
		lbDocena.setAlignment(Pos.BASELINE_LEFT);
		lbPeso.setAlignment(Pos.BASELINE_LEFT);
		lbTalla.setAlignment(Pos.BASELINE_LEFT);
		
		lbErrorCantidad.setAlignment(Pos.BASELINE_RIGHT);
		lbErrordocena.setAlignment(Pos.BASELINE_RIGHT);
		lbErrorPeso.setAlignment(Pos.BASELINE_RIGHT);
		
		lbNuevoRollo.prefWidthProperty().bind(vbox.widthProperty());
		cbColor.prefWidthProperty().bind(vbox.widthProperty());
		cbDiametro.prefWidthProperty().bind(vbox.widthProperty());
		cbProducto.prefWidthProperty().bind(vbox.widthProperty());
		cbTalla.prefWidthProperty().bind(vbox.widthProperty());
		txtPeso.prefWidthProperty().bind(vbox.widthProperty());
		txtDocena.prefWidthProperty().bind(vbox.widthProperty());
		txtCantidad.prefWidthProperty().bind(vbox.widthProperty());
		btnAdd.prefWidthProperty().bind(vbox.widthProperty());
		
		hbox.prefWidthProperty().bind(vbox.widthProperty());
		hbox1.prefWidthProperty().bind(vbox.widthProperty());
		hbox2.prefWidthProperty().bind(vbox.widthProperty());
		
		vbox.setMaxWidth(400);
		vbox.setMinWidth(100);
		vbox.setMaxHeight(510);
		vbox.setMinHeight(100);
		vbox.setPadding(new Insets(10));
		vbox.setId("grid-color");
	}
	
	//CREATE PRODUCT TABLE AND INITIALIZE HIS VALUES
	public void table() {
		//PROPIEDADES DE LA TABLA PRODUCTOS
		table = new TableView<>();
		table.setEditable(true);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		columnPartida = new TableColumn<>("PARTIDA");
		columnPartida.setCellValueFactory(new PropertyValueFactory<>("partida"));
		columnPartida.setCellFactory(TextFieldTableCell.forTableColumn());
		
		columnTipoPro = new TableColumn<>("TIPO DE ROLLO");
		columnTipoPro.setCellValueFactory(
				new Callback<CellDataFeatures<Producto,TipoProducto>, ObservableValue<TipoProducto>>() {
			
			@Override
			public ObservableValue<TipoProducto> call(CellDataFeatures<Producto, TipoProducto> param) {
				Producto p = param.getValue();
				return new SimpleObjectProperty<TipoProducto>(p.getTipoProducto());
			}
		});
		columnTipoPro.setCellFactory(ComboBoxTableCell.forTableColumn(tipoProList));
		
		columnTalla = new TableColumn<>("TALLA");
		columnTalla.setCellValueFactory(
				new Callback<CellDataFeatures<Producto,Talla>, ObservableValue<Talla>>() {

					@Override
					public ObservableValue<Talla> call(CellDataFeatures<Producto, Talla> param) {
						Producto p = param.getValue();
						return new SimpleObjectProperty<Talla>(p.getTalla());
					}
		});
		columnTalla.setCellFactory(ComboBoxTableCell.forTableColumn(tallaList));
		
		columnDiametro = new TableColumn<>("DIAMETRO");
		columnDiametro.setCellValueFactory(
				new Callback<CellDataFeatures<Producto,Talla>, ObservableValue<Talla>>() {

					@Override
					public ObservableValue<Talla> call(CellDataFeatures<Producto, Talla> param) {
						Producto p = param.getValue();
						return new SimpleObjectProperty<>(p.getDiametro());
					}
		});
		columnDiametro.setCellFactory(ComboBoxTableCell.forTableColumn(diametroList));
		
		columnColor = new TableColumn<>("Color");
		columnColor.setCellValueFactory(
				new Callback<CellDataFeatures<Producto,Color>, ObservableValue<Color>>() {

					@Override
					public ObservableValue<Color> call(CellDataFeatures<Producto, Color> param) {
						Producto p = param.getValue();
						return new SimpleObjectProperty<>(p.getColor());
					}
				});
		columnColor.setCellFactory(ComboBoxTableCell.forTableColumn(colorList));
		
		columnPeso = new TableColumn<>("PESO");
		columnPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
		columnPeso.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Float>() {

			@Override
			public String toString(Float object) {
				return object == null ? "" : object.toString();
			}

			@Override
			public Float fromString(String string) {
				if(string == null || string.isEmpty())
					return null;
				else {
					try {
						float valor = Float.parseFloat(string);
						return valor < 0 ? null : valor;
					} catch (NumberFormatException e) {
						return null;
					}
				}
			}
		}));
		
		columnDocena = new TableColumn<>("DOCENA");
		columnDocena.setCellValueFactory(new PropertyValueFactory<>("docena"));
		columnDocena.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
			
			@Override
			public String toString(Double obj) {
				return obj == null ? "" : obj.toString();
			}
			
			@Override
			public Double fromString(String string) {
				if(string == null || string.isEmpty())
					return null;
				else {
					try {
						double valor = Double.parseDouble(string);
						return valor < 0 ? null : valor;
					} catch (NumberFormatException e) {
						return null;
					}
				}
			}
		}));
		
		
		columnEstado = new TableColumn<>("ESTADO");
		columnEstado.setCellValueFactory(
				new Callback<CellDataFeatures<Producto,String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Producto, String> param) {
						switch (param.getValue().getStatus()) {
							case 0:
								return new SimpleObjectProperty<>("CONCLUIDO");
							case 1:
								return new SimpleObjectProperty<>("ALMACEN");
							case 2:
								return new SimpleObjectProperty<>("CORTE");
							case 3:
								return new SimpleObjectProperty<>("CONFECCION");
							default:
								return new SimpleObjectProperty<>("");	
						}
					}
				});
		
		table.getColumns().add(columnPartida);
		table.getColumns().add(columnTipoPro);
		table.getColumns().add(columnTalla);
		table.getColumns().add(columnDiametro);
		table.getColumns().add(columnColor);
		table.getColumns().add(columnPeso);
		table.getColumns().add(columnDocena);
		table.getColumns().add(columnEstado);
		
		table.setItems(productoList);
		
		//CREAR CONTEXT MENU PARA ELIMINAR UNO O MAS REGISTROS
		menuContext = new ContextMenu();
		itemBorrar = new MenuItem("Borrar Rollo");
		menuContext.getItems().add(itemBorrar);
		table.setContextMenu(menuContext);
		
	}
	
	private void validarTxt() {
		txtPeso.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				if(!Validacion.esFloat(txtPeso.getText())) {
					lbErrorPeso.setText("*Solo numeros!");
					lbErrorPeso.setStyle("-fx-text-fill:red");
					txtPeso.setText(null);
				}else {
					lbErrorPeso.setText(null);
				}
			}
		});
		
		txtDocena.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				if(!Validacion.esDouble(txtDocena.getText())) {
					lbErrordocena.setText("*Solo numeros");
					lbErrordocena.setStyle("-fx-text-fill:red");;
					txtDocena.setText(null);
				}else
					lbErrordocena.setText(null);
			}
		});
		
		txtCantidad.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent evt) {
				if(!Validacion.esEntero(txtCantidad.getText())) {
					lbErrorCantidad.setText("*Solo numeros");
					lbErrorCantidad.setStyle("-fx-text-fill:red");
					txtCantidad.setText(null);
				}else
					lbErrorCantidad.setText(null);
			}
		});
	}
	
	private BorderPane bp;
	private Label titulo,lbTipoPro,lbTalla,lbDiametro,lbColor,lbPeso,lbDocena,lbCantidad,
		lbErrorPeso,lbErrordocena,lbErrorCantidad,lbNuevoRollo;
	public TableView<Producto> table;
	public TableColumn<Producto,String> columnPartida;
	public TableColumn<Producto,TipoProducto> columnTipoPro;
	public TableColumn<Producto,Talla> columnTalla;
	public TableColumn<Producto,Talla> columnDiametro;
	public TableColumn<Producto,Color> columnColor;
	public TableColumn<Producto,Float> columnPeso;
	public TableColumn<Producto,Double> columnDocena;
	public TableColumn<Producto,String> columnEstado;
	public ObservableList<TipoProducto> tipoProList;
	public ObservableList<Color> colorList;
	public ObservableList<Talla> tallaList;
	public ObservableList<Talla> diametroList;
	public ObservableList<Producto> productoList;
	private ContextMenu menuContext;
	public MenuItem itemBorrar;
	private VBox vbox;
	private HBox hbox,hbox1,hbox2;
	public TextField txtPeso,txtDocena,txtCantidad;
	public ComboBox<TipoProducto> cbProducto;
	public ComboBox<Talla> cbTalla;
	public ComboBox<Talla> cbDiametro;
	public ComboBox<Color> cbColor;
	public Button btnAdd;
	public Hyperlink linkNuevo,linkTabla;
}
