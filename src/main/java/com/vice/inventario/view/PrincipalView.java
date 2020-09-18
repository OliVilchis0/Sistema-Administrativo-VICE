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
	
	/*public PrincipalView() {
		init();
	}*/
	
	public void init() {
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
		vbox.setId("bg-darck");
		vbox.setSpacing(5.0d);
		
		imagenVice = new ImageView(new Image("/imagenes/vice.png"));
		separator = new Separator();
		btnInicio = new Button("    Inicio",new ImageView(new Image("/imagenes/home.png")));
		btnConfig = new Button("    Configuracion",new ImageView(new Image("/imagenes/configuraciones.png")));
		btnRollos = new Button("    Rollos",new ImageView(new Image("/imagenes/rollo.png")));
		btnCorte = new Button("    Corte",new ImageView(new Image("/imagenes/tijeras.png")));
		btnAlmacen = new Button("    Almacen",new ImageView(new Image("/imagenes/almacen.png")));
		
		imagenVice.setId("img-vice");
		btnInicio.getStyleClass().add("hbox-btn");
		btnConfig.getStyleClass().add("hbox-btn");
		btnRollos.getStyleClass().add("hbox-btn");
		btnCorte.getStyleClass().add("hbox-btn");
		btnAlmacen.getStyleClass().add("hbox-btn");
		
		btnInicio.setPrefSize(200.0d, 30.0d);
		btnConfig.setPrefSize(200.0d, 30.0d);
		btnRollos.setPrefSize(200.0d, 30.0d);
		btnCorte.setPrefSize(200.0d, 30.0d);
		btnAlmacen.setPrefSize(200.0d, 30.0d);
		
		btnInicio.setAlignment(Pos.BASELINE_LEFT);
		btnConfig.setAlignment(Pos.BASELINE_LEFT);
		btnRollos.setAlignment(Pos.BASELINE_LEFT);
		btnCorte.setAlignment(Pos.BASELINE_LEFT);
		btnAlmacen.setAlignment(Pos.BASELINE_LEFT);
		//btnInicio.setAlignment(Pos.BOTTOM_RIGHT);
		//btInicio.setPrefSize(200.0d, 50.0d);
		vbox.getChildren().addAll(
				imagenVice,
				separator,
				btnInicio,
				btnConfig,
				btnRollos,
				btnCorte,
				btnAlmacen);
		
				
		bp.setTop(menuBar);
		bp.setLeft(vbox);
		
		
		scene = new Scene(bp);
		stage = new Stage();
		scene.getStylesheets().add("/estilos/estilos.css");
		stage.setTitle("AnchorPaneApp");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
	}
	
	public BorderPane bp;
	private VBox vbox;
	private Menu menuNomina,menuInventario,menuConfig,menuAyuda;
	private MenuBar menuBar;
	private Separator separator;
	public Button btnInicio,btnConfig,btnRollos,btnCorte,btnAlmacen;
	private Scene scene;
	private Stage stage;
	private ImageView imagenVice;
}
