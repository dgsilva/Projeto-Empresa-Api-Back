package com.api.empresa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name="tb_usuario")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(length = 100, nullable = false, unique = true)
	private String email;
	@Column(length =50, nullable = false)
	private String senha;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataCriacao;

}
