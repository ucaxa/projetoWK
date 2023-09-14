package testeWK.apiWK.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import testeWK.apiWK.domain.Atividade;


public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

	

	@Query("""
            select a 
            from Atividade a
            where a.projeto.id = projeto
            """)
	List<Atividade> FindByProjeto(Long projeto);

	//Page<DadosListagemAtividade> FindByAtividadesPorProjeto(Pageable paginacao);
	
	

}
