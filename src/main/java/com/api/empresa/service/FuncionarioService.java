package com.api.empresa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.empresa.dto.mapper.FuncionarioMapper;
import com.api.empresa.dto.request.FuncionarioDTO;
import com.api.empresa.dto.response.FuncionarioResponseDTO;
import com.api.empresa.entities.Funcionario;
import com.api.empresa.repositories.EmpresaRepository;
import com.api.empresa.repositories.FuncionarioRepository;

@Service
@Transactional
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	private FuncionarioMapper funcionarioMapper;
	private EmpresaRepository empresaRepository;

	@Autowired
	public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper,
			EmpresaRepository empresaRepository) {
		super();
		this.funcionarioRepository = funcionarioRepository;
		this.funcionarioMapper = funcionarioMapper;
		this.empresaRepository = empresaRepository;
	}

	public ResponseEntity<FuncionarioResponseDTO> create(FuncionarioDTO funcionarioDTO) {
		FuncionarioResponseDTO message = new FuncionarioResponseDTO();

		Funcionario funcionario = funcionarioMapper.toModel(funcionarioDTO);

		if (funcionarioRepository.findByCpf(funcionarioDTO.getCpf()) != null) {
			message.setStatusCode(400);
			message.setMensagem("O CPF '" + funcionarioDTO.getCpf() + "' já está cadastrado, tente outro.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}

		if (funcionarioRepository.findByMatricula(funcionarioDTO.getMatricula()) != null) {
			message.setStatusCode(400);
			message.setMensagem("A Matricula '" + funcionarioDTO.getMatricula() + "' já está cadastrada, tente outro.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}

		if (empresaRepository.findById(funcionarioDTO.getIdEmpresa()).isEmpty()) {
			message.setStatusCode(400);
			message.setMensagem("A empresa informada não foi encontrada");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
		}

		funcionario.setEmpresa(empresaRepository.findById(funcionarioDTO.getIdEmpresa()).get());
		funcionarioRepository.save(funcionario);

		message.setStatusCode(201);
		message.setMensagem("Funcionario cadastrado com sucesso!");
		return ResponseEntity.status(HttpStatus.CREATED).body(message);

	}

	public List<FuncionarioDTO> findAll() {
		List<Funcionario> funcionarioList = funcionarioRepository.findAll();
		return funcionarioList.stream()
				.map(funcionarioMapper::toDTO)
				.collect(Collectors.toList());
	}

}
