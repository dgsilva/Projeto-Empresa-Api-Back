package com.api.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.empresa.entities.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	//método para consultar os dados de 1 empresa baseado na razao social
	@Query("from Empresa e where e.razaoSocial = :param")
	Empresa findByRazaoSocial(@Param("param") String razaoSocial);
	
	//método para consultar os dados de 1 empresa baseado no cnpj
	@Query("from Empresa e where e.cnpj = :param")
	Empresa findByCnpj(@Param("param") String cnpj);
	
	

  
}
