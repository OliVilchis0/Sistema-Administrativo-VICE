package com.vice.inventario.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Inventario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne()
	@JoinColumn(name = "talla_id", nullable = false)
	private Talla talla;
	@ManyToOne()
	@JoinColumn(name = "color_id", nullable = false)
	private Color color;
	private byte condicion;
	private int total;
	private double precio;
	private int stock;
}
