package com.vice.inventario.service;

import java.util.List;

import com.vice.inventario.model.Inventario;

public interface InventarioService {
	
	public List<Inventario> getAll();
	public Inventario getInventario(long id);
	public Inventario create(Inventario inv);
	public Inventario Update(Inventario inv);
	public Inventario delete(long id);
}
