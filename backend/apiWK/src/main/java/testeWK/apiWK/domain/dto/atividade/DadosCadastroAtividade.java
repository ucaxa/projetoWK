package testeWK.apiWK.domain.dto.atividade;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroAtividade(
		
		@NotNull
		String descricao,
		
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
