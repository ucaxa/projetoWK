package testeWK.apiWK.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import testeWK.apiWK.domain.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>{



	@Query("""
            select p 
            from Projeto p
            where p.status = 'EM_ABERTO'
            """)
	List<Projeto> FindAllEmAndamento();

	

	@Query("""
    select p 
    from Projeto p
    where
    p.status =:status
    """)
	List<Projeto> FindByPesquisaPorStatus(String status);

	

}
