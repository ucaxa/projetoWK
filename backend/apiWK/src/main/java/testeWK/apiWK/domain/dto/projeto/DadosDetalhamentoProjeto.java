package testeWK.apiWK.domain.dto.projeto;

import testeWK.apiWK.domain.Projeto;
import testeWK.apiWK.enums.Status;

public record DadosDetalhamentoProjeto(String nome,	Status status) {
	
	public DadosDetalhamentoProjeto(Projeto projeto) {
		this(projeto.getNome(), projeto.getStatus());
	}

}
