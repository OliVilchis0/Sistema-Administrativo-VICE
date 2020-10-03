package com.vice.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vice.inventario.model.Inventario;
import com.vice.inventario.repository.InventarioRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventarioServiceImpl implements InventarioService{
	
	@Autowired
	private InventarioRepository repo;

	@Override
	public List<Inventario> getAll() {
		return repo.findAll();
	}

	@Override
	public Inventario getInventario(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Inventario create(Inventario inv) {
		return repo.save(inv);
	}

	@Override
	public Inventario Update(Inventario inv) {
		Inventario inventariodb = this.getInventario(inv.getId());
		
		if(inventariodb == null)
			return null;
		
		inventariodb.setColor(inv.getColor());
		inventariodb.setCondicion(inv.getCondicion());
		inventariodb.setStock(inv.getStock());
		inventariodb.setTalla(inv.getTalla());
		inventariodb.setTotal(inv.getTotal());
		
		return repo.save(inventariodb);
	}

	@Override
	public void delete(long id) {
		Inventario inventariodb = this.getInventario(id);
		
		if(inventariodb != null)
			repo.delete(inventariodb);
	}
	
	@Override
	public Inventario existe(Inventario inv) {
		Inventario invdb = repo.siExisteInv(inv.getColor(), inv.getTalla(), inv.getCondicion());
		if(invdb == null) 
			return null;
		else 
			return invdb;
	}

}
