package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.entity.Partida;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface JogadaRepository extends PagingAndSortingRepository<Jogada, Long> {


    Optional<Jogada> findJogadaByJogadorAndJogo(Jogador jogador, Partida jogo);

}
