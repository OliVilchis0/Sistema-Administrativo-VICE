package com.vice.inventario.view;

import org.springframework.stereotype.Component;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

@Component
public class PrincipalView {
		
	public void init(Stage stage) {
		
		bp = new BorderPane();
		
		menuNomina = new Menu("Nomina",new ImageView(new Image("/imagenes/nomina.png")));
		menuNomina.getItems().addAll(
				new MenuItem("Inicio"),
				new MenuItem("Empleados"),
				new MenuItem("Reporte"));
		
		menuInventario = new Menu("Inventario",new ImageView(new Image("/imagenes/inventario.png")));
		menuInventario.getItems().addAll(
				new MenuItem("Inicio",new ImageView(new Image("/imagenes/home.png"))),
				new MenuItem("Configuracion",new ImageView(new Image("/imagenes/configuraciones.png"))),
				new MenuItem("Rollos",new ImageView(new Image("/imagenes/rollo.png"))),
				new MenuItem("Corte",new ImageView(new Image("/imagenes/tijeras.png"))),
				new MenuItem("Almacen",new ImageView(new Image("/imagenes/almacen.png"))));
		
		
		menuConfig = new Menu("Configuraciones",new ImageView(new Image("/imagenes/ajustes.png")));
		menuAyuda = new Menu("Ayuda",new ImageView(new Image("/imagenes/pregunta.png")));
		
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(menuNomina,menuInventario,menuConfig,menuAyuda);		
		
		
		vbox = new VBox();
		vbox.setId("bg-darck-simple");
		vbox.setSpacing(5.0d);
		
		imagenVice = new ImageView(new Image("/imagenes/vice.png"));
		separator = new Separator();
		btnInicio = new Button("    Inicio",new ImageView(new Image("/imagenes/home.png")));
		btnConfig = new Button("    Configuracion",new ImageView(new Image("/imagenes/configuraciones.png")));
		btnRollos = new Button("    Rollos",new ImageView(new Image("/imagenes/rollo.png")));
		btnCorte = new Button("    Corte",new ImageView(new Image("/imagenes/tijeras.png")));
		btnAlmacen = new Button("    Almacen",new ImageView(new Image("/imagenes/almacen.png")));
		
		imagenVice.setId("img-vice");
		btnInicio.getStyleClass().add("bg-darck");
		btnConfig.getStyleClass().add("bg-darck");
		btnRollos.getStyleClass().add("bg-darck");
		btnCorte.getStyleClass().add("bg-darck");
		btnAlmacen.getStyleClass().add("bg-darck");
		
		btnInicio.prefWidthProperty().bind(vbox.widthProperty());
		btnConfig.prefWidthProperty().bind(vbox.widthProperty());
		btnRollos.prefWidthProperty().bind(vbox.widthProperty());
		btnCorte.prefWidthProperty().bind(vbox.widthProperty());
		btnAlmacen.prefWidthProperty().bind(vbox.widthProperty());
		
		btnInicio.setAlignment(Pos.BASELINE_LEFT);
		btnConfig.setAlignment(Pos.BASELINE_LEFT);
		btnRollos.setAlignment(Pos.BASELINE_LEFT);
		btnCorte.setAlignment(Pos.BASELINE_LEFT);
		btnAlmacen.setAlignment(Pos.BASELINE_LEFT);
		vbox.getChildren().addAll(
				imagenVice,
				separator,
				btnInicio,
				btnConfig,
				btnRollos,
				btnCorte,
				btnAlmacen);
		
		vbox.prefWidthProperty().bind(bp.widthProperty());
		vbox.setMaxWidth(180);
		vbox.setMinWidth(50);
		
				
		bp.setTop(menuBar);
		bp.setLeft(vbox);
		
		scene = new Scene(bp);
		this.stage = stage;
		scene.getStylesheets().add("/estilos/estilos.css");
		this.stage.setTitle("AnchorPaneApp");
		this.stage.setScene(scene);
		this.stage.setMaximized(true);
		this.stage.show();
	}
	
	public BorderPane bp;
	private VBox vbox;
	public Menu menuNomina,menuInventario,menuConfig,menuAyuda;
	private MenuBar menuBar;
	private Separator separator;
	public Button btnInicio,btnConfig,btnRollos,btnCorte,btnAlmacen;
	private Scene scene;
	private Stage stage;
	private ImageView imagenVice;
}
