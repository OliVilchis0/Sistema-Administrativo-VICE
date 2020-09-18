package com.vice.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vice.inventario.model.Talla;
import com.vice.inventario.repository.TallaRepository;

@Service
public class TallaServiceImpl implements TallaService{

	@Autowired
	private TallaRepository repo;
	
	@Override
	public List<Talla> getAll() {
		return repo.findAll();
	}

	@Override
	public Talla getTalla(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Talla create(Talla talla) {
		talla.setStatus((byte) 1);
		return repo.save(talla);
	}

	@Override
	public Talla Update(Talla talla) {
		Talla talladb = this.getTalla(talla.getId());
		
		if(talladb == null)
			return null;
		
		talladb.setNombre(talla.getNombre());
		talladb.setStatus(talla.getStatus());
		
		return repo.save(talladb);
	}

	@Override
	public Talla delete(long id) {
		Talla talladb = this.getTalla(id);
		
		if(talladb == null)
			return null;
		
		talladb.setStatus((byte) 0);
		return repo.save(talladb);
	}

}
