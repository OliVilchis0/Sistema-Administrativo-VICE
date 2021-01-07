package com.vice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vice.inventario.model.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{
	public Color findByNombre(String nombre);
}
