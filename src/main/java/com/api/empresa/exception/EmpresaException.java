package com.api.empresa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmpresaException extends Exception{

	private static final long serialVersionUID = 1L;

	public EmpresaException(Long id) {
	super("Empresa não encontrado");
	}
}
