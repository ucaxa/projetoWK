package testeWK.apiWK.controllers;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import testeWK.apiWK.domain.Cliente;
import testeWK.apiWK.domain.dto.cliente.DadosAtualizacaoCliente;
import testeWK.apiWK.domain.dto.cliente.DadosCadastroCliente;
import testeWK.apiWK.domain.dto.cliente.DadosDetalhamentoCliente;
import testeWK.apiWK.domain.dto.cliente.DadosListagemCliente;
import testeWK.apiWK.repository.ClienteRepository;


@RestController
@RequestMapping("clientes")
@SecurityRequirement(name = "bearer-key")
public class ClienteController {
	
	@Autowired	
	ClienteRepository clienteRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCliente> inserir(@RequestBody @Valid DadosCadastroCliente dados, 
			UriComponentsBuilder uriBuilder) {
		var cliente = new Cliente(dados);
		clienteRepository.save(cliente);
		
		var uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
	} 
	

	
	@GetMapping
	public ResponseEntity<List<DadosListagemCliente>> listarTodos(){
		var listaCliente = clienteRepository.findAll();
		var listaDtoCliente = listaCliente.stream().map(DadosListagemCliente::new).toList();
		return ResponseEntity.ok(listaDtoCliente);
	}
	
	
	

		
	@GetMapping(value="/")
	public ResponseEntity<List<DadosListagemCliente>> buscarPorNome(
			@RequestParam(name="nome") String nome) {
		var listaClientes = clienteRepository.findByNomeContainingIgnoreCase(nome)	;
		var clientesDto = listaClientes.stream().map(DadosListagemCliente::new).collect(Collectors.toList());
		return ResponseEntity.ok(clientesDto);
	}
	


	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoCliente> alterar(@RequestBody @Valid DadosAtualizacaoCliente dados) {
		var cliente = clienteRepository.getReferenceById(dados.id());
		cliente.atualizar(dados);
		return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
	}
	

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		var cliente = clienteRepository.getReferenceById(id);
		clienteRepository.delete(cliente);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoCliente> detalhar(@PathVariable Long id) {
		var cliente = clienteRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
	}
	


}
