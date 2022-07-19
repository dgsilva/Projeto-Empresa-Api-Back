package com.api.empresa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.empresa.dto.mapper.EmpresaMapper;
import com.api.empresa.dto.request.EmpresaDTO;
import com.api.empresa.dto.response.EmpresaResponseDTO;
import com.api.empresa.entities.Empresa;
import com.api.empresa.exception.EmpresaException;
import com.api.empresa.repositories.EmpresaRepository;

import java.util.Optional;

@Service()
@Transactional
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	private EmpresaMapper empresaMapper;
	
	@Autowired
	public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper) {
		super();
		this.empresaRepository = empresaRepository;
		this.empresaMapper = empresaMapper;
	}

	public ResponseEntity<EmpresaResponseDTO> create(EmpresaDTO empresaDTO){
		EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO();
		Empresa empresa  = empresaMapper.toModel(empresaDTO);
		if(empresaRepository.findByRazaoSocial(empresaDTO.getRazaoSocial())!=null) {
			empresaResponseDTO.setStatus(400);
			empresaResponseDTO.setMessage("A razão social '"+empresaDTO.getRazaoSocial()+ 
					"' já está cadastrada, tente outro.");
			return  ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(empresaResponseDTO);
		}
		if(empresaRepository.findByCnpj(empresaDTO.getCnpj())!=null) {
			empresaResponseDTO.setStatus(400);
			empresaResponseDTO.setMessage("O cnpj '"+empresaDTO.getCnpj()+ 
					"' já está cadastrada, tente outro.");
			
			return  ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(empresaResponseDTO);
		}
		empresaRepository.save(empresa);
		empresaResponseDTO.setStatus(201);
		empresaResponseDTO.setMessage("Empresa cadastrada com suceso!");
		  return  ResponseEntity
					.status(HttpStatus.CREATED)
					.body(empresaResponseDTO);
		}
	
	public ResponseEntity<EmpresaResponseDTO> update(Long idEmpresa, EmpresaDTO empresaDTO) {
		EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO();
		Empresa empresa = empresaMapper.toModel(empresaDTO);
		
			Optional<Empresa> registro = empresaRepository.findById(empresaDTO.getIdEmpresa());
			if(registro.isPresent()) {
				 empresa = registro.get();
				 
				 empresa.setNomeFantasia(empresaDTO.getNomeFantasia());
				 empresa.setRazaoSocial(empresaDTO.getRazaoSocial());
				 
				empresaRepository.save(empresa);
				
				empresaResponseDTO.setStatus(200);
				empresaResponseDTO.setMessage("Alterando com sucesso!");
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(empresaResponseDTO);
				
			}else {
				empresaResponseDTO.setStatus(400);
				empresaResponseDTO.setMessage("Empresa não encontrada. O ID informado é inválido.");
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(empresaResponseDTO);
			}
			
	}
	
	public List<EmpresaDTO> findAll(){
		List<Empresa> empresaList = empresaRepository.findAll();
		return empresaList.stream()
				.map(empresaMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public EmpresaDTO findById(Long id)throws EmpresaException{
		Empresa empresa = empresaRepository.findById(id)
				.orElseThrow(()-> new EmpresaException(id));
		return empresaMapper.toDTO(empresa);
	}

	public ResponseEntity<EmpresaResponseDTO> delete (Long id) {
		EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO();
		try {
		 Optional<Empresa> registro = empresaRepository.findById(id);
			
		if(registro.isPresent()) {
			empresaRepository.deleteById(id);
			empresaResponseDTO.setStatus(200);
			empresaResponseDTO.setMessage("Empresa Excluido com sucesso!");
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(empresaResponseDTO);
		}else {
			empresaResponseDTO.setStatus(204);
			empresaResponseDTO.setMessage("Empresa não encontrada. O ID informado é invalido!");
			return ResponseEntity
					.status(HttpStatus.NO_CONTENT)
					.body(empresaResponseDTO);
		}
		}catch(Exception e ) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(empresaResponseDTO);
		}
	}
	
}
	
