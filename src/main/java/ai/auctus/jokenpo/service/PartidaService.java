package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.CadastroJogoDTO;
import ai.auctus.jokenpo.dto.FazerJogadaDTO;
import ai.auctus.jokenpo.dto.JogadaDTO;
import ai.auctus.jokenpo.dto.PartidaoDTO;
import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.entity.Partida;
import ai.auctus.jokenpo.entity.Turno;
import ai.auctus.jokenpo.exception.PartidaException;
import ai.auctus.jokenpo.repository.JogadaRepository;
import ai.auctus.jokenpo.repository.JogadorRepository;
import ai.auctus.jokenpo.repository.PartidaRepository;
import ai.auctus.jokenpo.repository.TurnoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;
import static org.hibernate.internal.util.collections.CollectionHelper.isEmpty;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final JogadorRepository jogadorRepository;
    private final JogadaRepository jogadaRepository;
    private final TurnoRepository turnoRepository;

    public PartidaoDTO cadastrarJogo(CadastroJogoDTO jogoDTO) {
        var partida = Partida
                .builder()
                .nome(jogoDTO.getNome())
                .build();

        this.partidaRepository.save(partida);

        var turno = Turno.builder()
                .partida(partida)
                .build();

        this.turnoRepository.save(turno);

        return PartidaoDTO
                .builder()
                .idJogo(partida.getId())
                .nome(partida.getNome())
                .build();
    }

    private Turno getTurnoAtivoDaPartida(Long idPartida) {
        final var turno = this.turnoRepository.buscarUltimoTurnoSePartidaAtiva(idPartida, PageRequest.of(0, 1));

        if (isEmpty(turno))
            throw new PartidaException(NOT_FOUND, "Jogo informado não existe");

        return turno.get(0);
    }

    public JogadaDTO jogar(Long idpartida, FazerJogadaDTO fazerJogadaDTO) {
        final var turno = this.getTurnoAtivoDaPartida(idpartida);
        final var jogador = this.getJogador(fazerJogadaDTO);

        this.jogadaRepository
                .findJogadaByJogadorAndTurno(jogador, turno)
                .ifPresent(jogada -> {
                    throw new PartidaException(CONFLICT, "Você já jogou nesse turno");
                });

        var jogada = Jogada
                .builder()
                .jogada(fazerJogadaDTO.getJokenpoEnum())
                .turno(turno)
                .jogador(jogador)
                .build();

        this.jogadaRepository.save(jogada);

        return JogadaDTO
                .builder()
                .idPartida(turno.getIdPartida())
                .idJogador(jogador.getId())
                .jokenpoEnum(fazerJogadaDTO.getJokenpoEnum())
                .build();
    }

    private Jogador getJogador(FazerJogadaDTO fazerJogadaDTO) {
        final var jogador = this.jogadorRepository.findById(fazerJogadaDTO.getIdJogador());

        if (jogador.isEmpty()) {
            throw new PartidaException(NOT_FOUND, "Jogador informado não existe");
        } else if (FALSE.equals(jogador.get().getAtivo())) {
            throw new PartidaException(NOT_FOUND, "Jogador está inativo");
        }
        return jogador.get();
    }


}
