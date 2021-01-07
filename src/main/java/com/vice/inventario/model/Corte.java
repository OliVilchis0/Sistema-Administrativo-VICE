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
}
