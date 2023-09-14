package testeWK.apiWK.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import testeWK.apiWK.domain.Atividade;
import testeWK.apiWK.domain.dto.atividade.DadosAtualizacaoAtividade;
import testeWK.apiWK.domain.dto.atividade.DadosCadastroAtividade;
import testeWK.apiWK.domain.dto.atividade.DadosDetalhamentoAtividade;
import testeWK.apiWK.domain.dto.atividade.DadosListagemAtividade;
import testeWK.apiWK.domain.validacoes.atividade.ValidacoeRegrasDeNegocioAtividade;
import testeWK.apiWK.exceptions.ValidacaoException;
import testeWK.apiWK.repository.AtividadeRepository;
import testeWK.apiWK.repository.ClienteRepository;
import testeWK.apiWK.repository.ProjetoRepository;

@Service
public class GerenciaDeAtividades {
	
	
	@Autowired	
	ClienteRepository clienteRepository;
	
	@Autowired
	ProjetoRepository projetoRepository;
	
	@Autowired
	AtividadeRepository atividadeRepository;
	
	@Autowired
	private List<ValidacoeRegrasDeNegocioAtividade> validadoresAtividade;
	
	public void  verificaProjetoECliente(DadosCadastroAtividade dados) {

		if (!clienteRepository.existsById(dados.idCliente())) {
			throw new ValidacaoException("Id do cliente informado não existe");
		}
		
		if ( !projetoRepository.existsById(dados.idProjeto())) {
			throw new ValidacaoException("Id do Projeto informado não existe");
		}
	}
	
	
	public DadosDetalhamentoAtividade cadastrarAtividade(DadosCadastroAtividade dados) {
		
		verificaProjetoECliente(dados);
	    validadoresAtividade.forEach(v-> v.validar(dados));
		var cliente = clienteRepository.getReferenceById(dados.idCliente());
		var projeto = projetoRepository.getReferenceById(dados.idProjeto());
				var atividade = new Atividade(null,dados.descricao(),projeto,cliente,dados.dataCadastro(),dados.dataInicio(),
				dados.dataConclusao());
			
		atividadeRepository.save(atividade);
		return new DadosDetalhamentoAtividade(atividade);
	}
	
	public DadosDetalhamentoAtividade alterarAtividade(DadosAtualizacaoAtividade dados) {
		var atividade = atividadeRepository.getReferenceById(dados.id());
		atividade.atualizar(dados);
		return new DadosDetalhamentoAtividade(atividade);
	}
	
	public void excluirAtividade(Long id) {
		var atividade = atividadeRepository.getReferenceById(id);
		atividadeRepository.delete(atividade);
	}
	
	public List<DadosListagemAtividade> listarAtividades() {
		var atividades= atividadeRepository.findAll();
		return 	atividades.stream().map(DadosListagemAtividade::new).collect(Collectors.toList());
		
	}
	
	

}
