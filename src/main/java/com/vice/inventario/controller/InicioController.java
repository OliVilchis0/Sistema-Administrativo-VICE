package com.vice.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.vice.inventario.service.ColorServiceImpl;
import com.vice.inventario.service.CorteServiceImpl;
import com.vice.inventario.service.InventarioServiceImpl;
import com.vice.inventario.service.ProductoServiceImpl;
import com.vice.inventario.service.TallaServiceImpl;
import com.vice.inventario.service.TipoProductoServiceImpl;
import com.vice.inventario.view.InicioView;

import javafx.scene.chart.XYChart;

@Controller
public class InicioController {
	
	@Autowired private InicioView view;
	@Autowired private ColorServiceImpl serviceColor;
	@Autowired private TallaServiceImpl serviceTalla;
	@Autowired private TipoProductoServiceImpl serviceTipoPro;
	@Autowired private ProductoServiceImpl servicePro;
	@Autowired private CorteServiceImpl serviceCorte;
	@Autowired private InventarioServiceImpl serviceInv;
	
	public void setPanel(InicioView view) {
		this.view = view;
		//COUNT ALL ROWS OF TALLA,COLOR,DIAMETRO, TIPO PRODUCTO AND SET IN LABEL
		view.lbTallas.setText(serviceTalla.getAll().stream().filter(x->x.getStatus()==1).count()+" Tallas");
		view.lbColores.setText(serviceColor.getAll().stream().filter(x->x.getStatus()==1).count()+" Colores");
		view.lbDiametros.setText(serviceTalla.getAll().stream().filter(x->x.getStatus()==2).count()+" Diametros");
		view.lbTiposPro.setText(serviceTipoPro.getAll().stream().filter(x->x.getStatus()==1).count()+" Tipos de producto");
		//COUNT ALL ROWS OF PRODUCT AND SELECTING BY STATUS
		view.lbRolosAlmacen.setText(servicePro.getAll().stream().filter(x->x.getStatus()==1).count()+" Rollos en almacen");
		view.lbRollosCorte.setText(servicePro.getAll().stream().filter(x->x.getStatus()==2).count()+" Rollos en corte");
		view.lbRollosConf.setText(servicePro.getAll().stream().filter(x->x.getStatus()==3).count()+" Rollos en confeccion");
		//COUNT THE T-SHIRT AND ORDER BY GOOD OR BAD
		view.lbPBuenasDif.setText(serviceInv.getAll().stream().filter(x->x.getCondicion()==1).count()+" Diferentes playeras buenas");
		view.lbPMalasDif.setText(serviceInv.getAll().stream().filter(x->x.getCondicion()==2).count()+" Diferentes playeras malas");
		//COUNT ALL ROW IN CORTE
		view.lbCorte2.setText(serviceCorte.getAll().stream().filter(x->x.getStatus()==1).count()+" Cortes Realizados");
		//SHOW VALUE OF GOOD T-SHIRT
		view.lbPBuenas.setText("Playeras buenas (Pzs) "+serviceInv.getPrecio((byte)1));
		
		int total = serviceInv.countTotal((byte)1);
		double precio = serviceInv.getPrecio((byte)1);
		double totalPrecio = total*precio;
		view.lbBuenasTotal.setText(
				"Playeras Buenas: "+total+" = $"+totalPrecio);
		//SHOW VALUE OF BAD T-SHIRT
		total = serviceInv.countTotal((byte)2);
		precio = serviceInv.getPrecio((byte)2);
		double totalPrecioM = total*precio;
		view.lbPMalas.setText("Playeras malas (Pzs) "+serviceInv.getPrecio((byte)2));
		view.lbMalasTotal.setText(
				"Playeras Malas: "+total+" = $"+totalPrecioM);
		//SHOW VALUE OF INVENTORY
		view.lbValorInv2.setText(
				"Valor inventario total: "+(totalPrecio+totalPrecioM));
		
		//DATA OF BAR CHART
		barChart();
	}
	
	private void barChart() {
		//CREATE LIST OF DATA
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		series.setName("Playeras Buenas");

		
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		series2.setName("Playeras Malas");
		//LIST ALL ROWS WITH STATUS EQUALS 1 AND ADD IN THE LIST OF DATA
		serviceInv.getAll().stream().forEach(x->{
			
			if(x.getCondicion()==1) {
				series.getData().add(new XYChart.Data<>(
						x.getTalla().getNombre()+" "+x.getColor().getNombre(),x.getTotal())
						);
			}else {
				series2.getData().add(new XYChart.Data<>(
						x.getTalla().getNombre()+" "+x.getColor().getNombre(),x.getTotal())
						);
			}
		});
		
		view.data.add(series);
		view.data.add(series2);
	}
	
	public void init() {
		view.init();
	}
}
