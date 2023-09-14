package testeWK.apiWK.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import testeWK.apiWK.domain.dto.projeto.DadosAtualizacaoProjeto;
import testeWK.apiWK.domain.dto.projeto.DadosCadastroProjeto;
import testeWK.apiWK.enums.Status;



@Table(name = "projetos")
@Entity(name = "Projeto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="nome",nullable = false)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private LocalDateTime dataCriacao;
	
	
	
	
	public Projeto(@Valid DadosCadastroProjeto dados) {
		this.nome = dados.nome();
		this.status=Status.CRIADO;
		this.dataCriacao= LocalDateTime.now();
	}

	public void atualizar(@Valid DadosAtualizacaoProjeto dados) {
		
		if (dados.nome() != null) {
			this.nome = dados.nome();
		}
		
		if (dados.status()!=null) {
		this.status = dados.status();
		}
		
	}
	

}
