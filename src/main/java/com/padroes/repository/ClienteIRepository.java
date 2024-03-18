package com.padroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.padroes.entities.Cliente;

@Repository
public interface ClienteIRepository extends JpaRepository<Cliente, Long> {

}
