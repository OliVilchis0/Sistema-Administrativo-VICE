package com.vice.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vice.inventario.model.Producto;
import com.vice.inventario.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	@Autowired
	private ProductoRepository repo;

	@Override
	public List<Producto> getAll() {
		return repo.findAll();
	}

	@Override
	public Producto getPriducto(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Producto create(Producto producto) {
		producto.setStatus((byte) 1);
		return repo.save(producto);
	}

	@Override
	public Producto Update(Producto priducto) {
		Producto productodb = this.getPriducto(priducto.getId());
		
		if(productodb == null)
			return null;
		
		productodb.setCantidad(priducto.getCantidad());
		productodb.setColor(priducto.getColor());
		productodb.setDiametro(priducto.getDiametro());
		productodb.setDocena(priducto.getDocena());
		productodb.setPartida(priducto.getPartida());
		productodb.setPeso(priducto.getPeso());
		productodb.setStatus(priducto.getStatus());
		productodb.setTalla(priducto.getTalla());
		productodb.setTipoProducto(priducto.getTipoProducto());
		return repo.save(productodb);
	}

	@Override
	public Producto delete(long id) {
		Producto productodb = this.getPriducto(id);
		
		if(productodb == null)
			return null;
		
		productodb.setStatus((byte) 0);
		return repo.save(productodb);
	}

}
