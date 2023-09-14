package testeWK.apiWK.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import testeWK.apiWK.domain.dto.cliente.DadosAtualizacaoCliente;
import testeWK.apiWK.domain.dto.cliente.DadosCadastroCliente;


@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Cliente {
		
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
		
	public Cliente(@Valid DadosCadastroCliente dados) {
		this.nome = dados.nome();
		this.email = dados.email();
		this.telefone = dados.telefone();
	}

	public void atualizar(@Valid DadosAtualizacaoCliente dados) {
		// TODO Auto-generated method stub
		
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		
		
		if (dados.email()!=null) {
		this.email = dados.email();
		}
		
		
		if (dados.telefone()!=null) {
		this.telefone = dados.telefone();
		}
		
		
		
	}
	
	

}
