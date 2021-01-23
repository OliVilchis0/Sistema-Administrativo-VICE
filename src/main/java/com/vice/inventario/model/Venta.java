package com.vice.inventario.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venta")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "producto_id")
	private Inventario Inventario;
	private int cantidad;
	private double precio;
	private double total;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Inventario getInventario() {
		return Inventario;
	}
	public void setInventario(Inventario inventario) {
		Inventario = inventario;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
}
