package com.vice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vice.inventario.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long>{

}
