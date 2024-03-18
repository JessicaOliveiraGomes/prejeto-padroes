package com.padroes.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.padroes.entities.Endereco;

@FeignClient (name = "viacep", url = "http://viacep.com.br/ws")
public interface ViaCepIService {
	
	@GetMapping ("/{cep}/json/")
	Endereco consultarCep(@PathVariable("cep") String cep);

}
