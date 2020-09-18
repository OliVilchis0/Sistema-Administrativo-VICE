package com.vice.inventario.service;

import java.util.List;

import com.vice.inventario.model.Venta;

public interface VentaService {
	
	public List<Venta> getAll();
	public Venta getVenta(long id);
	public Venta create(Venta venta);
	public Venta Update(Venta venta);
	public Venta delete(long id);
}
