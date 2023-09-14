package testeWK.apiWK.domain.dto.usuario;

import testeWK.apiWK.domain.Usuario;

public record DadosDetalhamentoUsuario(String login, String senha) {
	
	public DadosDetalhamentoUsuario(Usuario usuario) {
		this(usuario.getLogin(),usuario.getSenha());
	}

}
