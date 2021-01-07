package com.vice.inventario.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vice.inventario.model.Color;
import com.vice.inventario.model.Inventario;
import com.vice.inventario.model.Talla;

public interface InventarioRepository extends JpaRepository<Inventario, Long>{
	@Transactional
	@Query("SELECT a FROM Inventario a WHERE a.color=?1 AND a.talla=?2 AND a.condicion=?3")
	public Inventario siExisteInv(Color color,Talla talla, byte condicio);
	
	@Transactional
	@Query("SELECT precio FROM Inventario WHERE condicion=?1 GROUP BY condicion")
	public double getPrecio(byte condicion);
	
	@Transactional
	@Query("SELECT sum(total) FROM Inventario WHERE condicion=?1")
	public int countTotal(byte condicion);
	
	public List<Inventario> findByCondicion(byte Condicion);
}
