package com.vice.inventario.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vice.inventario.model.Color;

@Repository
public interface ColorService {
	
	public List<Color> getAll();
	public Color getColor(long id);
	public Color create(Color color);
	public Color Update(Color color);
	public Color delete(long id);
	public List<Color> deleteAll(List<Color> lista);
	public long count();
	public boolean existeColor(String nombre);
	public Color getColor(String nombre);
}
