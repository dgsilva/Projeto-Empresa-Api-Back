package com.api.empresa.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.api.empresa.cryptography.MD5Cryptography;
import com.api.empresa.dto.request.UsuarioDTO;
import com.api.empresa.dto.response.UsuarioResponseDTO;
import com.api.empresa.entities.Usuario;
import com.api.empresa.repositories.UsuarioRepository;
import com.api.empresa.security.JwtSecurity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public ResponseEntity<UsuarioResponseDTO>logar(UsuarioDTO usuarioDTO){
		try {
			UsuarioResponseDTO response  = new UsuarioResponseDTO();
			Usuario usuario = usuarioRepository.findByEmailAndSenha(
					usuarioDTO.getEmail(),MD5Cryptography.encrypt(usuarioDTO.getSenha()));
					
			if(usuario != null) {
				response.setStatusCode(200);
				response.setMensagem("Usuário autenticado com sucesso.");
				response.setAccessTokens((getAccessToken(usuario.getEmail())));
				response.setNomeUsuario(usuario.getNome());
				response.setEmailUsuario(usuario.getEmail());
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(response);
				
			}else {
				response.setStatusCode(401);
				response.setMensagem("Acesso negado.");
				
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(response);
			}
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	
	private String getAccessToken(String emailUsuario) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		// COTI_JWT -> nome da aplicação que gerou o token!
		String token = Jwts.builder().setId("EMPRESA_JWT").setSubject(emailUsuario)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				.signWith(SignatureAlgorithm.HS512, JwtSecurity.SECRET.getBytes()).compact();

		return token;
	}
}
