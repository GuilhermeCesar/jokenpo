package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Jogador;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface JogadorRepository extends PagingAndSortingRepository<Jogador, Long> , JpaSpecificationExecutor<Jogador> {


}
