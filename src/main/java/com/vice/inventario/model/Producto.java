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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Talla getTalla() {
		return talla;
	}

	public void setTalla(Talla talla) {
		this.talla = talla;
	}

	public Talla getDiametro() {
		return diametro;
	}

	public void setDiametro(Talla diametro) {
		this.diametro = diametro;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public double getDocena() {
		return docena;
	}

	public void setDocena(double docena) {
		this.docena = docena;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}
