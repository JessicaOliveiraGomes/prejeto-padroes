package com.padroes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.padroes.entities.Cliente;
import com.padroes.entities.Endereco;
import com.padroes.repository.ClienteIRepository;
import com.padroes.repository.EnderecoIRepository;

@Service
public class ClienteService implements ClienteIService {

	@Autowired
	private ViaCepIService viaCepIService;
	@Autowired
	private ClienteIRepository clienteIRepository;
	@Autowired
	private EnderecoIRepository enderecoIRepository;

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteIRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteIRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void adicionar(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteIRepository.findById(id);
		if (clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		clienteIRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoIRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepIService.consultarCep(cep);
			enderecoIRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteIRepository.save(cliente);
	}
}
