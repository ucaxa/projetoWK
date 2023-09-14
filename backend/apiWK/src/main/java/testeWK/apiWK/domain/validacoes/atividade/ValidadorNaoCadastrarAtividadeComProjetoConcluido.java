package testeWK.apiWK.domain.validacoes.atividade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import testeWK.apiWK.domain.dto.atividade.DadosCadastroAtividade;
import testeWK.apiWK.enums.Status;
import testeWK.apiWK.exceptions.ValidacaoException;
import testeWK.apiWK.repository.ProjetoRepository;

@Component
public class ValidadorNaoCadastrarAtividadeComProjetoConcluido implements ValidacoeRegrasDeNegocioAtividade{

	@Autowired
	ProjetoRepository projetoRepository;
	
	@Override
	public void validar(DadosCadastroAtividade dados) {
		var projeto = projetoRepository.getReferenceById(dados.idProjeto());
		
		if (projeto.getStatus().equals(Status.CONCLUIDO)) {
			throw new ValidacaoException(" Não é permitido cadastrar atividade pra um projeto concluído");

		}
		
	}

}
