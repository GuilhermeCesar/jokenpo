package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.CadastroJogoDTO;
import ai.auctus.jokenpo.dto.FazerJogadaDTO;
import ai.auctus.jokenpo.dto.JogadaDTO;
import ai.auctus.jokenpo.dto.JogoDTO;
import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.entity.Jogo;
import ai.auctus.jokenpo.exception.JogoException;
import ai.auctus.jokenpo.repository.JogadaRepository;
import ai.auctus.jokenpo.repository.JogadorRepository;
import ai.auctus.jokenpo.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.Boolean.FALSE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final JogadorRepository jogadorRepository;
    private final JogadaRepository jogadaRepository;

    public JogoDTO cadastrarJogo(CadastroJogoDTO jogoDTO) {
        var jogo = Jogo
                .builder()
                .nome(jogoDTO.getNome())
                .build();

        this.jogoRepository.save(jogo);

        return JogoDTO
                .builder()
                .idJogo(jogo.getId())
                .nome(jogo.getNome())
                .build();
    }

    private Jogo getJogo(Long idJogo) {
        final var jogo = this.jogoRepository.findById(idJogo);

        if (jogo.isEmpty()) {
            throw new JogoException(NOT_FOUND, "Jogo informado não existe");
        } else if (FALSE.equals(jogo.get().getAtivo())) {
            throw new JogoException(NOT_FOUND, "Jogo finalizado");
        }
        return jogo.get();
    }

    public JogadaDTO jogar(Long idJogo, FazerJogadaDTO fazerJogadaDTO) {
        final var jogo = this.getJogo(idJogo);
        final var jogador = this.getJogador(fazerJogadaDTO);

        var jogada = Jogada
                .builder()
                .jogada(fazerJogadaDTO.getJokenpoEnum())
                .jogo(jogo)
                .jogador(jogador)
                .build();

        this.jogadaRepository.save(jogada);

        return JogadaDTO
                .builder()
                .idJogada(jogada.getId())
                .idJogador(jogador.getId())
                .idJogo(jogo.getId())
                .jokenpoEnum(fazerJogadaDTO.getJokenpoEnum())
                .build();
    }

    private Jogador getJogador(FazerJogadaDTO fazerJogadaDTO) {
        final var jogador = this.jogadorRepository.findById(fazerJogadaDTO.getIdJogador());

        if (jogador.isEmpty()) {
            throw new JogoException(NOT_FOUND, "Jogador informado não existe");
        } else if (FALSE.equals(jogador.get().getAtivo())) {
            throw new JogoException(NOT_FOUND, "Jogador está inativo");
        }
        return jogador.get();
    }


}
