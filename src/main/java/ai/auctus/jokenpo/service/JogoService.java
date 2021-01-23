package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.CadastroJogoDTO;
import ai.auctus.jokenpo.dto.FazerJogadaDTO;
import ai.auctus.jokenpo.dto.JogadaDTO;
import ai.auctus.jokenpo.dto.JogoDTO;
import ai.auctus.jokenpo.entity.Jogada;
import ai.auctus.jokenpo.entity.Jogo;
import ai.auctus.jokenpo.exception.JogoException;
import ai.auctus.jokenpo.repository.JogadaRepository;
import ai.auctus.jokenpo.repository.JogadorRepository;
import ai.auctus.jokenpo.repository.JogoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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


    public JogadaDTO jogar(Long idJogo, FazerJogadaDTO fazerJogadaDTO) {
        final var jogo = this.jogoRepository.findById(idJogo)
                .orElseThrow(() -> new JogoException(HttpStatus.NOT_FOUND, "Jogo informado não existe"));

        final var jogador = this.jogadorRepository.findById(fazerJogadaDTO.getIdJogador())
                .orElseThrow(() -> new JogoException(HttpStatus.NOT_FOUND, "Jogador informado não existe"));

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


}
