package testeWK.apiWK.domain.dto.atividade;

import java.time.LocalDateTime;

import testeWK.apiWK.domain.Atividade;

public record DadosListagemAtividade(Long id,String descricao, Long idProjeto,Long idCliente,	
		LocalDateTime dataCadastro,LocalDateTime dataInicio,LocalDateTime dataConclusao) {
	
	public DadosListagemAtividade(Atividade atividade) {
		this(atividade.getId(),atividade.getDescricao(),  atividade.getProjeto().getId(), atividade.getCliente().getId(),
				atividade.getDataCadastro(), atividade.getDataInicio(), atividade.getDataConclusao());
	}

}
