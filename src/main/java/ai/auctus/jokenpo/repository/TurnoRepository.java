package ai.auctus.jokenpo.repository;

import ai.auctus.jokenpo.entity.Turno;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TurnoRepository extends PagingAndSortingRepository<Turno, Long> {

    @Query(""" 
            SELECT turno FROM Turno AS turno
            INNER JOIN turno.partida AS partida
            WHERE partida.ativo = TRUE AND partida.id = :partida
            ORDER BY turno.id DESC
            """)
    List<Turno> buscarUltimoTurnoSePartidaAtiva(@Param("partida") Long partida, Pageable pageable);
}
