package testeWK.apiWK.domain.dto.cliente;

import testeWK.apiWK.domain.Cliente;

public record DadosDetalhamentoCliente(String nome,	String email,String telefone
		) {
     public DadosDetalhamentoCliente(Cliente cliente) {
    	 this(cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
     }
}
