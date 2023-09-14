package testeWK.apiWK.domain.dto.atividade;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoAtividade(
	
		@NotNull
		String descricao,
		
		@NotNull
		Long id,
		
		@NotNull
		Long idProjeto,
		
		@NotNull
		Long idCliente,
		
		@NotNull
		LocalDateTime dataCadastro,
		
		LocalDateTime dataInicio,
		
		LocalDateTime dataConclusao
		
		) {

}
