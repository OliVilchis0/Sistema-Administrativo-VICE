package com.vice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vice.inventario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	public Producto findByPartida(String partida);
}
