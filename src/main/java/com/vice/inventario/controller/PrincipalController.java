package com.vice.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vice.inventario.view.PrincipalView;

@Controller
public class PrincipalController {
	
	@Autowired
	private PrincipalView view;
	
	public void init() {
		view.init();
	}
	
	/*public void newPanel(ActionEvent evt) {
		ConfiguracionesView viewConfig = new ConfiguracionesView();
		ConfiguracionController ctrConfing = new ConfiguracionController(viewConfig); 
		view.bp.setCenter(viewConfig.getPanel());
	}*/
}

