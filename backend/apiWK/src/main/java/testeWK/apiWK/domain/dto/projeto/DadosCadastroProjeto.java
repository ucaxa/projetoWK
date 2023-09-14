package testeWK.apiWK.domain.dto.projeto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import testeWK.apiWK.enums.Status;

public record DadosCadastroProjeto(
		
		@NotBlank
		String nome
		
	//	@NotNull
	//	Status status,
		
	//	@NotNull
	//	LocalDateTime dataCriacao
		
				
		) {
	


}
