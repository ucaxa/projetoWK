package testeWK.apiWK.domain.dto.projeto;

import jakarta.validation.constraints.NotNull;
import testeWK.apiWK.enums.Status;

public record DadosAtualizacaoProjeto(
		
		@NotNull
		Long id,
	
		String nome,
		
		@NotNull
		Status status) {

}
