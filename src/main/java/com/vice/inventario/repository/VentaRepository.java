package com.vice.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vice.inventario.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{

}
