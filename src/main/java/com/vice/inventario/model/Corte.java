package com.vice.inventario.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "corte")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Corte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne()
	@JoinColumn(name = "producto_id")
	private Producto producto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	@Column(name = "numero_playeras")
	private int numPlayeras;
	private float relacion;
	@ManyToOne()
	@JoinColumn(name = "talla_id")
	private Talla talla;
	private byte status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getNumPlayeras() {
		return numPlayeras;
	}
	public void setNumPlayeras(int numPlayeras) {
		this.numPlayeras = numPlayeras;
	}
	public float getRelacion() {
		return relacion;
	}
	public void setRelacion(float relacion) {
		this.relacion = relacion;
	}
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	public byte getStatus() {
		return status;
	}
	public void setStatus(byte status) {
		this.status = status;
	}

}
