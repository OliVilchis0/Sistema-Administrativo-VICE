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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public byte getCondicion() {
		return condicion;
	}
	public void setCondicion(byte condicion) {
		this.condicion = condicion;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
