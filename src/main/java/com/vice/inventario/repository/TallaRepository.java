package com.vice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vice.inventario.model.Talla;

public interface TallaRepository extends JpaRepository<Talla, Long>{
	public Talla findByNombre(String nombre);
}
