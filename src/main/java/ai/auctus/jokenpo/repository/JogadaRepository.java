package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.entity.Turno;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface JogadaRepository extends PagingAndSortingRepository<Jogada, Long> {

    Optional<Jogada> findJogadaByJogadorAndTurno(Jogador jogador, Turno turno);

    List<Jogada> findJogadaByTurno(Turno turno);
}
