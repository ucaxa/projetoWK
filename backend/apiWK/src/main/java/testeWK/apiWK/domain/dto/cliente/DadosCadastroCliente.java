package testeWK.apiWK.domain.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record DadosCadastroCliente(

	
	@NotBlank
	String nome,
	@NotBlank
	@Email
	String email,
	@NotBlank
	String telefone
	
	
	
	) {
}
