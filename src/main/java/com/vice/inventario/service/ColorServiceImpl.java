package com.vice.inventario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vice.inventario.model.Color;
import com.vice.inventario.repository.ColorRepository;

@Service()
public class ColorServiceImpl implements ColorService{
	
	@Autowired
	private ColorRepository repo;

	@Override
	public List<Color> getAll() {return repo.findAll();}

	@Override
	public Color getColor(long id) {return repo.findById(id).orElse(null);}
	
	@Override
	public Color getColor(String nombre) {return repo.findByNombre(nombre);}

	@Override
	public Color create(Color color) {
		color.setStatus((byte)1);
		return repo.save(color);
	}

	@Override
	public Color Update(Color color) {
		Color colordb = this.getColor(color.getId());
		if(colordb == null)
			return null;
		
		colordb.setNombre(color.getNombre());
		colordb.setStatus(color.getStatus());
		return repo.save(colordb);
	}

	@Override
	public Color delete(long id) {
		Color colordb = this.getColor(id);
		
		if(colordb == null)
			return null;
		
		colordb.setStatus((byte)0);
		return repo.save(colordb);
	}
	
	@Override
	public List<Color> deleteAll(List<Color> lista) {
		
		if(lista != null) {
			List<Color> colores = new ArrayList<>();
			for(Color c : lista) {
				colores.add(delete(c.getId()));
			}
			return colores;
		}else
			return null;
	}
	
	@Override
	public long count() {
		return repo.count();
	}
	
	@Override
	public boolean existeColor(String nombre) {
		Color c = repo.findByNombre(nombre);
		if(c == null)
			return false;
		else
			return true;
	}
	
}
