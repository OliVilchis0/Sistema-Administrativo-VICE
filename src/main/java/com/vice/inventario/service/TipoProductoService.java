package com.vice.inventario.service;

import java.util.List;

import com.vice.inventario.model.TipoProducto;

public interface TipoProductoService {
	
	public List<TipoProducto> getAll();
	public TipoProducto getTipoProducto(long id);
	public TipoProducto getTipoProducto(String nombre);
	public TipoProducto create(TipoProducto tp);
	public TipoProducto Update(TipoProducto tp);
	public TipoProducto delete(long id);
	public List<TipoProducto> deleteAll(List<TipoProducto> lista);
	public long count();
	public boolean existeTipoPro(String nombre);
}
