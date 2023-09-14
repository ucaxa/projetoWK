package testeWK.apiWK.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import testeWK.apiWK.domain.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	

/*	@Query("select c Cliente where c.nome like '%?1%'")
	List<Cliente> buscaClientesPorNome(String nome);*/
	
	List<Cliente> findByNomeContainingIgnoreCase(String nome);


}
