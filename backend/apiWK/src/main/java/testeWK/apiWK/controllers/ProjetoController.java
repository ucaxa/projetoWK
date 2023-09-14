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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import testeWK.apiWK.domain.Projeto;
import testeWK.apiWK.domain.dto.projeto.DadosAtualizacaoProjeto;
import testeWK.apiWK.domain.dto.projeto.DadosCadastroProjeto;
import testeWK.apiWK.domain.dto.projeto.DadosDetalhamentoProjeto;
import testeWK.apiWK.domain.dto.projeto.DadosListagemProjeto;
import testeWK.apiWK.repository.ProjetoRepository;

@RestController
@RequestMapping("projetos")
@SecurityRequirement(name = "bearer-key")
public class ProjetoController {
	
	@Autowired
	ProjetoRepository projetoRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoProjeto> inserir(@RequestBody @Valid DadosCadastroProjeto dados, 
			UriComponentsBuilder uriBuilder) {
		var projeto = new Projeto(dados);
        projetoRepository.save(projeto);
		var uri = uriBuilder.path("/projetos").buildAndExpand(projeto.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoProjeto(projeto));
    
		
	} 
	

	

	
	@GetMapping
	public ResponseEntity<List<DadosListagemProjeto>> listarTodos(){
		var lista = projetoRepository.findAll();
		var listadto= lista.stream().map(DadosListagemProjeto::new).toList();	;	
		return ResponseEntity.ok(listadto);
	}
	
	

	
	@GetMapping("/buscarAbertos")
	public ResponseEntity<List<DadosListagemProjeto>> buscarAbertos(){
		var listaDeProjetosAbertos = projetoRepository.FindAllEmAndamento().stream().map(DadosListagemProjeto::new).collect(Collectors.toList());
	    return ResponseEntity.ok(listaDeProjetosAbertos);
	}
	
	
		
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoProjeto> alterar(@RequestBody @Valid DadosAtualizacaoProjeto dados) {
		var projeto = projetoRepository.getReferenceById(dados.id());
		projeto.atualizar(dados);
		return ResponseEntity.ok(new DadosDetalhamentoProjeto(projeto));
	}
	

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		var projeto = projetoRepository.getReferenceById(id);
		projetoRepository.delete(projeto);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoProjeto> detalhar(@PathVariable Long id) {
		var projeto = projetoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoProjeto(projeto));
	}
	


}
