package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.CadastroJogoDTO;
import ai.auctus.jokenpo.dto.FazerJogadaDTO;
import ai.auctus.jokenpo.dto.JogadaDTO;
import ai.auctus.jokenpo.dto.JogoDTO;
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
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final JogadorRepository jogadorRepository;
    private final JogadaRepository jogadaRepository;
    private final TurnoRepository turnoRepository;

    public JogoDTO cadastrarJogo(CadastroJogoDTO jogoDTO) {
        var partida = Partida
                .builder()
                .nome(jogoDTO.getNome())
                .build();

        this.partidaRepository.save(partida);

        var turno = Turno.builder()
                .partida(partida)
                .build();

        this.turnoRepository.save(turno);

        return JogoDTO
                .builder()
                .idJogo(partida.getId())
                .nome(partida.getNome())
                .build();
    }

    private Partida getJogo(Long idJogo) {
        final var jogo = this.partidaRepository.findById(idJogo);

        if (jogo.isEmpty()) {
            throw new PartidaException(NOT_FOUND, "Jogo informado não existe");
        } else if (FALSE.equals(jogo.get().getAtivo())) {
            throw new PartidaException(NOT_FOUND, "Jogo finalizado");
        }
        return jogo.get();
    }

    public JogadaDTO jogar(Long idJogo, FazerJogadaDTO fazerJogadaDTO) {
        final var jogo = this.getJogo(idJogo);
        final var jogador = this.getJogador(fazerJogadaDTO);

//        this.jogadaRepository
//                .findJogadaByJogadorAndJogo(jogador, jogo)
//                .ifPresent(jogada -> {
//                    throw new PartidaException(CONFLICT, "Você já jogou nessa partida");
//                });

//        var jogada = Jogada
//                .builder()
//                .jogada(fazerJogadaDTO.getJokenpoEnum())
//                .jogo(jogo)
//                .jogador(jogador)
//                .build();

//        this.jogadaRepository.save(jogada);

//        return JogadaDTO
//                .builder()
//                .idJogada(jogada.getId())
//                .idJogador(jogador.getId())
//                .idJogo(jogo.getId())
//                .jokenpoEnum(fazerJogadaDTO.getJokenpoEnum())
//                .build();

        return null;
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
