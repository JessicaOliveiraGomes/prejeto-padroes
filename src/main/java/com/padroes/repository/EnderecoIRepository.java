package com.padroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padroes.entities.Endereco;

@Repository
public interface EnderecoIRepository extends JpaRepository<Endereco, String> {

}
