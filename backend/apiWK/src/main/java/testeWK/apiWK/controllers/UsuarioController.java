package testeWK.apiWK.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;
import testeWK.apiWK.domain.Usuario;
import testeWK.apiWK.domain.dto.usuario.DadosCadastroUsuario;
import testeWK.apiWK.domain.dto.usuario.DadosDetalhamentoUsuario;
import testeWK.apiWK.domain.dto.usuario.DadosListagemUsuario;
import testeWK.apiWK.repository.UsuarioRepository;


@RestController
@RequestMapping("usuarios")
@CrossOrigin(origins = "http://localhost:8081")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository  usuarioRepository;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, 
			UriComponentsBuilder uriBuilder) {
		var usuario = new Usuario(dados);
		usuarioRepository.save(usuario);
		
		var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
	} 
	
	
	@GetMapping
	public ResponseEntity<List<DadosListagemUsuario>> listarTodos(){
		var lista = usuarioRepository.findAll();
		var listaDtoUsuario= lista.stream().map(DadosListagemUsuario::new).toList();
		return ResponseEntity.ok(listaDtoUsuario);
	}
	

}
