package testeWK.apiWK.domain.dto.projeto;

import java.time.LocalDateTime;

import testeWK.apiWK.domain.Projeto;
import testeWK.apiWK.enums.Status;

public record DadosListagemProjeto(Long id,String nome,Status status, LocalDateTime dataCriacao) {
    public DadosListagemProjeto(Projeto projeto) {
    	this(projeto.getId(), projeto.getNome(),projeto.getStatus(), projeto.getDataCriacao());
    }
}
