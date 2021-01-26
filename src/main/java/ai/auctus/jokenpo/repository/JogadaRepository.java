package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.dto.ResultadoDTO;
import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.entity.Turno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JogadaRepository extends PagingAndSortingRepository<Jogada, Long> {

    Optional<Jogada> findJogadaByJogadorAndTurno(Jogador jogador, Turno turno);

    List<Jogada> findJogadaByTurno(Turno turno);

    @Query(""" 
            SELECT new ai.auctus.jokenpo.dto.ResultadoDTO(ganhador.id,jogada.jogada, partida.id ) FROM Jogada AS jogada
            INNER JOIN jogada.turno as turno 
            INNER JOIN turno.partida AS partida
            INNER JOIN partida.ganhador as ganhador
            WHERE partida.ativo = TRUE AND partida.id = :partida
            ORDER BY turno.id DESC
            """)
    ResultadoDTO buscaResultadoVitorioso(@Param("partida") Long partida);
}
