package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.dto.ResultadoDTO;
import ai.auctus.jokenpo.entity.Turno;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends PagingAndSortingRepository<Turno, Long> {

    @Query(""" 
            SELECT turno FROM Turno AS turno
            INNER JOIN turno.partida AS partida
            WHERE partida.ativo = TRUE AND partida.id = :partida and turno.finalizado = false
            ORDER BY turno.id DESC
            """)
    Optional<Turno> buscarUltimoTurnoSePartidaAtiva(@Param("partida") Long partida);



}
