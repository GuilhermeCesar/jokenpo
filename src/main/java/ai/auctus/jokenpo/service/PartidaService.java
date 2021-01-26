package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.*;
import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.entity.Partida;
import ai.auctus.jokenpo.entity.Turno;
import ai.auctus.jokenpo.exception.PartidaException;
import ai.auctus.jokenpo.helper.MessageHelper;
import ai.auctus.jokenpo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static ai.auctus.jokenpo.helper.MessageHelper.ErrorCode.*;
import static java.lang.Boolean.FALSE;
import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final JogadorRepository jogadorRepository;
    private final JogadaRepository jogadaRepository;
    private final TurnoRepository turnoRepository;
    private final MessageHelper messageHelper;

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

    public JogadaDTO jogar(Long idPartida, FazerJogadaDTO fazerJogadaDTO) {
        final Turno turno = getTurnoAtivo(idPartida);
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

    private Turno getTurnoAtivo(Long idPartida) {
        final var turno = this.turnoRepository.buscarUltimoTurnoSePartidaAtiva(idPartida);
        if (turno.isEmpty()) {
            throw new PartidaException(INTERNAL_SERVER_ERROR, this.messageHelper.get(ERROR_PARTIDA_ENCERRADA));
        }
        return turno.get();
    }

    private Jogador getJogador(FazerJogadaDTO fazerJogadaDTO) {
        final var jogador = this.jogadorRepository.findById(fazerJogadaDTO.getIdJogador());

        if (jogador.isEmpty() || FALSE.equals(jogador.get().getAtivo())) {
            throw new PartidaException(NOT_FOUND, this.messageHelper.get(ERROR_JOGADOR_NAO_ENCONTRADO));
        }
        return jogador.get();
    }

    public Page<PartidaDTO> getPartida(Long idPartida, Boolean ativo, Pageable pageable) {
        final var partidaSpec = PartidaSpec
                .builder()
                .ativo(ofNullable(ativo))
                .id(ofNullable(idPartida))
                .build();
        final var partidaPage = this.partidaRepository.findAll(partidaSpec, pageable);

        return partidaPage.map(partida -> PartidaDTO
                .builder()
                .nome(partida.getNome())
                .idJogo(partida.getId())
                .ativa(partida.getAtivo())
                .build());
    }


    public ResultadoDTO resultado(Long idPartida) {
        final var turnoEmPartidaEmAndamento = this.turnoRepository.buscarUltimoTurnoSePartidaAtiva(idPartida);
        if (turnoEmPartidaEmAndamento.isEmpty()) {
            return getBuscaResultadoVitorioso(idPartida);
        }

        final var jogadasDoTurno = this.jogadaRepository.findJogadaByTurno(turnoEmPartidaEmAndamento.get());
        var jogadas = new ArrayList<>(jogadasDoTurno);

        for (int i = 0; i < jogadasDoTurno.size(); i++) {
            var jogada = jogadas.remove(i);

            var venceOuEmpataComJogadaAtual = jogadas
                    .parallelStream()
                    .filter(jogadaFilter -> jogadaFilter.venceOuEmpata(jogada.getJogada()))
                    .findAny();

            if (venceOuEmpataComJogadaAtual.isEmpty()) {
                return vencedor(turnoEmPartidaEmAndamento.get(), jogada);
            }
            jogadas = new ArrayList<>(jogadasDoTurno);
        }
        return empate(turnoEmPartidaEmAndamento.get());
    }

    private ResultadoDTO getBuscaResultadoVitorioso(Long idPartida) {
        var resultado = this.jogadaRepository.buscaResultadoVitorioso(idPartida);
        return resultado.withMensagem(this.messageHelper.get(SUCESSO_TEMOS_VENCEDOR));
    }

    private ResultadoDTO vencedor(Turno turno, Jogada jogada) {
        var partida = turno.getPartida();
        partida.withGanhador(jogada.getJogador());
        return getVencedor(partida, jogada, jogada.getJogador());
    }

    private ResultadoDTO getVencedor(Partida partida, Jogada jogada, Jogador jogador) {
        return ResultadoDTO
                .builder()
                .mensagem(this.messageHelper.get(SUCESSO_TEMOS_VENCEDOR))
                .idJogadorGanhador(jogador.getId())
                .idPartida(partida.getId())
                .jokenpoEnum(jogada.getJogada())
                .build();
    }

    private ResultadoDTO empate(Turno turno) {
        comecaNovoTurno(turno);
        return ResultadoDTO
                .builder()
                .mensagem(this.messageHelper.get(EMPATE))
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
