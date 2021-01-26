package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.*;
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

import java.util.ArrayList;

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

    public PartidaDTO cadastrarJogo(CadastroJogoDTO jogoDTO) {
        var partida = Partida
                .builder()
                .nome(jogoDTO.getNome())
                .build();

        this.partidaRepository.save(partida);

        var turno = Turno.builder()
                .partida(partida)
                .build();

        this.turnoRepository.save(turno);

        return PartidaDTO
                .builder()
                .idJogo(partida.getId())
                .nome(partida.getNome())
                .build();
    }

    private Turno getTurnoAtivoDaPartida(Long idPartida) {
        final var turno = this.turnoRepository.buscarUltimoTurnoSePartidaAtiva(idPartida, PageRequest.of(0, 1));

        if (isEmpty(turno))
            throw new PartidaException(NOT_FOUND, "Jogo informado não existe ou foi desativado");

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


    public ResultadoDTO resultado(Long idPartida) {
        final var turno = this.getTurnoAtivoDaPartida(idPartida);

        final var jogadasDoTurno = this.jogadaRepository.findJogadaByTurno(turno);
        var jogadas = new ArrayList<>(jogadasDoTurno);

        for (int i = 0; i < jogadasDoTurno.size(); i++) {
            var jogada = jogadas.remove(i);

            var venceOuEmpataComJogadaAtual = jogadas
                    .parallelStream()
                    .filter(jogadaFilter -> jogadaFilter.venceOuEmpata(jogada.getJogada()))
                    .findAny();

            if (venceOuEmpataComJogadaAtual.isEmpty()) {
                return vencedor(turno, jogada);
            }
            jogadas = new ArrayList<>(jogadasDoTurno);
        }
        return empate(turno);
    }

    private ResultadoDTO vencedor(Turno turno, Jogada jogada) {
        var partida = turno.getPartida();
        partida.withGanhador(jogada.getJogador());
        return ResultadoDTO
                .builder()
                .mensagem("Temos um vencedor")
                .idJogadorVencedor(jogada.getJogador().getId())
                .idPartida(turno.getIdPartida())
                .jokenpoEnum(jogada.getJogada())
                .build();
    }

    private ResultadoDTO empate(Turno turno) {
        comecaNovoTurno(turno);
        return ResultadoDTO
                .builder()
                .mensagem("Houve um empate, novo turno começa")
                .build();
    }

    private void comecaNovoTurno(Turno turno) {
        turno.setFinalizado(true);
        this.turnoRepository.save(turno);
        final var novoTurno = Turno
                .builder()
                .partida(turno.getPartida())
                .build();
        this.turnoRepository.save(novoTurno);
    }


}
