package com.vice.inventario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vice.inventario.model.Venta;
import com.vice.inventario.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService{

	@Autowired
	private VentaRepository repo;
	@Override
	public List<Venta> getAll() {
		return repo.findAll();
	}

	@Override
	public Venta getVenta(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Venta create(Venta venta) {
		return repo.save(venta);
	}

	@Override
	public Venta Update(Venta venta) {
		Venta ventadb = this.getVenta(venta.getId());
		
		if(ventadb == null)
			return null;
		
		ventadb.setCantidad(venta.getCantidad());
		ventadb.setFecha(venta.getFecha());
		ventadb.setInventario(venta.getInventario());
		ventadb.setPrecio(venta.getPrecio());
		ventadb.setTotal(venta.getTotal());
		return repo.save(ventadb);
	}

	@Override
	public Venta delete(long id) {
		Venta ventadb = this.getVenta(id);
		
		if(ventadb == null)
			return null;
		return repo.save(ventadb);
	}

}
