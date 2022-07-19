package com.api.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.empresa.dto.request.FuncionarioDTO;
import com.api.empresa.dto.response.FuncionarioResponseDTO;
import com.api.empresa.entities.Funcionario;
import com.api.empresa.service.FuncionarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/funcionario")
@ApiOperation("Funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping()
	public List<FuncionarioDTO>listarAll(){
		List<FuncionarioDTO> listar = funcionarioService.findAll();
		return listar;
	}
	
	
	@ApiOperation("Salvando os dados de funcionario")
	@PostMapping()
	public ResponseEntity<FuncionarioResponseDTO>save(@RequestBody FuncionarioDTO funcionarioDTO){
		return funcionarioService.create(funcionarioDTO);
	}
}
