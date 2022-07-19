package com.api.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.empresa.dto.request.EmpresaDTO;
import com.api.empresa.dto.response.EmpresaResponseDTO;
import com.api.empresa.dto.response.MessagemDTO;
import com.api.empresa.exception.EmpresaException;
import com.api.empresa.service.EmpresaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/empresas")
@ApiOperation("Empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;
	
	@ApiOperation("Listando os dados da empresa")
	@GetMapping
	public List<EmpresaDTO> findAll(){
		List<EmpresaDTO> empresaList = empresaService.findAll();
		return empresaList;
	}
	
	@ApiOperation("Listando somente id da empresa")
	@GetMapping("/{id}")
	public EmpresaDTO findById(@PathVariable Long id) throws EmpresaException {
	  return empresaService.findById(id);	
	}
	
	@ApiOperation("Salvando os dados de empresa")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping()
	public ResponseEntity<EmpresaResponseDTO> create(@RequestBody EmpresaDTO empresaDTO) throws Exception{
		return empresaService.create(empresaDTO);
	}
	
	@ApiOperation("Alterando os dados de empresa")
	@ResponseStatus(HttpStatus.CREATED)
	@PutMapping("/{idEmpresa}")
	public ResponseEntity<EmpresaResponseDTO> update(@PathVariable Long idEmpresa,@RequestBody EmpresaDTO empresaDTO){
		return empresaService.update(idEmpresa,empresaDTO);
	}
	
	@ApiOperation("Deletando os dados de empresa")
	@DeleteMapping("/{id}")
	public ResponseEntity<EmpresaResponseDTO> delete(@PathVariable Long id){
		return empresaService.delete(id);
	}
	
		
}
