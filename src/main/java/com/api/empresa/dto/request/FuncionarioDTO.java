package com.api.empresa.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class FuncionarioDTO {
	
	private Integer idFuncionario;
	private String nome;
	private String matricula;
	private String cpf;
	private Date dataAdmissao;
	private Long idEmpresa;

}