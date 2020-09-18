package com.vice.inventario;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javafx.application.Application;

@ComponentScan(basePackages  = {
		"com.vice.inventario.controller","com.vice.inventario.repository","com.vice.inventario.service",
		"com.vice.inventario.view"})
@EntityScan(basePackages = {"com.vice.inventario.model"})
@EnableJpaRepositories(basePackages = {"com.vice.inventario.repository","com.vice.inventario.service"})
@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		//Application.launch(args);
		Application.launch(JavafxApplication.class);
	}
}