package com.api.empresa.dto.mapper;

import org.mapstruct.Mapper;

import com.api.empresa.dto.request.UsuarioDTO;
import com.api.empresa.entities.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	Usuario	toModel(UsuarioDTO dto);
	
	UsuarioDTO toDTO(Usuario dto);
	
	
}
