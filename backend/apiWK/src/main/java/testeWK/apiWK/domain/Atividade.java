package testeWK.apiWK.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testeWK.apiWK.domain.dto.atividade.DadosAtualizacaoAtividade;
import testeWK.apiWK.domain.dto.atividade.DadosCadastroAtividade;


@Table(name = "atividades")
@Entity(name = "Atividade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Atividade {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricao;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	private LocalDateTime dataCadastro;
	
	private LocalDateTime dataInicio;
	
	private LocalDateTime dataConclusao;
	
	
	
	public Atividade(@Valid DadosCadastroAtividade dados) {
		this.dataCadastro = dados.dataCadastro();
		this.dataInicio = dados.dataInicio();
		this.dataConclusao = dados.dataConclusao();
		
	}

	public void atualizar(@Valid DadosAtualizacaoAtividade dados) {
		this.dataInicio = dados.dataInicio();
		this.dataConclusao = dados.dataConclusao();
	}





}
