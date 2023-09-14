package testeWK.apiWK.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;


@RestControllerAdvice
public class TratadorErros {
	
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		
		var errors = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(errors.stream().map(DadosErroValidacao::new));
	}
	
	
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity tratarErroDeRegraDeNegocio(ValidacaoException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError error) {
			this(error.getField(),error.getDefaultMessage());
		}
	}


}
