package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Partida;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PartidaRepository extends PagingAndSortingRepository<Partida, Long>, JpaSpecificationExecutor<Partida> {

}
