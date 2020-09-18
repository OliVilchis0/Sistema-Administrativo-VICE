package com.vice.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vice.inventario.model.Corte;
import com.vice.inventario.repository.CorteRepository;

@Service
public class CorteServiceImpl implements CorteService{
	
	@Autowired
	private CorteRepository repo;

	@Override
	public List<Corte> getAll() {
		return repo.findAll();
	}

	@Override
	public Corte getCorte(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Corte create(Corte corte) {
		corte.setStatus((byte) 1);		
		return repo.save(corte);
	}

	@Override
	public Corte Update(Corte corte) {
		Corte cortedb = this.getCorte(corte.getId());
		
		if(cortedb == null)
			return null;
		
		cortedb.setFecha(corte.getFecha());
		cortedb.setNumPlayeras(corte.getNumPlayeras());
		cortedb.setProducto(corte.getProducto());
		cortedb.setRelacion(corte.getRelacion());
		cortedb.setStatus(corte.getStatus());
		cortedb.setTalla(corte.getTalla());
		
		return repo.save(cortedb);
	}

	@Override
	public Corte delete(long id) {
		Corte cortedb = this.getCorte(id);
		
		if(cortedb == null)
			return null;
		
		cortedb.setStatus((byte)0);
		return repo.save(cortedb);
	}

}
