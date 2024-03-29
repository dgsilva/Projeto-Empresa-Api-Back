package com.api.empresa.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEmpresa;
    @Column(length = 150, nullable = false)
	private String nomeFantasia;
    @Column(length = 150, nullable = false , unique = true) 
	private String razaoSocial;
    @Column(length = 25, nullable = false , unique = true)
	private String cnpj;
    @OneToMany(mappedBy = "empresa")
	private List<Funcionario>funcionarios;
}