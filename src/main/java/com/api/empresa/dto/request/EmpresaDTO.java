package com.api.empresa.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

	private Long idEmpresa;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
//	private List<Funcionario>funcionarios;
}
