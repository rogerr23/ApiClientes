package br.com.roger.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roger.dtos.ClienteRequestDto;
import br.com.roger.entities.Cliente;
import br.com.roger.repositories.ClienteRepository;

@RequestMapping("/api/clientes")
@RestController
public class ClienteController {

	@PostMapping
	public String post(@RequestBody ClienteRequestDto dto) {

		var cliente = new Cliente();

		cliente.setId(UUID.randomUUID());
		cliente.setNome(dto.getNome());
		cliente.setEmail(dto.getEmail());
		cliente.setTelefone(dto.getTelefone());

		var clienteRepository = new ClienteRepository();
		clienteRepository.create(cliente);

		return "Cliente cadastrado com sucesso.";

	}

	@PutMapping("{id}")
	public String put(@PathVariable UUID id, @RequestBody ClienteRequestDto dto) {

		var cliente = new Cliente();

		cliente.setId(id);
		cliente.setNome(dto.getNome());
		cliente.setEmail(dto.getEmail());
		cliente.setTelefone(dto.getTelefone());

		var clienteRepository = new ClienteRepository();
		clienteRepository.update(cliente);

		return "Cliente atualizado com sucesso.";
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) {

		var clienteRepository = new ClienteRepository();
		clienteRepository.delete(id);

		return "Cliente deletado com sucesso.";

	}

	@GetMapping
	public List<Cliente> getAll() {

		var clienteRepository = new ClienteRepository();
		return clienteRepository.findAll();

	}

	@GetMapping("{id}")
	public Cliente getById(@PathVariable UUID id) {

		var clienteRepository = new ClienteRepository();
		return clienteRepository.findById(id);

	}
}