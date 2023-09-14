package testeWK.apiWK.domain.dto.usuario;

import testeWK.apiWK.domain.Usuario;

public record DadosListagemUsuario(String login,String senha) {
	
	public DadosListagemUsuario(Usuario usuario) {
		this(usuario.getLogin(), usuario.getSenha());
	}

}
