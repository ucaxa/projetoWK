package testeWK.apiWK.controllers;

import java.util.List;

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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import testeWK.apiWK.domain.dto.atividade.DadosAtualizacaoAtividade;
import testeWK.apiWK.domain.dto.atividade.DadosCadastroAtividade;
import testeWK.apiWK.domain.dto.atividade.DadosDetalhamentoAtividade;
import testeWK.apiWK.domain.dto.atividade.DadosListagemAtividade;
import testeWK.apiWK.repository.AtividadeRepository;
import testeWK.apiWK.service.GerenciaDeAtividades;

@RestController
@RequestMapping("atividades")
@SecurityRequirement(name = "bearer-key")
public class AtividadeController {
	
	
	@Autowired
	GerenciaDeAtividades gerenciadorDeAtividades;
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoAtividade> inserir(@RequestBody @Valid DadosCadastroAtividade dados) {
		var dto =  gerenciadorDeAtividades.cadastrarAtividade(dados);
		return ResponseEntity.ok(dto);
	
	} 
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoAtividade> alterar(@RequestBody @Valid DadosAtualizacaoAtividade dados) {
		var dto = gerenciadorDeAtividades.alterarAtividade(dados);
		return ResponseEntity.ok(dto);
	}
	
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		gerenciadorDeAtividades.excluirAtividade(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	

	@GetMapping
	public ResponseEntity<List<DadosListagemAtividade>> listarTodos() {
		var atividades = gerenciadorDeAtividades.listarAtividades();
		return ResponseEntity.ok(atividades);
	}
	
	
	@GetMapping(value="/")
	public ResponseEntity<List<DadosListagemAtividade>> buscarAtividadesPorProjeto(@RequestParam(name="projeto") Long projeto){
		var atividades = atividadeRepository.FindByProjeto(projeto);
		var atividadesdto = atividades.stream().map(DadosListagemAtividade::new).toList();
		return ResponseEntity.ok(atividadesdto);
	}
	
		




}
