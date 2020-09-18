package com.vice.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vice.inventario.model.TipoProducto;
import com.vice.inventario.repository.TipoProductoRepository;

@Service
public class TipoProductoServiceImpl implements TipoProductoService{

	@Autowired
	private TipoProductoRepository repo;
	
	@Override
	public List<TipoProducto> getAll() {
		return repo.findAll();
	}

	@Override
	public TipoProducto getTipoProducto(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public TipoProducto create(TipoProducto tp) {
		tp.setStatus((byte) 0);
		return repo.save(tp);
	}

	@Override
	public TipoProducto Update(TipoProducto tp) {
		TipoProducto tpdb = this.getTipoProducto(tp.getId());
		
		if(tpdb == null)
			return null;
		
		tpdb.setNombre(tp.getNombre());
		tpdb.setStatus(tp.getStatus());
		
		return repo.save(tpdb);
	}

	@Override
	public TipoProducto delete(long id) {
		TipoProducto tpdb = this.getTipoProducto(id);
		
		if(tpdb == null)
			return null;
		
		tpdb.setStatus((byte) 0);
		return repo.save(tpdb);
	}

}
