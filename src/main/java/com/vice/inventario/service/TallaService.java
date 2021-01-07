package com.vice.inventario.service;

import java.util.List;

import com.vice.inventario.model.Talla;

public interface TallaService {
	
	public List<Talla> getAll();
	public Talla getTalla(long id);
	public Talla getTalla(String nombre);
	public Talla create(Talla talla);
	public Talla Update(Talla talla);
	public Talla delete(long id);
	public List<Talla> deleteAll(List<Talla> listas);
	public long count();
	public boolean existeTalla(String nombre);
}
