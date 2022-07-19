package com.api.empresa.dto.mapper;

import org.mapstruct.Mapper;

import com.api.empresa.dto.request.EmpresaDTO;
import com.api.empresa.entities.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

	Empresa toModel(EmpresaDTO dto);
	
	EmpresaDTO toDTO(Empresa dto);
}
