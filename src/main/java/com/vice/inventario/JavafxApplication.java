package com.vice.inventario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import com.vice.inventario.controller.PrincipalController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JavafxApplication extends Application{
	
	@Autowired
	private PrincipalController ctr;
	
	@Override
	public void init() {
		SpringApplication.run(InventarioApplication.class).getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void start(Stage stage) throws Exception {
		ctr.init();
	}
	
	@Override
	public void stop() {
		Platform.exit();
	}

}
