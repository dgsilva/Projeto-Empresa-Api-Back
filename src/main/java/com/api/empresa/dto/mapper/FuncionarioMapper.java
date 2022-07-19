package com.api.empresa.dto.mapper;

import org.mapstruct.Mapper;

import com.api.empresa.dto.request.FuncionarioDTO;
import com.api.empresa.entities.Funcionario;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

	Funcionario	toModel(FuncionarioDTO dto);
	
	FuncionarioDTO toDTO(Funcionario dto);
	
}
