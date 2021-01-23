package com.vice.inventario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "talla")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Talla {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(length = 50)
	private String nombre;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private byte status;
	
	@Override
	public String toString() {
		return this.nombre;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}
}
