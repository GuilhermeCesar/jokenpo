package ai.auctus.jokenpo.service;

import ai.auctus.jokenpo.dto.CadastroJogadorDTO;
import ai.auctus.jokenpo.dto.JogadorDTO;
import ai.auctus.jokenpo.entity.Jogador;
import ai.auctus.jokenpo.exception.JogadorException;
import ai.auctus.jokenpo.repository.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorDTO cadastrarJogador(CadastroJogadorDTO cadastroJogadorDTO) {
        final var jogador = Jogador.builder()
                .nome(cadastroJogadorDTO.getNome())
                .build();

        this.jogadorRepository.save(jogador);

        return getJogadorDTO(jogador);
    }

    private JogadorDTO getJogadorDTO(Jogador jogador) {
        return JogadorDTO
                .builder()
                .idJogador(jogador.getId())
                .jogador(jogador.getNome())
                .ativo(jogador.getAtivo())
                .build();
    }

    public JogadorDTO buscaJogador(Long idJogador) {
        var jogador = jogadorRepository.findById(idJogador)
                .orElseThrow(() -> new JogadorException(HttpStatus.NOT_FOUND, "Jogador não encontrado"));

        return getJogadorDTO(jogador);
    }
}
