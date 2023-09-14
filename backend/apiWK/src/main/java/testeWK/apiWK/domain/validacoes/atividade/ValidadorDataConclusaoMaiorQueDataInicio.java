package testeWK.apiWK.domain.validacoes.atividade;

import org.springframework.stereotype.Component;

import testeWK.apiWK.domain.dto.atividade.DadosCadastroAtividade;
import testeWK.apiWK.exceptions.ValidacaoException;

@Component
public class ValidadorDataConclusaoMaiorQueDataInicio implements ValidacoeRegrasDeNegocioAtividade {

	@Override
	public void validar(DadosCadastroAtividade dados) {
		var dataInicio = dados.dataInicio();
		var dataConclusao = dados.dataConclusao();	
		 var dataConclusaoMaioQueInicio = dataConclusao.isBefore(dataInicio);
		 
		 if (dataConclusaoMaioQueInicio) {
			    throw new ValidacaoException(" Data de Conclusão deve ser maior que a data de inicio");
		}
	}
}
