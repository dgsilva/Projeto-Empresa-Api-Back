package com.api.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.empresa.dto.request.UsuarioDTO;
import com.api.empresa.dto.response.UsuarioResponseDTO;
import com.api.empresa.service.LoginService;

@RestController
@RequestMapping("api/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<UsuarioResponseDTO>logar(@RequestBody UsuarioDTO usuarioDTO){
	   return loginService.logar(usuarioDTO);
	}

}
