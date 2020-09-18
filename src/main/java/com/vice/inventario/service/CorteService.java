package com.vice.inventario.service;

import java.util.List;

import com.vice.inventario.model.Corte;

public interface CorteService {
	
	public List<Corte> getAll();
	public Corte getCorte(long id);
	public Corte create(Corte corte);
	public Corte Update(Corte corte);
	public Corte delete(long id);
}
