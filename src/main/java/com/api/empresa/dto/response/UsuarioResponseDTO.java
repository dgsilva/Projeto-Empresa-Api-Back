package com.api.empresa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioResponseDTO {

	private Integer statusCode;
	private String mensagem;
	private String accessTokens;
	private String nomeUsuario;
	private String emailUsuario;
}
