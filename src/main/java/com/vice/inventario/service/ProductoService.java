package com.vice.inventario.service;

import java.util.List;

import com.vice.inventario.model.Producto;

public interface ProductoService {
	
	public List<Producto> getAll();
	public Producto getPriducto(long id);
	public Producto create(Producto producto);
	public Producto Update(Producto priducto);
	public Producto delete(long id);
}
