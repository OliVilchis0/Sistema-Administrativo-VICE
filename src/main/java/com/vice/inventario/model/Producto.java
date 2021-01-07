package com.vice.inventario.model;

import javax.persistence.Column;
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
@Table(name = "producto")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(length = 50)
	private String partida;
	@ManyToOne()
	@JoinColumn(name = "tipo_producto_id",nullable = false)
	private TipoProducto tipoProducto;
	@ManyToOne()
	@JoinColumn(name = "talla_id",nullable = false)
	private Talla talla;
	@ManyToOne()
	@JoinColumn(name = "diametro_id",nullable = false)
	private Talla diametro;
	@ManyToOne()
	@JoinColumn(name = "color_id",nullable = false)
	private Color color;
	private float peso;
	private double docena;
	private int cantidad;
	private byte status;
	
	@Override
	public String toString() {
		return this.partida;
	}
}
