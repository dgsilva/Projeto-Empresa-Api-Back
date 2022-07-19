package com.api.empresa.entities;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idFuncionario;
	@Column(length = 150, nullable = false)
	private String nome;
	@Column(length = 150, nullable = false, unique = true)
	private String matricula;
	@Column(length = 150, nullable = false, unique = true)
	private String cpf;
	@Column(nullable = false)
	private LocalDate dataAdmissao;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idEmpresa", nullable = true)
	private Empresa empresa;
	
}
